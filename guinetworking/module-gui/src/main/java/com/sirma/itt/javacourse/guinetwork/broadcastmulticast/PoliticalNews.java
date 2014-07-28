package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

/**
 * Generates news about politics etc.
 * 
 * @author Radoslav
 */
public class PoliticalNews extends News {

	/**
	 * Initializes the type of the news.
	 */
	public PoliticalNews() {
		type = 2;
	}

	@Override
	public String topic() {
		return "Politic related news";
	}

	@Override
	public String body() {
		return "Generates political news here";
	}

}
