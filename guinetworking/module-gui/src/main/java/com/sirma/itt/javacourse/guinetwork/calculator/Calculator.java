package com.sirma.itt.javacourse.guinetwork.calculator;

import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main class that creates frame window and use the {@link Panel} as content.
 * 
 * @author radoslav
 */
public class Calculator {

	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame window = new JFrame("Calculator");
				window.setContentPane(new Panel());
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setResizable(false);
				window.pack();
				Point center = GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getCenterPoint();
				window.setBounds(center.x - window.getWidth() / 2,
						center.y - window.getHeight() / 2, window.getWidth(),
						window.getHeight());
				window.setVisible(true);	
			}
		});
	
	}

}
