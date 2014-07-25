package com.sirma.itt.javacourse.guinetwork.serverclientconn;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * Creates instance of {@link ServerSocket} and use it.
 * 
 * @author radoslav
 */
public class Server {

	private ServerSocket server;
	private Socket client;
	private JTextArea logField;

	/**
	 * Initialises the GUI field which the server is logging its operations and
	 * the server itself.
	 * 
	 * @param logField
	 *            The GUI field.
	 * @throws IOException
	 *             If no available port has been found.
	 */
	public Server(JTextArea logField) throws IOException {
		server = create();
		this.logField = logField;
	}

	/**
	 * Starts listening for inbound connection from the client. After a client
	 * has connected it sends a message.
	 * 
	 * @throws IOException
	 *             In case of event that interrupts the listening process.
	 */
	public void listen() throws IOException {
		while (true) {
			try {
				client = server.accept();
			} catch (IOException e) {
				close();
				System.exit(0);
			}
			logField.append("\n Client with id " + client.getInetAddress());
			try {
				PrintWriter serverToClient = new PrintWriter(
						client.getOutputStream());
				serverToClient.println("Hello!" + System.currentTimeMillis());
				logField.append("\n Message sent to client:"
						+ client.getInetAddress());
				serverToClient.close();
			} finally {
				client.close();
			}
			logField.append("\n Client with ip<" + client.getInetAddress()
					+ "> disconnected.");
		}// TODO try{}finally{} and System.exit(0)
	}

	/**
	 * Tries to use port in the range 7000-7020
	 * 
	 * @return Instance of {@link ServerSocket} using the available port in the
	 *         range.
	 * @throws IOException
	 *             If no available port was found.
	 */
	private ServerSocket create() throws IOException {
		for (int i = 7000; i <= 7020; i++) {
			try {
				return new ServerSocket(i);
			} catch (IOException e) {
				continue;
			}
		}
		throw new IOException("no available ports found");
	}

	/**
	 * Closes connections.
	 */
	public void close() {
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {

			}
		}
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {

			}
		}
	}

}
