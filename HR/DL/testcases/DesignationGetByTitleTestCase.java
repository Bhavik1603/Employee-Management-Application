import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationGetByTitleTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
DesignationDTOInterface designationDTO;
DesignationDAO designationDAO = new DesignationDAO();
designationDTO=designationDAO.getByTitle(title);
System.out.println("Code: "+designationDTO.getCode()+"\nTitle: "+designationDTO.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}