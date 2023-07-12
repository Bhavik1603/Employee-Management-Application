import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationGetCountTestCase
{
public static void main(String args[])
{
try
{
DesignationDAO designationDAO = new DesignationDAO();
System.out.println("Designation count : " + designationDAO.getCount());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
