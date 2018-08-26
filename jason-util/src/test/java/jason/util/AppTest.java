package jason.util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AppTest {
	@Before
	public void before() {
		System.out.println("@Before");
	}

	@Test
	public void test() {
		System.out.println("@Test");
		assertEquals(5 + 5, 10);
	}

	@Ignore
	@Test
	public void testIgnore() {
		System.out.println("@Ignore");
	}

	@Test(timeout = 50)
	public void testTimeout() {
		System.out.println("@Test(timeout = 50)");
		assertEquals(5 + 5, 10);
		Assert.assertSame("", "");
	}

	@Test(expected = ArithmeticException.class)
	public void testExpected() {
		System.out.println("@Test(expected = Exception.class)");
		throw new ArithmeticException();
	}

	@After
	public void after() {
		System.out.println("@After");
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("@BeforeClass");
	};

	@AfterClass
	public static void afterClass() {
		System.out.println("@AfterClass");
	};
};
