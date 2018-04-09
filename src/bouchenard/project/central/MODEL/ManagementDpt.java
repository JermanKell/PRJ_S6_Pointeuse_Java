package bouchenard.project.central.MODEL;

import java.util.HashMap;
import java.util.Map;

public class ManagementDpt extends Department {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String,Manager> hashmapManager;
	
	/**
	 * Constructor. initialize a ManagementDpt object
	 * @param newId the ManagementDpt's id
	 * @param newName the ManagementDpt's name
	 */
	public ManagementDpt(String newId, String newName) {
		SetId(newId);
		SetName(newName);
		hashmapManager = new HashMap<String, Manager>();	
	}
	
	/**
	 * Getter
	 * @return the hashmap list of the ManagementDpt
	 */
	public HashMap<String,Manager> GetHashMapManager() {
		return hashmapManager;
	}
	
	/**
	 * Add a new Manager in the hashmap list of the ManagementDpt
	 * @param newManager the Manager object to add
	 */
	public void AddManager(Manager newManager) {
		hashmapManager.put(newManager.GetId(), newManager);
	}
	
	/**
	 * Try to remove a Manager in the hashmap list of the ManagementDpt
	 * @param removeManager the Manager object to remove
	 * @return true if the Manager has been removed else false
	 */
	public boolean RemoveManager(Employee removeManager) {
		if(hashmapManager.remove(removeManager.GetId()) != null)
			return true;
		else
			return false;
	}
	
	public String toString() {
		String str = super.toString();
		
		str += "List of managers of the department:\n\n";
		for(Map.Entry<String, Manager> entry : hashmapManager.entrySet()) {
			str = str + entry.getValue().toString() + "\n";
		}
		return str;
	}

}
