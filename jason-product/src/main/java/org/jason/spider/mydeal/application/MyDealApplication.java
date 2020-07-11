package org.jason.spider.mydeal.application;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jason.annotation.Application;
import org.jason.spider.mydeal.MyDealUtil;

@Application(name = "MyDeal Application - Craw MyDeal")
public class MyDealApplication {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(100);

        CompletionService<List<String>> complesionService = new ExecutorCompletionService<List<String>>(executor);

        Integer count = MyDealUtil.getPages(complesionService);

        int totalCount = 0;

        for (int index = 0; index < count; index++) {
            try {
                List<String> oneCategoryPage = complesionService.take().get();
                totalCount += oneCategoryPage.size();

                System.out.printf("Get %s items \n", oneCategoryPage.size());

            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
            }
        }

        System.out.printf("Total pages are %s", totalCount);
        executor.shutdown();
    }
}
