package com.sirma.itt.javacourse.guinetwork.downloadagent;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * Provides graphical interface to programme that allows to download files from
 * the internet via their url adress.
 * 
 * @author radoslav
 */
public class DownloadAgentGUI extends JFrame {

	private JPanel contentPanel = new JPanel();
	private JTextField urlBar = new JTextField();
	private JLabel urlLabel = new JLabel("Url:");
	private JButton saveButton = new JButton("Save file");
	private JLabel errorLog = new JLabel("");
	private JProgressBar progressBar = new JProgressBar();

	/**
	 * Initialises all components.
	 */
	public DownloadAgentGUI() {
		urlBar.setPreferredSize(new Dimension(200, 20));

		contentPanel.add(urlLabel);
		contentPanel.add(urlBar);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				errorLog.setText("");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Save in");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				if (fileChooser.showOpenDialog(contentPanel) == JFileChooser.APPROVE_OPTION) {
					try {
						saveButton.setEnabled(false);
						useDownloadAgent(urlBar.getText(), fileChooser
								.getSelectedFile().toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPanel.add(saveButton);
		errorLog.setVisible(true);
		progressBar.setPreferredSize(new Dimension(200, 10));
		contentPanel.add(progressBar);
		contentPanel.add(errorLog);

		contentPanel.setVisible(true);
		contentPanel.setPreferredSize(new Dimension(400, 70));

		Point centre = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint();
		setContentPane(contentPanel);
		pack();
		setBounds(centre.x - getWidth() / 2, centre.y - getHeight() / 2,
				getWidth(), getHeight());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Creates and calls the {@link DownloadAgent} which will download and save
	 * the file.
	 * 
	 * @param sourceURL
	 *            The source link.
	 * @param fileSavePath
	 *            The choosed from the {@link FileChooser} directory.
	 */
	private void useDownloadAgent(String sourceURL, String fileSavePath) {
		DownloadAgent agent = new DownloadAgent(sourceURL, fileSavePath,
				errorLog, saveButton);
		agent.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("progress")) {
					int progress = (int) evt.getNewValue();
					progressBar.setValue(progress);
					if (progress == 100) {

					}
				}
			}
		});

		agent.execute();
	}

	/**
	 * Starts the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new DownloadAgentGUI();
	}
}
