package org.jason.service;

import org.jason.dao.Greeting;
import org.jason.dao.GreetingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Autowired
	private GreetingDAO greetingDAO;
	
	@Override
	public Greeting getMessage() {
		return greetingDAO.getGreeting();
	}
	
}
