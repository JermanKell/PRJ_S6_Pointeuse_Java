package bouchenard.project.emulator.VUE;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.*;

import bouchenard.project.emulator.CONTROLLER.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Emulator extends JFrame {

	private static final long serialVersionUID = -1791098883363376732L;
	
	/**
	 * Elements for the configuration of the window
	 */
	private JPanel contentPane;
	private JLabel labelTime = new JLabel();
	private JLabel labelDate = new JLabel();
	private JLabel labelRounded = new JLabel();
	private JComboBox comboBoxListId = new JComboBox() ;
	private ControllerE controller;

	/**
	 * Creates a new window corresponding of the emulator application
	 */
	public Emulator() {
		
		controller =  new ControllerE();
		controller.UpdateView(this);
		controller.UpdateInfoEmployeeClientSocket();
		SetDateTime();
		
		setTitle("Time tracker emulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 239);
		this.setMinimumSize(new Dimension(459,239));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panDate = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panDate, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panDate, 61, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panDate, 40, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panDate, -83, SpringLayout.EAST, contentPane);
		contentPane.add(panDate);
		panDate.setLayout(new GridLayout(1, 1, 0, 0));
		
		
		labelDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelDate.setHorizontalAlignment(SwingConstants.CENTER);
		panDate.add(labelDate);
		
		JPanel panTime = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panTime, 6, SpringLayout.SOUTH, panDate);
		sl_contentPane.putConstraint(SpringLayout.WEST, panTime, 157, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panTime, 40, SpringLayout.SOUTH, panDate);
		contentPane.add(panTime);
		panTime.setLayout(new GridLayout(1, 1, 0, 0));
		
		
		labelTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelTime.setHorizontalAlignment(SwingConstants.CENTER);
		panTime.add(labelTime);
		
		JPanel panRounded = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.EAST, panTime, -6, SpringLayout.WEST, panRounded);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panRounded, 6, SpringLayout.SOUTH, panDate);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panRounded, 40, SpringLayout.SOUTH, panDate);
		sl_contentPane.putConstraint(SpringLayout.WEST, panRounded, 234, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panRounded, -46, SpringLayout.EAST, contentPane);
		contentPane.add(panRounded);
		panRounded.setLayout(new GridLayout(0, 1, 0, 0));
		
		labelRounded.setHorizontalAlignment(SwingConstants.CENTER);
		panRounded.add(labelRounded);
		
		JPanel panSynchronizer = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panSynchronizer, 98, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panSynchronizer, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panSynchronizer, 128, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panSynchronizer, 207, SpringLayout.WEST, contentPane);
		contentPane.add(panSynchronizer);
		panSynchronizer.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton ButtonSynchronizer = new JButton("Sync. infos from main app.");
		
		JPanel panListId = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panListId, 17, SpringLayout.SOUTH, panSynchronizer);
		sl_contentPane.putConstraint(SpringLayout.WEST, panListId, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panListId, 47, SpringLayout.SOUTH, panSynchronizer);
		sl_contentPane.putConstraint(SpringLayout.EAST, panListId, 207, SpringLayout.WEST, contentPane);
		contentPane.add(panListId);
		
		UpdateComboBoxListIdEmployee();
		panListId.setLayout(new BoxLayout(panListId, BoxLayout.X_AXIS));
		comboBoxListId.setMaximumRowCount(10);
		panListId.add(comboBoxListId);
		
		JPanel panCheckInOut = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panCheckInOut, -37, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panCheckInOut, -118, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panCheckInOut, -7, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panCheckInOut, -10, SpringLayout.EAST, contentPane);
		contentPane.add(panCheckInOut);
		panCheckInOut.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton ButtonCheckInOut = new JButton("Check in/out");
		ButtonCheckInOut.addActionListener(new CheckInOut());
		panCheckInOut.add(ButtonCheckInOut);
		ButtonSynchronizer.addActionListener(new SynchronizeId());
		panSynchronizer.add(ButtonSynchronizer);
		
		
		addWindowListener(new WindowsEvent());
		
		
		setVisible(true);
	}
	
	/**
	 * Updates the employees IDs list if new ID was received
	 */
	public void UpdateComboBoxListIdEmployee() {
		comboBoxListId.removeAllItems();
		comboBoxListId.setModel(new DefaultComboBoxModel(controller.LoadListEmployeeId().toArray()));
		comboBoxListId.setSelectedIndex(-1);
	}
	
	/**
	 * Displays the current time
	 */
	private void SetDateTime() {
		new Thread(new Horloge(labelDate, labelTime, labelRounded)).start();
	}
	
	/**
	 * Works to gather all employees IDs between each other in one array
	 */
	class SynchronizeId implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.UpdateInfoEmployeeClientSocket();
		}
	}
	
	/**
	 * Works on the sending of the record if an employee
	 */
	class CheckInOut implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable(){public void run(){
				if(comboBoxListId.getSelectedIndex() == -1)
					JOptionPane.showMessageDialog(null, "Pick an employee's id to send a record", "", JOptionPane.INFORMATION_MESSAGE);
				else
				{
					int cont = JOptionPane.showConfirmDialog(null, "You are about to send a message.\nContinue?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(cont == JOptionPane.OK_OPTION)
						controller.SendRecordClientSocket(comboBoxListId.getSelectedItem().toString());
				}
			}}).start();
		}
	}
	
	/**
	 * Events Handler
	 */
	class WindowsEvent implements WindowListener {
		@Override
		public void windowClosing(WindowEvent e) {
			new Thread(new Runnable(){public void run(){
				controller.Serialize();
			}}).start();
			//System.exit(0);
				
		}

		@Override
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
