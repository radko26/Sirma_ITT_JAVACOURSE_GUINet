package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

/**
 * Generates sport related news.
 * 
 * @author Radoslav
 */
public class SportNews extends News {

	public SportNews() {
		type = 1;
	}

	@Override
	public String topic() {
		return "Sport news";
	}

	@Override
	public String body() {
		return "Generates news about events in the sport world.";
	}

}
