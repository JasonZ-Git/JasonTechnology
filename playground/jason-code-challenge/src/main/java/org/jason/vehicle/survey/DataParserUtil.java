
/**
 * Copyright: All right reserved by Jason, 2015
 *
 * @author jason.zhang
 */
package org.jason.vehicle.survey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class is designed to parse the data of the file
 *
 * @author jason.zhang
 */
public class DataParserUtil {
  /*
   * This is the maximum time interval for the same car to be recorded by 2 sensors. Usually, a car is recorded by senor A, and then after 3 or 4 ms, it is recorded by sensor B 10 ms should be a
   * proper maximum value.
   */
  private static final int MAX_TIME_INTERVAL = 10;

  /*
   * The time interval for the same car to pass a sensor The normal value should be 2.5m/16.67 = 150ms (60km/h = 16.67m/s). The maximum value could be 150 *200/60 = 500 (Assume the maximum speed is
   * 200km/h)
   */
  private static final int SAME_CAR_MAX_TIME_INTERVAL = 500;

  /**
   * Read file into lines, each line is a string object.
   *
   * @param inputFile to read, it should be a text file, for example txt or csv file.
   * @return A list, each item stand for a line.
   *
   * @throws IOException If an I/O error happens.
   */
  public static List<String> readFile(File inputFile) throws IOException {
    List<String> lineList = new ArrayList<String>();
    if (!inputFile.exists()) {
      return lineList;
    }

    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    String line = "";
    while ((line = reader.readLine()) != null) {
      lineList.add(line.trim());
    }
    reader.close();

    return lineList;
  }

  /**
   * In the records, the sequence could be like: A*** 1 A*** 2 A*** 3 B*** 4 A*** 5 B*** 6
   *
   * Line 3,5 and line 4,6 stand for the same car, so if line 3 and 5 are deleted, the date could be easier to analyze. After delete 3 and 5, the result will become: A*** 1 A*** 2 B*** 4 B*** 6
   *
   * @param originalRecords original records from data file
   * @return records without unused data
   */
  public static List<String> removeUnusedRecord(List<String> originalRecords) {
    ListIterator<String> iterator = originalRecords.listIterator();
    List<String> newList = new ArrayList<String>();
    while (iterator.hasNext()) {
      String firstLine = iterator.next();
      int firstDateTime = Integer.parseInt(firstLine.substring(1));
      if (firstLine.startsWith("A") && iterator.hasNext()) {
        String secondLine = iterator.next();
        int secondDateTime = Integer.parseInt(secondLine.substring(1));
        int interval = secondDateTime - firstDateTime;

        if (secondLine.startsWith("A")) {
          newList.add(firstLine);
          iterator.previous();
        } else { // if starts with 'B'
          if (interval < MAX_TIME_INTERVAL && interval > 0) {
            newList.add(secondLine);
          } else {
            System.out.println("interval is: " + interval + " first line  " + firstLine + " second line: " + secondLine);
          }
        }
      }
    }

    return newList;
  }

  /**
   * Parse data into {Record} objects
   *
   * @param processedRecords original record. The sequence of the originalRecords should be the same as original data, which is in a time-based order.
   * @return {Record} objects
   */
  public static List<VehicleRecord> parseDataIntoRecords(List<String> originalRecords) {
    List<String> processedRecords = removeUnusedRecord(originalRecords);

    List<VehicleRecord> records = new ArrayList<VehicleRecord>();
    int date = 1;

    ListIterator<String> iterator = processedRecords.listIterator();
    int previousTime = 0;
    while (iterator.hasNext()) {
      String firstLine = iterator.next();
      String sensorNumber = firstLine.substring(0, 1);

      if (iterator.hasNext()) {
        String secondLine = iterator.next();
        if (secondLine.startsWith(sensorNumber)) {
          int time1 = Integer.parseInt(firstLine.substring(1));
          int time2 = Integer.parseInt(secondLine.substring(1));
          int invervals = time2 - time1;

          // If the second line comes from the different date.
          if (time1 < previousTime) {
            ++date;
          }

          // For safety purpose, if there are some dirty data (for example, one record line missing), other records could still work well.
          if (invervals < SAME_CAR_MAX_TIME_INTERVAL) {
            // It means time1 and time2 are from the same car.
            VehicleRecord record = new VehicleRecord(time1, time2, date, sensorNumber);
            records.add(record);
          } else {
            // The second line is the record of a different car, then iterator should go back one,
            // as it go forward twice.
            iterator.previous();
          }
        } else {
          // The second line is the record of a different sensor, then iterator should go back one,
          // as it go forward twice.
          iterator.previous();
        }

        previousTime = Integer.parseInt(secondLine.substring(1));
      }
    }

    return records;
  }

}
