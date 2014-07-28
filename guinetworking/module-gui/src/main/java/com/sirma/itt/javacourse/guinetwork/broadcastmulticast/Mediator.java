package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

/**
 * Receives a piece of news and choose to which channel they should be sent.
 * 
 * @author Radoslav
 */
public class Mediator {

	public static String choose(News news) {
		if (news.type == 1) {
			return "230.0.0.1";
		} else if (news.type == 2) {
			return "230.0.0.2";
		} else {
			throw new IllegalArgumentException("no such type");
		}
	}

}
