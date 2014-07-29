package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client that connects to a multicast group and listen for data.
 * 
 * @author Radoslav
 */
public class Client {
	private static final Logger LOG = LoggerFactory.getLogger(Client.class);
	private static MulticastSocket socket;
	private static DatagramPacket packet;
	private static InetAddress groupAdress;

	/**
	 * Method that listens for data from server.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int channel = scan.nextInt();
		scan.close();
		LOG.info("client started");
		if (channel == 1) {

			groupAdress = InetAddress.getByName("230.0.0.1");
		} else {
			groupAdress = InetAddress.getByName("230.0.0.2");
		}
		try {
			socket = new MulticastSocket(7001);
		} catch (SocketException e) {
			throw new SocketException("Socket initialisation error");
		}

		try {
			socket.joinGroup(groupAdress);
		} catch (NullPointerException e) {
			LOG.error(e.getMessage());
		}

		byte[] buffer = new byte[256];

		packet = new DatagramPacket(buffer, buffer.length);
		while (new String(packet.getData(), 0, packet.getLength()) != null) {
			socket.receive(packet);
			LOG.info(new String(packet.getData(), 0, packet.getLength()));

		}

		socket.leaveGroup(groupAdress);
		socket.close();
	}

}
