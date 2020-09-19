package org.jason.yeeyi.rest;

import org.jason.util.SpiderUtil;
import org.jason.util.exception.PageNotFoundException;
import org.jason.yeeyi.datamodel.RentingVO;
import org.jason.yeeyi.util.YeeyiHelper;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class YeeyiRestController {

    @RequestMapping("/getYeeyiRentings")
    @ResponseStatus(HttpStatus.OK)
    public List<RentingVO> crawlYeeyi(@RequestParam(value = "url", defaultValue = YeeyiHelper.YeeyiRentingURL) String url) throws PageNotFoundException {
        try {
            Document document = SpiderUtil.crawlPage(url);

            List<RentingVO> items = YeeyiHelper.toRentingVO(document);

            return items;

        } catch (IOException e) {
            throw new PageNotFoundException();
        }
    }

    @RequestMapping("/getYeeyiPretty")
    @ResponseStatus(HttpStatus.OK)
    public String getYeeyiPretty(@RequestParam(value = "url", defaultValue = YeeyiHelper.YeeyiRentingURL) String url) throws PageNotFoundException {
        List<RentingVO> items = crawlYeeyi(url);

        return items.stream().map(item -> item.toString()).collect(Collectors.joining("<br>"));
    }
}
