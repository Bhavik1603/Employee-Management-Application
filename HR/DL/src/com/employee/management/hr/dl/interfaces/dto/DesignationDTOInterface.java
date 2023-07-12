package com.employee.management.hr.dl.interfaces.dto;



// Serializable is a *marker interface* [no methods have been declared in it]


public interface DesignationDTOInterface extends Comparable<DesignationDTOInterface>, java.io.Serializable
{
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}

