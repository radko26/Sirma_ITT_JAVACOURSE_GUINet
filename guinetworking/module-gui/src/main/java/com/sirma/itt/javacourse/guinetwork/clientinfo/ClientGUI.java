package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Client GUI using {@link Client} to connect to server.
 * 
 * @author Radoslav
 */

public class ClientGUI extends JFrame {
	private static final Point POINT_CENTER = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getCenterPoint();
	private JPanel contentPanel = new JPanel();
	private JTextArea logField = new JTextArea();
	private JScrollPane scroll = new JScrollPane(logField,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	/**
	 * Initialises the GUI.
	 */
	public ClientGUI() {
		super("Client");

		logField.setBorder(BorderFactory.createLineBorder(Color.RED));
		logField.setEnabled(false);

		scroll.setPreferredSize(new Dimension(400, 300));

		contentPanel.setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(500, 300));

		contentPanel.add(scroll, BorderLayout.PAGE_START);
		contentPanel.setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPanel);
		pack();
		setBounds(POINT_CENTER.x - getWidth() / 2, POINT_CENTER.y - getHeight()
				/ 2, getWidth(), getHeight());
		setVisible(true);

		connect();
	}

	/**
	 * Connects with the server.
	 */
	private void connect() {
		try {
			new Client(logField).connect();
		} catch (IOException e) {
			logField.append("\n" + e.getMessage()
					+ " terminating in 5 seconds..");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {

			}
			System.exit(0);
		}
	}

	/**
	 * Starts the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientGUI();
	}

}
