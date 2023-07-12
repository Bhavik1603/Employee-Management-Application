import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationDeleteTestCase
{
public static void main(String args[])
{
int code=Integer.parseInt(args[0]);
try
{
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.delete(code);
System.out.println("Designation Deleted....");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}