package org.jason.util.object;

/**
 * This class represent the timezone object that is converted from json
 * 
 * The original source is here: https://developers.google.com/maps/documentation/timezone/intro
 * 
 * @author Jason Zhang
 */
public class GoogleTimeZone {
	private int dstOffset;
	private int rawOffset;
	private String status;
	private String timeZoneId;
	private String timezoneName;
	private String error_message;

	public int getDstOffset() {
		return dstOffset;
	}

	public void setDstOffset(int dstOffset) {
		this.dstOffset = dstOffset;
	}

	public int getRawOffset() {
		return rawOffset;
	}

	public void setRawOffset(int rawOffset) {
		this.rawOffset = rawOffset;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public String getTimezoneName() {
		return timezoneName;
	}

	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

}

