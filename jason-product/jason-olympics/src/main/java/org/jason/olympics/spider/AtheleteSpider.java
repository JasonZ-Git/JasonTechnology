package org.jason.olympics.spider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
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
public class AtheleteSpider {
  private static final Logger logger = LoggerFactory.getLogger(AtheleteSpider.class);
  private static final String TOYKO_ATHELETES = "https://olympics.com/tokyo-2020/olympic-games/en/results/all-sports/zzje001a.json";
  private static final int THREAD_COUNT = 30;
  private String olympicCode;

  public AtheleteSpider(String olympicCode) {
    this.olympicCode = olympicCode;
  }
  
  /**
   * Read Athelete Data for the given olympic
   * 
   * @return
   */
  public List<Athlete> getAtheletes(/* This will be extended to have an olympic enum */) {

    List<Athlete> athletes = new ArrayList<>();

    try {
      JsonObject athleteString = SpiderUtil.readJSON(TOYKO_ATHELETES);

      JsonArray bodyData = athleteString.get("data").asJsonArray();

      List<String> athletesURL = bodyData.stream().filter(item -> item.asJsonObject().get("lnk") != null).map(item -> item.asJsonObject().get("lnk").toString())
          .map(item -> item.substring(1, item.length() - 1)).collect(Collectors.toList());

      List<AthleteSpiderTask> athleteSpiderTasks = new ArrayList<>();

      for (String currentAthletesURL : athletesURL) {

        String absoluteAthletePage = getAbsoluteURL(TOYKO_ATHELETES, currentAthletesURL);

        athleteSpiderTasks.add(AthleteSpiderTask.build(absoluteAthletePage));
      }

      ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

      List<Future<Athlete>> result = executorService.invokeAll(athleteSpiderTasks, countTimeOut(), TimeUnit.SECONDS);

      for (Future<Athlete> current : result) {
        athletes.add(current.get());
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    return athletes;
  }

  private int countTimeOut() {
    int athleteNumber = 10_000;

    return athleteNumber / THREAD_COUNT * 3;
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
