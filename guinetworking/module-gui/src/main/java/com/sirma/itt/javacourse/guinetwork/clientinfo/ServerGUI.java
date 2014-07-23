package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Graphical user interface using {@link Sever} to launch server.
 * 
 * @author Radoslav
 */
public class ServerGUI extends JFrame{
	private static final Point POINT_CENTER = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint(); 
	private JPanel contentPanel = new JPanel();
	private JButton stopButton = new JButton("Stop server");
	private JTextArea logField  =new JTextArea();
	private JScrollPane scroll = new JScrollPane(logField,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	/**
	 * Initialises the GUI.
	 */
	public ServerGUI(){
		super("Server");
		logField.setMargin(new Insets(5, 5, 2, 0));
		scroll.setPreferredSize(new Dimension(300,270));
		stopButton.setPreferredSize(new Dimension(100,30));
		
		contentPanel.setLayout(new BorderLayout());
		
		contentPanel.add(stopButton,BorderLayout.PAGE_END);
		contentPanel.add(scroll,BorderLayout.PAGE_START);
		contentPanel.setPreferredSize(new Dimension(400,300));
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
	public static void main(String[] args){
		new ServerGUI();
	}

}
