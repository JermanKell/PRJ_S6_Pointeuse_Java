package bouchenard.project.central.MODEL;


public class Manager extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StdDepartment departmentLed;
	
	
	/**
	 * Quick Constructor. initialize a Manager object
	 * @param id the manager's id
	 * @param firstName the manager's first name
	 * @param lastName the manager's last name
	 * @param sex the manager's sex
	 * @param stdDepartmentLed the StdDepartment led
	 * @warning the timeStart and timeEnd attributes are initialized to 0
	 */
	public Manager(String id, String firstName, String lastName, Sex sex, StdDepartment stdDepartmentLed) 
	{
		super(id, firstName, lastName, sex);
		this.departmentLed = stdDepartmentLed;
	}
	
	/**
	 * Long Constructor. initialize an Employee object
	 * @param id the manager's id
	 * @param firstName the manager's first name
	 * @param lastName the manager's last name
	 * @param sex the manager's sex
	 * @param stdDepartmentLed the StdDepartment led
	 * @param mail the manager's mail
	 * @param phone the manager's phone
	 * @param streetNumber the manager's street number
	 * @param streetName the manager's street name
	 * @param town the manager's town
	 * @param zipcode the manager's zip code
	 * @warning the timeStart and timeEnd attributes are initialized to 0
	 */
	public Manager(String id, String firstName, String lastName, Sex sex, StdDepartment stdDepartmentLed, String mail, String phone, int streetNumber, String streetName, String town, String zipCode) {
		super(id, firstName, lastName, sex, mail, phone, streetNumber, streetName, town, zipCode);
		this.departmentLed = stdDepartmentLed;
	}
	
	/**
	 * Getter
	 * @return a StdDepartment the StdDepartment led by this manager
	 */
	public StdDepartment GetStdDepartmentLed() {
		return departmentLed;
	}
	
	/**
	 * Setter
	 * @param newStdDepartmentLed the StdDepartment led by this manager
	 */
	public void SetStdDepartementLed(StdDepartment newStdDepartmentLed) {
		departmentLed = newStdDepartmentLed;
	}
	
	public String toString() {
		if(departmentLed != null)
			return super.toString() + "Department led: " + departmentLed.GetName() + "\n";
		else
			return super.toString() + "Department led: null\n";
	}
}
