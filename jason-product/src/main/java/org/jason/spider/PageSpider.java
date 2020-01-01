package org.jason.spider;

import java.io.IOException;
import java.util.List;

public interface PageSpider<T> {
  public List<T> crawl() throws IOException;
}
