package algorithm;

import java.util.stream.IntStream;

import org.junit.Test;

import java.util.Objects;

public class DailyTest {

    @Test
    public void test() {
        long charNumber = IntStream.rangeClosed(0, 0x10ffff).mapToObj(Character.UnicodeBlock::of).filter(Objects::nonNull).distinct().count();

        IntStream.rangeClosed(0, 0x10ffff).mapToObj(Character.UnicodeBlock::of).filter(Objects::nonNull).distinct().forEach(System.out::println);
        
        System.out.println(charNumber);
    }
}
