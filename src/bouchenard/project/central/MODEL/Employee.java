package bouchenard.project.central.MODEL;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Employee extends Person implements Cloneable {

	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private LocalTime timeStart;

	private LocalTime timeEnd;
	
	private int minutsSupply;
	
	private StdDepartment affiliatedDpt;
	
	
	/**
	 * Quick Constructor. initialize an Employee object
	 * @param id the employee's id
	 * @param firstName the employee's first name
	 * @param lastName the employee's last name
	 * @param sex the employee's sex
	 * @warning the timeStart and timeEnd attributes are initialized to 0
	 */
	public Employee(String id, String firstName, String lastName, Sex sex) {
		this.id = id;
		SetFirstName(firstName);
		SetLastName(lastName);
		SetSex(sex);
		SetMail(null);
		SetPhone(null);
		SetStreetNumber(0);
		SetStreetName(null);
		SetTown(null);
		SetZipCode(null);
		timeStart = LocalTime.parse("00:00", DateTimeFormatter.ofPattern("HH:mm"));
		timeEnd = LocalTime.parse("00:00", DateTimeFormatter.ofPattern("HH:mm"));
		minutsSupply = 0;
		affiliatedDpt = null;
	}
	
	/**
	 * Long Constructor. initialize an Employee object
	 * @param id the employee's id
	 * @param firstName the employee's first name
	 * @param lastName the employee's last name
	 * @param sex the employee's sex
	 * @param mail the employee's mail
	 * @param phone the employee's phone
	 * @param streetNumber the employee's street number
	 * @param streetName the employee's street name
	 * @param town the employee's town
	 * @param zipcode the employee's zip code
	 * @warning the timeStart and timeEnd attributes are initialized to 0
	 */
	public Employee(String id, String firstName, String lastName, Sex sex, String mail, String phone, int streetNumber, String streetName, String town, String zipCode) {
		this(id, firstName, lastName, sex);
		SetMail(mail);
		SetPhone(phone);
		SetStreetNumber(streetNumber);
		SetStreetName(streetName);
		SetTown(town);
		SetZipCode(zipCode);
	}
	
	/**
	 * Getter
	 * @return the employee's id
	 */
	public String GetId() {
		return id;
	}
	
	/**
	 * Getter
	 * @return a LocalTime which matches the start time of the employee
	 */
	public LocalTime GetTimeStart() {
		return timeStart;
	}
	
	/**
	 * Getter
	 * @return a LocalTime which matches the end time of the employee
	 */
	public LocalTime GetTimeEnd() {
		return timeEnd;
	}
	
	/**
	 * Getter
	 * @return a int which matches to supply minuts of the employee. It can be negative if the employee is late
	 */
	public int GetMinutsSupply() {
		return minutsSupply;
	}
	
	/**
	 * Set the start and end time
	 * @param hour an hour
	 * @param minute the minutes
	 */
	public void SetTime(int startHours, int startMinuts, int endHours, int endMinuts) {
		if(startHours * 60 + startMinuts >= endHours * 60 + endMinuts)
			throw new IllegalArgumentException("The start time is upper than  the end time");
		if(startHours <0 || startHours >23)
			throw new IllegalArgumentException("Argument Hour invalid");
		if(startMinuts < 0 || startMinuts > 59)
			throw new IllegalArgumentException("Argument Minute invalid");
		
		timeStart = LocalTime.parse(ConvertHourMinuteToString(startHours) + ":" + ConvertHourMinuteToString(startMinuts), DateTimeFormatter.ofPattern("HH:mm"));
		
		if(endHours <0 || endHours >23)
			throw new IllegalArgumentException("Argument Hour invalid");
		if(endMinuts < 0 || endMinuts > 59)
			throw new IllegalArgumentException("Argument Minute invalid");
		
		timeEnd = LocalTime.parse(ConvertHourMinuteToString(endHours) + ":" + ConvertHourMinuteToString(endMinuts), DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	/**
	 * Setter
	 * @param minutes the supply minutes of the employee. It can be negative if the employee is late
	 */
	public void SetMinutsSupply(int minutes) {
		minutsSupply = minutes;
	}
	
	/**
	 * Check if the two Employee are equals
	 * @param e an Employee object to compare
	 * @return true if the two Employee are the same value
	 */
	public boolean equals(Employee e) {
		if(this.GetFirstName() == e.GetFirstName() &&
				this.GetLastName() == e.GetLastName() &&
				this.GetSex() == e.GetSex() &&
				this.GetMail() == e.GetMail() &&
				this.GetPhone() == e.GetPhone() &&
				this.GetStreetNumber() == e.GetStreetNumber() &&
				this.GetStreetName() == e.GetStreetName() &&
				this.GetTown() == e.GetTown() &&
				this.GetZipCode() == e.GetZipCode() &&
				id == e.id &&
				timeStart.equals(e.timeStart) &&
				timeEnd.equals(e.timeEnd) &&
				minutsSupply == e.minutsSupply)
			return true;
		return false;			
	}
	
	public String toString() {
		if(affiliatedDpt != null)
			return "Id: " + id + "\n" + super.toString() + "Start to work at: " + timeStart.toString() + "\nEnd to work at: " + timeEnd.toString() + "\nMinutsSupply: " + minutsSupply + "\nAffiliated to department: " + affiliatedDpt.GetName() +"\n";
		else
			return "Id: " + id + "\n" + super.toString() + "Start to work at: " + timeStart.toString() + "\nEnd to work at: " + timeEnd.toString() + "\nMinutsSupply: " + minutsSupply + "\nAffiliated to department: null\n";
	}
	
	/**
	 * Get the stdDepartment affiliated
	 * @return a stdDepartment object
	 */
	public StdDepartment GetAffiliatedDepartment() {
		return affiliatedDpt;
	}
	
	/**
	 * Set the affiliated department at this employee.
	 * @param affiliatedDpt, null if the employee is not any more affiliated to a department.
	 */
	public void SetAffiliatedDpt(StdDepartment affiliatedDpt) {
		this.affiliatedDpt = affiliatedDpt;
	}
	

	/**
	 * Duplicate an employee object.
	 * @return a copy
	 */
    @Override
    public Employee clone() throws CloneNotSupportedException {   
	return (Employee)super.clone();
    }  
	
	/**
	 * Private method. It convert an hour or minutes in good String format
	 * @param hourOrMinut an hour or minutes
	 */
	private String ConvertHourMinuteToString(int hourOrMinut) {
		String strHourOrMinute;
		if(hourOrMinut < 10)
			strHourOrMinute = "0" + Integer.toString(hourOrMinut);
		else
			strHourOrMinute = Integer.toString(hourOrMinut);
		
		return strHourOrMinute;
	}
}
