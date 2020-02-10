package org.jason.util;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * This class should be refactored using the new HttpClient which is available from Java 11 @TODO
 * 
 * @author Jason Zhang
 *
 */

public final class WebCrawlUtil {
  private static final Logger logger  = Logger.getLogger(WebCrawlUtil.class);
  
  // Use google bot as agent string.
  private static final String USER_AGENT = "Mozilla/5.0";
  
  private static final String USER_AGENT_KEY = "User-Agent";
  
  
  private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(Version.HTTP_2).build();

  private WebCrawlUtil() {
    throw new AssertionError("No " + WebCrawlUtil.class + " instances for you!");
  }

  /**
   * A HTTP request will be send and the response content will be checked.
   * 
   * @param url - The URL to visit
   * @return whether or not the crawl was successful
   * @throws IOException If URL is not valid, or due to network connection problem, a exception will
   *         be thrown.
   */
  public static Document crawlPage(@Nonnull final String url) throws IOException {
    Objects.requireNonNull(url); 
    
    Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
    connection.timeout(300_000);
    Document htmlDocument = connection.get();

    // If response not valid, return null. (Do not throw out exception, let the spider class to decide what to do)
    if (!checkResponse(connection.response())) {
      throw new IOException(connection.response().statusMessage());
    }

    return htmlDocument;
  }
  
  // TODO Test -- Needs to test
  public static HttpResponse<String> crawlPage2 (@Nonnull final String url) throws IOException, InterruptedException {
	  Objects.requireNonNull(url); 
	  
	  HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).setHeader(USER_AGENT_KEY, USER_AGENT).build();
	  
	  var response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
	  
	  return response;
  }

  /**
   * Check whether response is valid.
   * 
   * @param response
   * @return false if status code is not 2** or 3** or context type is not 'text/html', otherwise
   *         true.
   */
  private static boolean checkResponse(final Response response) {
    if (response.statusCode() / 100 != 2 && response.statusCode() / 100 != 3) {
      logger.error("Something wrong " + response.statusMessage());
      return false;
    }

    if (!response.contentType().contains("text/html")) {
      logger.error("Retrieved something other than HTML");
      return false;
    }

    return true;
  }
}
