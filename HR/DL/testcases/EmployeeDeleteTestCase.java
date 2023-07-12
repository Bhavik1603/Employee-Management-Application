import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import java.util.*;
import java.text.*;
public class EmployeeDeleteTestCase
{
public static void main(String gg[])
{
String employeeId=gg[0];
try
{
EmployeeDAOInterface employeeDAO = new EmployeeDAO();
employeeDAO.delete(employeeId);
System.out.println("Employee Deleted......");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}