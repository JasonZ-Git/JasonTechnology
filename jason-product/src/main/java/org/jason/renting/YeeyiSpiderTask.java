package org.jason.renting;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.jason.renting.dao.RentRecordDO;
import org.jason.renting.services.YeeyiServiceImpl;
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
  
  @Autowired
  private YeeyiServiceImpl yeeyiService;
  
  @EventListener(ApplicationReadyEvent.class)
  public void contextStarted() {
    Thread thread = new Thread(this);
    thread.start();
  }
  
  

  private void sleepInSeconds(int i) {
    
    System.out.println("=========================== Sleep Started ==================================");
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      logger.error(e);
    }
    
    System.out.println("=========================== Sleep Over ==================================");
  }



  private void saveRecords(List<RentRecordDO> rentRecords) {
    yeeyiService.saveRentRecords(rentRecords);
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



  @Override
  public void run() {

    System.out.println("=========================== Spring Started ==================================");
    
    while (true) {
      
      Document document =  readYeeyi();
      
      List<RentRecordDO> rentRecords = YeeyiUtil.toRentingDO(document);
      
      saveRecords(rentRecords);
      
      sleepInSeconds(20);
    }
  }
  

}
