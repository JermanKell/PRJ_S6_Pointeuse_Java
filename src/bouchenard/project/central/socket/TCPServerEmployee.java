package bouchenard.project.central.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import bouchenard.project.central.CONTROLLER.ControllerC;

public class TCPServerEmployee implements Runnable {

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
	public TCPServerEmployee(int NumPort, ControllerC controller) {
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
	
	/**
	 * Launching of a thread to establish the connection
	 */
	public void run() {
		
		try 
		{
			while(true)
			{
				socket = socketserver.accept();
				writeListIdEmployee();
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
	 * Sends the employees array to the emulator application
	 */
	private void writeListIdEmployee() {
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(controller.GetListIdEmployee());
			out.flush();
			out.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}