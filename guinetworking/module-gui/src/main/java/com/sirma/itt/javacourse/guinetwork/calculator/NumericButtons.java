package com.sirma.itt.javacourse.guinetwork.calculator;

import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Generates buttons that represent digits.
 * 
 * @author radoslav
 */
public class NumericButtons {

	private ArrayList<JButton> buttons = new ArrayList<>();

	/**
	 * Creates the buttons when instance has been created.
	 */
	public NumericButtons() {
		createNumericButtons();
	}

	/**
	 * Generates the buttons.
	 */
	private void createNumericButtons() {
		for (int i = 10; i >= -1; i--) {
			JButton button = new JButton();
			if (i == -1) {
				button.setText(".");
			} else if (i == 1) {
				button.setText("RMP");
				button.setEnabled(false);
			} else if (i == 0) {
				button.setText("0");
			} else {
				button.setText(Integer.toString(i - 1));
			}
			button.setVisible(true);
			buttons.add(button);
		}
	}

	/**
	 * Return list of generated buttons.
	 * 
	 * @return The list.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

}
