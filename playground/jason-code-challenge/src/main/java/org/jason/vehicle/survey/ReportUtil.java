/**
 * Copyright: All right reserved by Jason, 2015
 *
 * @author jason.zhang
 */
package org.jason.vehicle.survey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * This class is used to generate all kinds of reports according the {Report} data.
 * @author jason.zhang
 *
 */
public class ReportUtil {
    /**
     * Print the report
     *
     * @param records record data recorded by sensor.
     */
    public static void runReport(List<VehicleRecord> records) {
        List<VehicleRecord> aRecords = getRecordsOfOneDirection(records, "A");
        List<VehicleRecord> bRecords = getRecordsOfOneDirection(records, "B");

        runCarNumberReportOfaDirection(aRecords, "A");
        runCarNumberReportOfaDirection(bRecords, "B");

        runCarNumberReportForDifferentPeriod(aRecords, "A");
        runCarNumberReportForDifferentPeriod(bRecords, "B");

        runPeakNumberReport(aRecords, "A");
        runPeakNumberReport(bRecords, "B");

        runCarSpeedDistrubutionReport(aRecords, "A");
        runCarSpeedDistrubutionReport(bRecords, "B");

        runCarDistanceReport(aRecords, "A");
        runCarDistanceReport(bRecords, "B");
    }

    /**
     * Filter out all the records of a direction.
     *
     * @param records Record data
     * @param direction In this case, it could be 'A' or 'B'
     *
     * @return Record of the direction.
     */
    public static List<VehicleRecord> getRecordsOfOneDirection(List<VehicleRecord> records, final String direction) {
        List<VehicleRecord> recordsOfDirecton = new ArrayList<VehicleRecord>();
        records.stream().filter((s) -> s.getSensor().equals(direction)).forEach(recordsOfDirecton::add);
        return recordsOfDirecton;
    }

    /**
     * Report of total vehicle counts in a direction.
     *
     * @param records of a direction
     * @param direction direction name.
     */
    public static int runCarNumberReportOfaDirection(List<VehicleRecord> records, final String direction) {
        List<VehicleRecord> recordsOfaDirection = getRecordsOfOneDirection(records, direction);
        System.out.println("Total vehicle counts in directory " + direction + " is: " + recordsOfaDirection.size());

        return recordsOfaDirection.size();
    }

    /**
     * Report of total vehicle counts in the morning or evening in a direction.
     *
     * @param records of a direction
     * @param direction direction name.
     */
    public static void runCarNumberReportForMornigEvening(List<VehicleRecord> records, final String direction) {
        List<VehicleRecord> recordsOfaDirection = getRecordsOfOneDirection(records, direction);

        System.out.println();
        List<VehicleRecord> timebasedList = new ArrayList<VehicleRecord>();
        recordsOfaDirection.stream().filter((s) -> s.isMorning()).forEach(timebasedList::add);
        System.out.println("Total vehicle counts of " + direction + " direction in the morning is: " + timebasedList.size());

        timebasedList.clear();
        recordsOfaDirection.stream().filter((s) -> s.isEvening()).forEach(timebasedList::add);
        System.out.println("Total vehicle counts of " + direction + " direction in the evening is: " + timebasedList.size());
    }

    /**
     * Report of total vehicle counts for different periods in a direction.
     *
     * @param records of a direction
     * @param direction direction name.
     */
    public static void runCarNumberReportForDifferentPeriod(List<VehicleRecord> records, final String direction) {
        List<VehicleRecord> recordsOfaDirection = getRecordsOfOneDirection(records, direction);

        System.out.println();
        runCarNumberReportForMornigEvening(recordsOfaDirection, direction);

        runCarNumberReportAccordingToTimeInterval(recordsOfaDirection, 60, direction);
        runCarNumberReportAccordingToTimeInterval(recordsOfaDirection, 30, direction);
        runCarNumberReportAccordingToTimeInterval(recordsOfaDirection, 20, direction);
        runCarNumberReportAccordingToTimeInterval(recordsOfaDirection, 15, direction);
    }

    /**
     * Report of total vehicle counts every {timeInterval} minutes in a direction.
     *
     * @param records of a direction
     * @param timeInterval minutes of time interval.
     */
    public static List<Integer> runCarNumberReportAccordingToTimeInterval(List<VehicleRecord> records, int timeInterval, final String direction) {
        List<VehicleRecord> recordsOfaDirection = getRecordsOfOneDirection(records, direction);

        System.out.println();
        List<Integer> carNumberForPeriod = new ArrayList<Integer>();
        for (int index = 0; index < 24 * 60 / timeInterval; ++index) {
            List<VehicleRecord> timebasedList = new ArrayList<VehicleRecord>();
            final int temp = index + 1;
            recordsOfaDirection.stream().filter((s) -> s.getRecordingTime() > (temp - 1) * timeInterval * 60 * 1000)
                    .filter((s) -> s.getRecordingTime() <= temp * timeInterval * 60 * 1000)
                    .forEach(timebasedList::add);
            carNumberForPeriod.add(timebasedList.size());
        }
        System.out.println("Total vehicle counts every " + timeInterval + " minutes in direction " + direction + " is: " + carNumberForPeriod);

        return carNumberForPeriod;
    }

    /**
     * Run the report of peak hours in a day for a direction.
     *
     * @param records of a direction
     * @param direction direction name.
     */
    public static List<Integer> runPeakNumberReport(List<VehicleRecord> records, final String direction) {
        List<VehicleRecord> recordsOfaDirection = getRecordsOfOneDirection(records, direction);

        System.out.println();
        TreeMap<Integer, Integer> carNumberByHour = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        for (int index = 0; index < 24; ++index) {
            List<VehicleRecord> timebasedList = new ArrayList<VehicleRecord>();
            final int temp = index + 1;
            recordsOfaDirection.stream().filter((s) -> s.getRecordingTime() > (temp - 1) * 60 * 60 * 1000)
                    .filter((s) -> s.getRecordingTime() <= temp * 60 * 60 * 1000)
                    .forEach(timebasedList::add);
            carNumberByHour.put(timebasedList.size(), index);
        }

        // Find peak time
        List<Integer> peakHours = new ArrayList<Integer>(carNumberByHour.values());
        System.out.print("Peak hours in direction " + direction + " are from " + peakHours.get(1) + " o'clock to " + (peakHours.get(1) + 1) + " o'clock,");
        System.out.println(" and from " + peakHours.get(0) + " o'clock to " + (peakHours.get(0) + 1) + " o'clock");

        return peakHours;
    }

    /**
     * Generate the report of the (rough) speed distribution of traffic
     *
     * @param records of a direction
     * @param direction direction name.
     */
    public static List<Integer> runCarSpeedDistrubutionReport(List<VehicleRecord> records, final String direction) {
        List<VehicleRecord> recordsOfaDirection = getRecordsOfOneDirection(records, direction);

        System.out.println();

        List<Integer> speeds = new ArrayList<Integer>();
        recordsOfaDirection.forEach((s) -> speeds.add(s.getSpeed()));
        Collections.sort(speeds);
        System.out.println("This is the speed report of direction " + direction + ", lowest speed is: " + speeds.get(0) + "km/h, highest speed is " + speeds.get(speeds.size() - 1) + "km/h");

        int speedRangeFrom = speeds.get(0) / 10 * 10;
        int speedRangeTo = (speeds.get(speeds.size() - 1) / 10 + 1) * 10;

        List<Integer> vehicleCounts = new ArrayList<Integer>();
        for (int currentSpeed = speedRangeFrom; currentSpeed < speedRangeTo; currentSpeed += 10) {
            final int tempSpeed = currentSpeed;
            int speedDistance = 10;
            int vehicleCount = (int) recordsOfaDirection.stream().filter((s) -> s.getSpeed() > tempSpeed)
                    .filter((s) -> s.getSpeed() <= (tempSpeed + speedDistance)).count();
            vehicleCounts.add(vehicleCount);
        }

        System.out.println("Vehicle counts from " + speedRangeFrom + "km/h to " + speedRangeTo + "km/h per 10km/h is: " + vehicleCounts);

        return vehicleCounts;
    }

    /**
     * Generate the report of rough distance between cars during various periods.
     *
     * @param records of a direction
     * @param direction direction name.
     */
    public static List<Integer> runCarDistanceReport(List<VehicleRecord> records, final String direction) {
        List<VehicleRecord> recordsOfaDirection = getRecordsOfOneDirection(records, direction);

        System.out.println();
        List<Integer> averageDistanceByHour = new ArrayList<Integer>();
        for (int index = 0; index < 24; ++index) {
            List<VehicleRecord> timebasedRecord = new ArrayList<VehicleRecord>();
            final int temp = index + 1;
            recordsOfaDirection.stream().filter((s) -> s.getRecordingTime() > (temp - 1) * 60 * 60 * 1000)
                    .filter((s) -> s.getRecordingTime() <= temp * 60 * 60 * 1000)
                    .forEach(timebasedRecord::add);
            List<Double> distances = new ArrayList<Double>();
            for (int index2 = 0; index2 < timebasedRecord.size() - 1; index2++) {
                int tempTimeDiff = timebasedRecord.get(index2 + 1).getFirstAxleRecordingTime() - timebasedRecord.get(index2).getSecordAxleRecordingTime();
                double distance = tempTimeDiff * 1.0 / 1000 * 60 / 3.6;
                distances.add(distance);
            }
            int sum = distances.stream().mapToInt(i -> i.intValue()).sum();
            int averageDistance = sum / distances.size();
            averageDistanceByHour.add(averageDistance);
        }

        System.out.println("This is the vehicle distance report, average distance between vehicles in direction " + direction + " per hour in a day from 0am is: " + averageDistanceByHour + " meters");

        return averageDistanceByHour;
    }
}
