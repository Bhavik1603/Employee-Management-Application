import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import java.util.*;
public class DesignationGetAllTestCase
{
public static void main(String args[])
{
try
{
DesignationDAO designationDAO = new DesignationDAO();
Set<DesignationDTOInterface> designations = designationDAO.getAll();
designations.forEach((designationDTO)->{
System.out.println("Code: "+designationDTO.getCode()+", Title: "+designationDTO.getTitle());
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}