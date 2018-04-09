package bouchenard.project.central.MODEL;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Company  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private Boss boss;

	private ManagementDpt managementDpt;

	private List<StdDepartment> listStdDepartment;

	private HashMap<String, Employee> hashmapEmployee;
	
	private HashMap<LocalDate, ListRecord> listListRecord;
	
	
	/**
	 * Constructor. initialize a Company object
	 * @param newName the company's name
	 */
	public Company(String newName)
	{
		name = newName;
		managementDpt = null;
		listStdDepartment = new ArrayList<StdDepartment>();
		boss = null;
		hashmapEmployee = new HashMap<String, Employee>();
		listListRecord = new HashMap<LocalDate, ListRecord>();
	}
	
	/**
	 * Getter
	 * @return the company's name
	 */
	public String GetName() {
		return name;
	}
	
	/**
	 * Getter
	 * @return a ManagementDpt object, the company's managementDpt
	 */
	public ManagementDpt GetManagementDpt() {
		return managementDpt;
	}
	
	/**
	 * Getter
	 * @return a list of StdDepartment objects, the list of StdDepartment of the company
	 */
	public List<StdDepartment> GetListStdDepartment() {
		return listStdDepartment;
	}
	
	/**
	 * Getter
	 * @return a Boss object, the company's boss
	 */
	public Boss GetBoss() {
		return boss;
	}
	
	/**
	 * Getter
	 * @return a hashmap list of Employee objects, the hashmap list of employees of the company
	 */
	public HashMap<String,Employee> GetHashMapEmployee() {
		return hashmapEmployee;
	}
	
	/**
	 * Getter
	 * @return the list of list of Record objects order by date
	 */
	public HashMap<LocalDate, ListRecord> GetListRecord() {
		return listListRecord;
	}
	
	/**
	 * Add a record in the list with the time of the record and update the MinutsSupply attribut of the employee in the company. If the list doesn't exist it is created.
	 * @param a Record objects
	 * @return true if the record has been added else false
	 * @throws CloneNotSupportedException 
	 * @warning Only two record per Employee can be added in the same list, one for the start time and one for the end time
	 */
	public void AddRecord(Record record) throws CloneNotSupportedException {
		
		Employee e = hashmapEmployee.get(record.GetIdEmployee());
		
		if(e == null)
			throw new IllegalArgumentException("This record is not valide because the employee's id doesn't exist in the company");
		
		if(listListRecord.get(record.GetDate().toLocalDate()) == null)
			listListRecord.put(record.GetDate().toLocalDate(), new ListRecord());
		
		if(listListRecord.get(record.GetDate().toLocalDate()).GetNumberRecordEmployee(record.GetIdEmployee()) == 0)
		{
			e.SetMinutsSupply(e.GetMinutsSupply() + (int)Duration.between(record.GetDate().toLocalTime(), e.GetTimeStart()).toMinutes());
			listListRecord.get(record.GetDate().toLocalDate()).GetListRecord().add(new RecordDay(e, record.GetDate().toLocalTime(), null));
		}
		else
			if(listListRecord.get(record.GetDate().toLocalDate()).GetNumberRecordEmployee(record.GetIdEmployee()) == 1)
			{
				e.SetMinutsSupply(e.GetMinutsSupply() + (int)Duration.between(e.GetTimeEnd(), record.GetDate().toLocalTime()).toMinutes());
				RecordDay recordDay = listListRecord.get(record.GetDate().toLocalDate()).GetRecordDayFromIdEmployee(record.GetIdEmployee());
				recordDay.SetClockOut(record.GetDate().toLocalTime());
			}
			else
				throw new IllegalArgumentException("There are already two record for this employee's id");
	}
	
	/**
	 * Setter
	 * @param DefineBoss the company's Boss
	 */
	public void SetBoss(Boss DefineBoss) {
		boss = DefineBoss;
	}
	
	/**
	 * Setter
	 * @param newManagementDpt the company's ManagementDpt
	 */
	public void SetManagementDpt(ManagementDpt newManagementDpt) {
		managementDpt = newManagementDpt;
	}
	
	/**
	 * Add a new StdDepartment into the company
	 * @param newStdDepartment an StdDepartment object
	 */
	public void AddStdDepartment(StdDepartment newStdDepartment) {
		for(int i=0; i < listStdDepartment.size(); i++)
			if(newStdDepartment.GetId().equals(listStdDepartment.get(i).GetId()))
				throw new IllegalArgumentException("A stdDepartment with the same id already exist.");
		listStdDepartment.add(newStdDepartment);
	}
	
	/**
	 * Search and remove a StdDepartment into the company
	 * @param removeStdDepartment the StdDepartment object to remove
	 */
	public void RemoveStdDepartement(StdDepartment removeStdDepartment) {
		int loop=0;
		boolean found = false;
		
		while(found == false && loop < listStdDepartment.size())
		{
			if(listStdDepartment.get(loop).GetName().equals(removeStdDepartment.GetName()))
			{
				listStdDepartment.get(loop).clearListEmployee();
				listStdDepartment.remove(loop);
				
				for(Entry<String, Manager> entry : managementDpt.GetHashMapManager().entrySet())
					if(entry.getValue().GetStdDepartmentLed() != null)
						if(entry.getValue().GetStdDepartmentLed().GetId().equals(removeStdDepartment.GetId()))
							entry.getValue().SetStdDepartementLed(null);
				found = true;
			}
			loop++;
		}
	}
	
	/**
	 * Add a new Employee into the hashmap list of the company
	 * @param newEmployee the Employee object to add
	 */
	public void AddEmployee(Employee newEmployee) {
		if(hashmapEmployee.get(newEmployee.GetId()) != null)
			throw new IllegalArgumentException("An employee with the same id already exist in the company");
		hashmapEmployee.put(newEmployee.GetId(), newEmployee);
		newEmployee.SetAffiliatedDpt(null);
	}
	
	/**
	 * Add a new Employee into the hashmap list of the company and in the specified StdDepartment
	 * @param objectStdDepartment the specified StdDepartment where the Employee must be added 
	 * @param newEmployee the Employee object to add
	 */
	public void AddEmployeeStdDepartement(StdDepartment objectStdDepartment, Employee newEmployee) {
		boolean found =  false;
		int loop = 0;
		
		if(hashmapEmployee.get(newEmployee.GetId()) != null)
			throw new IllegalArgumentException("An employee with the same id already exist in the company");
		if(objectStdDepartment != null)
		{
			while(found == false && loop < listStdDepartment.size())
			{
				if(listStdDepartment.get(loop).GetName() == objectStdDepartment.GetName())
				{
					hashmapEmployee.put(newEmployee.GetId(), newEmployee);
					listStdDepartment.get(loop).AddEmployee(newEmployee);
					found = true;
				}
				loop++;
			}
		}
		if (found == false)
			throw new IllegalArgumentException("The department has not been found in the company");
	}
	
	/**
	 * Search and delete totally an Employee in the company
	 * @param deleteEmployee the Employee object to delete
	 */
	public void DeleteEmployeeFromCompany(Employee deleteEmployee) {
		boolean found =  false;
		int loop = 0;
		
		if(hashmapEmployee.remove(deleteEmployee.GetId()) == null)
			throw new IllegalArgumentException("This employee doesn't exists in the company");
		else
		{
			while(found == false && loop < listStdDepartment.size())
			{
					if(listStdDepartment.get(loop).RemoveEmployee(deleteEmployee) == true)
						found = true;
					loop++;
			}
		}
	}
	
	/**
	 * Add a new Manager into the hashmap list of the company and in the hashmap list of managementDpt
	 * @param newManager the Manager object to add
	 * @warning if the managementDpt is not set the method call an exception
	 */
	public void AddManager(Manager newManager) {
		if(managementDpt == null)
			throw new IllegalArgumentException("The company has currently no management department");
		
		AddEmployee(newManager);
		managementDpt.AddManager(newManager);
	}
	
	/**
	 * Add a new Manager into the hashmap list of the company, in the specified StdDepartment and in the hashmap list of managementDpt
	 * @param objectStdDepartment the specified StdDepartment where the Manager must be added 
	 * @param newManager the Manager object to add
	 * @warning if the managementDpt is not set or the StdDepartment is not found, the method call an exception
	 */
	public void AddManagerStdDepartment(StdDepartment objectStdDepartment, Manager newManager) {
		if(managementDpt == null)
			throw new IllegalArgumentException("The company has currently no management department");
		
		AddEmployeeStdDepartement(objectStdDepartment, newManager);
		managementDpt.AddManager(newManager);
	}
	
	/**
	 * Search and delete totally a Manager in the company
	 * @param deleteManager the Manager object to delete
	 * @return true if the manager has been deleted else false
	 */
	public void DeleteManagerFromCompany(Manager deleteManager) {
		if(managementDpt.RemoveManager(deleteManager) != true)
			throw new IllegalArgumentException("This manager doesn't exists in the company");
		DeleteEmployeeFromCompany(deleteManager);
	}
	
	/**
	 * Delete a list if it exist.
	 * @param date a LocalDate object
	 */
	public void DeleteListRecord(LocalDate date) {
		if(listListRecord.get(date) != null)
			listListRecord.remove(date);
		else
			throw new IllegalArgumentException("This list doesn't exists in the company");
	}
	
	public void DeleteRecordEmployee(String IdEmployee) {
		for(Entry<LocalDate, ListRecord> entry : listListRecord.entrySet())
		{
			ListRecord listrecord = entry.getValue();
			for(int j=0; j < listrecord.GetListRecord().size(); j++)
			{
				RecordDay recordday = listrecord.GetListRecord().get(j);
				if(recordday.GetEmployee().GetId() == IdEmployee)
					listrecord.GetListRecord().remove(j);
			}
		}
			
	}
	
	
	public String toString() {
		int loop;
		
		String str = "Company: " + name + "\nBoss:\n" + boss.toString() + "\nManagement department:\n" + managementDpt.toString() + "List of departements:\n\n";
		
		for(loop=0; loop < listStdDepartment.size(); loop++)
			str += listStdDepartment.get(loop).toString();
		
		str += "List of employees of the company:\n\n";
		for(Map.Entry<String, Employee> entry : hashmapEmployee.entrySet())
			str = str + entry.getValue().toString() + "\n";
		
		return str;
	}
}
