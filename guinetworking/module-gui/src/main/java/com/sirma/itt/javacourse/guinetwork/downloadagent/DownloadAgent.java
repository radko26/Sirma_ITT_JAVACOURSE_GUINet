package com.sirma.itt.javacourse.guinetwork.downloadagent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;


/**
 * 
 * 
 * @author radoslav
 */
public class DownloadAgent{

	private OutputStream output;
	private int status = 0;
	private boolean ready = false;
	private URLConnection connection;

	/**
	 * 
	 * @param sourceURL
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void downloadFile(String sourceURL) throws IOException,
			InterruptedException {
		String[] path = sourceURL.split("/");

		URL url = new URL(sourceURL);

		connection = url.openConnection();
		System.out.println(connection.getContentLengthLong());
		InputStream input = connection.getInputStream();
		output = new FileOutputStream(new File(path[path.length - 1]));

		byte[] buffer = new byte[1024];
		int totalBytes = -1;
		while ((totalBytes = input.read(buffer)) != -1) {
			output.write(buffer, 0, totalBytes);
			
		}
		ready = true;
		input.close();
		output.close();
	}

	public int getStatus() {
		return status;
	}

	public boolean isReady() {
		return ready;
	}

}
