/**
 * Copyright: All right reserved by Jason, 2015
 *
 * @author jason.zhang
 */
package org.jason.vehicle.survey;

/**
 * Stand for a car record when a car(with 2 axles) passed the sensor
 *
 * @author jason.zhang
 */
public class VehicleRecord {

    // The length of the car between axles.
    private static final float CAR_LENGTH = 2.5f;

    // This is the time the first axle passing the sensor in milliseconds, 0 means 12:00:00 am.
    private int firstAxleRecordingTime;

    // This is the time the second axle passing the sensor in milliseconds, 0 means 12:00:00 am.
    private int secondAxleRecordingTime;

    // The date number of the record, starting from 1
    private int dateNumber;

    // Sensor number, currently, it is 'A' or 'B'
    private String sensor;

    public VehicleRecord(int firstAxleRecordingTime, int secondAxleRecordingTime, int dateNumber,
                         String sensor) {
        this.firstAxleRecordingTime = firstAxleRecordingTime;
        this.secondAxleRecordingTime = secondAxleRecordingTime;
        this.dateNumber = dateNumber;
        this.sensor = sensor;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public boolean isMorning() {
        return this.firstAxleRecordingTime <= 12 * 60 * 60 * 1000;
    }

    public boolean isEvening() {
        return this.firstAxleRecordingTime > 12 * 60 * 60 * 1000;
    }

    public int getFirstAxleRecordingTime() {
        return this.firstAxleRecordingTime;
    }

    public int getSecordAxleRecordingTime() {
        return this.secondAxleRecordingTime;
    }

    public int getRecordingTime() {
        return this.firstAxleRecordingTime;
    }

    public int getSpeed() {
        return (int) (CAR_LENGTH * 1000 / (this.secondAxleRecordingTime - this.firstAxleRecordingTime) * 3.6);
    }

    public int getDateNumber() {
        return this.dateNumber;
    }

}
