package bouchenard.project.central.VUE;

import java.awt.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer 
{
	/**
	 * Supply hours handler
	 */
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        int  indexColumn = -1;
        if(table.getName().equals("tableClocks"))
        	indexColumn = table.getColumn("ClockOut").getModelIndex();
        if(table.getName().equals("tableManagers"))
        	indexColumn = table.getColumn("DepartmentLed").getModelIndex();
        if(table.getName().equals("tableEmployees"))
        	indexColumn = table.getColumn("AffiliatedDepartment").getModelIndex();
        if(table.getName().equals("tableDepartments"))
        	indexColumn = table.getColumn("CountEmployee").getModelIndex();
        
        String val = String.valueOf(table.getValueAt(row, indexColumn));
        
        if(val.equals("null"))
        	cell.setForeground(Color.red);
        else
        	if(val.length() == 0)
        		cell.setForeground(Color.red);
        	else
        	{
        		try
        		{
        			int valInt = Integer.parseInt(val);
	        		if(valInt == 0)
	        			cell.setForeground(Color.red);
	        		else
	        			cell.setForeground(Color.black);
        		}
        		catch (NumberFormatException e)
        		{
        			cell.setForeground(Color.black);
        		}
        	}
        return cell;
    }
}