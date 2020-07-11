package jason.util;

import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

public class TempTest {

    @Test
    public void tempTest() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        System.out.println(commonPool);
    }
}
