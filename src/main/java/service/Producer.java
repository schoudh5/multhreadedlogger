package service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import models.Logger;
import models.Message;
import models.MessageType;

public class Producer implements Runnable, Logger, LoggerService {

  private BlockingQueue blockingQueue;
  private boolean isBusy;

  public Producer(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {

  }

  public boolean push(Message message) {


    this.blockingQueue.offer(message);
    return true;
  }


  public boolean isBusy() {
    return isBusy;
  }

  public void setBusy(boolean busy) {
    isBusy = busy;
  }

  @Override
  public void info(String message) {
    push(Message.of(message, MessageType.INFO));
  }

  @Override
  public void debug(String message) {
    push(Message.of(message, MessageType.DEBUG));
  }

  @Override
  public void error(String message) {
    push(Message.of(message, MessageType.ERROR));
  }
}
