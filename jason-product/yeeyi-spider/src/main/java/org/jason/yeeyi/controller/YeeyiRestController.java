package org.jason.yeeyi.controller;

import org.jason.util.SpiderUtil;
import org.jason.util.exception.PageNotFoundException;
import org.jason.yeeyi.datamodel.RentingVO;
import org.jason.yeeyi.util.YeeyiHelper;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class YeeyiRestController {

    @RequestMapping("/getYeeyiRentings")
    public String crawlYeeyi(@RequestParam(value = "url", defaultValue = YeeyiHelper.YeeyiRentingURL) String url) throws PageNotFoundException {
        try {
            Document document = SpiderUtil.crawlPage(url);

            List<RentingVO> items = YeeyiHelper.toRentingVO(document);

            return items.stream().map(item -> item.toString()).collect(Collectors.joining("<br>"));

        } catch (IOException e) {
            throw new PageNotFoundException();
        }
    }

    @RequestMapping("/")
    public String defaultPage() {
        return "<p>Please enter page url to be crawled:</p>"

                + "<p>Crawl only one page:</p>"

                + "<form method='GET' action='getYeeyiRentings'><input type='text' name='url' value/></br><button type='submit'>Submit</button></form>"

                + "<p> Default URL is: getYeeyiRentings </p>"

                + "<br />"

                + "<p>Crawl all related pages (maximum number is 50) </p>"

                + "<form method='GET' action='crawlPages'><input type='text' name='url'/></br><button type='submit'>Submit</button></form>"

                + "<p> Default URL is: " + YeeyiHelper.YeeyiRentingURL + "</p>";
    }
}
