package bouchenard.project.central.MODEL;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Record implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idEmployee;
	
	private LocalDateTime date;
	
	/**
	 * Constructor. initialize a record object
	 * @param The employee's id of the company
	 * @param objectDate a LocalDateTime object which matches the clock-in or the clock-out pointed by the employee
	 */
	public Record(String idEmployee, LocalDateTime objectDate) {
		this.idEmployee = idEmployee;
		date = objectDate;
	}
	
	/**
	 * Getter
	 * @return The employee's id of the company
	 */
	public String GetIdEmployee() {
		return idEmployee;
	}
	
	/**
	 * Getter
	 * @return a LocalDateTime object which matches the clock-in or the clock-out pointed by the employee
	 */
	public LocalDateTime GetDate() {
		return date;
	}
	
	
	/**
	 * Setter
	 * @param newDate a LocalDateTime object which matches the clock-in or the clock-out pointed by the employee
	 */
	public void SetDate(LocalDateTime newDate) {
		date = newDate;
	}
	
	public String toString() {
		return idEmployee + " " + date.toString();
	}

}
