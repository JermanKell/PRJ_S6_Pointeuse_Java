package bouchenard.project.central.MODEL;

import java.io.Serializable;

public abstract class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private Sex sex;

	private String mail;

	private String phone;

	private int streetNumber;

	private String streetName;

	private String town;

	private String zipCode;
	
	
	/**
	 * Getter
	 * @return the Person's first name
	 */
	public String GetFirstName() 
	{
		return firstName;
	}
	
	/**
	 * Getter
	 * @return the Person's last name
	 */
	public String GetLastName() 
	{
		return lastName;
	}
	
	/**
	 * Getter
	 * @return the Person's sex
	 */
	public String GetSex()
	{
		return sex.toString();
	}
	
	/**
	 * Getter
	 * @return the Person's mail
	 */
	public String GetMail()
	{
		return mail;
	}
	
	/**
	 * Getter
	 * @return  the Person's phone
	 */
	public String GetPhone()
	{
		return phone;
	}
	
	/**
	 * Getter
	 * @return the Person's street number
	 */
	public int GetStreetNumber()
	{
		return streetNumber;
	}
	
	/**
	 * Getter
	 * @return the Person's street name
	 */
	public String GetStreetName()
	{
		return streetName;
	}
	
	/**
	 * Getter
	 * @return the Person's town
	 */
	public String GetTown()
	{
		return town;
	}
	
	/**
	 * Getter
	 * @return the Person's zip code
	 */
	public String GetZipCode()
	{
		return zipCode;
	}
	
	/**
	 * Setter
	 * @param newFirstName the Person's first name
	 */
	public void SetFirstName(String newFirstName) 
	{
		firstName = newFirstName;
	}
	
	/**
	 * Setter
	 * @param newLastName the Person's last name
	 */
	public void SetLastName(String newLastName) 
	{
		lastName = newLastName;
	}
	
	/**
	 * Setter
	 * @param newMail the Person's sex
	 */
	public void SetSex(Sex newSex) 
	{
		sex = newSex;
	}
	
	/**
	 * Setter
	 * @param newMail the Person's mail
	 */
	public void SetMail(String newMail)
	{
		mail = newMail;
	}
	
	/**
	 * Setter
	 * @param newPhone the Person's phone
	 */
	public void SetPhone(String newPhone)
	{	
		phone = newPhone;
	}
	
	/**
	 * Setter
	 * @param newtreetNumber the Person's street number
	 */
	public void SetStreetNumber(int newtreetNumber)
	{
		streetNumber = newtreetNumber;
	}
	
	/**
	 * Setter
	 * @param newStreetName the Person's street name
	 */
	public void SetStreetName(String newStreetName)
	{
		streetName = newStreetName;
	}
	
	/**
	 * Setter
	 * @param newTown the Person's town
	 */
	public void SetTown(String newTown)
	{
		town = newTown;
	}
	
	/**
	 * Setter
	 * @param newZipCode the Person's zip code
	 */
	public void SetZipCode(String newZipCode)
	{
		zipCode = newZipCode;
	}
	
	public String toString() {
		return "Firstname: " + firstName + "\nLastname: " + lastName + "\nSex: " + sex.toString() + "\nMail: " + mail + "\nPhone: " + phone + "\nStreetNumber: " + streetNumber + "\nStreetName: " + streetName + "\nTown: " + town + "\nZipCode: " + zipCode + "\n"; 
	}
}
