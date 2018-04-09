package bouchenard.project.central.VUE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bouchenard.project.central.CONTROLLER.ControllerC;

import javax.swing.SpringLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateDepartmentV extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Controller of the main application
	private ControllerC controller; 
	// Panel to create a new window
	private JPanel contentPane;
	//Text field of the ID of the department
	private JTextField textId;
	// Text field of the name of the department
	private JTextField textName;

	/**
	 * Creates a new window to do some operations
	 * 		on departments objects
	 * @param controller
	 */
	public CreateDepartmentV(ControllerC controller) {
		this.controller = controller;
		
		setTitle("Create department");
		setBounds(100, 100, 280, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel pan_form = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pan_form, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, pan_form, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pan_form, 62, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pan_form, -10, SpringLayout.EAST, contentPane);
		contentPane.add(pan_form);
		pan_form.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel pan_label = new JPanel();
		pan_form.add(pan_label);
		pan_label.setLayout(new GridLayout(2, 1, 0, 1));
		
		JLabel labelId = new JLabel("Id : ");
		labelId.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelId);
		
		JLabel labelName = new JLabel("Name : ");
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelName);
		
		JPanel pan_text = new JPanel();
		pan_form.add(pan_text);
		pan_text.setLayout(new GridLayout(2, 1, 0, 1));
		
		textId = new JTextField();
		pan_text.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		pan_text.add(textName);
		textName.setColumns(10);
		
		JPanel pan_button = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pan_button, 9, SpringLayout.SOUTH, pan_form);
		sl_contentPane.putConstraint(SpringLayout.WEST, pan_button, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pan_button, 44, SpringLayout.SOUTH, pan_form);
		sl_contentPane.putConstraint(SpringLayout.EAST, pan_button, -10, SpringLayout.EAST, contentPane);
		contentPane.add(pan_button);
		pan_button.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCreate = new JButton("Create department");
		pan_button.add(btnCreate);
		
		btnCreate.addActionListener(new ValidateDepartment());
		
		setVisible(true);
	}
	
	/**
	 * After the creation of the department, verification of the data written
	 */
	class ValidateDepartment implements ActionListener {
		public void actionPerformed(ActionEvent e) {		
			if(textId.getText().isEmpty() || textName.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Fields id and name are required", "Error", JOptionPane.ERROR_MESSAGE);
			else
			{
				int cont = JOptionPane.showConfirmDialog(null, "Add this department ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							
				if(cont == JOptionPane.OK_OPTION)
				{
					new Thread(new Runnable(){public void run(){
						controller.InsertDepartment(textId.getText(), textName.getText());
					}}).start();
				}
			}
		}	
	}
}
