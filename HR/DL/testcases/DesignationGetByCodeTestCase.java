import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationGetByCodeTestCase
{
public static void main(String args[])
{
int code = Integer.parseInt(args[0]);
try
{
DesignationDTOInterface designationDTO;
DesignationDAO designationDAO = new DesignationDAO();
designationDTO = designationDAO.getByCode(code);
System.out.printf("Code : %d, Title : %s",designationDTO.getCode(),designationDTO.getTitle());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
