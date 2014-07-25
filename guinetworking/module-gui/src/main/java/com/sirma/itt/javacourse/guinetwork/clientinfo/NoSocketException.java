package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.net.SocketException;

/**
 * Custom exception that signalises there is no server.
 * 
 * @author Radoslav
 */
public class NoSocketException extends SocketException {

	/**
	 * Inherits the constructor.
	 * 
	 * @param msg
	 */
	public NoSocketException(String msg) {
		super(msg);
	}

}
