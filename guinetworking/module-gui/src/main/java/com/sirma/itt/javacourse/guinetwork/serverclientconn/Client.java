package com.sirma.itt.javacourse.guinetwork.serverclientconn;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

/**
 * Searches for running server with port between 7000 and 7020 and connects to
 * it. Receives its message and closes.
 * 
 * @author radoslav
 */
public class Client {

	private static JTextArea logField;

	/**
	 * Initialises the GUI field where it writes down its operations.
	 * 
	 * @param logField
	 */
	public Client(JTextArea logField) {
		this.logField = logField;
	}

	/**
	 * Tries to find appropriate running server in the port range.
	 * 
	 * @return Instance of {@link Socket} using the available port.
	 * @throws IOException
	 *             If no running servers were found.
	 */
	private Socket findServer() throws IOException {
		for (int i = 7000; i <= 7020; i++) {
			try {
				return new Socket("localhost", i);
			} catch (UnknownHostException e) {
				System.out.println("wrong host name");
			} catch (IOException e) {
				continue;
			}
		}
		throw new IOException("No server found");
	}

	/**
	 * Starts the server.
	 * 
	 * @throws IOException
	 *             If no running servers were found.
	 */
	public void start() throws IOException {
		Socket server = findServer();
		logField.append("\nClient connected to" + server.getLocalAddress()
				+ ":" + server.getLocalPort());

		logField.append("\nReading from server's input stream");
		Scanner scan = new Scanner(server.getInputStream());

		logField.append("\nWriting to the console");
		System.out.println(scan.nextLine());

		server.close();
		logField.append("\nClosing client..");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
		System.exit(0);

	}

}
