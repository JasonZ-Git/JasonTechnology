package org.jason.olympics.spider;

import org.jason.olympics.model.Athlete;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;
import org.jason.util.JasonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteAthleteToFileConsumer implements Consumer<Athlete> {

  private static final Logger logger = LoggerFactory.getLogger(WriteAthleteToFileConsumer.class);
  private Path file;

  public WriteAthleteToFileConsumer(Path file) {
    this.file = file;
  }

  @Override
  public void accept(Athlete athlete) {
    String sql = athlete.toInsertSQLString();

    try {
      JasonFileUtil.appendToFile(file, sql);
    } catch (IOException e) {
      logger.error("Error writing to file: {}", file, e);
    }
  }

}
