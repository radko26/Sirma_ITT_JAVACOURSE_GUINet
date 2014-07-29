package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates multiple multicast groups and broadcast the received data to the
 * appropriate groups.
 * 
 * @author Radoslav
 */
public class ServerThread extends Thread {
	private static final Logger LOG = LoggerFactory
			.getLogger(ServerThread.class);
	private DatagramSocket server;
	private String msg = "Test";
	private BlockingQueue<News> listOfNews;
	private News news;
	private AtomicBoolean running;

	/**
	 * Initialises the thread.
	 * 
	 * @param name
	 *            The name of the thread.
	 * @param running
	 *            The status of the server.
	 * @param listOfNews
	 *            The queue of generated news.
	 * @throws IOException
	 *             In case of {@link IOException} that occurred during server
	 *             communication to client..
	 */
	public ServerThread(String name, AtomicBoolean running,
			BlockingQueue<News> listOfNews) throws IOException {
		super(name);
		server = new DatagramSocket(7000);
		this.listOfNews = listOfNews;

		this.running = running;
	}

	@Override
	public void run() {
		LOG.info("Server started");
		while (running.get()) {
			news = listOfNews.poll();
			try {
				msg = news.topic();
			} catch (NullPointerException e) {
				throw new NullPointerException("Null in news.topic()");
			}
			String host = Mediator.choose(news);

			byte[] buffer = new byte[256];

			buffer = msg.getBytes();
			InetAddress groupAddress = null;
			try {
				groupAddress = InetAddress.getByName(host);
			} catch (UnknownHostException e1) {
				LOG.error(e1.getMessage());
			}

			DatagramPacket serverPacket = new DatagramPacket(buffer,
					buffer.length, groupAddress, 7001);// TODO RENAME
			try {
				server.send(serverPacket);
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}

		}
		server.close();

	}
}
