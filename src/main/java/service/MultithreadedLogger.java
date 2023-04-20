package service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import models.Message;
import models.Sink;


public class MultithreadedLogger  {

  private String loggerName;
  private Sink sink;
  private int bufferSize;
  private BlockingQueue<Message> messages;
  ExecutorService executorService;
  int N_PRODUCERS = Runtime.getRuntime().availableProcessors();
  List<Producer> producers = new LinkedList<>();
  Consumer consumer;


  public MultithreadedLogger(String loggerName, Sink sink, int bufferSize) {
    this.loggerName = loggerName;
    this.bufferSize = bufferSize;
    this.sink = sink;

    init();
  }

  private void init() {
    this.messages = new LinkedBlockingQueue<>(bufferSize);
    this.executorService = Executors.newFixedThreadPool(N_PRODUCERS);


    this.consumer = new Consumer();
    Thread t = new Thread(this.consumer);
    t.start();
    for(int i=0;i<N_PRODUCERS;i++){

      Producer producer = new Producer(messages);
      executorService.submit(producer);
      producers.add(producer);
      //Thread newProducer = new Thread(producer);
      //newProducer.start();
    }

  }


  public Optional<Producer> getProducer() {
    if(producers.stream().anyMatch(p-> !p.isBusy())){
      Optional<Producer> producer =  producers.stream().filter(p -> !p.isBusy()).findFirst();
      producer.get().setBusy(true);
      return producer;
    }
    return Optional.empty();

  }

  public void returnProducer(Producer messageProducer) {
    messageProducer.setBusy(false);
  }


  class Consumer implements Runnable  {

    @Override
    public void run() {

      while (true){
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if(!messages.isEmpty()){
          Message message = messages.poll();

          sink.write(message);
        }
      }
    }
  }

}
