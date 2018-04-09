package bouchenard.project.central.MODEL;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

public class ExportCSV {

	/**
	 * Constructor. Create a file in csv format that include all company's data 
	 * @param NameFile the name of the file
	 * @param companyToExport the company to export
	 */
	public ExportCSV(String NameFile, Company companyToExport) {
		try
		{
			FileWriter fileWriter = new FileWriter(NameFile + ".csv");
			
			fileWriter.append("Name of the company:;");
			fileWriter.append(companyToExport.GetName() + "\n\n");
			
			fileWriter.append("Boss of the company:\n");
			fileWriter.append("FirstName;");
			fileWriter.append("LastName;");
			fileWriter.append("Sex;");
			fileWriter.append("Mail;");
			fileWriter.append("Phone;");
			fileWriter.append("StreetNumber;");
			fileWriter.append("GetStreetName;");
			fileWriter.append("Town;");
			fileWriter.append("ZipCode\n");
			
			fileWriter.append(companyToExport.GetBoss().GetFirstName() + ";");
			fileWriter.append(companyToExport.GetBoss().GetLastName() + ";");
			fileWriter.append(companyToExport.GetBoss().GetSex() + ";");
			fileWriter.append(companyToExport.GetBoss().GetMail() + ";");
			fileWriter.append(companyToExport.GetBoss().GetPhone() + ";");
			fileWriter.append(companyToExport.GetBoss().GetStreetNumber() + ";");
			fileWriter.append(companyToExport.GetBoss().GetStreetName() + ";");
			fileWriter.append(companyToExport.GetBoss().GetTown() + ";");
			fileWriter.append(companyToExport.GetBoss().GetZipCode() + "\n\n");
			
			
			fileWriter.append("List of every employees in the company:\n");

			fileWriter.append("Id;");
			fileWriter.append("FirstName;");
			fileWriter.append("LastName;");
			fileWriter.append("Sex;");
			fileWriter.append("Mail;");
			fileWriter.append("Phone;");
			fileWriter.append("StreetNumber;");
			fileWriter.append("GetStreetName;");
			fileWriter.append("Town;");
			fileWriter.append("ZipCode;");
			fileWriter.append("TimeStart;");
			fileWriter.append("TimeEnd;");
			fileWriter.append("MinutsSupply\n");
			
			for(Entry<String, Employee> entry : companyToExport.GetHashMapEmployee().entrySet())
			{
				fileWriter.append(entry.getValue().GetId() + ";");
				fileWriter.append(entry.getValue().GetFirstName() + ";");
				fileWriter.append(entry.getValue().GetLastName() + ";");
				fileWriter.append(entry.getValue().GetSex() + ";");
				fileWriter.append(entry.getValue().GetMail() + ";");
				fileWriter.append(entry.getValue().GetPhone() + ";");
				fileWriter.append(entry.getValue().GetStreetNumber() + ";");
				fileWriter.append(entry.getValue().GetStreetName() + ";");
				fileWriter.append(entry.getValue().GetTown() + ";");
				fileWriter.append(entry.getValue().GetZipCode() + ";");
				fileWriter.append(entry.getValue().GetTimeStart() + ";");
				fileWriter.append(entry.getValue().GetTimeEnd() + ";");
				fileWriter.append(entry.getValue().GetMinutsSupply() + "\n");				
			}
			
			fileWriter.append("\nManagement department of the company:\n");
			
			fileWriter.append("Id;");
			fileWriter.append("Name\n");
			
			fileWriter.append(companyToExport.GetManagementDpt().GetId() + ";");
			fileWriter.append(companyToExport.GetManagementDpt().GetName() + "\n\n");
			
			fileWriter.append("List of managers:\n");
			
			fileWriter.append("Id;");
			fileWriter.append("FirstName;");
			fileWriter.append("LastName;");
			fileWriter.append("Sex;");
			fileWriter.append("Mail;");
			fileWriter.append("Phone;");
			fileWriter.append("StreetNumber;");
			fileWriter.append("GetStreetName;");
			fileWriter.append("Town;");
			fileWriter.append("ZipCode;");
			fileWriter.append("TimeStart;");
			fileWriter.append("TimeEnd;");
			fileWriter.append("MinutsSupply\n");
			
			for(Entry<String, Manager> entry : companyToExport.GetManagementDpt().GetHashMapManager().entrySet())
			{
				fileWriter.append(entry.getValue().GetId() + ";");
				fileWriter.append(entry.getValue().GetFirstName() + ";");
				fileWriter.append(entry.getValue().GetLastName() + ";");
				fileWriter.append(entry.getValue().GetSex() + ";");
				fileWriter.append(entry.getValue().GetMail() + ";");
				fileWriter.append(entry.getValue().GetPhone() + ";");
				fileWriter.append(entry.getValue().GetStreetNumber() + ";");
				fileWriter.append(entry.getValue().GetStreetName() + ";");
				fileWriter.append(entry.getValue().GetTown() + ";");
				fileWriter.append(entry.getValue().GetZipCode() + ";");
				fileWriter.append(entry.getValue().GetTimeStart() + ";");
				fileWriter.append(entry.getValue().GetTimeEnd() + ";");
				fileWriter.append(entry.getValue().GetMinutsSupply() + "\n");
			}
			
			fileWriter.append("\nList of Departments:");
			
			for(StdDepartment department: companyToExport.GetListStdDepartment())
			{
				fileWriter.append("\nId;");
				fileWriter.append("Name\n");
				
				fileWriter.append(department.GetId() + ";");
				fileWriter.append(department.GetName() + "\n");
				
				fileWriter.append("\nList of employees in the department:\n");
				
				fileWriter.append("Id;");
				fileWriter.append("FirstName;");
				fileWriter.append("LastName;");
				fileWriter.append("Sex;");
				fileWriter.append("Mail;");
				fileWriter.append("Phone;");
				fileWriter.append("StreetNumber;");
				fileWriter.append("GetStreetName;");
				fileWriter.append("Town;");
				fileWriter.append("ZipCode;");
				fileWriter.append("TimeStart;");
				fileWriter.append("TimeEnd;");
				fileWriter.append("MinutsSupply\n");
				
				for(Entry<String, Employee> entry : department.GetHashMapEmployee().entrySet())
				{
					fileWriter.append(entry.getValue().GetId() + ";");
					fileWriter.append(entry.getValue().GetFirstName() + ";");
					fileWriter.append(entry.getValue().GetLastName() + ";");
					fileWriter.append(entry.getValue().GetSex() + ";");
					fileWriter.append(entry.getValue().GetMail() + ";");
					fileWriter.append(entry.getValue().GetPhone() + ";");
					fileWriter.append(entry.getValue().GetStreetNumber() + ";");
					fileWriter.append(entry.getValue().GetStreetName() + ";");
					fileWriter.append(entry.getValue().GetTown() + ";");
					fileWriter.append(entry.getValue().GetZipCode() + ";");
					fileWriter.append(entry.getValue().GetTimeStart() + ";");
					fileWriter.append(entry.getValue().GetTimeEnd() + ";");
					fileWriter.append(entry.getValue().GetMinutsSupply() + "\n");
				}
			}
			
			fileWriter.close(); 
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
