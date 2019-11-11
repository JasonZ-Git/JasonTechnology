package org.jason.renting.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.jason.renting.dao.RentRecordDO;
import org.jason.renting.services.YeeyiServiceImpl;
import org.jason.renting.utils.YeeyiUtil;
import org.jason.util.WebCrawlUtil;
import org.jason.util.exception.PageNotFoundException;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentingController {

  private static final String DEFAULT_YEEYI_PAGE = "http://www.yeeyi.com/forum/index.php?app=forum&act=display&fid=142&rcity1=1";
  
  @Autowired
  private YeeyiServiceImpl yeeyiService;
  
  @RequestMapping("/crawlYeeyi")
  public String crawlYeeyi(@RequestParam(value = "url", defaultValue = DEFAULT_YEEYI_PAGE) String url) throws PageNotFoundException {
    try {
      Document document = WebCrawlUtil.crawlPage(url);
      
      List<RentingVO> items = YeeyiUtil.toRentingVO(document);
      
      return items.stream().map(item -> item.toString()).collect(Collectors.joining("<br>"));
      
    } catch (IOException e) {
      throw new PageNotFoundException();
    }
  }
  
  @RequestMapping("/list")
  public String listYeeyi() throws PageNotFoundException {
    List<RentRecordDO> records =  yeeyiService.getRentRecords();
    
    return records.stream().sorted((o1, o2) -> o2.getReleaseDate().compareTo(o1.getReleaseDate())).map(item -> RentingVO.from(item)).map(item -> item.toString()).collect(Collectors.joining("<br>"));
  }

  @RequestMapping("/")
  public String defaultPage() {
    return "<p>Please enter page url to be crawled:</p>"

    + "<p>Crawl only one page:</p>" 
    
    + "<form method='GET' action='crawlYeeyi'><input type='text' name='url' value/></br><button type='submit'>Submit</button></form>"
    
    + "<p> Default URL is: " + DEFAULT_YEEYI_PAGE + "</p>"
   
    + "<br />"
    
    + "<p>Crawl all related pages (maximum number is 50) </p>" 
    
    + "<form method='GET' action='crawlPages'><input type='text' name='url'/></br><button type='submit'>Submit</button></form>"

    + "<p> Default URL is: " + DEFAULT_YEEYI_PAGE + "</p>";
  }
}