package models;

public interface Logger {
  void info(String message);
  void debug(String message);
  void error(String message);
}
