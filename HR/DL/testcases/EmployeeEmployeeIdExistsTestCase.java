import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import java.util.*;
import java.text.*;
public class EmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
String employeeId=gg[0];
try
{
EmployeeDAOInterface employeeDAO = new EmployeeDAO();
System.out.println("Employee Id: "+employeeId+" Exists: "+employeeDAO.employeeIdExists(employeeId));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}