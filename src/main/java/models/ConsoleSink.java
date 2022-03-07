package models;

import java.time.LocalDateTime;

public class ConsoleSink implements Sink {




  @Override
  public void write(Message message) {

    System.out.println(LocalDateTime.now().toString() +"::"+message.getMessageType().getMessageLevel() + "::" + message.getContent());
  }
}
