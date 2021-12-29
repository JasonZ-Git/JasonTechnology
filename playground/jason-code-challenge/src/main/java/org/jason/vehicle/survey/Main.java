/**
 * Copyright: All right reserved by Jason, 2015
 *
 * @author jason.zhang
 */
package org.jason.vehicle.survey;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This is the main class, to run this method, a real file name should be provided.
 *
 * @author jason.zhang
 */
public class Main {

  public static void main(String[] args) {
    // TODO replace the filePath with a real file path(including file name), for example '/home/jason/Vehicle.txt'
    String filePath = "src/main/resources/VehicleSurveyCodingChallengeSampleData.txt";
    File file = new File(filePath);

    try {
      List<String> lines = DataParserUtil.readFile(file);

      List<VehicleRecord> records = DataParserUtil.parseDataIntoRecords(lines);

      ReportUtil.runReport(records);

    } catch (IOException e) {
      // Log something, needs to configure a log4j
    }
  }
}
