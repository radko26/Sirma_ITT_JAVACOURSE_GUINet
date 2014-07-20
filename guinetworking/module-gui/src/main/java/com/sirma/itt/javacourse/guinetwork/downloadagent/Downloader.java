package com.sirma.itt.javacourse.guinetwork.downloadagent;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * 
 * 
 * @author radoslav
 */
public class Downloader {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		
		new DownloadAgent().downloadFile("http://www.petartattoo.com/storage1/images/catalog/item153/View.jpg");
		
		/*
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setVisible(true);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setLocation(200, 200);
				
				final JProgressBar progressBar = new JProgressBar();
				progressBar.setValue(0);
				frame.add(progressBar);
				frame.pack();
				

				new SwingWorker<Integer, Integer>() {

					@Override
					protected Integer doInBackground() throws Exception {
						for(int i =0;i<10;i++){
							Thread.sleep(1000);
							publish(i*10);
						}
						return null;
					}
					@Override
					protected void done() {
						progressBar.setValue(100);
					}
					@Override
					protected void process(List<Integer> chunks) {
						int mostRecentValue = chunks.get(chunks.size()-1);
						progressBar.setValue(mostRecentValue);
					} 
					
				}.execute();	
			}
		}); 
		

		*/
	}
}
