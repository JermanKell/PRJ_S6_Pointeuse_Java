package bouchenard.project.central.MODEL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportCSV {
	private Company importedCompany;
	
	public ImportCSV(String FileName) {
		try
		{
	        FileReader fr = new FileReader(FileName + ".csv");
	        BufferedReader br = new BufferedReader(fr);
	 
	        for (String line = br.readLine(); line != null; line = br.readLine())//UN PASSAGE FOR = UNE LIGNE
	            System.out.println(line + "------FIN");
	 
	        br.close();
	        fr.close();
	 
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Company GetImportCSVCompany() {
		return importedCompany;
	}
}
