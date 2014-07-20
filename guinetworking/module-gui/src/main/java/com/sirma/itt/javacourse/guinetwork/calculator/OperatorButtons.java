package com.sirma.itt.javacourse.guinetwork.calculator;

import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Generates the buttons which are used for operations e.g +-/*
 * 
 * @author radoslav
 */
public class OperatorButtons {

	ArrayList<JButton> operatorButtons = new ArrayList<JButton>();
	char[] operator = new char[] { '+', '-', '*', '/', '(', ')', 'C', '=', 'B' };

	/**
	 * Creates the buttons.
	 */
	public OperatorButtons() {
		createButtons();
	}

	/**
	 * Creates the buttons using the names provide by the operator array.
	 */
	private void createButtons() {
		for (int i = 0; i < operator.length; i++) {
			JButton button = new JButton(Character.toString(operator[i]));
			button.setVisible(true);
			operatorButtons.add(button);
		}

	}

	/**
	 * Gets the buttons.
	 * 
	 * @return The list of buttons.
	 */
	public ArrayList<JButton> getButtons() {
		return operatorButtons;
	}
}
