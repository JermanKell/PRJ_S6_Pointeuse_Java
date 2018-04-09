package bouchenard.project.central.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.List;

import bouchenard.project.central.CONTROLLER.*;
import bouchenard.project.central.MODEL.*;

public class TCPServerRecord implements Runnable {

	// Sets the socket's server
	private ServerSocket socketserver = null;
	// Active socket of the server
	private Socket socket = null;
	// Controller of the main application view
	private ControllerC controller;
	
	/**
	 * Constructor to build the connection
	 * @param NumPort
	 * @param controller
	 */
	public TCPServerRecord(int NumPort, ControllerC controller) {
		try
		{
			socketserver = new ServerSocket(NumPort);
			this.controller = controller;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	public void run() {
		
		try 
		{
			while(true)
			{
				socket = socketserver.accept();
				readRecord();
	            socket.close();
	        }
	        
		} 
		catch (IOException e) 
		{
			try 
			{
				socketserver.close();
			} 
			catch (IOException e2) 
			{
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * Receives a records array to display them
	 */
	@SuppressWarnings("unchecked")
	private void readRecord() {
		try
		{
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	Object object = in.readObject();
        	List<Record> listRecord = (List<Record>)object;
        	try
        	{
        		for(int i=0; i<listRecord.size(); i++)
        			controller.AddRecordCompany(listRecord.get(i));
        		controller.GetView().UpdateJTableRecord();
        		controller.GetView().UpdateJTableEmployee();
			} 
        	catch (CloneNotSupportedException e) 
        	{
				e.printStackTrace();
			}
        	in.close();
		}
		catch (IOException | ClassNotFoundException | IllegalArgumentException e)
		{
			e.printStackTrace();
		}
	}
}
