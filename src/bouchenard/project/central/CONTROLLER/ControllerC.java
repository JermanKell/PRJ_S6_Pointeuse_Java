package bouchenard.project.central.CONTROLLER;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import bouchenard.project.central.MODEL.*;
import bouchenard.project.central.VUE.*;
import bouchenard.project.central.socket.*;

public class ControllerC {

	// Company object which the application will work on
	private Company company;
	// CentralV object: graphical interface for the display of the company content 
	private CentralV central;
	
	public ControllerC() {
		Company startup = new Company("BlueSkyNet");
		Boss b = new Boss("Alain", "Deleau", Sex.Male);
		startup.SetBoss(b);
		
		Employee e1 = new Employee("acqq9", "Jean", "Michel", Sex.Male);
		e1.SetTime(8, 30, 20, 45);
		
		Employee e2 = new Employee("azq3", "Bobby", "Jax", Sex.Male, "Bobby@mail", "0611223344", 12, "rue des fleur", "Paris", "98500");
		e2.SetTime(7, 45, 18, 15);
		
		Employee e3 = new Employee("rvv12", "Amélie", "Lagrange", Sex.Female, "AmLag@mail.fr", "0789895187", 44, "bis rue d'Esper", "Tours", "37000");
		e3.SetTime(6, 55, 19, 15);
		
		StdDepartment d1 = new StdDepartment("01", "Direction financière");
		StdDepartment d2 = new StdDepartment("02", "Direction marketing");
		
		ManagementDpt md = new ManagementDpt("00", "Direction Management");
		
		startup.SetManagementDpt(md);
		
		Manager m1 = new Manager("maa1", "Alexia", "Petit", Sex.Female, d1);
		m1.SetTime(7, 45, 20, 00);
		
		Manager m2 = new Manager("mbb2", "Benjamin", "Perrez", Sex.Male, d2);
		m2.SetTime(9, 55, 16, 15);
		
		Manager m3 = new Manager("mcc3", "Brian", "Erickson", Sex.Male, null);
		m3.SetTime(8, 30, 17, 30);
		
		startup.AddEmployee(e1);
		
		startup.AddStdDepartment(d1);
		startup.AddStdDepartment(d2);
		
		startup.AddEmployeeStdDepartement(d1, e2);
		startup.AddEmployeeStdDepartement(d1, e3);
		
		startup.AddManager(m1);
		startup.AddManager(m2);
		startup.AddManagerStdDepartment(d2, m3);
		
		startup.GetHashMapEmployee().get("acqq9").SetMinutsSupply(0);
		LocalDate today = LocalDate.now();
		try { startup.DeleteListRecord(today);}catch (IllegalArgumentException e) {e.printStackTrace();};
		Record r = new Record("acqq9", LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 31)));
		Record r2 = new Record("acqq9", LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 14)));
		
		Record r3 = new Record("mbb2", LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 33)));
		Record r4 = new Record("mbb2", LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 38)));
		
		try {
			startup.AddRecord(r);
			startup.AddRecord(r2);
			startup.AddRecord(r3);
			startup.AddRecord(r4);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		company = startup;
		//ExportCSV ecsv = new ExportCSV("monfichier", startup);
		//ImportCSV icsv = new ImportCSV("monfichier");
		
		central = null;
		//company = new DeserializerCompanyC().GetCompany();
	}
	
	/**
	 * Allows the user to sort the records array
	 * @param typeSort: kind of sort wanted
	 * @return an object containing the records sorted with the condition
	 */
	public Object[][] LoadDataTableClock(String typeSort) {
		int i = 0;
		int j;
		int count = 0;
		Object[][] data = null;
		if(company != null)
		{
			if(typeSort.equals("Current"))
			{
				if(company.GetListRecord().get(LocalDate.now()) != null)
				{
					List<RecordDay> listSingleRecordToday = company.GetListRecord().get(LocalDate.now()).GetRecordDaySingleRecord();
				 
					data = new Object[listSingleRecordToday.size()][6];
						
					for(RecordDay recordDay : listSingleRecordToday)
					{
						Employee employee = recordDay.GetEmployee();
						j = 0;
						data[i][j++] = LocalDate.now();
						data[i][j++] = employee.GetId();
						data[i][j++] = employee.GetFirstName();
						data[i][j++] = employee.GetLastName();
						data[i][j++] = recordDay.GetClockIn();
						data[i++][j] = recordDay.GetClockOut();
					} 
				 }
			}
			if(typeSort.equals("All"))
			{
				for(Entry<LocalDate, ListRecord> entry : company.GetListRecord().entrySet())
					count += entry.getValue().GetListRecord().size(); 
				data = new Object[count][6];
				
				for(Entry<LocalDate, ListRecord> entry : company.GetListRecord().entrySet())
				{
					ListRecord listRecord = entry.getValue();
					for(RecordDay recordDay : listRecord.GetListRecord())
					{
						Employee employee = recordDay.GetEmployee();
						j = 0;
						data[i][j++] = entry.getKey();
						data[i][j++] = employee.GetId();
						data[i][j++] = employee.GetFirstName();
						data[i][j++] = employee.GetLastName();
						data[i][j++] = recordDay.GetClockIn();
						data[i++][j] = recordDay.GetClockOut();
					}
				}
			}
		}
		return data;
	}
	
	/**
	 * Loads the employee's array to display
	 * @return an array containing the employees objects of the company
	 */
	public Object[][] LoadDataTableEmployee() {
		int i = 0;
		int j;
		Object[][] data;
		
		if(company != null)
		{
			data = new Object[company.GetHashMapEmployee().size()][14];
			
			for(Entry<String, Employee> entry : company.GetHashMapEmployee().entrySet())
			{
					j = 0;
					data[i][j++] = entry.getValue().GetId();
					data[i][j++] = entry.getValue().GetFirstName();
					data[i][j++] = entry.getValue().GetLastName();
					data[i][j++] = entry.getValue().GetSex();
					data[i][j++] = entry.getValue().GetMail();
					data[i][j++] = entry.getValue().GetPhone();
					data[i][j++] = entry.getValue().GetStreetNumber();
					data[i][j++] = entry.getValue().GetStreetName();
					data[i][j++] = entry.getValue().GetTown();
					data[i][j++] = entry.getValue().GetZipCode();
					data[i][j++] = entry.getValue().GetTimeStart();
					data[i][j++] = entry.getValue().GetTimeEnd();
					data[i][j++] = entry.getValue().GetMinutsSupply();
					if(entry.getValue().GetAffiliatedDepartment() != null)
						data[i++][j] = entry.getValue().GetAffiliatedDepartment().GetName();
					else
						data[i++][j] = null;
			}
		}
		else
			data = null;
		return data;
	}
	
	/**
	 * Loads the manager's array of the company
	 * @return an object containing the managers objects of the company
	 */
	public Object[][] LoadDataTableManager() {
		int i = 0;
		int j;
		Object[][] data;
		
		if(company != null)
		{
			data = new Object[company.GetManagementDpt().GetHashMapManager().size()][14];
			
			for(Entry<String, Manager> entry : company.GetManagementDpt().GetHashMapManager().entrySet())
			{
					j = 0;
					data[i][j++] = entry.getValue().GetId();
					data[i][j++] = entry.getValue().GetFirstName();
					data[i][j++] = entry.getValue().GetLastName();
					data[i][j++] = entry.getValue().GetSex();
					data[i][j++] = entry.getValue().GetMail();
					data[i][j++] = entry.getValue().GetPhone();
					data[i][j++] = entry.getValue().GetStreetNumber();
					data[i][j++] = entry.getValue().GetStreetName();
					data[i][j++] = entry.getValue().GetTown();
					data[i][j++] = entry.getValue().GetZipCode();
					data[i][j++] = entry.getValue().GetTimeStart();
					data[i][j++] = entry.getValue().GetTimeEnd();
					data[i][j++] = entry.getValue().GetMinutsSupply();
					if(entry.getValue().GetStdDepartmentLed() != null)
						data[i++][j] = entry.getValue().GetStdDepartmentLed().GetName();
					else
						data[i++][j] = null;
			}
		}
		else
			data = null;
		return data;
	}
	
	/**
	 * Loads the departments of the company
	 * @return an aray containing the departments ojects of the company
	 */
	public Object[][] LoadDataTableDepartment() {
		int i = 0;
		int j;
		Object[][] data;
		
		if(company != null)
		{
			data = new Object[company.GetListStdDepartment().size()][3];
			
			for(StdDepartment dpt : company.GetListStdDepartment())
			{
					j = 0;
					data[i][j++] = dpt.GetId();
					data[i][j++] = dpt.GetName();
					data[i++][j] = dpt.GetHashMapEmployee().size();
			}
		}
		else
			data = null;
		return data;
	}
	
	/**
	 * Loads the information of departments of the company 
	 * @return a list containing the IDs of the departments to display
	 */
	public List<String> LoadIdDepartment() {
		List<String> listIdDpt = new ArrayList<String>();
		if(company != null)
		{
			for(StdDepartment dpt : company.GetListStdDepartment())
				listIdDpt.add(dpt.GetId());
		}
		return listIdDpt;
	}
	
	/**
	 * Allows the user to add a new employee in the company with theses information
	 * 	if he don't already work for the company
	 * @param Id
	 * @param FirstName
	 * @param LastName
	 * @param Sex
	 * @param Mail
	 * @param Phone
	 * @param StreetNumber
	 * @param StreetName
	 * @param Town
	 * @param ZipCode
	 * @param TimeStart
	 * @param TimeEnd
	 * @param IdDpt
	 */
	public void InsertEmployee(String Id, String FirstName, String LastName, String Sex, String Mail, String Phone, String StreetNumber, String StreetName, String Town, String ZipCode, String TimeStart, String TimeEnd, String IdDpt) {
		try
		{
			int StreetNb = 0;
			if(StreetNumber.length() != 0)
				StreetNb = Integer.parseInt(StreetNumber);
			Employee e = new Employee(Id, FirstName, LastName, Enum.valueOf(Sex.class, Sex), Mail, Phone, StreetNb, StreetName, Town, ZipCode);
			e.SetTime(Integer.parseInt(TimeStart.substring(0, 2)), Integer.parseInt(TimeStart.substring(3, 5)), Integer.parseInt(TimeEnd.substring(0, 2)), Integer.parseInt(TimeEnd.substring(3, 5)));
			if(IdDpt == null)
				company.AddEmployee(e);
			else
			{
				StdDepartment department = null;
				for(int i=0; i < company.GetListStdDepartment().size(); i++)
					if(company.GetListStdDepartment().get(i).GetId().equals(IdDpt))
						department = company.GetListStdDepartment().get(i);
				company.AddEmployeeStdDepartement(department, e);
			}
			central.UpdateJTableEmployee();
			central.UpdateJTableDepartment();
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Makes an update of an employee status of the company if he exists
	 * @param Id
	 * @param FirstName
	 * @param LastName
	 * @param Sex
	 * @param Mail
	 * @param Phone
	 * @param StreetNumber
	 * @param StreetName
	 * @param Town
	 * @param ZipCode
	 * @param TimeStart
	 * @param TimeEnd
	 * @param IdDpt
	 */
	public void UpdateEmployee(String Id, String FirstName, String LastName, String Sex, String Mail, String Phone, String StreetNumber, String StreetName, String Town, String ZipCode, String TimeStart, String TimeEnd, String IdDpt) {
		try
		{
			int StreetNb = 0;
			if(StreetNumber.length() != 0)
				StreetNb = Integer.parseInt(StreetNumber);
			Employee e = company.GetHashMapEmployee().get(Id);
			e.SetFirstName(FirstName);
			e.SetLastName(LastName);
			e.SetSex(Enum.valueOf(Sex.class, Sex));
			e.SetMail(Mail);
			e.SetPhone(Phone);
			e.SetStreetNumber(StreetNb);
			e.SetStreetName(StreetName);
			e.SetTown(Town);
			e.SetZipCode(ZipCode);
			e.SetTime(Integer.parseInt(TimeStart.substring(0, 2)), Integer.parseInt(TimeStart.substring(3, 5)), Integer.parseInt(TimeEnd.substring(0, 2)), Integer.parseInt(TimeEnd.substring(3, 5)));
			if(IdDpt == null)
				e.SetAffiliatedDpt(null);
			else
			{
				StdDepartment department = null;
				for(int i=0; i < company.GetListStdDepartment().size(); i++)
					if(company.GetListStdDepartment().get(i).GetId().equals(IdDpt))
						department = company.GetListStdDepartment().get(i);
				e.SetAffiliatedDpt(department);
			}
			central.UpdateJTableEmployee();
			central.UpdateJTableDepartment();
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Searches an employee object with his ID
	 * @param Id
	 * @return the employee's object if he is found in the database of the company
	 */
	public Employee GetEmployeeFromId(String Id) {
		return company.GetHashMapEmployee().get(Id);
	}
	
	/**
	 * Searches an department object with his ID
	 * @param Id
	 * @return the department object if it is found
	 */
	public StdDepartment GetDepartmentFromId(String Id) {
		StdDepartment dpt = null;
		for(int i=0; i<company.GetListStdDepartment().size(); i++)
			if(company.GetListStdDepartment().get(i).GetId().equals(Id))
				dpt = company.GetListStdDepartment().get(i);
		return dpt;
	}
	
	/**
	 * Removes an employee object from the database of the company
	 * @param Id
	 */
	public void DeleteEmployee(String Id) {
		if(company.GetManagementDpt().GetHashMapManager().get(Id) == null)
			company.DeleteEmployeeFromCompany(company.GetHashMapEmployee().get(Id));
		else
			company.DeleteManagerFromCompany(company.GetManagementDpt().GetHashMapManager().get(Id));
		central.UpdateJTableEmployee();
		central.UpdateJTableManager();
		central.UpdateJTableDepartment();
	}
	
	/**
	 * Adds an a manager in the company if he don't exists in the company database
	 * @param Id
	 * @param FirstName
	 * @param LastName
	 * @param Sex
	 * @param Mail
	 * @param Phone
	 * @param StreetNumber
	 * @param StreetName
	 * @param Town
	 * @param ZipCode
	 * @param TimeStart
	 * @param TimeEnd
	 * @param IdDpt
	 * @param IdDptLed
	 */
	public void InsertManager(String Id, String FirstName, String LastName, String Sex, String Mail, String Phone, String StreetNumber, String StreetName, String Town, String ZipCode, String TimeStart, String TimeEnd, String IdDpt, String IdDptLed) {
		try
		{
			int StreetNb = 0;
			if(StreetNumber.length() != 0)
				StreetNb = Integer.parseInt(StreetNumber);
			StdDepartment departmentLed = null;
			for(int i=0; i < company.GetListStdDepartment().size(); i++)
				if(company.GetListStdDepartment().get(i).GetId().equals(IdDptLed))
					departmentLed = company.GetListStdDepartment().get(i);
			Manager m = new Manager(Id, FirstName, LastName, Enum.valueOf(Sex.class, Sex), departmentLed, Mail, Phone, StreetNb, StreetName, Town, ZipCode);
			m.SetTime(Integer.parseInt(TimeStart.substring(0, 2)), Integer.parseInt(TimeStart.substring(3, 5)), Integer.parseInt(TimeEnd.substring(0, 2)), Integer.parseInt(TimeEnd.substring(3, 5)));
			if(IdDpt == null)
				company.AddManager(m);
			else
			{
				StdDepartment department = null;
				for(int i=0; i < company.GetListStdDepartment().size(); i++)
					if(company.GetListStdDepartment().get(i).GetId().equals(IdDpt))
						department = company.GetListStdDepartment().get(i);
				company.AddManagerStdDepartment(department, m);
			}
			central.UpdateJTableManager();
			central.UpdateJTableEmployee();
			central.UpdateJTableDepartment();
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes a manager from the database of the company
	 * @param Id
	 */
	public void DeleteManager(String Id) {
		company.DeleteManagerFromCompany(company.GetManagementDpt().GetHashMapManager().get(Id));
		central.UpdateJTableEmployee();
		central.UpdateJTableManager();
		central.UpdateJTableDepartment();
	}
	
	/**
	 * Adds a department in the database of the company
	 * @param Id
	 * @param Name
	 */
	public void InsertDepartment(String Id, String Name) {
		try
		{
			StdDepartment dpt = new  StdDepartment(Id, Name);
			company.AddStdDepartment(dpt);
			central.UpdateJTableDepartment();
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a department object
	 * @param Id
	 * @param Name
	 */
	public void UpdateDepartment(String Id, String Name) {
		try
		{
			StdDepartment dpt = null;
			for(int i=0; i<company.GetListStdDepartment().size(); i++)
				if(company.GetListStdDepartment().get(i).GetId().equals(Id))
					dpt = company.GetListStdDepartment().get(i);
			dpt.SetName(Name);
			central.UpdateJTableDepartment();
			central.UpdateJTableEmployee();
			central.UpdateJTableManager();
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes a department from the company
	 * @param Id
	 */
	public void DeleteDepartment(String Id) {
		StdDepartment dptDelete = null;
		for(int i=0; i < company.GetListStdDepartment().size(); i++)
			if(company.GetListStdDepartment().get(i).GetId().equals(Id))
				dptDelete = company.GetListStdDepartment().get(i);
		company.RemoveStdDepartement(dptDelete);
		central.UpdateJTableDepartment();
		central.UpdateJTableEmployee();
		central.UpdateJTableManager();
	}
	
	/**
	 * Permits to serialize the company 
	 */
	public void Serialize() {
		new SerializerCompanyC(company);
	}
	
	/**
	 * Updates the view if there are some changes to do
	 * @param central
	 */
	public void UpdateView(CentralV central) {
		this.central = central;
	}
	
	/**
	 * Sets the connection between this application and the emulator application
	 * 		to receive the records
	 */
	public void OpenServerSocketRecord() {
		new Thread(new TCPServerRecord(8090, this)).start();
	}
	
	/**
	 * Sets the connection between this application and the emulator application
	 * 	to send the employees IDs
	 */
	public void OpenServerSocketEmployee() {
		new Thread(new TCPServerEmployee(8080, this)).start();
	}
	
	/**
	 * Permits to add a new record in the company
	 * @param record
	 * @throws CloneNotSupportedException: Avoid the application to send the same record twice
	 */
	public void AddRecordCompany(Record record) throws CloneNotSupportedException {
		company.AddRecord(record);
	}
	
	/**
	 * Returns the view after modifications to be displayed
	 * @return the view object to be displayed
	 */
	public CentralV GetView() {
		return central;
	}
	
	/**
	 * Permits to return an array of employees IDs
	 * @return A list of employees IDs
	 */
	public List<String> GetListIdEmployee() {
		List<String> listIdEmployee = new ArrayList<String>();
		for(Entry<String, Employee> entry : company.GetHashMapEmployee().entrySet())
			listIdEmployee.add(entry.getKey());
		return listIdEmployee;
	}
}
