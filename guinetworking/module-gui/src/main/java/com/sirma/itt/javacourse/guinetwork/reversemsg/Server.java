package com.sirma.itt.javacourse.guinetwork.reversemsg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * Creates and runs {@link ServerSocket}, which accept clients' messages and
 * prompt them back reversed.
 * 
 * @author Radoslav
 */
public class Server {

	private ServerSocket server;
	private Socket client;
	private JTextArea logField;
	private PrintWriter writeToClient;
	private BufferedReader readFromClient;
	private String message;

	/**
	 * Initialises the {@link JTextArea} field.
	 * 
	 * @param logField
	 *            The field.
	 */
	public Server(JTextArea logField) {
		this.logField = logField;
	}

	/**
	 * Creates the server and accept clients.
	 * 
	 * @throws IOException
	 *             If {@link IOException} occurs.
	 */
	public void start() throws IOException {
		server = create();
		while (true) {
			client = server.accept();
			logField.append("\nClient connected");

			writeToClient = new PrintWriter(client.getOutputStream(), true);
			readFromClient = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			writeToClient.println("Hello");
			writeToClient.flush();
			logField.append("\nWelcome message sent.");

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						String msg = readClientMessage();
						if (msg == null) {
							break;
						} else {
							writeReversedMessage(msg);
						}

					}
					logField.append("\nClient has disconnected...");
					writeToClient.close();
					try {
						readFromClient.close();
					} catch (IOException e) {

					}
				}

			}).start();

		}

	}

	/**
	 * Read from the client socket input stream.
	 * 
	 * @return The line read.
	 */
	private String readClientMessage() {
		while (true) {
			try {
				message = readFromClient.readLine();
				logField.append("\nMessage from client has been read.");
				return message;
				// }
			} catch (IOException e) {
				return null;
			}
		}
	}

	/**
	 * Writes back but reversed the message to the client.
	 * 
	 * @param msg
	 *            The message which was previously received.
	 */
	private void writeReversedMessage(String msg) {
		logField.append("\nThe message has been reversed and resend to the client.");
		StringBuffer msgBuffer = new StringBuffer();
		msgBuffer.append(msg);
		writeToClient.println("The reverse of " + msg + " is "
				+ msgBuffer.reverse());
	}

	/**
	 * Tries to create {@link ServerSocket} instance on the given port.
	 * 
	 * @return The instance if there was available port.
	 * @throws IOException
	 *             In case of no available port.
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
