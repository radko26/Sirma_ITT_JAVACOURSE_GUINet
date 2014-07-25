package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * Connects to server and listen for messages.
 * 
 * @author Radoslav
 */
public class Client {

	private Socket server;
	private JTextArea logField;

	/**
	 * Initialises the field where logs are kept.
	 * 
	 * @param logField
	 */
	public Client(JTextArea logField) {
		this.logField = logField;
	}

	/**
	 * Tries to connect to the server and read data from it.
	 * 
	 * @throws IOException
	 */
	public void connect() throws IOException {
		server = findServer();
		String message;
		BufferedReader inputFromServer = new BufferedReader(
				new InputStreamReader(server.getInputStream()));
		while (true) {
			if ((message = inputFromServer.readLine()) != null) {
				logField.append("\n" + message);
			} else {
				inputFromServer.close();
				server.close();
				throw new NoSocketException("Server has been stopped");
			}
		}
	}

	/**
	 * Checks if there is available server in the port range.
	 * 
	 * @return Socket connected to the server.
	 * @throws IOException
	 *             If no server was found.
	 */
	private Socket findServer() throws IOException {
		for (int i = 7000; i <= 7020; i++) {
			try {
				return new Socket("localhost", i);
			} catch (IOException e) {
				continue;
			}
		}
		throw new IOException("No server found");
	}

}
