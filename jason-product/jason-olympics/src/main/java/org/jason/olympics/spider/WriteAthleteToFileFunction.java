package org.jason.olympics.spider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Function;
import org.jason.olympics.model.Athlete;
import org.jason.util.JasonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Write Athlete data into SQL and append to file
 * 
 * @author Jason Zhang
 */
public class WriteAthleteToFileFunction implements Function<Athlete, Athlete> {

  private static final Logger logger = LoggerFactory.getLogger(WriteAthleteToFileFunction.class);
  private Path file;

  public WriteAthleteToFileFunction(Path file) {
    this.file = file;
  }

  @Override
  public Athlete apply(Athlete athlete) {
    Objects.requireNonNull(athlete);

    String sql = athlete.toInsertSQLString();

    try {
      JasonFileUtil.appendToFile(file, sql);
    } catch (IOException e) {
      logger.error("Error writing to file: {}", file, e);
    }
    return athlete;
  }

}
