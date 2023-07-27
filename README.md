## Employee Management Desktop Application

This application was designed in 3 layers (CONCEPT OF LAYERED PROGRAMMING) <br>
<ul>
  <li>
<b>Data Layer:</b> This Layer was divided into 2 Parts:
    <ul>
   <li>File Handing Technique </li> 
    <li>Database Technique </li>
    </ul>
    In the Data Layer, we store data by both techniques, we store employee data in files and in databases. CRUD operations can be done using data files or databases.
</li>
<li>
<b>Business Layer:</b> This Layer consists of Data Structures, as it takes too much time to perform operations and display them on Graphical User Interface while performing actions with file handling.</li>
<li>
<b>Presentation Layer:</b> This is a Graphical User Interface layer that uses Java Swing for programming, this layer presents employee data.</li>
</ul>

## Folders Abbreviations
<ul>
  <li>DL: Data Layer (File Handling Technique)</li>
  <li>DBDL: Database Data Layer (Database Technique)</li>
  <li>PL: Presentation Layer</li>
  <li>BL: Business Layer </li>
</ul>

## COMMON
This folder includes files that will be shared between all the layers like **enums** <br>
To compile GENDER.java <br>
```bash
HR\common\src> javac -d ..\classes -classpath;. com\employee\management\enums\*.java
```
To create a jar file of **common** folder: <br>
```bash
HR\common\classes> jar -cvf ..\dist\hr-common.jar com
```
The above line will create a **hr-common.jar** file.


## DL: DATA LAYER
To compile the following files: DAOException.java <br>
```bash
HR\DL\src> javac -d ..\classes -classpath ..\..\common\dist\hr-common.jar;. com\employee\management\hr\dl\exceptions\*.java
```
To compile the following files: DesignationDTOInterface.java, EmployeeDTOInterface.java <br>
```bash
HR\DL\src> javac -d ..\classes -classpath ..\..\common\dist\hr-common.jar;. com\employee\management\hr\dl\interfaces\dto\*.java
```
To compile the following files: DesignationDAOInterface.java, EmployeeDAOInterface.java <br>
```bash
HR\DL\src> javac -d ..\classes -classpath ..\..\common\dist\hr-common.jar;. com\employee\management\hr\dl\interfaces\dto\*.java
```
To compile the following files: DesignationDTO.java, EmployeeDTO.java <br>
```bash
HR\DL\src> javac -d ..\classes -classpath ..\..\common\dist\hr-common.jar;. com\employee\management\hr\dl\dto\*.java
```
To compile the following files: DesignationDAO.java, EmployeeDAO.java <br>
```bash
HR\DL\src> javac -d ..\classes -classpath ..\..\common\dist\hr-common.jar;. com\employee\management\hr\dl\dao\*.java
```
To compile **Testcases** <br>
```bash
HR\DL\testcases> javac -classpath ..\..\common\dist\hr-common.jar;..\classes;. *.java
```
