import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationAddTestCase
{
public static void main(String args[])
{
String title = args[0];
try
{
DesignationDTOInterface designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.add(designationDTO);
System.out.println("Designation : "+ title + " added with code as : " + designationDTO.getCode());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
