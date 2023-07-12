import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationUpdateTestCase
{
public static void main(String args[])
{
int code=Integer.parseInt(args[0]);
String title=args[1];
try
{
DesignationDTO designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.update(designationDTO);
System.out.println("Designation Updated.....");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}