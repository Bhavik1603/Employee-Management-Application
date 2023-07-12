import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationTitleExistsTestCase
{
public static void main(String args[])
{
String title=args[0];
try
{
DesignationDAO designationDAO = new DesignationDAO();
System.out.println(title+" Exists: "+designationDAO.titleExists(title));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}