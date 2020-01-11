package org.jason.util.calendar;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.jason.util.Requirements;

/**
 * This Util class is used to operate calendar, including get, create events.
 * 
 * @author Jason Zhang
 */
public class JasonCalendarUtil {
  private static final String APPLICATION_NAME = "Jason Calendar";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "calendar-generate-token";
  private static final Logger logger = Logger.getLogger(JasonCalendarUtil.class);
  /**
   * Global instance of the scopes required by this quickstart. If modifying these scopes, delete your
   * previously saved tokens/ folder.
   */
  private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
  private static final String CREDENTIALS_FILE_PATH = "credentials.json";

  /**
   * Creates an authorized Credential object.
   * 
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
    // Load client secrets.
    InputStream in = JasonCalendarUtil.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH))).setAccessType("offline").build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }

  private static Calendar getService() throws GeneralSecurityException, IOException {
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
    return service;
  }

  public static List<Event> getEvents(int maxNumber) throws GeneralSecurityException, IOException {
    Requirements.requireTrue(maxNumber <= 500);

    Calendar service = getService();

    DateTime now = new DateTime(System.currentTimeMillis());
    Events events = service.events().list("primary").setMaxResults(maxNumber).setTimeMin(now).setOrderBy("startTime").setSingleEvents(true).execute();
    List<Event> items = events.getItems();

    return items;
  }

  public static void printComingEvents(int maximum) {
    List<Event> items;
    try {
      items = getEvents(maximum);
    } catch (GeneralSecurityException | IOException e) {
      logger.error(e);
      
      return;
    }

    if (items.isEmpty()) {
      logger.info("No upcoming events found.");
      
      return;
    }

    logger.info("Upcoming events");
    
    for (Event event : items) {
      DateTime start = event.getStart().getDateTime();
      if (start == null) {
        start = event.getStart().getDate();
      }
      logger.info(event.getSummary() + " (" + start + ")\n");
    }
  }
}
