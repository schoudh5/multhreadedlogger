package models;

import lombok.Value;

@Value(staticConstructor="of")
public final class Message {
  String content;
  MessageType messageType;
}
