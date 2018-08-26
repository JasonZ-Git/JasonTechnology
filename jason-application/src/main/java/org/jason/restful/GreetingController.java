package org.jason.restful;

import org.jason.dao.Greeting;
import org.jason.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Autowired
	private GreetingService greetingService;

	@RequestMapping(value = "/greetingMsg", method = RequestMethod.GET)
	public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "world") String name) {
		Assert.notNull(name, "Name should not be null");
		
		return greetingService.getMessage();
	}
	
	@RequestMapping(value = "/greetingString", method = RequestMethod.GET)
	public String getGreetingMessage(@RequestParam(value = "name", defaultValue = "world") String name) {
		Assert.notNull(name, "Name should not be null");
		
		return greetingService.getMessage().getContent();
	}
}