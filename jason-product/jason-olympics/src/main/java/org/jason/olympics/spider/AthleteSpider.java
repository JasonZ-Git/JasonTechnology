package org.jason.olympics.spider;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.json.JsonArray;
import javax.json.JsonObject;
import org.jason.olympics.model.Athlete;
import org.jason.util.SpiderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Spider is used to retrieve all athlete data.
 * 
 * Current it supports Tokyo-Olympic. It will be extended to support all olympics
 * 
 * 
 * @author Jason Zhang
 * @version 1.0
 */
public class AthleteSpider {
  private static final Logger logger = LoggerFactory.getLogger(AthleteSpider.class);
  private static final String TOYKO_ATHELETES = "https://olympics.com/tokyo-2020/olympic-games/en/results/all-sports/zzje001a.json";
  private static final int THREAD_COUNT = 20; // Set to a reasonable size (1 or 2) to avoid getting blocked by olympics.com
  private String olympicCode;

  public AthleteSpider(@Nonnull String olympicCode) {
    Objects.requireNonNull(olympicCode);
    
    this.olympicCode = olympicCode;
  }

  /**
   * Read Athelete Data for the given olympic
   * 
   * @return
   */
  public List<Athlete> getAthletes(/* This will be extended to have an olympic enum */) {

    List<Athlete> athletes = new ArrayList<>();

    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    try {
      JsonObject athleteString = SpiderUtil.readJSON(TOYKO_ATHELETES);

      JsonArray bodyData = athleteString.get("data").asJsonArray();

      List<String> athletesURL = bodyData.stream().filter(item -> item.asJsonObject().get("lnk") != null).map(item -> item.asJsonObject().get("lnk").toString()) .map(item -> item.substring(1,
      item.length() - 1)).collect(Collectors.toList());

      List<AthleteSpiderTask> athleteSpiderTasks = new ArrayList<>();

      for (String currentAthletesURL : athletesURL) {

        String absoluteAthletePage = getAbsoluteURL(TOYKO_ATHELETES, currentAthletesURL);

        athleteSpiderTasks.add(AthleteSpiderTask.build(absoluteAthletePage));

        // Lauch the task and then write the result to a file - This can be further improved by using buffered writer instead of writing everytime - just good for now
        CompletableFuture<Athlete> athlete = CompletableFuture.supplyAsync(() -> AthleteSpiderTask.build(absoluteAthletePage).call())
                                            .thenApplyAsync(getAthleteFileFunction());

        athletes.add(athlete.get());
        
        // Slow dow to avoid getting blocked by the mean olympics.com website
        TimeUnit.MILLISECONDS.sleep(100);
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      executorService.shutdown();
    }

    return athletes;
  }

  private WriteAthleteToFileFunction getAthleteFileFunction() {
    String file = "olympic/athlete.sql";

    Path path = null;
    try {
      path = Paths.get(ClassLoader.getSystemResource(file).toURI());
    } catch (URISyntaxException e) {
      logger.error("error write to file {}", file, e);
    }

    return new WriteAthleteToFileFunction(path);
  }

  private String getAbsoluteURL(String baseURL, String relativeURL) {
    URL result = null;
    try {
      result = new URL(new URL(baseURL), relativeURL);
    } catch (MalformedURLException e) {

    }

    return result.toString();
  }
}
