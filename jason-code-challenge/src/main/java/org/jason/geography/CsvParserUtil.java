package org.jason.geography;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * This class is designed to parse and convert a CSV file.
 *
 * @author jason.zhang
 */
public class CsvParserUtil {
    /**
     * Read file
     *
     * @return
     */
    public static List<InputObject> readFile(File csvFile) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<InputObject> inputObjects = new ArrayList<InputObject>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] originalObject = line.split(cvsSplitBy);
                if (originalObject != null && originalObject.length == 3) {
                    InputObject inputObject = new InputObject(originalObject[0], originalObject[1], originalObject[2]);
                    inputObjects.add(inputObject);
                } else {
                    throw new RuntimeException("Can't parse the input object: " + originalObject);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return inputObjects;
    }

    /**
     * convert objects
     *
     * @param inputObjects
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static List<OutputObject> convertObjects(List<InputObject> inputObjects) throws IOException, ParseException {
        List<OutputObject> outputObjects = new ArrayList<OutputObject>();
        for (InputObject inputObject : inputObjects) {
            String timeZone = getTimeZone(inputObject.getLatitude(), inputObject.getLongitude());
            // Get local timezone
            TimeZone localisedTimeZone = TimeZone.getTimeZone(timeZone);

            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date UTCTime = dateformat.parse(inputObject.getUTCTime());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(UTCTime);

            dateformat.setTimeZone(localisedTimeZone);
            String localisedTime = dateformat.format(UTCTime);

            OutputObject output = new OutputObject(inputObject, timeZone, localisedTime);
            outputObjects.add(output);
        }
        return outputObjects;
    }

    /**
     * Get timezone
     *
     * @param latitude
     * @param longitude
     * @return
     * @throws IOException
     */
    public static String getTimeZone(String latitude, String longitude) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/timezone/json?location=" + latitude + "," + longitude + "&timestamp=1331161200";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String returnObject = response.toString();

        String timeZone = returnObject.split(",")[3].split("\"")[3];

        return timeZone;
    }

    /**
     * Write to output file
     *
     * @param outputFile
     * @param output
     * @throws IOException
     */
    public static void writeFile(File outputFile, List<OutputObject> output) throws IOException {
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (OutputObject current : output) {
            bw.write(current.toString());
            bw.newLine();
        }
        System.out.println("file is here: " + outputFile);
        bw.close();
    }

}

class InputObject {
    private String UTCTime;
    private String latitude;
    private String longitude;

    public InputObject(String UTCTime, String latitude, String longitude) {
        this.UTCTime = UTCTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public InputObject(InputObject itself) {
        this.UTCTime = itself.UTCTime;
        this.latitude = itself.latitude;
        this.longitude = itself.longitude;
    }

    public String getUTCTime() {
        return UTCTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}

class OutputObject extends InputObject {
    private String timeZone;
    private String localTime;

    public OutputObject(InputObject originalObject, String timeZone, String localTime) {
        super(originalObject);
        this.timeZone = timeZone;
        this.localTime = localTime;
    }

    public String toString() {
        return super.getUTCTime() + "," + super.getLatitude() + "," + super.getLongitude() + "," + this.timeZone + "," + this.localTime;
    }
}