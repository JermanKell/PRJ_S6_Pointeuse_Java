package bouchenard.project.emulator.CONTROLLER;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import bouchenard.project.central.MODEL.Record;
import bouchenard.project.emulator.MODEL.*;
import bouchenard.project.emulator.VUE.*;
import bouchenard.project.emulator.socket.*;

public class ControllerE {
	// Emulator application
	private Emulator emulator;
	// Object to store the records and the employees IDs
	private TimeClock timeclock;
	
	/**
	 * Sets the IDs of the employees who can be recorded
	 */
	public ControllerE() {
		emulator = null;
		/*List<String> list = new ArrayList<String>();
		list.add("acqq9"); list.add("azq3"); list.add("rvv12"); list.add("maa1"); list.add("mbb2");
		TimeClock tc = new TimeClock();
		tc.UpdateList(list);
		timeclock = tc;*/
		
		//timeclock = new DeserializerTimeClockE().GetTimeClock();
		if(timeclock == null)
			timeclock = new TimeClock();
	}
	
	/**
	 * Loads the IDs of the employees
	 * @return the list of the employees IDs
	 */
	public List<String> LoadListEmployeeId() {
			return timeclock.GetList();
	}
	
	/**
	 * Serializes the information 
	 */
	public void Serialize() {
		new SerializerTimeClockE(timeclock);
	}
	
	/**
	 * Permits to update the view
	 * @param emulator Emulator application
	 */
	public void UpdateView(Emulator emulator) {
		this.emulator = emulator;
	}
	
	/**
	 * Returns the emulator view
	 * @return the emulator object
	 */
	public Emulator GetView() {
		return emulator;
	}
	
	/**
	 * Updates the employees IDs array
	 * @param listEmployee IDs of the employees to store
	 */
	public void UpdateListEmployee(List<String> listEmployee) {
		timeclock.UpdateList(listEmployee);
	}
	
	/**
	 * Sends the ID of the employee who was recorded
	 * @param idEmployee ID of the employee
	 */
	public void SendRecordClientSocket(String idEmployee) {
		Record record = new Record(idEmployee, LocalDateTime.of(LocalDate.now(), new Time(LocalTime.now().getHour(), LocalTime.now().getMinute()).GetTime()));
		timeclock.AddRecordToSend(record);
		new Thread(new TCPClientRecord("localhost", 8090, timeclock)).start();
	}
	
	/**
	 * Updates the employees IDs array for the record
	 */
	public void UpdateInfoEmployeeClientSocket() {
		new Thread(new TCPClientEmployee("localhost", 8080, this)).start();
	}
}
