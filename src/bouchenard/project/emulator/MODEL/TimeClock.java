package bouchenard.project.emulator.MODEL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bouchenard.project.central.MODEL.Record;


public class TimeClock implements Serializable {

	private static final long serialVersionUID = 5140886212077922070L;
	// List of the employees IDs
	private List<String> listEmployeeId;
	// List of the records
	private List<Record> listRecordToSend;
	
	/**
	 * Initializes the arrays
	 */
	public TimeClock() {
		listEmployeeId = new ArrayList<String>();
		listRecordToSend = new ArrayList<Record>(); 
	}
	
	/**
	 * Updates the list of IDs
	 * @param listEmployeeId  list of the employees IDs
	 */
	public void UpdateList(List<String> listEmployeeId) {
		this.listEmployeeId = listEmployeeId;
	}
	
	/**
	 * Returns the list of employees IDs
	 * @return list of employees IDs
	 */
	public List<String> GetList() {
		return listEmployeeId;
	}
	
	/**
	 * Returns the list of records
	 * @return the list of records
	 */
	public List<Record> GetListRecordToSend() {
		return listRecordToSend;
	}
	
	/**
	 * Adds a record in the records array
	 * @param record Record to add
	 */
	public void AddRecordToSend(Record record) {
		listRecordToSend.add(record);
	}
	
	/**
	 * Clear the list of records to send to the main application
	 */
	public void ClearListRecordToSend() {
		listRecordToSend.clear();
	}
}
