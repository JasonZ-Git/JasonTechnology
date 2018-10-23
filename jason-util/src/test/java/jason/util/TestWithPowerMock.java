/**
 * 
 */
package jason.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test static method
 * 
 * @author Jason Zhang
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(IdGenerator.class)
public class TestWithPowerMock {
	@Test
	public void demoStaticMethodMocking() throws Exception {

		PowerMockito.mockStatic(IdGenerator.class);
		PowerMockito.when(IdGenerator.generateNewId()).thenReturn(2L);

		long value = new ClassUnderTest().getValue();
		Assert.assertEquals(value, 2L);
	}
}

class IdGenerator {

	public static long generateNewId() {
		return 1L;
	}

}

class ClassUnderTest {
	public long getValue() {
		return IdGenerator.generateNewId();
	}
}