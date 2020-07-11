package org.jason.spider.yeeyi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jason.spider.yeeyi.dao.RentRecordDO;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class YeeyiSpiderTask implements Runnable {

    private Logger logger = LogManager.getLogger(this.getClass());

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

            Document document = YeeyiUtil.readYeeyiRentingPage();

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
}
