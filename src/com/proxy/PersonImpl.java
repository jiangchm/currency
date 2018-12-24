package com.proxy;

public class PersonImpl implements Person {

	@Override
	public String eat(String something) {
		return "eating " + something;
	}

}
