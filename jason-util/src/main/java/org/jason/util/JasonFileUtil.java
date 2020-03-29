/**
 * 2015 Copyright jason.zhang
 */
package org.jason.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This file is designed to parse a text file.
 *
 * @author Jason.zhang
 */
public final class JasonFileUtil {
  private final static String SPLIT_NON_ALPHA = "\\P{Alpha}+";

  private static final Logger logger = LogManager.getLogger();

  private JasonFileUtil() {
    throw new AssertionError("No " + JasonFileUtil.class + " instances for you!");
  }

  /**
   * Read file into lines, each line is a string object.
   * 
   * Using this nio will save the trouble of closing files and read files by line.
   * 
   * @param inputFile to read, it should be a text file, for example txt or csv file.
   * @return A list, each item stand for a line.
   * @throws IOException
   */
  public static List<String> readFile(String inputFile) throws IOException {
    return Files.readAllLines(Paths.get(inputFile));
  }

  /**
   * inputFile file of classpath
   * 
   * @param inputFile
   * @return
   * @throws IOException
   */
  public static List<String> readClasspathFileIntoWords(String inputFile) throws IOException {
    try {
      List<String> lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(inputFile).toURI()));

      String[] words = lines.stream().collect(Collectors.joining(",")).split(SPLIT_NON_ALPHA);

      return Arrays.asList(words);
    } catch (URISyntaxException e) {
      throw new IOException(e);
    }
  }

  public static List<String> readFileIntoWords(String inputFile) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get(inputFile));

    String[] words = lines.stream().collect(Collectors.joining(",")).split(SPLIT_NON_ALPHA);

    return Arrays.asList(words);
  }

  /**
   * Write file content into a file.
   */
  public static void writeFile(String outputFile, String fileContent) throws IOException {
    Files.write(Paths.get(outputFile), Arrays.asList(fileContent));
  }

  /**
   * Write file content into a file.
   */
  public static void appendToFile(String outputFile, String fileContent) throws IOException {
    Files.write(Paths.get(outputFile), Arrays.asList(fileContent), StandardOpenOption.APPEND);
  }
}
