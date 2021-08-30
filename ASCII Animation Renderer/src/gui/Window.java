package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import render.*;
import javax.swing.JTextPane;

public class Window {

	private JFrame frmAnimationWindow;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtZ;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JLabel lblNewLabel_7;
	private JTextField textField_8;
	private JLabel lblNewLabel_8;
	private JTextField textField_9;
	private JTextPane screen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmAnimationWindow.setVisible(true);
					window.frmAnimationWindow.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmAnimationWindow = new JFrame();
		frmAnimationWindow.setBackground(Color.WHITE);
		frmAnimationWindow.setTitle("Animation Window");
		frmAnimationWindow.setBounds(100, 100, 816, 572);
		frmAnimationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		screen = new JTextPane();
		screen.setFont(new Font("Courier New", Font.PLAIN, 11));
		screen.setForeground(Color.WHITE);
		screen.setBackground(Color.BLACK);
		frmAnimationWindow.getContentPane().add(screen, BorderLayout.CENTER);
		
		Animator a = new Animator(screen, 4,120, 2);
		
		JButton btnNewButton = new JButton("Animate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.toggle();
				
				//c.render(txtrTest);
			}
		});
		frmAnimationWindow.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frmAnimationWindow.getContentPane().add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Equations");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		txtX = new JTextField();
		txtX.setText("x=");
		GridBagConstraints gbc_txtX = new GridBagConstraints();
		gbc_txtX.insets = new Insets(0, 0, 5, 0);
		gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtX.gridx = 0;
		gbc_txtX.gridy = 1;
		panel.add(txtX, gbc_txtX);
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setText("y=");
		GridBagConstraints gbc_txtY = new GridBagConstraints();
		gbc_txtY.insets = new Insets(0, 0, 5, 0);
		gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtY.gridx = 0;
		gbc_txtY.gridy = 2;
		panel.add(txtY, gbc_txtY);
		txtY.setColumns(10);
		
		txtZ = new JTextField();
		txtZ.setText("z=");
		GridBagConstraints gbc_txtZ = new GridBagConstraints();
		gbc_txtZ.insets = new Insets(0, 0, 5, 0);
		gbc_txtZ.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtZ.gridx = 0;
		gbc_txtZ.gridy = 3;
		panel.add(txtZ, gbc_txtZ);
		txtZ.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Object distance");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 5;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("View distance");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 6;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 7;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Light Location");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 8;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 9;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 10;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 0;
		gbc_textField_4.gridy = 11;
		panel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Light Intensity");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 12;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 0;
		gbc_textField_5.gridy = 13;
		panel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Duration");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 14;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 0;
		gbc_textField_6.gridy = 15;
		panel.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Frames");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 16;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 0;
		gbc_textField_7.gridy = 17;
		panel.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Sampling Resolution");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 18;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 0);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 0;
		gbc_textField_8.gridy = 19;
		panel.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Screen Size");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 20;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 0;
		gbc_textField_9.gridy = 21;
		panel.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
	}

}
