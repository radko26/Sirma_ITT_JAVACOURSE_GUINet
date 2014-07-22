package com.sirma.itt.javacourse.guinetwork.serverclientconn;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * Graphical interface to {@link Client}.
 * 
 * @author radoslav
 */
public class ClientGUI extends JFrame{
	private static final Point POINT_CENTER = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	private static JTextArea logField = new JTextArea();
	private static JPanel panel = new JPanel();
	
	/**
	 * Initialises all components.
	 */
	public ClientGUI(){
		super("Client");
		logField.setVisible(true);
		logField.setPreferredSize(new Dimension(500, 100));
		logField.setText("Waiting for response");

		panel.setSize(500, 500);
		panel.setVisible(true);
		panel.add(logField);

		
		setContentPane(panel);
		pack();
		setBounds(POINT_CENTER.x - getWidth()/2,POINT_CENTER.y - getHeight()/2, getWidth(),getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		connect();
		
	}
	/**
	 * Unites {@link Client} and {@link ClientGUI}.
	 */
	private void connect() {
		try {
			new Client(logField).start();
		} catch (IOException e) {
			logField.setText(e.getMessage());
		}
		
	}

	/**
	 * Starts the client.
	 * @param args
	 */
	public static void main(String[] args){
		new ClientGUI();
	}
	

}
