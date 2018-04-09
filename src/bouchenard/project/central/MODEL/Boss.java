package bouchenard.project.central.MODEL;


public class Boss extends Person {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Quick Constructor. initialize a Boss object
	 * @param firstName the boss's first name
	 * @param lastName the boss's last name
	 * @param sex the boss's sex
	 */
	public Boss(String firstName, String lastName, Sex sex) 
	{
		SetFirstName(firstName);
		SetLastName(lastName);
		SetSex(sex);
		SetMail(null);
		SetPhone(null);
		SetStreetNumber(0);
		SetStreetName(null);
		SetTown(null);
		SetZipCode(null);
	}
	
	/**
	 * Long Constructor. initialize a Boss object
	 * @param firstName the boss's first name
	 * @param lastName the boss's last name
	 * @param sex the boss's sex
	 * @param mail the boss's mail
	 * @param phone the boss's phone
	 * @param streetNumber the boss's street number
	 * @param streetName the boss's street name
	 * @param town the boss's town
	 * @param zipcode the boss's zip code
	 */
	public Boss(String firstName, String lastName, Sex sex, String mail, String phone, int streetNumber, String streetName, String town, String zipCode)
	{
		this(firstName, lastName, sex);
		SetMail(mail);
		SetPhone(phone);
		SetStreetNumber(streetNumber);
		SetStreetName(streetName);
		SetTown(town);
		SetZipCode(zipCode);
	}
}
