package bouchenard.project.central.MODEL;

import java.io.Serializable;
import java.time.LocalTime;

public class RecordDay implements Serializable {

	private static final long serialVersionUID = 1L;

	private Employee employee;
	
	private LocalTime clockIn;
	
	private LocalTime clockOut;
	
	/**
	 * Constructor. initialize a RecordDay object. The employee is duplicated
	 * @param employee an Employee object
	 * @param clockIn a LocalTime object. It can be set to null
	 * @param clockOut a LocalTime object. It can be set to null
	 * @throws CloneNotSupportedException 
	 */
	public RecordDay(Employee employee, LocalTime clockIn, LocalTime clockOut) throws CloneNotSupportedException {
		this.employee = employee.clone();
		this.clockIn = clockIn;
		this.clockOut = clockOut;
	}
	
	/**
	 * Getter
	 * @return the employee's id
	 */
	public Employee GetEmployee() {
		return employee;
	}
	
	/**
	 * Getter
	 * @return a LocalTime object which is the clock in
	 */
	public LocalTime GetClockIn() {
		return clockIn;
	}
	
	/**
	 * Getter
	 * @return a LocalTime object which is the clock out
	 */
	public LocalTime GetClockOut() {
		return clockOut;
	}
	
	/**
	 * Setter
	 * @param clockIn the start time pointed by the employee
	 */
	public void SetClockIn(LocalTime clockIn) {
		this.clockIn = clockIn;
	}
	
	/**
	 * Setter
	 * @param clockOut the end time pointed by the employee
	 */
	public void SetClockOut(LocalTime clockOut) {
		this.clockOut = clockOut;
	}
}
