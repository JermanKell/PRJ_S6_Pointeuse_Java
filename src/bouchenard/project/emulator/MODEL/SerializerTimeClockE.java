package bouchenard.project.emulator.MODEL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializerTimeClockE {
	/**
	 * Serializes a TimeClock object
	 * @param timeclock object which contains an array of the employees IDs and the records
	 */
	public SerializerTimeClockE(TimeClock timeclock){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
			
		try
		{
			fos = new FileOutputStream(new File("dataTimeClock.txt"));
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(timeclock);
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