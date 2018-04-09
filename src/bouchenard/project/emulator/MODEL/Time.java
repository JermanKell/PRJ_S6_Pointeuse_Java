package bouchenard.project.emulator.MODEL;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
	
	private LocalTime time;
	
	/**
	 * Constructor. initialize a Time object
	 * @param Hour the hour
	 * @param Minute the minutes
	 * @warning the time is rounded off at the quarter of an hour the closer
	 */
	public Time(int Hour, int Minute) {
		String StrHour;
		String StrMinute;
		
		if(Hour <0 || Hour >23)
			throw new IllegalArgumentException("Argument Hour invalid");
		if(Minute < 0 || Minute > 59)
			throw new IllegalArgumentException("Argument Minute invalid");
		
		if(Minute < 8)
			Minute = 0;
		else
			if(Minute < 23)
				Minute = 15;
			else
				if(Minute < 38)
					Minute = 30;
				else
					if(Minute < 53)
						Minute = 45;
					else
					{
						Minute = 0;
						Hour += 1;
					}
		StrHour = ConvertHourMinuteToString(Hour);
		StrMinute = ConvertHourMinuteToString(Minute);
		time = LocalTime.parse(StrHour + ":" + StrMinute, DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	/**
	 * Getter
	 * @return a LocalTime object
	 */
	public LocalTime GetTime() {
		return time;
	}
	
	public String toString() {
		if(time.getMinute() < 10)
			return time.getHour() + ":0" + time.getMinute();
		else
			return time.getHour() + ":" + time.getMinute();
	}
	
	private String ConvertHourMinuteToString(int HourOrMinut) {
		String StrHourOrMinute;
		if(HourOrMinut < 10)
			StrHourOrMinute = "0" + Integer.toString(HourOrMinut);
		else
			StrHourOrMinute = Integer.toString(HourOrMinut);
		
		return StrHourOrMinute;
	}
}
