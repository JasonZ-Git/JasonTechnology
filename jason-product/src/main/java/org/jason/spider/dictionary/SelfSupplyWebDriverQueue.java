package org.jason.spider.dictionary;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

import org.openqa.selenium.WebDriver;

/**
 * This Queue is specially designed for WebDriver - It is possible to extend for every object.
 * <p>
 * It will create a new item(through default constructor) if it hasn't reached the capacity.
 * <p>
 * Seems I should design a WebDriver wrapper which extend and contains a WebDriver inside and also a flag indicate whether it is being used.
 *
 * @author Jason Zhang
 */
@ThreadSafe
public class SelfSupplyWebDriverQueue extends AbstractQueue<WebDriver> {

    private List<WebDriver> innterDriver;

    public SelfSupplyWebDriverQueue(int capacity) {
        //innterDriver = new ArrayList
    }

    @Override
    public boolean offer(WebDriver e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public WebDriver poll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WebDriver peek() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<WebDriver> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
}
