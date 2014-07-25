package com.sirma.itt.javacourse.guinetwork.reversemsg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Client GUI using {@link Client} to connect to server.
 * 
 * @author Radoslav
 */

public class ClientGUI extends JFrame implements KeyEventDispatcher {
	private static final Point POINT_CENTER = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getCenterPoint();
	private JPanel contentPanel = new JPanel();
	private static JTextArea logField = new JTextArea();
	private JScrollPane scroll = new JScrollPane(logField,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JButton sendButton = new JButton("Send");
	private JTextArea msgField = new JTextArea();
	private AtomicBoolean sendButtonPressed = new AtomicBoolean(false);
	private Memento<String> history = new Memento<String>();
	private String message = "";

	/**
	 * Initialises the GUI.
	 */
	public ClientGUI() {
		super("Client");

		logField.setBorder(BorderFactory.createLineBorder(Color.RED));
		logField.setEnabled(false);

		scroll.setPreferredSize(new Dimension(400, 230));

		contentPanel.setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(500, 300));

		contentPanel.add(scroll, BorderLayout.PAGE_START);
		contentPanel.setVisible(true);

		sendButton.setPreferredSize(new Dimension(100, 30));
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				history.save(msgField.getText());
				sendButtonPressed.set(true);
			}
		});

		contentPanel.add(sendButton, BorderLayout.PAGE_END);
		msgField.setPreferredSize(new Dimension(400, 40));
		contentPanel.add(msgField);

		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
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
		new Client(msgField, sendButtonPressed, logField).execute();
	}

	/**
	 * Starts the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientGUI();

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				try {
					msgField.setText(history.redo());
					// System.out.println("up");
				} catch (IndexOutOfBoundsException e1) {

				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {

				try {
					msgField.setText(history.undo());
					// System.out.println("down");
				} catch (IndexOutOfBoundsException e2) {
					// e2.printStackTrace();

				}
			}
		}
		return false;
	}

}
