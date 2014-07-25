package com.sirma.itt.javacourse.guinetwork.reversemsg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class Client extends SwingWorker<Void, Void> {

	private JTextArea logField;
	private JTextArea msgField;
	private Socket server;
	private AtomicBoolean sendButtonPressed;
	private BufferedReader inputFromServer;
	private PrintWriter outputToServer;
	private String msgFromServer = "";

	/**
	 * Initialises the fields.
	 * 
	 * @param msgField
	 *            The field where the message to the server is written.
	 * @param sendButtonPressed
	 *            AtomicBoolean that is set to true if the send button has been
	 *            pressed.
	 * @param logField
	 *            The field where all operations are logged.
	 */
	public Client(JTextArea msgField, AtomicBoolean sendButtonPressed,
			JTextArea logField) {
		this.sendButtonPressed = sendButtonPressed;
		this.msgField = msgField;
		this.logField = logField;
	}

	/**
	 * Tries to find server running on the given port range.
	 * 
	 * @return The connected socket.
	 * @throws IOException
	 *             If there is no such server.
	 */
	private Socket find() throws IOException {
		for (int i = 7000; i <= 7020; i++) {
			try {
				return new Socket("localhost", i);
			} catch (IOException e) {
				continue;
			}
		}
		throw new IOException("No server found");
	}

	@Override
	protected Void doInBackground() throws IOException {
		server = find();
		inputFromServer = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		outputToServer = new PrintWriter(server.getOutputStream(), true);
		while (true) {
			if (inputFromServer.ready()) {
				msgFromServer = inputFromServer.readLine();
				logField.append("\n" + msgFromServer);
			}

			if (sendButtonPressed.get()) {
				if (msgField.getText().equals(".")) {
					msgField.setText("");
					logField.append("\nDisconnected");
					inputFromServer.close();
					outputToServer.close();
					server.close();
					break;
				}
				outputToServer.println(msgField.getText());
				msgField.setText("");
				sendButtonPressed.set(false);
			}
		}
		return null;
	}

	@Override
	protected void done() {
		try {
			get();
		} catch (InterruptedException | ExecutionException e) {
			logField.append(e.getMessage());
		}

	}
}
