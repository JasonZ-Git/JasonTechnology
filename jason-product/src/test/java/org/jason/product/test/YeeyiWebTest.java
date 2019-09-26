package org.jason.product.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;
import org.jason.renting.controller.RentingVO;
import org.jason.renting.utils.YeeyiUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

public class YeeyiWebTest {

  @Test
  public void testParse() throws IOException, URISyntaxException {
    InputStream in = this.getClass().getResourceAsStream("yeeyi.html");
    BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("GB18030")));
    String htmlContent = reader.lines().collect(Collectors.joining());
    Document document = Jsoup.parse(htmlContent);

    List<RentingVO> items = YeeyiUtil.toRentingVO(document);

    Assert.assertTrue(items != null);
    Assert.assertTrue(items.size() > 10);
  }
}
