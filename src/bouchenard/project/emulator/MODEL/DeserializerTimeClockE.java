package bouchenard.project.emulator.MODEL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializerTimeClockE {
	// Object which contains all records in function of the employees IDs
	private TimeClock timeclock;
	
	/**
	 * Deserializes the object
	 */
	public  DeserializerTimeClockE() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		TimeClock timeclock = null;
		
		try
		{
			fis = new FileInputStream(new File("dataTimeClock.txt"));
			ois = new ObjectInputStream(fis);
			timeclock = (TimeClock)ois.readObject();
			
		}
		catch(ClassNotFoundException | IOException  e) 
		{
			e.printStackTrace();
		}
		finally
		{
			this.timeclock = timeclock;
		}
	}
	
	/**
	 * Returns a TimeClock object
	 * @return TimeClock object
	 */
	public TimeClock GetTimeClock() {
		return timeclock;
	}
}