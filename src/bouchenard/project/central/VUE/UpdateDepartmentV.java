package bouchenard.project.central.VUE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bouchenard.project.central.CONTROLLER.ControllerC;
import bouchenard.project.central.MODEL.StdDepartment;

import javax.swing.SpringLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDepartmentV extends JFrame {
	// Controller of the main application
	private ControllerC controller; 
	// Department to modify
	private StdDepartment department;
	// Panel for the creation of the window
	private JPanel contentPane;
	// Text field for the name
	private JTextField textName;

	/**
	 * Creates a new window to modify the information of the department
	 * @param controller
	 * @param department
	 */
	public UpdateDepartmentV(ControllerC controller, StdDepartment department) {
		this.controller = controller;
		this.department = department;
		
		setTitle("Update department");
		setBounds(100, 100, 280, 144);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel pan_form = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pan_form, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, pan_form, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pan_form, 38, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pan_form, -10, SpringLayout.EAST, contentPane);
		contentPane.add(pan_form);
		pan_form.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel pan_label = new JPanel();
		pan_form.add(pan_label);
		pan_label.setLayout(new GridLayout(1, 1, 0, 1));
		
		JLabel labelName = new JLabel("Name : ");
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelName);
		
		JPanel pan_text = new JPanel();
		pan_form.add(pan_text);
		pan_text.setLayout(new GridLayout(1, 1, 0, 1));
		
		textName = new JTextField();
		textName.setText(department.GetName());
		pan_text.add(textName);
		textName.setColumns(10);
		
		JPanel pan_button = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pan_button, 6, SpringLayout.SOUTH, pan_form);
		sl_contentPane.putConstraint(SpringLayout.WEST, pan_button, 0, SpringLayout.WEST, pan_form);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pan_button, 46, SpringLayout.SOUTH, pan_form);
		sl_contentPane.putConstraint(SpringLayout.EAST, pan_button, -10, SpringLayout.EAST, contentPane);
		contentPane.add(pan_button);
		pan_button.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCreate = new JButton("Update department");
		pan_button.add(btnCreate);
		
		btnCreate.addActionListener(new ValidateDepartment());
		
		setVisible(true);
	}
	
	/**
	 * Validates the information after modifications
	 */
	class ValidateDepartment implements ActionListener {
		public void actionPerformed(ActionEvent e) {		
			if(textName.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Fields name is required", "Error", JOptionPane.ERROR_MESSAGE);
			else
			{
				int cont = JOptionPane.showConfirmDialog(null, "Update this department ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							
				if(cont == JOptionPane.OK_OPTION)
				{
					new Thread(new Runnable(){public void run(){
						controller.UpdateDepartment(department.GetId(), textName.getText());
					}}).start();
				}
			}
		}	
	}
}