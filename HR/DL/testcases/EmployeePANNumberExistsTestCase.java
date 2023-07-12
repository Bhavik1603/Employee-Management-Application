import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import java.util.*;
import java.text.*;
public class EmployeePANNumberExistsTestCase
{
public static void main(String gg[])
{
String panNumber=gg[0];
try
{
EmployeeDAOInterface employeeDAO = new EmployeeDAO();
System.out.println("PAN Number: "+panNumber+" Exists: "+employeeDAO.panNumberExists(panNumber));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}