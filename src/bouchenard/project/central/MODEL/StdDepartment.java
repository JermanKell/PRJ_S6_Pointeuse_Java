package bouchenard.project.central.MODEL;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StdDepartment extends Department {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Employee> hashmapEmployee;
	
	/**
	 * Constructor. initialize a StdDepartment object
	 * @param newId the StdDepartment's id
	 * @param newName the StdDepartment's name
	 */
	public StdDepartment(String newId, String newName) {
		SetId(newId);
		SetName(newName);
		hashmapEmployee = new HashMap<String, Employee>();	
	}

	/**
	 * Getter
	 * @return the hashmap list of the StdDepartment
	 */
	public HashMap<String, Employee> GetHashMapEmployee() {
		return hashmapEmployee;
	}
	
	/**
	 * Add a new Employee in the hashmap list of the StdDepartment
	 * @param newEmployee the Employee object to add
	 */
	public void AddEmployee(Employee newEmployee) {
		newEmployee.SetAffiliatedDpt(this);
		hashmapEmployee.put(newEmployee.GetId(), newEmployee);
	}
	
	/**
	 * Try to remove an Employee in the hashmap list of the StdDepartment
	 * @param removeEmployee the Employee object to remove
	 * @return true if the Employee has been removed else false
	 */
	public boolean RemoveEmployee(Employee removeEmployee) {
		if(hashmapEmployee.remove(removeEmployee.GetId()) != null)
		{
			removeEmployee.SetAffiliatedDpt(null);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Remove all emloyee from the stdDepartment
	 */
	public void clearListEmployee() {
		for(Entry<String, Employee> entry : hashmapEmployee.entrySet())
			entry.getValue().SetAffiliatedDpt(null);
		hashmapEmployee.clear();
	}
	
	public String toString() {
		String str = super.toString();
		
		str += "List of employees of this department:\n\n";
		for(Map.Entry<String, Employee> entry : hashmapEmployee.entrySet()) {
			str = str + entry.getValue().toString() + "\n";
		}
		return str;
	}
}
