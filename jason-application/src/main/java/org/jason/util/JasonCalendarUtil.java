package org.jason.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

/**
 * This Util class is used to operate calendar, including get, create events.
 *
 * @author jason.zhang
 */
public class JasonCalendarUtil {
  private static final Logger logger = Logger.getLogger(JasonCalendarUtil.class);
  /** Application name. */
  private static final String APPLICATION_NAME = "Google Calendar - Jason servant";

  /** Directory to store user credentials for this application. */
  private static java.io.File DATA_STORE_DIR = null;

  /** Global instance of the {@link FileDataStoreFactory}. */
  private static FileDataStoreFactory DATA_STORE_FACTORY;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  /** Global instance of the HTTP transport. */
  private static HttpTransport HTTP_TRANSPORT;
  private static final String CALENDAR_ID = "primary";

  /**
   * If modifying these scopes, delete your previously saved credentials at
   * ~/.credentials/calendar-java-quickstart
   */
  private static final List<String> SCOPES =
      Arrays.asList(CalendarScopes.CALENDAR, CalendarScopes.CALENDAR_READONLY);

  static {
    try {
      DATA_STORE_DIR = new java.io.File(
          JasonCalendarUtil.class.getClassLoader().getResource("credential").toURI());
      HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
      DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
    } catch (Throwable t) {
      logger.error("Can't access store dir" + DATA_STORE_DIR, t);
      System.exit(1);
    }
  }

  /**
   * Get events from the start time to end time.
   * 
   * @param start start time of a period.
   * @param end end time of of a period.
   * 
   * @return
   * @throws IOException
   */
  public static List<Event> getEvents(DateTime start, DateTime end) throws IOException {
    Events events = getCalendarService().events().list(CALENDAR_ID).setTimeMin(start).setTimeMax(end)
        .setOrderBy("startTime").setSingleEvents(true).execute();
    return events.getItems();
  }

  /**
   * Create a new event.
   * 
   * @param startTime Start of the event. (Auckland Time)
   * @param endTime End of the event. (Auckland)
   * @param eventSummary short information of the event.
   * @throws IOException
   */
  public static Event createEvent(DateTime startTime, DateTime endTime, String eventSummary)
      throws IOException {
    Event event = new Event().setSummary(eventSummary);

    EventDateTime start = new EventDateTime().setDateTime(startTime);
    event.setStart(start);

    EventDateTime end = new EventDateTime().setDateTime(endTime);
    event.setEnd(end);

    return getCalendarService().events().insert(CALENDAR_ID, event).execute();
  }

  public static void deleteEvent(String eventId) throws IOException {
    getCalendarService().events().delete(CALENDAR_ID, eventId).execute();
  }

  /**
   * Creates an authorized Credential object.
   * 
   * @return an authorized Credential object.
   * @throws IOException
   */
  private static Credential authorize() throws IOException {
    // Load client secrets.
    InputStream in = JasonCalendarUtil.class.getResourceAsStream("/credential/client_secret.json");
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow =
        new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
    Credential credential =
        new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("Jason");
    return credential;
  }

  /**
   * Build and return an authorized Calendar client service.
   * 
   * @return an authorized Calendar client service
   * @throws IOException
   */
  private static com.google.api.services.calendar.Calendar getCalendarService() throws IOException {
    Credential credential = authorize();
    return new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY,
        credential).setApplicationName(APPLICATION_NAME).build();
  }
}
