package com.sirma.itt.javacourse.guinetwork.serverclientconn;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Creates and runs graphic interface to the {@link Server}.
 * 
 * @author radoslav
 */
public class ServerGUI extends JFrame implements ActionListener {

	private JPanel contentPanel = new JPanel();
	private static final Point POINT_CENTER = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getCenterPoint();
	private JButton button = new JButton("Stop server");
	private JTextArea logField = new JTextArea();
	private JScrollPane scroll = new JScrollPane(logField,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private Server server;

	/**
	 * Initialises the GUI and starts the server.
	 * 
	 * @throws IOException
	 * 
	 */
	public ServerGUI() {
		super("Server");
		logField.setVisible(true);
		logField.setEnabled(false);
		logField.setText("Waiting for client");

		button.addActionListener(this);

		contentPanel.setLayout(new GridLayout(0, 2));
		contentPanel.setVisible(true);
		contentPanel.setPreferredSize(new Dimension(500, 100));
		contentPanel.add(button);
		contentPanel.add(scroll);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPanel);
		pack();
		setBounds(POINT_CENTER.x - getWidth() / 2,
				POINT_CENTER.y - getHeight(), getWidth(), getHeight());
		setVisible(true);

		try {
			server = new Server(logField);
			server.listen();
		} catch (IOException e1) {
			logField.append("\n" + e1.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		server.close();
		System.exit(0);
	}

	/**
	 * Starts the server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ServerGUI();
	}
}
