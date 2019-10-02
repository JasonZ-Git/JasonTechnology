package org.jason.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jason.util.finalclass.GoogleTimeZone;
import com.google.gson.Gson;

public final class JasonTimeZoneUtil {

	/**
	 *   Jason.Zhang has applied an API from google for this application
	 */
	private static final String GOOLE_TIMEZONE_API = "https://maps.googleapis.com/maps/api/timezone/json?location=%1s,%2s&timestamp=%3s&key=AIzaSyBjc0vgrHCJkwUMu7hjTh5V7PojvbWu_rU";

	private JasonTimeZoneUtil() {
      throw new AssertionError("No " + JasonTimeZoneUtil.class + " instances for you!");
	}
	
	/**
	 * Get timezone from google API.
	 */
	public static GoogleTimeZone getTimeZone(String latitude, String longitude) throws IOException {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		String googleAPI = String.format(GOOLE_TIMEZONE_API, latitude, longitude, now.getTimeInMillis()/1000);

		URL timezoneRequest = new URL(googleAPI);
		HttpURLConnection con = (HttpURLConnection) timezoneRequest.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		List<String> responde = IOUtils.readLines(con.getInputStream());
		String jsonResult = StringUtils.join(responde.iterator(), "");
		StringBuffer response = new StringBuffer();
		con.disconnect();

		return new Gson().fromJson(jsonResult, GoogleTimeZone.class);
	}
}
