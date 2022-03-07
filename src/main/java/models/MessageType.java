package models;

public enum MessageType {
  INFO("INFO"),
  ERROR("ERROR"),
  DEBUG("BEBUG");

  // declaring private variable for getting values
  private String messageLevel;

  // getter method
  public String getMessageLevel()
  {
    return this.messageLevel;
  }

  // enum constructor - cannot be public or protected
  private MessageType(String messageLevel)
  {
    this.messageLevel = messageLevel;
  }
}
