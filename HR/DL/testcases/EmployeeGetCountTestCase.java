import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import java.util.*;
import java.text.*;
public class EmployeeGetCountTestCase
{
public static void main(String gg[])
{
try
{
EmployeeDAOInterface employeeDAO = new EmployeeDAO();
System.out.println("Number of records: "+employeeDAO.getCount());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}