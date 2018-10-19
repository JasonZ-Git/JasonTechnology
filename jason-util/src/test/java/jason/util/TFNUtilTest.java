package jason.util;

import java.util.Arrays;
import java.util.Collection;

import org.jason.util.TFNUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TFNUtilTest {

    @Parameter(0)
    public  String tfnToBeChecked;
    
    @Parameter(1)
    public  Boolean result;
    
    
    @Parameters
    public static Collection<Object[]> data(){
        Object[][] dataArray = new Object[][] {{"123456782", Boolean.TRUE}, {"123456783", Boolean.FALSE}};
        
        return Arrays.asList(dataArray);
    }
    
    @Test
    public void testTFN(){
       Assert.assertEquals(result, (Boolean)TFNUtil.isValidTFN(tfnToBeChecked));
    }
}
