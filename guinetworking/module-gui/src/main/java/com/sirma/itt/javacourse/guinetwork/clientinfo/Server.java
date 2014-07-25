package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

/**
 * Socket server that enables clients to connect and transfer data. TODO
 * 
 * @author Radoslav
 */
public class Server {

	private ServerSocket server;
	private Socket client;
	private JTextArea logField;
	private volatile ArrayList<ServerHandleClientThread> clientsList = new ArrayList<ServerHandleClientThread>();

	/**
	 * Initialises the GUI field where logs are kept.
	 * 
	 * @param logField
	 *            The text field.
	 */
	public Server(JTextArea logField) {
		this.logField = logField;
	}

	/**
	 * Accepts client connections and starts new thread for each client.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		server = create();
		while (true) {
			client = server.accept();
			logField.append("\nNew client with id:" + (clientsList.size() + 1)
					+ " has connected");
			clientsList.add(new ServerHandleClientThread(client, clientsList));
			clientsList.get(clientsList.size() - 1).start();
		}

	}

	/**
	 * Closes all connections.
	 */
	public void close() {
		try {
			server.close();
		} catch (IOException e) {

		}
		try {
			client.close();
		} catch (IOException e) {
		}

		System.exit(0);
	}

	/**
	 * Tries to create socket server on the given port range.
	 * 
	 * @return Ready instance of {@link ServerSocket}
	 * @throws IOException
	 *             If there is no available port.
	 */
	private ServerSocket create() throws IOException {
		for (int i = 7000; i <= 7020; i++) {
			try {
				return new ServerSocket(i);
			} catch (IOException e) {
				continue;
			}
		}
		throw new IOException("No available port");
	}

}
