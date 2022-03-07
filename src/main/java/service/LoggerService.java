package service;

import models.Message;

public interface LoggerService {
  boolean push(Message message);
}
