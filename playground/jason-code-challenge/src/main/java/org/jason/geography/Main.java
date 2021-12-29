/**
 * Copyright: All right reserved by Jason, 2015
 *
 * @author jason.zhang
 */
package org.jason.geography;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * This is the main class, to run this method, a real file name should be provided.
 *
 * @author jason.zhang
 */
public class Main {

  public static void main(String[] args) {
    try {
      String fileName = "src/main/resources/geography/simpleData.csv";
      File inputFile = new File(fileName);
      List<InputObject> inputObjects = CsvParserUtil.readFile(inputFile);
      List<OutputObject> outputObjects = CsvParserUtil.convertObjects(inputObjects);
      File outputFile = new File(inputFile.getParentFile().getAbsoluteFile() + File.separator + "outputFile.csv");
      CsvParserUtil.writeFile(outputFile, outputObjects);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
