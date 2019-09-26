package org.jason.renting.task;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.jason.renting.dao.RentRecordDO;
import org.jason.renting.services.YeeyiServiceImpl;
import org.jason.renting.utils.YeeyiUtil;
import org.jason.util.WebCrawlUtil;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class YeeyiSpiderTask implements Runnable {

  // Only Melbourne for now
  private static final String YeeyiRentingURL = "http://www.yeeyi.com/forum/index.php?app=forum&act=display&fid=142&rcity1=1";
  
  private Logger logger = Logger.getLogger(this.getClass());
  
  private int intervalInSeconds = 20;
  
  @Autowired
  private YeeyiServiceImpl yeeyiService;
  
  @EventListener(ApplicationReadyEvent.class)
  public void contextStarted() {
    Thread thread = new Thread(this);
    thread.start();
  }
  
  @Override
  public void run() {
    
    System.out.println("=========================== Spring Started ==================================");
    
    while (true) {
      
      Document document =  readYeeyi();
      
      List<RentRecordDO> rentRecords = YeeyiUtil.toRentingDO(document);
      
      int newRecords = saveRecords(rentRecords);
      
      smartSleep(newRecords, rentRecords.size());
    }
  }
  
  private void smartSleep(int newRecords, int totalRecordsFound) {
    
    // this.intervalInSeconds = this.intervalInSeconds * totalRecordsFound / newRecords - 5;
    
    // this.intervalInSeconds = Math.max(this.intervalInSeconds, 20);
    
    int random = (int) (Math.random() * 10);
    
    System.out.println("=========================== Sleep Started ================================== " + intervalInSeconds);
    try {
      TimeUnit.SECONDS.sleep(10 + random);
    } catch (InterruptedException e) {
      logger.error(e);
    }
    
    System.out.println("=========================== Sleep Over ==================================");
  }

  private int saveRecords(List<RentRecordDO> rentRecords) {
    return yeeyiService.saveRentRecords(rentRecords);
  }

  private Document readYeeyi() {
    Document yeeyiRentPage =  null;
    
    try {
      yeeyiRentPage = WebCrawlUtil.crawlPage(YeeyiRentingURL);
    } catch (IOException e) {
      logger.error(e);
    }
    
    return yeeyiRentPage;
  }

  

}
