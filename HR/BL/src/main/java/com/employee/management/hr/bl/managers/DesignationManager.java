package com.employee.management.hr.bl.managers;
import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.pojo.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import com.employee.management.hr.dl.exceptions.*;
import java.util.*;

public class DesignationManager implements DesignationManagerInterface
{
private Map<Integer, DesignationInterface> codeWiseDesignationsMap;
private Map<String, DesignationInterface> titleWiseDesignationsMap;
private Set<DesignationInterface> designationsSet;
private static DesignationManager designationManager = null;
//*******************************************************************************
private DesignationManager() throws BLException // Koi abb iss class ka object nahi bana paayega aur access nahi kr paayega iss class ko
{
populateDataStructures();
}
//*******************************************************************************
private void populateDataStructures() throws BLException
{
this.codeWiseDesignationsMap = new HashMap<>();
this.titleWiseDesignationsMap = new HashMap<>();
this.designationsSet = new TreeSet<>();
try
{
Set<DesignationDTOInterface> dlDesignations;
dlDesignations = new DesignationDAO().getAll();
DesignationInterface designation;
for(DesignationDTOInterface dlDesignation: dlDesignations)
{
designation = new Designation();
designation.setCode(dlDesignation.getCode());
designation.setTitle(dlDesignation.getTitle());
this.codeWiseDesignationsMap.put(new Integer(designation.getCode()), designation);
this.titleWiseDesignationsMap.put(designation.getTitle().toUpperCase(), designation);
this.designationsSet.add(designation);
}
}catch(DAOException daoException)
{
BLException blException = new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
//*******************************************************************************
public static DesignationManagerInterface getDesignationManager() throws BLException // isse ekk hi object bann paayega
{
if(designationManager == null) designationManager = new DesignationManager();
return designationManager;
}
//*******************************************************************************
public void addDesignation(DesignationInterface designation) throws BLException
{
// Check Exceptions Begin
BLException blException = new BLException();
if(designation == null)
{
blException.setGenericException("Designation required");
throw blException;
}
int code = designation.getCode();
String title = designation.getTitle();
if(code != 0)
{
blException.addException("code", "Code should be zero");
}
if(title == null)
{
blException.addException("title", "Title required");
title = "";
}
else
{
title = title.trim();
if(title.length() == 0)
{
blException.addException("title", "Title required");
}
}
if(title.length() > 0)
{
if(this.titleWiseDesignationsMap.containsKey(title.toUpperCase()))
{
blException.addException("title", "Designation : " + title + " exists");
}
}
if(blException.hasExceptions())
{
throw blException;
}
// Check Exceptions End
// Add logic begins here
try
{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
designationDAO.add(designationDTO);
code = designationDTO.getCode();
designation.setCode(code);
Designation dsDesignation;
dsDesignation = new Designation();
dsDesignation.setCode(code);
dsDesignation.setTitle(title);
codeWiseDesignationsMap.put(new Integer(code), dsDesignation);
titleWiseDesignationsMap.put(title.toUpperCase(), dsDesignation);
designationsSet.add(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}
}
//*******************************************************************************
public void updateDesignation(DesignationInterface designation) throws BLException
{
BLException blException = new BLException();
if(designation == null)
{
blException.setGenericException("Designation Required");
throw blException;
}
int code = designation.getCode();
String title = designation.getTitle();
if(code <= 0) blException.addException("code", "Invalid Code: " + code);
if(code > 0)
{
if(this.codeWiseDesignationsMap.containsKey(code) == false)
{
blException.addException("code", "Inavlid Code: " + code);
throw blException;
}
}
if(title == null)
{
blException.addException("title", "Title Required");
title = "";
}
else
{
title = title.trim();
if(title.length() == 0) blException.addException("title", "Title Required");
}
if(title.length() > 0)
{
// new title already exists 
DesignationInterface d = titleWiseDesignationsMap.get(title.toUpperCase());
if(d != null && d.getCode() != code) blException.addException("title", "Designation: " + title + " exists.");
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
DesignationInterface dsDesignation = codeWiseDesignationsMap.get(code);
DesignationDTOInterface dlDesignation = new DesignationDTO();
dlDesignation.setCode(code);
dlDesignation.setTitle(title);
new DesignationDAO().update(dlDesignation);
// remove the old one from all DS
codeWiseDesignationsMap.remove(code);
titleWiseDesignationsMap.remove(dsDesignation.getTitle().toUpperCase());
designationsSet.remove(dsDesignation);
// update the DS object
dsDesignation.setTitle(title);
// update the DS
codeWiseDesignationsMap.put(code, dsDesignation);
titleWiseDesignationsMap.put(title.toUpperCase(), dsDesignation);
designationsSet.add(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}
}
//*******************************************************************************
public void removeDesignation(int code) throws BLException
{
BLException blException = new BLException();
if(code <= 0)
{
blException.addException("code", "Invalid Code: " + code);
throw blException;
}
if(code > 0)
{
if(this.codeWiseDesignationsMap.containsKey(code) == false)
{
blException.addException("code", "Inavlid Code: " + code);
throw blException;
}
}
try
{
DesignationInterface dsDesignation = codeWiseDesignationsMap.get(code);
new DesignationDAO().delete(code);
codeWiseDesignationsMap.remove(code);
titleWiseDesignationsMap.remove(dsDesignation.getTitle().toUpperCase());
designationsSet.remove(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
//*******************************************************************************
public DesignationInterface getDesignationByCode(int code) throws BLException
{
DesignationInterface designation = codeWiseDesignationsMap.get(code);
if(designation == null)
{
BLException blException = new BLException();
blException.addException("code", "Invalid Code: " + code);
throw blException;
}
// Create New Object and Send
DesignationInterface d = new Designation();
d.setCode(designation.getCode());
d.setTitle(designation.getTitle());
return d;
}
//*******************************************************************************
public DesignationInterface getDesignationByTitle(String title) throws BLException
{
DesignationInterface designation = titleWiseDesignationsMap.get(title.toUpperCase());
if(designation == null)
{
BLException blException = new BLException();
blException.addException("title", "Invalid Designation: " + title);
throw blException;
}
// Create New Object and Send
DesignationInterface d = new Designation();
d.setCode(designation.getCode());
d.setTitle(designation.getTitle());
return d;
}
//*******************************************************************************
// for internal use, will be use in same package
DesignationInterface getDSDesignationByCode(int code)
{
DesignationInterface designation = codeWiseDesignationsMap.get(code);
return designation;
}
//*******************************************************************************
public int getDesignationCount() 
{
return designationsSet.size();
}
//*******************************************************************************
public boolean designationCodeExists(int code) 
{
return codeWiseDesignationsMap.containsKey(code);
}
//*******************************************************************************
public boolean designationTitleExists(String title) 
{
return titleWiseDesignationsMap.containsKey(title.toUpperCase());
}
//*******************************************************************************
public Set<DesignationInterface> getDesignations() 
{
// Create clone and return
Set<DesignationInterface> designations = new TreeSet<>();
designationsSet.forEach((designation)->{
DesignationInterface d = new Designation();
d.setCode(designation.getCode());
d.setTitle(designation.getTitle());
designations.add(d);
});
return designations;
}
}