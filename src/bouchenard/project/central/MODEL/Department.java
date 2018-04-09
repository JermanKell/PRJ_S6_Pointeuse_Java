package bouchenard.project.central.MODEL;

import java.io.Serializable;

public abstract class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;
	
	
	/**
	 * Getter
	 * @return the Department's id
	 */
	public String GetId() {
		return id;
	}
	
	/**
	 * Getter
	 * @return the Department's name
	 */
	public String GetName() {
		return name;
	}
	
	/**
	 * Setter
	 * @param id the Department's id
	 */
	public void SetId(String id) {
		this.id = id;
	}
	
	/**
	 * Setter
	 * @param name the Department's name
	 */
	public void SetName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Id: " + id + "\nName of Department: " + name + "\n";
	}
}
