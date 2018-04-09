package bouchenard.project.central.MODEL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializerCompanyC {

	private Company company;
	
	public  DeserializerCompanyC() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Company company = null;
		
		try
		{
			fis = new FileInputStream(new File("dataCompany.txt"));
			ois = new ObjectInputStream(fis);
			company = (Company)ois.readObject();
			
		}
		catch(ClassNotFoundException | IOException  e) 
		{
			e.printStackTrace();
		}
		finally
		{
			this.company = company;
		}
	}
	
	public Company GetCompany() {
		return company;
	}
}
