package org.jason.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.ReplacedBy;
import org.jason.annotation.ToRefactor;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * This class should be refactored using the new HttpClient which is available from Java 11 @TODO
 *
 * @author Jason Zhang
 * @since 2013-01-05
 */

@ToRefactor("Using HttpClient from Java 11 to refactor")
public final class SpiderUtil {
    private static final Logger logger = LogManager.getLogger(SpiderUtil.class);

    // Use google bot as agent string.
    private static final String USER_AGENT = "Mozilla/5.0";

    private SpiderUtil() {
        throw new AssertionError("No " + SpiderUtil.class + " instances for you!");
    }

    /**
     * A HTTP request will be send and the response content will be checked. This is used in Java 8, and replaced in Java 11
     *
     * @param url - The URL to visit
     * @return whether or not the crawl was successful
     * @throws IOException If URL is not valid, or due to network connection problem, a exception will be thrown.
     */
    @ReplacedBy("crawlPage")
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

    /**
     * In the long run, this method should be rewrite by new HTTPClient in Java 11
     *
     * @return A Json String
     */
    public static String readJson(@Nonnull final String jsonURL) throws IOException {
        Objects.requireNonNull(jsonURL);
        Parameters.requireTrue(jsonURL.endsWith(".json"));

        InputStream is = new URL(jsonURL).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        } finally {
            is.close();
        }
    }

    /**
     * Check whether response is valid.
     *
     * @param response
     * @return false if status code is not 2** or 3** or context type is not 'text/html', otherwise true.
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
