package bouchenard.project.central.VUE;

import javax.swing.border.*;

import bouchenard.project.central.CONTROLLER.ControllerC;
import bouchenard.project.central.MODEL.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UpdateEmployeeV extends JFrame {

	private static final long serialVersionUID = 1861695049735192681L;
	
	// Controller of the main application
	private ControllerC controller; 
	// Employee object to modify
	private Employee employee;
	
	/**
	 * Elements to create a new window
	 */
	private JPanel contentPane;
	private JPanel pan_label;
	private JPanel pan_text;
	private JLabel labelFirstName;
	private JLabel labelLastName;
	private JLabel labelSex;
	private JLabel labelMail;
	private JLabel LabelPhone;
	private JLabel labelStreetNumber;
	private JLabel labelTown;
	private JLabel labelStartTime;
	private JLabel labelEndTime;
	private JLabel labelStreetName;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textMail;
	private JTextField textPhone;
	private JTextField textStreetNumber;
	private JTextField textStreetName;
	private JTextField textTown;
	private JPanel pan_form;
	private JLabel labelZipCode;
	private JTextField textZipCode;
	private JPanel pan_button;
	private JButton btnCreateEmployee;
	private JComboBox comboBoxSex;
	private JSpinner spinnerStartTime;
	private JSpinner spinnerEndTime;
	private JLabel labelDepartment;
	private JComboBox comboBoxDepartment;

	/**
	 * Creates a new window to modify the information
	 * @param controller
	 * @param employee
	 */
	public UpdateEmployeeV(ControllerC controller, Employee employee) {
		this.controller = controller;
		this.employee = employee;
		
		setTitle("Update employee");
		setBounds(100, 100, 280, 410);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		pan_form = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pan_form, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, pan_form, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pan_form, -10, SpringLayout.EAST, contentPane);
		contentPane.add(pan_form);
		pan_form.setLayout(new GridLayout(1, 2, 0, 0));
		
		pan_label = new JPanel();
		pan_form.add(pan_label);
		pan_label.setLayout(new GridLayout(0, 1, 0, 1));
		
		labelFirstName = new JLabel("First name : ");
		labelFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelFirstName);
		
		labelLastName = new JLabel("Last name : ");
		labelLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelLastName);
		
		labelSex = new JLabel("Sex : ");
		labelSex.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelSex);
		
		labelMail = new JLabel("Mail : ");
		labelMail.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelMail);
		
		LabelPhone = new JLabel("Phone : ");
		LabelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(LabelPhone);
		
		labelStreetNumber = new JLabel("Street number : ");
		labelStreetNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelStreetNumber);
		
		labelStreetName = new JLabel("Street name : ");
		labelStreetName.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelStreetName);
		
		labelTown = new JLabel("Town : ");
		labelTown.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelTown);
		
		labelZipCode = new JLabel("Zip code : ");
		labelZipCode.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelZipCode);
		
		labelStartTime = new JLabel("Start time : ");
		labelStartTime.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelStartTime);
		
		labelEndTime = new JLabel("End time : ");
		labelEndTime.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelEndTime);
		
		labelDepartment = new JLabel("Department : ");
		labelDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		pan_label.add(labelDepartment);
		
		pan_text = new JPanel();
		pan_form.add(pan_text);
		pan_text.setLayout(new GridLayout(0, 1, 0, 1));
		
		textFirstName = new JTextField();
		textFirstName.setText(employee.GetFirstName());
		pan_text.add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setText(employee.GetLastName());
		pan_text.add(textLastName);
		textLastName.setColumns(10);
		
		String[] SexName = new String[]{"Male", "Female", "Unknown"};
		comboBoxSex = new JComboBox(SexName);
		comboBoxSex.setMaximumRowCount(3);
		if(employee.GetSex() == "Male")
			comboBoxSex.setSelectedIndex(0);
		else
			if(employee.GetSex() == "Female")
				comboBoxSex.setSelectedIndex(1);
			else
				comboBoxSex.setSelectedIndex(2);
		pan_text.add(comboBoxSex);
		
		textMail = new JTextField();
		textMail.setText(employee.GetMail());
		pan_text.add(textMail);
		textMail.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setText(employee.GetPhone());
		pan_text.add(textPhone);
		textPhone.setColumns(10);
		
		textStreetNumber = new JTextField();
		textStreetNumber.setText(String.valueOf(employee.GetStreetNumber()));
		pan_text.add(textStreetNumber);
		textStreetNumber.setColumns(10);
		
		textStreetName = new JTextField();
		textStreetName.setText(employee.GetStreetName());
		pan_text.add(textStreetName);
		textStreetName.setColumns(10);
		
		textTown = new JTextField();
		textTown.setText(employee.GetTown());
		pan_text.add(textTown);
		textTown.setColumns(10);
		
		textZipCode = new JTextField();
		textZipCode.setText(employee.GetZipCode());
		pan_text.add(textZipCode);
		textZipCode.setColumns(10);
		
		Date date = Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.of(employee.GetTimeStart().getHour(), employee.GetTimeStart().getMinute())).toInstant(OffsetDateTime.now().getOffset()));
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
		spinnerStartTime = new JSpinner(sm);
		pan_text.add(spinnerStartTime);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerStartTime, "HH:mm");
		spinnerStartTime.setEditor(de);

		
		Date date2 = Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.of(employee.GetTimeEnd().getHour(), employee.GetTimeEnd().getMinute())).toInstant(OffsetDateTime.now().getOffset()));
		SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null, Calendar.MINUTE);
		spinnerEndTime = new JSpinner(sm2);
		pan_text.add(spinnerEndTime);
		JSpinner.DateEditor de2 = new JSpinner.DateEditor(spinnerEndTime, "HH:mm");
		spinnerEndTime.setEditor(de2);
		
		List<String> listId = controller.LoadIdDepartment();
		listId.add(0, "null");
		comboBoxDepartment = new JComboBox(listId.toArray());
		if(employee.GetAffiliatedDepartment() == null)
			comboBoxDepartment.setSelectedIndex(0);
		else
			for(int i=1; i < listId.size(); i++)
				if(employee.GetAffiliatedDepartment().GetId().equals(listId.get(i)))
					comboBoxDepartment.setSelectedIndex(i);
		pan_text.add(comboBoxDepartment);
	
		
		pan_button = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.WEST, pan_button, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pan_button, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pan_form, -6, SpringLayout.NORTH, pan_button);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pan_button, -37, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pan_button, 0, SpringLayout.SOUTH, contentPane);
		contentPane.add(pan_button);
		pan_button.setLayout(new GridLayout(1, 1, 10, 10));
		
		btnCreateEmployee = new JButton("Update employee");
		pan_button.add(btnCreateEmployee);
		
		btnCreateEmployee.addActionListener(new ValidateEmployee());
		
		setVisible(true);
	}
	
	/**
	 * Verifies the information written
	 */
	class ValidateEmployee implements ActionListener {
		public void actionPerformed(ActionEvent e) {		
			if(textFirstName.getText().isEmpty() || textLastName.getText().isEmpty() || comboBoxSex.getSelectedIndex() == -1)
				JOptionPane.showMessageDialog(null, "Fields id, first name, last name and sex are required", "Error", JOptionPane.ERROR_MESSAGE);
			else
			{
				int cont = JOptionPane.showConfirmDialog(null, "Update this employee ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							
				if(cont == JOptionPane.OK_OPTION)
				{
					new Thread(new Runnable(){public void run(){
						String startHour =  new JSpinner.DateEditor(spinnerStartTime, "HH:mm").getFormat().format(spinnerStartTime.getValue());
						String endHour =  new JSpinner.DateEditor(spinnerEndTime, "HH:mm").getFormat().format(spinnerEndTime.getValue());
						
						if(comboBoxDepartment.getSelectedIndex() == 0)
							controller.UpdateEmployee(employee.GetId(), textFirstName.getText(), textLastName.getText(), comboBoxSex.getSelectedItem().toString(), textMail.getText(), textPhone.getText(), textStreetNumber.getText(), textStreetName.getText(), textTown.getText(), textZipCode.getText(), startHour, endHour, null);
						else
							controller.UpdateEmployee(employee.GetId(), textFirstName.getText(), textLastName.getText(), comboBoxSex.getSelectedItem().toString(), textMail.getText(), textPhone.getText(), textStreetNumber.getText(), textStreetName.getText(), textTown.getText(), textZipCode.getText(), startHour, endHour, String.valueOf(comboBoxDepartment.getSelectedItem()));
					}}).start();
				}
			}
		}	
	}
}
