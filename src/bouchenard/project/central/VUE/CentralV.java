package bouchenard.project.central.VUE;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bouchenard.project.central.CONTROLLER.ControllerC;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CentralV extends JFrame {

	private static final long serialVersionUID = -3338938907865562316L;
	
	private ControllerC controller;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	
	private JTable tableClocks;
	private JTable tableEmployees;
	private JTable tableDepartments;
	private JTable tableManagers;
	
	private JComboBox comboBoxSort = new JComboBox();

	/**
	 * Sets a graphical interface for the main application
	 */
	public CentralV() {
		setTitle("Main Application");
		controller =  new ControllerC();
		controller.UpdateView(this);
		controller.OpenServerSocketEmployee();
		controller.OpenServerSocketRecord();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(/*1800, 800*/1000, 450);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(300, 250));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(tabbedPane);
		
		/////////////////////////////////////////////////////////////////////////
		//////////////////////////////	 Clocks 	/////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		//clocksView = new ClocksV(tabbedPane);
		JPanel panClocks = new JPanel();
		tabbedPane.addTab("Clocks", null, panClocks, null);
		tabbedPane.setEnabledAt(0, true);
		SpringLayout sl_panClocks = new SpringLayout();
		panClocks.setLayout(sl_panClocks);
		
		JPanel panTableClocks = new JPanel();
		sl_panClocks.putConstraint(SpringLayout.NORTH, panTableClocks, 0, SpringLayout.NORTH, panClocks);
		sl_panClocks.putConstraint(SpringLayout.WEST, panTableClocks, 0, SpringLayout.WEST, panClocks);
		sl_panClocks.putConstraint(SpringLayout.SOUTH, panTableClocks, -50, SpringLayout.SOUTH, panClocks);
		sl_panClocks.putConstraint(SpringLayout.EAST, panTableClocks, 0, SpringLayout.EAST, panClocks);
		panClocks.add(panTableClocks);
		panTableClocks.setLayout(new GridLayout(1, 1, 0, 0));
		
		tableClocks = new JTable();
		tableClocks.setName("tableClocks");
		panTableClocks.add(new JScrollPane(tableClocks));
		
		JPanel panSort = new JPanel();
		sl_panClocks.putConstraint(SpringLayout.NORTH, panSort, -44, SpringLayout.SOUTH, panClocks);
		sl_panClocks.putConstraint(SpringLayout.WEST, panSort, -249, SpringLayout.EAST, panClocks);
		sl_panClocks.putConstraint(SpringLayout.SOUTH, panSort, -6, SpringLayout.SOUTH, panClocks);
		sl_panClocks.putConstraint(SpringLayout.EAST, panSort, -10, SpringLayout.EAST, panClocks);
		panClocks.add(panSort);
		panSort.setLayout(new BoxLayout(panSort, BoxLayout.X_AXIS));
		
		JLabel labelSort = new JLabel("Sort by :  ");
		labelSort.setHorizontalAlignment(SwingConstants.RIGHT);
		panSort.add(labelSort);
		
		comboBoxSort.setMaximumRowCount(2);
		comboBoxSort.setModel(new DefaultComboBoxModel(new String[] {"All", "Current"}));
		comboBoxSort.setSelectedIndex(0);
		panSort.add(comboBoxSort);
		
		comboBoxSort.addActionListener(new SortRecord());
		
		UpdateJTableRecord();
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		
		
		
		/////////////////////////////////////////////////////////////////////////
		//////////////////////////////	Employees	/////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		JPanel panEmployees = new JPanel();
		tabbedPane.addTab("Employees", null, panEmployees, null);
		tabbedPane.setEnabledAt(0, true);
		SpringLayout sl_panEmployees = new SpringLayout();
		panEmployees.setLayout(sl_panEmployees);
		
		JPanel panTableEmployees = new JPanel();
		sl_panEmployees.putConstraint(SpringLayout.NORTH, panTableEmployees, 0, SpringLayout.NORTH, panEmployees);
		sl_panEmployees.putConstraint(SpringLayout.WEST, panTableEmployees, 0, SpringLayout.WEST, panEmployees);
		sl_panEmployees.putConstraint(SpringLayout.SOUTH, panTableEmployees, -50, SpringLayout.SOUTH, panEmployees);
		sl_panEmployees.putConstraint(SpringLayout.EAST, panTableEmployees, 0, SpringLayout.EAST, panEmployees);
		panEmployees.add(panTableEmployees);
		panTableEmployees.setLayout(new GridLayout(1, 1, 0, 0));
		
		tableEmployees = new JTable();
		tableEmployees.setName("tableEmployees");
		panTableEmployees.add(new JScrollPane(tableEmployees));
		
		JPanel panButtonDeleteEmp = new JPanel();
		panEmployees.add(panButtonDeleteEmp);
		panButtonDeleteEmp.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnDeleteEmp = new JButton("Delete");
		btnDeleteEmp.addActionListener(new DeleteEmployeeC());
		panButtonDeleteEmp.add(btnDeleteEmp);
		
		JPanel panButtonCreateEmp = new JPanel();
		sl_panEmployees.putConstraint(SpringLayout.NORTH, panButtonDeleteEmp, -39, SpringLayout.SOUTH, panButtonCreateEmp);
		sl_panEmployees.putConstraint(SpringLayout.SOUTH, panButtonDeleteEmp, 0, SpringLayout.SOUTH, panButtonCreateEmp);
		sl_panEmployees.putConstraint(SpringLayout.NORTH, panButtonCreateEmp, -44, SpringLayout.SOUTH, panEmployees);
		sl_panEmployees.putConstraint(SpringLayout.WEST, panButtonCreateEmp, -87, SpringLayout.EAST, panEmployees);
		sl_panEmployees.putConstraint(SpringLayout.SOUTH, panButtonCreateEmp, -5, SpringLayout.SOUTH, panEmployees);
		sl_panEmployees.putConstraint(SpringLayout.EAST, panButtonCreateEmp, -10, SpringLayout.EAST, panEmployees);
		panEmployees.add(panButtonCreateEmp);
		panButtonCreateEmp.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCreateEmp = new JButton("Create");
		btnCreateEmp.addActionListener(new CreateEmployeeC());
		panButtonCreateEmp.add(btnCreateEmp);
		
		JPanel panButtonUpdateEmp = new JPanel();
		sl_panEmployees.putConstraint(SpringLayout.WEST, panButtonDeleteEmp, -83, SpringLayout.WEST, panButtonUpdateEmp);
		sl_panEmployees.putConstraint(SpringLayout.NORTH, panButtonUpdateEmp, 6, SpringLayout.SOUTH, panTableEmployees);
		sl_panEmployees.putConstraint(SpringLayout.SOUTH, panButtonUpdateEmp, -5, SpringLayout.SOUTH, panEmployees);
		sl_panEmployees.putConstraint(SpringLayout.EAST, panButtonDeleteEmp, -6, SpringLayout.WEST, panButtonUpdateEmp);
		sl_panEmployees.putConstraint(SpringLayout.WEST, panButtonUpdateEmp, -83, SpringLayout.WEST, panButtonCreateEmp);
		sl_panEmployees.putConstraint(SpringLayout.EAST, panButtonUpdateEmp, -6, SpringLayout.WEST, panButtonCreateEmp);
		panEmployees.add(panButtonUpdateEmp);
		panButtonUpdateEmp.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnUpdateEmp = new JButton("Update");
		btnUpdateEmp.addActionListener(new UpdateEmployeeC());
		panButtonUpdateEmp.add(btnUpdateEmp);
		
		UpdateJTableEmployee();
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		
		
		
		/////////////////////////////////////////////////////////////////////////
		//////////////////////////////	Departments	/////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		JPanel panDepartments = new JPanel();
		tabbedPane.addTab("Departments", null, panDepartments, null);
		SpringLayout sl_panDepartments = new SpringLayout();
		panDepartments.setLayout(sl_panDepartments);
		
		JPanel panTableDepartments = new JPanel();
		sl_panDepartments.putConstraint(SpringLayout.NORTH, panTableDepartments, 0, SpringLayout.NORTH, panDepartments);
		sl_panDepartments.putConstraint(SpringLayout.WEST, panTableDepartments, 0, SpringLayout.WEST, panDepartments);
		sl_panDepartments.putConstraint(SpringLayout.SOUTH, panTableDepartments, -50, SpringLayout.SOUTH, panDepartments);
		sl_panDepartments.putConstraint(SpringLayout.EAST, panTableDepartments, 0, SpringLayout.EAST, panDepartments);
		panDepartments.add(panTableDepartments);
		panTableDepartments.setLayout(new GridLayout(1, 1, 0, 0));
		
		tableDepartments = new JTable();
		tableDepartments.setName("tableDepartments");
		panTableDepartments.add(new JScrollPane(tableDepartments));
		
		JPanel panButtonDeleteDpt = new JPanel();
		sl_panDepartments.putConstraint(SpringLayout.NORTH, panButtonDeleteDpt, 6, SpringLayout.SOUTH, panTableDepartments);
		sl_panDepartments.putConstraint(SpringLayout.SOUTH, panButtonDeleteDpt, -5, SpringLayout.SOUTH, panDepartments);
		panDepartments.add(panButtonDeleteDpt);
		
		JPanel panButtonCreateDpt = new JPanel();
		sl_panDepartments.putConstraint(SpringLayout.NORTH, panButtonCreateDpt, -44, SpringLayout.SOUTH, panDepartments);
		sl_panDepartments.putConstraint(SpringLayout.WEST, panButtonCreateDpt, -87, SpringLayout.EAST, panDepartments);
		panButtonDeleteDpt.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnDeleteDpt = new JButton("Delete");
		btnDeleteDpt.addActionListener(new DeleteDepartmentC());
		panButtonDeleteDpt.add(btnDeleteDpt);
		sl_panDepartments.putConstraint(SpringLayout.SOUTH, panButtonCreateDpt, -5, SpringLayout.SOUTH, panDepartments);
		sl_panDepartments.putConstraint(SpringLayout.EAST, panButtonCreateDpt, -10, SpringLayout.EAST, panDepartments);
		panDepartments.add(panButtonCreateDpt);
		panButtonCreateDpt.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnCreateDpt = new JButton("Create");
		btnCreateDpt.addActionListener(new CreateDepartmentC());
		panButtonCreateDpt.add(btnCreateDpt);
		
		UpdateJTableDepartment();
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		
		
		
		/////////////////////////////////////////////////////////////////////////
		//////////////////////////////	Managers	/////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		JPanel panManagers = new JPanel();
		tabbedPane.addTab("Managers", null, panManagers, null);
		SpringLayout sl_panManagers = new SpringLayout();
		panManagers.setLayout(sl_panManagers);
		
		JPanel panTableManagers = new JPanel();
		sl_panManagers.putConstraint(SpringLayout.NORTH, panTableManagers, 0, SpringLayout.NORTH, panManagers);
		sl_panManagers.putConstraint(SpringLayout.WEST, panTableManagers, 0, SpringLayout.WEST, panManagers);
		sl_panManagers.putConstraint(SpringLayout.SOUTH, panTableManagers, -50, SpringLayout.SOUTH, panManagers);
		sl_panManagers.putConstraint(SpringLayout.EAST, panTableManagers, 0, SpringLayout.EAST, panManagers);
		panManagers.add(panTableManagers);
		panTableManagers.setLayout(new GridLayout(1, 1, 0, 0));
		
		tableManagers = new JTable();
		tableManagers.setName("tableManagers");
		panTableManagers.add(new JScrollPane(tableManagers));
		
		JPanel panButtonDeleteMan = new JPanel();
		sl_panManagers.putConstraint(SpringLayout.NORTH, panButtonDeleteMan, -44, SpringLayout.SOUTH, panManagers);
		sl_panManagers.putConstraint(SpringLayout.WEST, panButtonDeleteMan, -170, SpringLayout.EAST, panManagers);
		sl_panManagers.putConstraint(SpringLayout.EAST, panButtonDeleteMan, -93, SpringLayout.EAST, panManagers);
		panManagers.add(panButtonDeleteMan);
		
		JPanel panButtonCreateMan = new JPanel();
		
		sl_panManagers.putConstraint(SpringLayout.SOUTH, panButtonDeleteMan, 0, SpringLayout.SOUTH, panButtonCreateMan);
		panButtonDeleteMan.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnDeleteMan = new JButton("Delete");
		btnDeleteMan.addActionListener(new DeleteManagerC());
		panButtonDeleteMan.add(btnDeleteMan);
		
		sl_panManagers.putConstraint(SpringLayout.NORTH, panButtonCreateMan, -44, SpringLayout.SOUTH, panManagers);
		sl_panManagers.putConstraint(SpringLayout.WEST, panButtonCreateMan, -87, SpringLayout.EAST, panManagers);
		sl_panManagers.putConstraint(SpringLayout.SOUTH, panButtonCreateMan, -5, SpringLayout.SOUTH, panManagers);
		sl_panManagers.putConstraint(SpringLayout.EAST, panButtonCreateMan, -10, SpringLayout.EAST, panManagers);
		panManagers.add(panButtonCreateMan);
		panButtonCreateDpt.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panButtonUpdateDpt = new JPanel();
		sl_panDepartments.putConstraint(SpringLayout.WEST, panButtonDeleteDpt, -83, SpringLayout.WEST, panButtonUpdateDpt);
		sl_panDepartments.putConstraint(SpringLayout.EAST, panButtonDeleteDpt, -6, SpringLayout.WEST, panButtonUpdateDpt);
		sl_panDepartments.putConstraint(SpringLayout.NORTH, panButtonUpdateDpt, -44, SpringLayout.SOUTH, panDepartments);
		sl_panDepartments.putConstraint(SpringLayout.WEST, panButtonUpdateDpt, -83, SpringLayout.WEST, panButtonCreateDpt);
		sl_panDepartments.putConstraint(SpringLayout.EAST, panButtonUpdateDpt, -6, SpringLayout.WEST, panButtonCreateDpt);
		sl_panDepartments.putConstraint(SpringLayout.SOUTH, panButtonUpdateDpt, -5, SpringLayout.SOUTH, panDepartments);
		panDepartments.add(panButtonUpdateDpt);
		panButtonUpdateDpt.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnUpdateDpt = new JButton("Update");
		btnUpdateDpt.addActionListener(new UpdateDepartmentC());
		panButtonUpdateDpt.add(btnUpdateDpt);
		panButtonCreateMan.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCreateMan = new JButton("Create");
		btnCreateMan.addActionListener(new CreateManagerC());
		panButtonCreateMan.add(btnCreateMan);
		
		UpdateJTableManager();
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		
		addWindowListener(new WindowsEvent());
		tabbedPane.setSelectedIndex(0);
		setVisible(true);
	}
	
	/**
	 * Permits to a user to create a new employee in the company
	 */
	class CreateEmployeeC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable(){public void run(){
				new CreateEmployeeV(controller);
			}}).start();
		}
	}
	
	/**
	 * Updates an employee object
	 */
	class UpdateEmployeeC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable(){public void run(){
				if(tableEmployees.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null, "Pick an employee to update", "", JOptionPane.INFORMATION_MESSAGE);
				else
					new UpdateEmployeeV(controller, controller.GetEmployeeFromId(tableEmployees.getValueAt(tableEmployees.getSelectedRow(), 0).toString()));
			}}).start();
		}
	}
	
	/**
	 * Removes an employee from the database of the company
	 */
	class DeleteEmployeeC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable(){public void run(){
						if(tableEmployees.getSelectedRow() == -1)
							JOptionPane.showMessageDialog(null, "Pick an employee to delete", "", JOptionPane.INFORMATION_MESSAGE);
						else
						{
							int cont = JOptionPane.showConfirmDialog(null, "Warning! This employee is going to be definitively deleted.\nContinue?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(cont == JOptionPane.OK_OPTION)
								controller.DeleteEmployee(tableEmployees.getValueAt(tableEmployees.getSelectedRow(), 0).toString());
						}
					}}).start();	
		}
	}
	
	/**
	 * Creates a manager in the company
	 */
	class CreateManagerC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable(){public void run(){
				new CreateManagerV(controller);
			}}).start();
		}
	}
	
	/**
	 * Removes the managers from the company
	 */
	class DeleteManagerC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable(){public void run(){
						if(tableManagers.getSelectedRow() == -1)
							JOptionPane.showMessageDialog(null, "Pick a manager to delete", "", JOptionPane.INFORMATION_MESSAGE);
						else
						{
							int cont = JOptionPane.showConfirmDialog(null, "Warning! This manager is going to be definitively deleted.\nContinue?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(cont == JOptionPane.OK_OPTION)
								controller.DeleteManager(tableManagers.getValueAt(tableManagers.getSelectedRow(), 0).toString());
						}
					}}).start();	
		}
	}
	
	/**
	 * Creates a new department for the company
	 */
	class CreateDepartmentC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable(){public void run(){
				new CreateDepartmentV(controller);
			}}).start();
		}
	}
	
	/**
	 * Updates a department object
	 */
	class UpdateDepartmentC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable(){public void run(){
				if(tableDepartments.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null, "Pick a department to update", "", JOptionPane.INFORMATION_MESSAGE);
				else
					new UpdateDepartmentV(controller, controller.GetDepartmentFromId(tableDepartments.getValueAt(tableDepartments.getSelectedRow(), 0).toString()));
			}}).start();
		}
	}
	
	/**
	 * Removes a department from the company
	 */
	class DeleteDepartmentC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable(){public void run(){
						if(tableDepartments.getSelectedRow() == -1)
							JOptionPane.showMessageDialog(null, "Pick a department to delete", "", JOptionPane.INFORMATION_MESSAGE);
						else
						{
							int cont = JOptionPane.showConfirmDialog(null, "Warning! This department is going to be definitively deleted.\nContinue?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(cont == JOptionPane.OK_OPTION)
								controller.DeleteDepartment(tableDepartments.getValueAt(tableDepartments.getSelectedRow(), 0).toString());
								
						}
					}}).start();	
		}
	}
	
	/**
	 * Permits to sort the records in the array
	 */
	class SortRecord implements ActionListener {
		public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable(){public void run(){
							UpdateJTableRecord();
					}}).start();	
		}
	}
	
	/**
	 * Permits to serialize the company after the closing of the main application
	 */
	class WindowsEvent implements WindowListener {
		@Override
		public void windowClosing(WindowEvent e) {
			new Thread(new Runnable(){public void run(){
				controller.Serialize();
			}}).start();
				
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
	
	/**
	 * Updates the records array of the main application
	 */
	public void UpdateJTableRecord() {
		String  titleRecord[] = {"Date", "Id", "FirstName", "LastName", "ClockIn", "ClockOut"};
		Object[][] dataRecord = controller.LoadDataTableClock(String.valueOf(comboBoxSort.getSelectedItem()));
		DefaultTableModel newModel = new DefaultTableModel(dataRecord,titleRecord){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int iRowIndex, int iColumnIndex){return false;}};
		tableClocks.setModel(newModel);
		tableClocks.getTableHeader().setReorderingAllowed(false);
		tableClocks.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tableClocks.clearSelection();
	}
	
	/**
	 * Updates the employees array of the main application
	 */
	public void UpdateJTableEmployee() {
		String  titleEmployee[] = {"Id", "FirstName", "LastName", "Sex", "Mail", "Phone", "StreetNumber", "StreetName", "Town", "ZipCode", "StartTime", "EndTime", "MinutsSupply", "AffiliatedDepartment"};
		Object[][] dataEmployee = controller.LoadDataTableEmployee();
		DefaultTableModel newModel = new DefaultTableModel(dataEmployee, titleEmployee){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int iRowIndex, int iColumnIndex){return false;}};
		tableEmployees.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tableEmployees.getTableHeader().setReorderingAllowed(false);
		tableEmployees.setModel(newModel);
		tableEmployees.clearSelection();
	}
	
	/**
	 * Updates the managers array of the main application
	 */
	public void UpdateJTableManager() {
		String  titleManager[] = {"Id", "FirstName", "LastName", "Sex", "Mail", "Phone", "StreetNumber", "StreetName", "Town", "ZipCode", "StartTime", "EndTime", "MinutsSupply", "DepartmentLed"};
		Object[][] dataManager = controller.LoadDataTableManager();
		DefaultTableModel newModel = new DefaultTableModel(dataManager, titleManager){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int iRowIndex, int iColumnIndex){return false;}};
		tableManagers.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tableManagers.getTableHeader().setReorderingAllowed(false);
		tableManagers.setModel(newModel);
		tableManagers.clearSelection();
	}
	
	/**
	 * Updates the departments array of the main application
	 */
	public void UpdateJTableDepartment() {
		String  titleDepartment[] = {"Id", "Name", "CountEmployee"};
		Object[][] dataDepartment = controller.LoadDataTableDepartment();
		DefaultTableModel newModel = new DefaultTableModel(dataDepartment, titleDepartment){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int iRowIndex, int iColumnIndex){return false;}};
		tableDepartments.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tableDepartments.getTableHeader().setReorderingAllowed(false);
		tableDepartments.setModel(newModel);
		tableDepartments.clearSelection();
	}
	
	/**
	 * Returns the controller of the main application
	 * @return a controller object
	 */
	public ControllerC GetController() {
		return controller;
	}
}
