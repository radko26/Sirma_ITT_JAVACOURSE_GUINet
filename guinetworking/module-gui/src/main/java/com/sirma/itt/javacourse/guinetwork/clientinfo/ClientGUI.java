package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sirma.itt.javacourse.guinetwork.serverclientconn.Client;

/**
 * Client GUI using {@link Client} to connect to server.
 * @author Radoslav
 */

public class ClientGUI extends JFrame {
	private static final Point POINT_CENTER = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
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
		
		
		
		scroll.setPreferredSize(new Dimension(400,300));
		
		
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(500, 300));
		
		contentPanel.add(scroll,BorderLayout.PAGE_START);
		contentPanel.setVisible(true);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPanel);
		pack();
		setBounds(POINT_CENTER.x - getWidth()/2,POINT_CENTER.y - getHeight()/2,getWidth(),getHeight());
		setVisible(true);
	}
	
	

	/**
	 * Starts the GUI.
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientGUI();
	}

}
