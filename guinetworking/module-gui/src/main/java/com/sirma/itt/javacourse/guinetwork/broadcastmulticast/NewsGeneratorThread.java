package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * Class that produces two type in a random way and put them into queue for
 * consumption.
 * 
 * @author Radoslav
 */
public class NewsGeneratorThread extends Thread {

	private final BlockingQueue<News> listOfNews;

	public NewsGeneratorThread(BlockingQueue<News> listOfNews) {
		this.listOfNews = listOfNews;
	}

	@Override
	public void run() {

		while (true) {
			int choice = (int) (Math.random() * 2 + 1);
			try {
				listOfNews.put(new NewsFactory(choice).generate());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
