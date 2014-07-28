package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Main that starts both the news generator thread and the running broadcaster
 * service.
 * 
 * @author Radoslav
 */
public class Server {

	private static AtomicBoolean running = new AtomicBoolean(true);

	public static void main(String[] args) throws IOException {

		BlockingQueue<News> listOfNews = new ArrayBlockingQueue<News>(10);
		new NewsGeneratorThread(listOfNews).start();
		new ServerThread("Server", running, listOfNews).start();
	}
}
