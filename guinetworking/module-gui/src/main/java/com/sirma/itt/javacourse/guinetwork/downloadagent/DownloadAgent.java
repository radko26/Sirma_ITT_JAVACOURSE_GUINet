package com.sirma.itt.javacourse.guinetwork.downloadagent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * Swing thread that downloads file.
 * 
 * @author radoslav
 */
public class DownloadAgent extends SwingWorker<BigDecimal, Void> {

	private OutputStream output;
	private URLConnection connection;
	private String sourceURL;
	private String fileSavePath;
	private int totalBytes = -1;
	private JLabel errorLog;
	private InputStream input;
	private JButton saveButton;

	/**
	 * Initialises resources that will be used for downloading the file.
	 * 
	 * @param sourceURL
	 *            The source url.
	 * @param fileSavePath
	 *            The path where the downloaded file will be saved;
	 * @param errorLog
	 *            The GUI component that will display any errors.
	 * @param saveButton
	 */
	public DownloadAgent(String sourceURL, String fileSavePath,
			JLabel errorLog, JButton saveButton) {
		this.sourceURL = sourceURL;
		this.fileSavePath = fileSavePath;
		this.errorLog = errorLog;
		this.saveButton = saveButton;
	}

	/**
	 * Creates connection the the source server and copies the file content.
	 * 
	 * @param sourceURL
	 *            The source URL.
	 * @throws IOException
	 *             In case of any input/output errors.
	 * @throws MalformedURLException
	 *             In case of incorrect URL.
	 */
	private void downloadFile(String sourceURL) throws IOException,
			MalformedURLException {
		String[] path = sourceURL.split("/");
		URL url;
		try {
			url = new URL(sourceURL);
		} catch (MalformedURLException e) {
			throw new MalformedURLException("Incorrect URL");
		}

		try {
			connection = url.openConnection();
		} catch (IOException e) {
			throw new IOException("Error loading the url");
		}
		BigDecimal length = new BigDecimal(Long.toString(connection
				.getContentLengthLong()));

		try {
			input = connection.getInputStream();
		} catch (IOException e) {
			throw new IOException("Error downloading");
		}
		output = new FileOutputStream(new File(fileSavePath + "/"
				+ path[path.length - 1]));
		byte[] buffer = new byte[1024];

		BigDecimal currentTransferred = new BigDecimal("0");
		while ((totalBytes = input.read(buffer)) > 0) {
			output.write(buffer, 0, totalBytes);
			currentTransferred = currentTransferred.add(new BigDecimal(
					totalBytes));
			setProgress(currentTransferred.divide(length, 2)
					.multiply(new BigDecimal(100)).intValue());
		}
		input.close();
		output.close();
	}

	@Override
	protected BigDecimal doInBackground() throws IOException {
		try {
			downloadFile(sourceURL);
		} catch (Exception e) {

			throw new IOException(e.getMessage());
		}
		return new BigDecimal(totalBytes);
	}

	@Override
	protected void done() {
		try {
			get();
			errorLog.setText("Completed");
		} catch (InterruptedException | ExecutionException e) {
			errorLog.setText(e.getMessage());
		} finally {
			saveButton.setEnabled(true);
		}

	}

}
