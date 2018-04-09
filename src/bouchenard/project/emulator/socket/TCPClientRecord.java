package bouchenard.project.emulator.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

import bouchenard.project.emulator.MODEL.TimeClock;

public class TCPClientRecord implements Runnable {
	// Socket object for the initialization of the connection
	private Socket socket = null;
	// IP address of the recipient application
	private String IP = null;
	// ID of the port for the transmission
	private int Port = 0;
	// A data object containing a list of records and a list of employees IDs
	private TimeClock timeclock;
	
	// Sets the connection settings 
	public TCPClientRecord(String AdIP, int NumPort, TimeClock timeclock) {
		IP = AdIP;
		Port = NumPort;
		this.timeclock = timeclock;
	}	
	
	/**
	 * Runs the connection and work on the data to send
	 */
	public void run() {
		try 
		{
			socket = new Socket(IP, Port);
			writeRecord();
			socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the data on the communication port to send them
	 */
	private void writeRecord() {
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(timeclock.GetListRecordToSend());
			timeclock.ClearListRecordToSend();
			out.flush();
			out.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}