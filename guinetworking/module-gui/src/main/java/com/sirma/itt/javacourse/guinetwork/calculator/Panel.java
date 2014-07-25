package com.sirma.itt.javacourse.guinetwork.calculator;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel which holds all other panels and apply their actions.
 * 
 * @author radoslav
 */
public class Panel extends JPanel {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	private JPanel leftSide = new JPanel();
	private JPanel rightSide = new JPanel();
	private JPanel operatorsPanel = new JPanel();
	private JPanel textPanel = new JPanel();

	private NumericButtons numericButtons = new NumericButtons();
	private OperatorButtons operatorButtons = new OperatorButtons();
	private MonitorField monitorField = new MonitorField();
	private StringBuilder log = new StringBuilder();

	private Stack<Integer> deleteToken = new Stack<Integer>();

	/**
	 * Creates the sub panels and adds them to itself.
	 */
	public Panel() {

		setVisible(true);
		setLayout(new GridLayout(0, 2));

		leftSide.setVisible(true);
		leftSide.setSize(200, 200);
		leftSide.setLayout(new GridLayout(4, 4));
		add(leftSide);

		rightSide.setVisible(true);
		rightSide.setSize(200, 200);
		rightSide.setLayout(null);

		textPanel.setVisible(true);
		textPanel.setBounds(25, 0, 150, 100);
		rightSide.add(textPanel);

		operatorsPanel.setVisible(true);
		operatorsPanel.setLayout(new GridLayout(3, 3));
		operatorsPanel.setBounds(25, 100, 150, 150);

		rightSide.add(operatorsPanel);
		add(rightSide);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();

		add(numericButtons);
		add(operatorButtons);
		add(monitorField);
	}

	/**
	 * Adds all the numeric buttons + '.' which is used in the representing of
	 * non integer number.
	 * 
	 * @param buttons
	 *            The numeric button class which generates the buttons.
	 */
	public void add(NumericButtons buttons) {
		for (final JButton button : buttons.getButtons()) {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					log.append(button.getText());
					monitorField.getMonitor().setText(log.toString());
					deleteToken.add(1);
				}
			});
			leftSide.add(button);
		}
	}

	public void add(MonitorField label) {
		textPanel.add(label.getMonitor());
	}

	/**
	 * Adds the operation buttons to the panel and apply them their actions.
	 * 
	 * @param buttons
	 *            The operation buttons class which generates the buttons.
	 */
	public void add(OperatorButtons buttons) {
		for (final JButton button : buttons.getButtons()) {
			if (button.getText().contentEquals("C")) {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						log = new StringBuilder("");
						print(log.toString());
						deleteToken.clear();
					}
				});
			} else if (button.getText().contentEquals("B")) {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// System.out.println(deleteToken.peek());
						if (deleteToken.empty()) {

						} else {
							log.delete(log.length() - deleteToken.pop(),
									log.length());// deletes last
													// deleteToken.pop()
													// characters.
							print(log.toString());
						}
					}
				});
			} else if (button.getText().contentEquals("=")) {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							print(Compute.compute(log.toString()));
						} catch (IllegalArgumentException e1) {
							// log = new StringBuilder();
							print(e1.getMessage());
						}
					}
				});
			} else {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (button.getText().equals("(")) {

							if (log.length() > 0) {
								if (log.charAt(log.length() - 1) <= '9'
										&& log.charAt(log.length() - 1) >= '0') {
									log.append(" " + button.getText() + " ");
								} else {
									log.append(button.getText() + " ");
									deleteToken.add(2);
								}
							} else {
								log.append(button.getText() + " ");
								deleteToken.add(2);
							}
						} else if ((button.getText().equals("+") || button
								.getText().equals("-"))
								&& deleteToken.isEmpty()) {
							log.append(button.getText() + " ");
							deleteToken.add(2);
						} else if (button.getText().equals(")")) {
							if (log.length() > 0) {
								if (log.charAt(log.length() - 1) >= '0'
										&& log.charAt(log.length() - 1) <= '9') {
									log.append(" " + button.getText() + " ");
									deleteToken.add(3);
								} else {
									log.append(button.getText() + " ");
									deleteToken.add(2);
								}
							} else {
								log.append(button.getText() + " ");
								deleteToken.add(2);
							}
						} else {
							log.append(" " + button.getText() + " ");
							deleteToken.add(3);
						}
						print(log.toString());
					}
				});
			}
			operatorsPanel.add(button);
		}
	}

	/**
	 * Sets the log screen on the panel to given text.
	 * 
	 * @param text
	 *            The text.
	 */
	private void print(String text) {
		monitorField.getMonitor().setText(text);
	}
}
