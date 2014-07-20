package com.sirma.itt.javacourse.guinetwork.calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JTextField;

/**
 * Creates text field.
 * 
 * @author radoslav
 */
public class MonitorField extends JTextField {

	/**
	 * Creates monitor.
	 */
	public MonitorField() {
		super("0");
		setMargin(new Insets(0, 100, 0, 0));
		setPreferredSize(new Dimension(150, 50));
		setMargin(new Insets(0, 10, 0, 0));
		setBackground(Color.WHITE);
		setEnabled(false);
		setVisible(true);
	}

	/**
	 * Gets the field.
	 * 
	 * @return The field.
	 */
	public MonitorField getMonitor() {
		return this;
	}

}
