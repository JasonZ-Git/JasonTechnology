# Question 1:
Can you please provide some Unit Test for this class:
https://github.com/JasonZ-Git/JasonTechnology/blob/master/jason-util/src/main/java/org/jason/util/algorithm/FabonacciUtil.java

## Answer
```Java
import org.junit.Test;
import static org.junit.Assert.*;

public class FabonacciUtilTest {
    
    @Test
    public void testComputeFabonacciNumberForNegativeIndex() {
        assertEquals(-1, FabonacciUtil.computeFabonacciNumber(-1));
        assertEquals(-1, FabonacciUtil.computeFabonacciNumber(Integer.MIN_VALUE));
    }
    
    @Test
    public void testComputeFabonacciNumberForZeroIndex() {
        assertEquals(0, FabonacciUtil.computeFabonacciNumber(0));
    }
    
    @Test
    public void testComputeFabonacciNumberForPositiveIndex() {
        assertEquals(1, FabonacciUtil.computeFabonacciNumber(1));
        assertEquals(1, FabonacciUtil.computeFabonacciNumber(2));
        assertEquals(2, FabonacciUtil.computeFabonacciNumber(3));
        assertEquals(3, FabonacciUtil.computeFabonacciNumber(4));
        assertEquals(5, FabonacciUtil.computeFabonacciNumber(5));
        assertEquals(8, FabonacciUtil.computeFabonacciNumber(6));
        assertEquals(13, FabonacciUtil.computeFabonacciNumber(7));
        assertEquals(21, FabonacciUtil.computeFabonacciNumber(8));
        assertEquals(34, FabonacciUtil.computeFabonacciNumber(9));
    }
}
```

# Question 2:
Can you please write some unit test for method getFabonacciSeries:
(read code provided)

## Answer:
```Java
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;

public class FabonacciUtilTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFabonacciSeriesForZeroSize() {
        FabonacciUtil.getFabonacciSeries(0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFabonacciSeriesForNegativeSize() {
        FabonacciUtil.getFabonacciSeries(-1);
    }
    
    @Test
    public void testGetFabonacciSeriesForSizeOne() {
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, FabonacciUtil.getFabonacciSeries(1));
    }
    
    @Test
    public void testGetFabonacciSeriesForSizeTwo() {
        List<Integer> expected = Arrays.asList(1, 1);
        assertEquals(expected, FabonacciUtil.getFabonacciSeries(2));
    }
    
    @Test
    public void testGetFabonacciSeriesForSizeTen() {
        List<Integer> expected = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        assertEquals(expected, FabonacciUtil.getFabonacciSeries(10));
    }
}
```

# Question 3:
Can you please write some unit tests for this method:
```Java
public static int getFabonacciNumber_1(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException("index must be positive numbers");
    }

    if (number == 1 || number == 2) {
      return 1;
    }

    return getFabonacciNumber_1(number - 1) + getFabonacciNumber_1(number - 2) + 1;
  }
```
  
## Answer:
```Java
import org.junit.Test;
import static org.junit.Assert.*;

public class FabonacciUtilTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFabonacciNumber_1ForZeroIndex() {
        FabonacciUtil.getFabonacciNumber_1(0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFabonacciNumber_1ForNegativeIndex() {
        FabonacciUtil.getFabonacciNumber_1(-1);
    }
    
    @Test
    public void testGetFabonacciNumber_1ForIndexOne() {
        assertEquals(1, FabonacciUtil.getFabonacciNumber_1(1));
    }
    
    @Test
    public void testGetFabonacciNumber_1ForIndexTwo() {
        assertEquals(1, FabonacciUtil.getFabonacciNumber_1(2));
    }
    
    @Test
    public void testGetFabonacciNumber_1ForIndexThree() {
        assertEquals(3, FabonacciUtil.getFabonacciNumber_1(3));
    }
    
    @Test
    public void testGetFabonacciNumber_1ForIndexTen() {
        assertEquals(423, FabonacciUtil.getFabonacciNumber_1(10));
    }
}
```

# Question 
Can you please write some unit tests for these 2 methods:
```Java
  public static String singleQuoted(@Nonnull String source) {
    Objects.requireNonNull(source);

    return String.format(SINGLE_QUOTED_FORMAT, source);
  }


  public static String squareBracketed(@Nonnull String source) {
    Objects.requireNonNull(source);

    return String.format(SQUARE_BRACKET_FORMAT, source);
  }
```

## Answer
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilTest {
    
    @Test(expected = NullPointerException.class)
    public void testSingleQuotedForNull() {
        StringUtil.singleQuoted(null);
    }
    
    @Test
    public void testSingleQuotedForEmptyString() {
        assertEquals("''", StringUtil.singleQuoted(""));
    }
    
    @Test
    public void testSingleQuotedForNonEmptyString() {
        assertEquals("'Hello, world!'", StringUtil.singleQuoted("Hello, world!"));
    }
    
    @Test(expected = NullPointerException.class)
    public void testSquareBracketedForNull() {
        StringUtil.squareBracketed(null);
    }
    
    @Test
    public void testSquareBracketedForEmptyString() {
        assertEquals("[]", StringUtil.squareBracketed(""));
    }
    
    @Test
    public void testSquareBracketedForNonEmptyString() {
        assertEquals("[Hello, world!]", StringUtil.squareBracketed("Hello, world!"));
    }
}


