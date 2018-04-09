package bouchenard.project.emulator.VUE;

import java.util.Calendar;
import java.util.Locale;

import javax.swing.JLabel;

import bouchenard.project.emulator.MODEL.*;

public class Horloge implements Runnable {
	/**
	 * Elements for the display of the time on the view
	 */
	private Calendar calendar;
	private String date;
	private String hour;
	private String hourRounded;
	private JLabel labelDate;
	private JLabel labelTime;
	private JLabel labelRounded;

	/**
	 * Recovers the time data to display them
	 * @param labelDate
	 * @param labelTime
	 * @param labelRounded
	 */
	public Horloge(JLabel labelDate, JLabel labelTime, JLabel labelRounded) {
		this.labelDate = labelDate;
		this.labelTime = labelTime;
		this.labelRounded = labelRounded;
	}
	
	/**
	 * Displays of the time data
	 */
	public void run() {
		while(true)
	    {
			calendar = Calendar.getInstance();
	    	
	    	hour = calendar.get(Calendar.HOUR_OF_DAY) + ":";
	    	if(calendar.get(Calendar.MINUTE) < 10)
	    		hour += "0" + calendar.get(Calendar.MINUTE);
	    	else
	    		hour += calendar.get(Calendar.MINUTE);
	    	
	    	hourRounded = "...let's say " + new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)).toString();
	    	
	    	date = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + ":";
	    	date += calendar.get(Calendar.DAY_OF_MONTH) + "th, ";
	    	date += calendar.get(Calendar.YEAR);
			
	    	labelDate.setText(date);
	    	labelTime.setText(hour);
	    	labelRounded.setText(hourRounded);
		    try 
		    {
		    	Thread.sleep(1000);
		    } 
		    catch (InterruptedException e)
		    {
		    	e.printStackTrace();
		    }
	    }
	}
}