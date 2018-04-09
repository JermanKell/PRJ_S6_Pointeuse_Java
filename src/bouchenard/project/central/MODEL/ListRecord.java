package bouchenard.project.central.MODEL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<RecordDay> listRecord;

	public ListRecord() {
		listRecord = new ArrayList<RecordDay>();
	}
	
	/**
	 * Getter
	 * @return a list of Record objects
	 */
	public List<RecordDay> GetListRecord() {
		return listRecord;
	}
	
	/**
	 * Getter that return the recordDay for an employee
	 * @param idEmployee the employee'is
	 * @return a RecordDay which belong to the employee
	 */
	public RecordDay GetRecordDayFromIdEmployee(String idEmployee) {
		for(int i=0; i < listRecord.size(); i++)
			if(listRecord.get(i).GetEmployee().GetId().equals(idEmployee))
				return listRecord.get(i);
		throw new IllegalArgumentException("No employee's id were found");
	}
	
	/**
	 * Count the number of record in the list for an employee's id.
	 * @param idEmployee the employee's id to search and count his number in the list
	 * @return the number of record belong to the param idEmployee
	 */
	public int GetNumberRecordEmployee(String idEmployee) {
		int count = 0;
		for(int i=0; i < listRecord.size(); i++)
		{
			if(listRecord.get(i).GetEmployee().GetId().equals(idEmployee))
			{
				if(listRecord.get(i).GetClockOut() == null || listRecord.get(i).GetClockIn() == null)
					count++;
				else
					count += 2;
			}
			
		}
		return count;
	}
	
	/**
	 * Get a list of recordDay which contain only one record
	 * @return a List<RecordDay>
	 */
	public List<RecordDay> GetRecordDaySingleRecord() {
		List<RecordDay> listSingleRecord = new ArrayList<RecordDay>();
		
		for(int i=0; i < listRecord.size(); i++)
			if(listRecord.get(i).GetClockIn() != null && listRecord.get(i).GetClockOut() == null)
				listSingleRecord.add(listRecord.get(i));
		return listSingleRecord;
	}
}