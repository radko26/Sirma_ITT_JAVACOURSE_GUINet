package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Separate thread that process the client.
 * 
 * @author Radoslav
 */
public class ServerHandleClientThread extends Thread {
	private ArrayList<ServerHandleClientThread> clientsList;
	private PrintWriter writer;
	private Socket client;

	/**
	 * Initialises the client socket and other connected clients.
	 * 
	 * @param client
	 *            The client socket
	 * @param clientsList
	 *            Other client threads
	 */
	public ServerHandleClientThread(Socket client,
			ArrayList<ServerHandleClientThread> clientsList) {
		this.clientsList = clientsList;
		this.client = client;
	}

	@Override
	public void run() {

		try {
			writer = new PrintWriter(client.getOutputStream(), true);
			writer.println("Hello client widh id:" + clientsList.size());
		} catch (IOException e) {
		}
		synchronized (this) {
			for (ServerHandleClientThread clientsRunning : clientsList) {
				if (clientsRunning != this) {
					clientsRunning.writer.println("Client with id:"
							+ clientsList.size() + " has connected");
				}
			}
		}
	}

}
