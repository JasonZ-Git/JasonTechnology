package org.jason;

import org.jason.annotation.Application;

import org.jason.util.SpiderUtil;
import org.jason.yeeyi.datamodel.RentingVO;
import org.jason.yeeyi.datamodel.YeeyiCriteria;
import org.jason.yeeyi.util.YeeyiHelper;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class YeeyiTest {

    private static final List<String> DEFAULT_AREA = Arrays.asList("camberwell", "Burwood", "surrey hill", "caulfield", "malvern", "Toorak", "south yarra", "glen iris", "richmond", "hawthorn");

    @Test
    public void printAll() throws IOException, InterruptedException {

        for (String current : DEFAULT_AREA) {
            String extended = YeeyiCriteria.build().district(current).getSearchCriteria();
            String url = YeeyiHelper.YeeyiRentingURL + extended;
            Document document = SpiderUtil.crawlPage(url);

            List<RentingVO> items = YeeyiHelper.toRentingVO(document);

            List<RentingVO> filteredItems = YeeyiHelper.defaultFilter(items);

            filteredItems = filteredItems.stream().filter(item -> Integer.parseInt(item.getHouseStyle().substring(0, 1)) <= 4).collect(Collectors.toList());

            String content = filteredItems.stream().map(item -> item.toString()).collect(Collectors.joining("\n"));

            StringBuilder builder = new StringBuilder();
            builder.append("=====" + current + "=====").append("\n");
            builder.append(content).append("\n\n");
            TimeUnit.SECONDS.sleep(2);

            System.out.println(builder.toString());
        }
    }
}
