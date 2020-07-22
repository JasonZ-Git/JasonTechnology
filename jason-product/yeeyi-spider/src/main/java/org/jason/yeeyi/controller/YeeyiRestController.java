package org.jason.yeeyi.controller;

import org.jason.util.SpiderUtil;
import org.jason.util.exception.PageNotFoundException;
import org.jason.yeeyi.datamodel.RentingVO;
import org.jason.yeeyi.util.YeeyiHelper;
import org.jsoup.nodes.Document;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
