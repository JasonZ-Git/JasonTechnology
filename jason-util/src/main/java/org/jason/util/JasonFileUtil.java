/**
 * 2015 Copyright jason.zhang
 */
package org.jason.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This file is designed to parse a text file.
 *
 * @author Jason.zhang
 */
public final class JasonFileUtil {

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
   * Write file content into a file.
   */
  public static void writeFile(String outputFile, String fileContent) throws IOException {
    Files.write(Paths.get(outputFile), Arrays.asList(fileContent));
  }
  
  
  public static void main(String[] args) throws IOException {
    List<String> source = JasonFileUtil.readFile("/home/jason/score.txt");
    
    List<String>dest = new ArrayList<String>();
    for(String current : source) {
      String newStr = "<div class=\"music-note-container shake-rotate\">" + current +
      "<span class=\"material-icons w3-text-grey w3-hover-text-black w3-small delete-score-btn\" onclick=\"removeElement(this)\">close</span></div>";
      dest.add(newStr);
    }
    Files.write(Paths.get("/home/jason/out.txt"), dest);
  }
}
