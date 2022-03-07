package Factory;

import models.Logger;
import service.Producer;

public interface LoggerFactory {
  Logger getProducer();
}
