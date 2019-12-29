package org.jason.task;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.commons.lang3.StringUtils;
import org.jason.util.WebCrawlUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@ThreadSafe
public class MonicaLoveShopping {

	private static int Two_Dollor_ToPrice = 5;

	public static void main(String[] args) throws InterruptedException, IOException {

		crawlMyDealAndReport();

	}

	public static List<String> crawlMyDealPageByCategory() throws IOException {

		return new MydealSpider().crawl();
	}

	public static void crawlMyDealAndReport() throws IOException, InterruptedException {

		List<String> urls = crawlMyDealPageByCategory();

		Map<String, Integer> pagesWithPrice = getAllPagesWithLowPrice(urls);

		report(pagesWithPrice);

	}

	public static void report(Map<String, Integer> pageWithPrices) throws InterruptedException {

		Map<String, Integer> result = new HashMap<>();

		for (Entry<String, Integer> current : pageWithPrices.entrySet()) {
			if (!result.keySet().contains(current.getKey())) {
				System.out.println("########## New Deal Found #############");

				System.out.println("$" + current.getValue() + " found on page " + current.getKey());

				result.put(current.getKey(), current.getValue());
			}
		}
	}

	public static Map<String, Integer> getAllPagesWithLowPrice(List<String> urls) throws InterruptedException {

		List<String> potentialPages = new ArrayList<>();
		Map<String, Integer> potentialPagesMap = new ConcurrentHashMap<String, Integer>();

		ExecutorService threadSerivices = Executors.newFixedThreadPool(100);

		List<Callable<String>> callableTasks = new ArrayList<>();

		for (final String currentPage : urls) {
			Callable<String> callableTask = () -> {
				List<Integer> prices = selectPriceOfPage(currentPage);

				if (prices.isEmpty())
					return "";

				potentialPages.add(currentPage);
				potentialPagesMap.put(currentPage, prices.get(0));

				return "Task's execution";
			};

			callableTasks.add(callableTask);
		}

		threadSerivices.invokeAll(callableTasks);

		threadSerivices.shutdown();

		return potentialPagesMap;
	}

	public static Map<String, Integer> sortMap(Map<String, Integer> sourceMap) {
		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				return v1.compareTo(v2);
			}
		};

		// Sort method needs a List, so let's first convert Set to List in Java
		List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(sourceMap.entrySet());

		// sorting HashMap by values using comparator
		Collections.sort(listOfEntries, valueComparator);

		LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());

		// copying entries from List to Map
		for (Entry<String, Integer> entry : listOfEntries) {
			sortedByValue.put(entry.getKey(), entry.getValue());
		}

		return sortedByValue;
	}

	public static List<Integer> selectPriceOfPage(String sourePageURL) throws IOException {
		Document myDealPage = WebCrawlUtil.crawlPage(sourePageURL);

		String oneDollorSuperDealSelector = "[itemprop=price]";

		Elements oneDollorSuperResult = myDealPage.select(oneDollorSuperDealSelector);

		List<Integer> prices = new ArrayList<>();

		for (Element current : oneDollorSuperResult) {
			Double value = Double.valueOf(current.text());

			if (value.intValue() <= Two_Dollor_ToPrice) {
				String originalPrice = current.parent().select(".rrp").text().replace("RRP $", "").trim();

				if (Double.valueOf(originalPrice) > 100) {
					System.out.println("################################################");
					System.out.println("Super Deal found " + sourePageURL);
					System.out.println("################################################");
				}

				prices.add(value.intValue());
			}
		}

		//
		Elements result = myDealPage.select(".numericSpan .mainPriceSpan");

		for (Element current : result) {
			String dotNumber = current.nextElementSibling().text();
			current.nextElementSibling().text();
			if (StringUtils.isNumeric(current.text()) && StringUtils.isBlank(dotNumber.trim())) {
				prices.add(Integer.valueOf(current.text()));
			}
		}

		return prices;
	}
	
	public static interface PageSpider {
		public List<String> crawl() throws IOException;
	}
	
	public static class MydealSpider implements PageSpider {
		
		private static final String MY_DEAL_CATEGORIES = "https://www.mydeal.com.au/categories";

		private static final String MY_DEAL_CATEGORY_CLASS = ".category-panel-body .category-item";

		private static final String MY_DEAL_SEARCH = "https://www.mydeal.com.au/%s?sort=recommended&fromPrice=1&toPrice=%d&tagId=2733&filter=0&filteredTagId=0&pageSize=48&usr=1";

		
		@Override
		public List<String> crawl() throws IOException {

			Document myDealPage = WebCrawlUtil.crawlPage(MY_DEAL_CATEGORIES);

			Elements newSearchEles = myDealPage.select(MY_DEAL_CATEGORY_CLASS);

			// Using Set to remove duplication automatically.
			Set<String> urls = new HashSet<>();

			for (Element current : newSearchEles) {

				String hrefCategory = current.child(0).getElementsByAttribute("href").attr("href");

				String url = String.format(MY_DEAL_SEARCH, hrefCategory, priceLimit);

				urls.add(url);
			}

			return new ArrayList<String>(urls);
		
		}

		private BigDecimal priceLimit = new BigDecimal(Integer.MAX_VALUE);
		public MydealSpider setPriceLimit(BigDecimal priceLimit) {this.priceLimit = priceLimit; return this;}
	}
	
	public static enum WebSite {
		YeeYi,
		MyDeal;
	}

}
