package com.employee.management.hr.dl.dao;
import com.employee.management.enums.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
private final static String FILE_NAME="employee.data";

//*****************************************************************************************

public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
//Validations Starts here........

if(employeeDTO==null) throw new DAOException("Employee in null");
String employeeId;
String name=employeeDTO.getName();
if(name==null) throw new DAOException("Employee in null");
name=name.trim();
if(name.length()==0) throw new DAOException("Length of name cannot be zero");
int designationCode=employeeDTO.getDesignationCode();
if(designationCode<=0) throw new DAOException("Invalid Designation Code: "+designationCode);
DesignationDAOInterface designationDAO= new DesignationDAO();
boolean isDesignationValid=designationDAO.codeExists(designationCode);
if(!isDesignationValid) throw new DAOException("Invalid Designation Code: "+designationCode);
Date dateOfBirth=employeeDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("Date of birth is null");
char gender=employeeDTO.getGender();
if(gender==' ') throw new DAOException("Gender is not set to MALE/FEMALE");
boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("Basic Salary is null");
if(basicSalary.signum()==-1) throw new DAOException("Basis Salary is Negative");
String panNumber=employeeDTO.getPANNumber();
if(panNumber==null) throw new DAOException("Pan Number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length Pan Number is Zero");
String aadharCardNumber=employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("Aadhar Card Number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Length Aadhar Card Number is Zero");

//Validations Ends here........
try
{
int lastGeneratedEmployeeId=10000000;
String lastGeneratedEmployeeIdString="";
int recordCount=0;
String recordCountString="";
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if( randomAccessFile.length()==0)
{
lastGeneratedEmployeeIdString=String.format("%-10s","10000000");
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString);
randomAccessFile.writeBytes("\n");
recordCountString=String.format("%-10s","0");
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
}
else
{
lastGeneratedEmployeeIdString= randomAccessFile.readLine().trim();
recordCountString= randomAccessFile.readLine().trim();
lastGeneratedEmployeeId=Integer.parseInt(lastGeneratedEmployeeIdString);
recordCount=Integer.parseInt(recordCountString);
}
boolean panNumberExists=false,aadharCardNumberExists=false;
String fPanNumber,fAadharCardNumber;
int x;
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
for(x=1;x<=7;x++) randomAccessFile.readLine();
fPanNumber= randomAccessFile.readLine();
fAadharCardNumber= randomAccessFile.readLine();
if(panNumberExists==false && fPanNumber.equalsIgnoreCase(panNumber)) panNumberExists=true;
if(aadharCardNumberExists==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)) aadharCardNumberExists=true;
if(aadharCardNumberExists && panNumberExists) break;
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN Number ("+panNumber+") and Aadhar Card Number ("+aadharCardNumber+") exists");
}
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN Number ("+panNumber+") exists");
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar Card Number ("+aadharCardNumber+") exists");
}
lastGeneratedEmployeeId++;
employeeId="A"+lastGeneratedEmployeeId;
recordCount++;
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
randomAccessFile.seek(0);
lastGeneratedEmployeeIdString=String.format("%-10d",lastGeneratedEmployeeId);
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(employeeId);
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
}

//*****************************************************************************************

public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Employee in null");
String employeeId=employeeDTO.getEmployeeId().trim();
if(employeeId==null) throw new DAOException("EmployeeId is null");
if(employeeId.length()==0) throw new DAOException("Lenght of EmployeeId cannot be zero");
String name=employeeDTO.getName();
if(name==null) throw new DAOException("Name in null");
name=name.trim();
if(name.length()==0) throw new DAOException("Length of name cannot be zero");
int designationCode=employeeDTO.getDesignationCode();
if(designationCode<=0) throw new DAOException("Invalid Designation Code: "+designationCode);
DesignationDAOInterface designationDAO= new DesignationDAO();
boolean isDesignationValid=designationDAO.codeExists(designationCode);
if(!isDesignationValid) throw new DAOException("Invalid Designation Code: "+designationCode);
Date dateOfBirth=employeeDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("Date of birth is null");
char gender=employeeDTO.getGender();
if(gender==' ') throw new DAOException("Gender is not set to MALE/FEMALE");
boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("Basic Salary is null");
if(basicSalary.signum()==-1) throw new DAOException("Basis Salary is Negative");
String panNumber=employeeDTO.getPANNumber();
if(panNumber==null) throw new DAOException("Pan Number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length Pan Number is Zero");
String aadharCardNumber=employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("Aadhar Card Number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Length Aadhar Card Number is Zero");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid Employee Id: "+employeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+employeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
String fEmployeeId;
String fName;
int fDesignationCode;
char fGender;
Date fDateOfBirth;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPANNumber;
String fAadharCardNumber;
int x;
boolean employeeIdFound=false;
boolean panNumberFound=false;
boolean aadharCardNumberFound=false;
String panNumberFoundAgainstEmployeeId="";
String aadharCardNumberFoundAgainstEmployeeId="";
long foundAt=0;
while(randomAccessFile.getFilePointer()< randomAccessFile.length())
{
if(employeeIdFound==false) foundAt= randomAccessFile.getFilePointer();
fEmployeeId= randomAccessFile.readLine();
for(x=1;x<=6;x++) randomAccessFile.readLine();
fPANNumber= randomAccessFile.readLine();
fAadharCardNumber= randomAccessFile.readLine();
if(employeeIdFound==false && fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeIdFound=true;
}
if(panNumberFound==false && fPANNumber.equalsIgnoreCase(panNumber))
{
panNumberFound=true;
panNumberFoundAgainstEmployeeId=fEmployeeId;
}
if(aadharCardNumberFound==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
aadharCardNumberFound=true;
aadharCardNumberFoundAgainstEmployeeId=fEmployeeId;
}
if(employeeIdFound && aadharCardNumberFound && panNumberFound)break;
}
if(employeeIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid EmployeeId: "+employeeId);
}
boolean panNumberExists=false;
if(panNumberFound && panNumberFoundAgainstEmployeeId.equalsIgnoreCase(employeeId)==false)
{
panNumberExists=true;
}
boolean aadharCardNumberExists=false;
if(aadharCardNumberFound && aadharCardNumberFoundAgainstEmployeeId.equalsIgnoreCase(employeeId)==false)
{
aadharCardNumberExists=true;
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN Number ("+panNumber+") and Aadhar Card Number ("+aadharCardNumber+") exists");
}
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN Number ("+panNumber+") exists");
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar Card Number ("+aadharCardNumber+") exists");
}
randomAccessFile.seek(foundAt);
for(x=1;x<=9;x++)randomAccessFile.readLine();
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandom=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
tmpRandom.writeBytes(randomAccessFile.readLine()+"\n");
}
randomAccessFile.seek(foundAt);
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
randomAccessFile.writeBytes(sdf.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
tmpRandom.seek(0);
while(tmpRandom.getFilePointer()<tmpRandom.length())
{
randomAccessFile.writeBytes(tmpRandom.readLine()+"\n");
}
randomAccessFile.setLength( randomAccessFile.getFilePointer());
tmpRandom.setLength(0);
randomAccessFile.close();
tmpRandom.close();
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
}

//*****************************************************************************************

public void delete(String employeeId) throws DAOException
{
//employeeId=employeeId.trim();
if(employeeId==null) throw new DAOException("EmployeeId is null");
if(employeeId.length()==0) throw new DAOException("Lenght of EmployeeId cannot be zero");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid Employee Id: "+employeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
 randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+employeeId);
}
 randomAccessFile.readLine();
int recordCount=Integer.parseInt( randomAccessFile.readLine().trim());
String fEmployeeId;
int x;
boolean employeeIdFound=false;
long foundAt=0;
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
foundAt= randomAccessFile.getFilePointer();
fEmployeeId= randomAccessFile.readLine();
for(x=1;x<=8;x++) randomAccessFile.readLine();
if(employeeIdFound==false && fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeIdFound=true;
break;
}
}
if(employeeIdFound==false)
{
 randomAccessFile.close();
throw new DAOException("Invalid EmployeeId: "+employeeId);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandom=new RandomAccessFile(tmpFile,"rw");
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
tmpRandom.writeBytes( randomAccessFile.readLine()+"\n");
}
 randomAccessFile.seek(foundAt);
tmpRandom.seek(0);
while(tmpRandom.getFilePointer()<tmpRandom.length())
{
 randomAccessFile.writeBytes(tmpRandom.readLine()+"\n");
}
 randomAccessFile.setLength( randomAccessFile.getFilePointer());
recordCount--;
String recordCountString=String.format("%-10d",recordCount);
 randomAccessFile.seek(0);
 randomAccessFile.readLine();
 randomAccessFile.writeBytes(recordCountString+"\n");
tmpRandom.setLength(0);
 randomAccessFile.close();
tmpRandom.close();
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}

}

//*****************************************************************************************

public Set<EmployeeDTOInterface> getAll() throws DAOException
{
Set<EmployeeDTOInterface> employees = new TreeSet<>();
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if( randomAccessFile.length()==0)
{
 randomAccessFile.close();
return employees;
}
 randomAccessFile.readLine();
 randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
char fGender;
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(randomAccessFile.readLine());
employeeDTO.setName( randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(sdf.parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
//nothing to write here
}
fGender= randomAccessFile.readLine().charAt(0);
if(fGender=='M' || fGender=='m') employeeDTO.setGender(GENDER.MALE);
if(fGender=='F' || fGender=='f') employeeDTO.setGender(GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
 randomAccessFile.close();
return employees;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
}

//*****************************************************************************************

public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException
{
if(new DesignationDAO().codeExists(designationCode)==false)
{
throw new DAOException("Invalid Designation Code: "+designationCode);
}
Set<EmployeeDTOInterface> employees = new TreeSet<>();
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if( randomAccessFile.length()==0)
{
 randomAccessFile.close();
return employees;
}
 randomAccessFile.readLine();
 randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
String fEmployeeId;
String fName;
int fDesignationCode;
int x;
char fGender;
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
fEmployeeId= randomAccessFile.readLine();
fName= randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode!=designationCode)
{
for(x=1;x<=6;x++)randomAccessFile.readLine();
continue;
}
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
try
{
employeeDTO.setDateOfBirth(sdf.parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
//nothing to write here
}
fGender= randomAccessFile.readLine().charAt(0);
if(fGender=='M' || fGender=='m') employeeDTO.setGender(GENDER.MALE);
if(fGender=='F' || fGender=='f') employeeDTO.setGender(GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
 randomAccessFile.close();
return employees;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
}

//*****************************************************************************************

public boolean isDesignationAlloted(int designationCode) throws DAOException
{
if(new DesignationDAO().codeExists(designationCode)==false)
{
throw new DAOException("Invalid Designation Code: "+designationCode);
}
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if( randomAccessFile.length()==0)
{
 randomAccessFile.close();
return false;
}
 randomAccessFile.readLine();
 randomAccessFile.readLine();
int fDesignationCode;
int x;
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
 randomAccessFile.readLine();
 randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode==designationCode)
{
randomAccessFile.close();
return true;
}
for(x=1;x<=6;x++)randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
}
//*****************************************************************************************

public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
if(employeeId==null) throw new DAOException("Invalid EmployeeId: "+employeeId);
employeeId=employeeId.trim();
if(employeeId.length()==0) throw new DAOException("Length of EmployeeId can not be Zero");
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid EmployeeId: "+employeeId);
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException("Invalid EmployeeId: "+employeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
char fGender;
int x;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(sdf.parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
//nothing to write here
}
fGender = randomAccessFile.readLine().charAt(0);
if(fGender == 'M' || fGender == 'm') employeeDTO.setGender(GENDER.MALE);
if(fGender=='F' || fGender == 'f') employeeDTO.setGender(GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean( randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal( randomAccessFile.readLine()));
employeeDTO.setPANNumber( randomAccessFile.readLine());
employeeDTO.setAadharCardNumber( randomAccessFile.readLine());
 randomAccessFile.close();
return employeeDTO;
}
for(x=1;x<=8;x++)randomAccessFile.readLine();
}
 randomAccessFile.close();
throw new DAOException("Invalid EmployeeId: "+employeeId);
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}

}

//*****************************************************************************************

public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
if(panNumber==null) throw new DAOException("Invalid PANNumber: "+panNumber);
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length of PANNumber can not be Zero");
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid PANNumber: "+panNumber);
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if( randomAccessFile.length()==0)
{
 randomAccessFile.close();
throw new DAOException("Invalid PANNumber: "+panNumber);
}
 randomAccessFile.readLine();
 randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fPANNumber;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fAadharCardNumber;
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
int x;
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
fEmployeeId= randomAccessFile.readLine();
fName= randomAccessFile.readLine();
fDesignationCode=Integer.parseInt( randomAccessFile.readLine());
try
{
fDateOfBirth=sdf.parse( randomAccessFile.readLine());
}catch(ParseException pe)
{
//nothing to write here
}
fGender= randomAccessFile.readLine().charAt(0);
fIsIndian=Boolean.parseBoolean( randomAccessFile.readLine());
fBasicSalary=new BigDecimal( randomAccessFile.readLine());
fPANNumber= randomAccessFile.readLine();
fAadharCardNumber= randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setGender((fGender=='M' || fGender=='m')? GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
 randomAccessFile.close();
return employeeDTO;
}
}
 randomAccessFile.close();
throw new DAOException("Invalid PANNumber: "+panNumber);
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}

}

//*****************************************************************************************

public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) throw new DAOException("Invalid Aadhar Card Number: "+aadharCardNumber);
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Length of Aadhar Card Number can not be Zero");
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid Aadhar Card Number: "+aadharCardNumber);
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if( randomAccessFile.length()==0)
{
 randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card Number: "+aadharCardNumber);
}
 randomAccessFile.readLine();
 randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fPANNumber;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fAadharCardNumber;
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
int x;
while( randomAccessFile.getFilePointer()< randomAccessFile.length())
{
fEmployeeId= randomAccessFile.readLine();
fName= randomAccessFile.readLine();
fDesignationCode=Integer.parseInt( randomAccessFile.readLine());
try
{
fDateOfBirth=sdf.parse( randomAccessFile.readLine());
}catch(ParseException pe)
{
//nothing to write here
}
fGender= randomAccessFile.readLine().charAt(0);
fIsIndian=Boolean.parseBoolean( randomAccessFile.readLine());
fBasicSalary=new BigDecimal( randomAccessFile.readLine());
fPANNumber= randomAccessFile.readLine();
fAadharCardNumber= randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setGender((fGender=='M' || fGender=='m')? GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
 randomAccessFile.close();
return employeeDTO;
}
}
 randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card Number: "+aadharCardNumber);
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}

}

//*****************************************************************************************

public boolean employeeIdExists(String employeeId) throws DAOException
{
if(employeeId==null) return false;
employeeId=employeeId.trim();
if(employeeId.length()==0) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if( randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fEmployeeId;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
randomAccessFile.close();
return true;
}
for(x=1;x<=8;x++)randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}

}

//*****************************************************************************************

public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) return false;
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fAadharCardNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=8;x++)randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}

}

//*****************************************************************************************

public boolean panNumberExists(String panNumber) throws DAOException
{
if(panNumber==null) return false;
panNumber=panNumber.trim();
if(panNumber.length()==0) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fPANNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=7;x++)randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
}

//*****************************************************************************************

public int getCount() throws DAOException
{
try
{
File file=new File(FILE_NAME);
if(file.exists()==false)return 0;
RandomAccessFile randomAccessFile= new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}
}

//*****************************************************************************************

public int getCountByDesignation(int designationCode) throws DAOException
{
try
{
if(new DesignationDAO().codeExists(designationCode)==false) throw new DAOException("Inavlid Designation Code: "+designationCode);
File file=new File(FILE_NAME);
if(file.exists()==false)return 0;
RandomAccessFile randomAccessFile= new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
int x;
int recordCount=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode=Integer.parseInt( randomAccessFile.readLine());
if(fDesignationCode==designationCode) recordCount++;
for(x=1;x<=6;x++) randomAccessFile.readLine();
}
 randomAccessFile.close();
return recordCount;
}catch(IOException ioe)
{
throw new DAOException(ioe.getMessage());
}

}

//*****************************************************************************************
}