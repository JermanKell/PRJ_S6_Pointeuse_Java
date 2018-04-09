package bouchenard.project.central.MODEL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializerCompanyC {
	
	public SerializerCompanyC(Company objectCompany){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
			
		try
		{
			fos = new FileOutputStream(new File("dataCompany.txt"));
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(objectCompany);
		}
		catch(IOException e) 
		{
	         e.printStackTrace();
	    } 
		finally 
		{
			try 
			{
				if(oos != null)
					oos.close();
	        	 
	            if (fos != null)
	               fos.close();
	        } 
	         catch (IOException e) 
	         {
	            e.printStackTrace();
	         }
	    }
		
		
	}
}
