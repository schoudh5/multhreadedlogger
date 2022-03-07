import Factory.ConsoleLogger;
import Factory.LoggerFactory;
import models.Logger;

public class Invoker {

  public static void main(String[] args) {
    LoggerFactory loggerFactory = new ConsoleLogger();
    Logger producer1 = loggerFactory.getProducer();
    Logger producer2 = loggerFactory.getProducer();
    Logger producer3 = loggerFactory.getProducer();


    producer1.info("Hello its a message from saurabh");
    producer2.debug("Hello its a message from flipkart");
    producer3.error("Hello its a message from amazon");

  }
}
