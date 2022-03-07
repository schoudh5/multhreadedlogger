package Factory;

import java.util.Optional;
import models.ConsoleSink;
import models.Logger;
import service.MultithreadedLogger;
import service.Producer;

public class ConsoleLogger implements LoggerFactory {

  private MultithreadedLogger multithreadedLogger = new MultithreadedLogger("console logger",
      new ConsoleSink(),
      200);

  @Override
  public Logger getProducer() {
    Optional<Producer> producer = multithreadedLogger.getProducer();
    if(producer.isPresent()){
      return producer.get();
    } else{
      System.out.println("Things are a little busy right now!!");
      return null;
    }
  }
}
