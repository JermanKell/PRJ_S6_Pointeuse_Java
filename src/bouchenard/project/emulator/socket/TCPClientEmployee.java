package bouchenard.project.emulator.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.List;

import bouchenard.project.emulator.CONTROLLER.ControllerE;

public class TCPClientEmployee implements Runnable {
	// Socket object for the initialization of the connection
	private Socket socket = null;
	// IP address of the recipient application
	private String IP = null;
	// ID of the port for the transmission
	private int Port = 0;
	// Controller of the emulator view
	private ControllerE controller;
	
	// Sets the connection settings
	public TCPClientEmployee(String AdIP, int NumPort, ControllerE controller) {
		IP = AdIP;
		Port = NumPort;
		this.controller = controller;
	}	
	
	/**
	 * Launching of the connection
	 */
	public void run() {
		try 
		{
			socket = new Socket(IP, Port);
			readListIdEmployee();
			socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the available data who were received on the port
	 */
	@SuppressWarnings("unchecked")
	private void readListIdEmployee() {
		try
		{
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	Object object = in.readObject();
        	List<String> list = (List<String>)object;
        	
        	if(list != null)
        	{
        		controller.UpdateListEmployee(list);
        		controller.GetView().UpdateComboBoxListIdEmployee();
        	}
        	in.close();
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
}
