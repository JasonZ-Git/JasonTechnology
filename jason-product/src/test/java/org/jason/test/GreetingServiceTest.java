package org.jason.test;

import org.jason.dao.Greeting;
import org.jason.dao.GreetingDAO;
import org.jason.service.GreetingService;
import org.jason.service.GreetingServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {

	@InjectMocks
	private GreetingService greetingService = new GreetingServiceImpl();

	@Mock
	private GreetingDAO greetingDAO;

	@Test
	public void testDemo() {
		
		Mockito.when(greetingDAO.getGreeting()).thenReturn(new Greeting(1234l, "Hello World"));
		
		Greeting greeting = greetingService.getMessage();

		Assert.assertEquals(greeting.getId(), 1234l);
	}
}
