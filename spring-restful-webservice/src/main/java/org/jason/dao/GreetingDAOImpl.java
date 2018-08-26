package org.jason.dao;

import org.springframework.stereotype.Repository;

@Repository
public class GreetingDAOImpl implements GreetingDAO {

	@Override
	public Greeting getGreeting() {
		return new Greeting(12345l, "Hello World=");
	}

}
