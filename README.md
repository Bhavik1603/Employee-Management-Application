## Employee Management Desktop Application

This application was designed in 3 layers (CONCEPT OF LAYERED PROGRAMMING) <br>
<ul>
  <li>
<b>Data Layer:</b> This Layer was divided into 2 Parts:
    <ul>
   <li>File Handing Technique </li> 
    <li>Database Technique </li>
    </ul>
    In Data Layer we store data by both techniques, we store data of employee in files and in database both. CRUD operations can be done using data files or database.
</li>
<li>
<b>Business Layer:</b> This Layer consist of Data Structures, as it takes too much time to perform operations and display them on Graphical User Interface while performing action with file handling.</li>
<li>
<b>Presentation Layer:</b> This is Graphical User Interface layer uses Java Swing for programming, this layer presents employee data.</li>
</ul>

## Folders Abbreviations
<ul>
  <li>DL: Data Layer (File Handling Technique)</li>
  <li>DBDL: Database Data Layer (Database Technique)</li>
  <li>PL: Presentation Layer</li>
  <li>BL: Business Layer </li>
</ul>

## COMMON
### Folder Structure
HR\common>
<ul>
  <li>classes
  <ul>
    <li>com
      <ul>
        <li>employee
          <ul>
            <li>management
              <ul>
                <li>enums
                  <ul>
                    <li>GENDER.class</li>
                  </ul>
                </li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </li>
  </ul></li>
  <li>dist
    <ul>
      <li>hr-common.jar</li>
    </ul>
  </li>
  <li>src
  <ul>
    <li>com
      <ul>
        <li>employee
          <ul>
            <li>management
              <ul>
                <li>enums
                  <ul>
                    <li>GENDER.java</li>
                  </ul>
                </li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </li>
  </ul>
  </li>
  <li>testcases</li>

    
This folder includes files that will be shared between all the layers like **enums** <br>
To compile GENDER.java <br>
```bash
HR\common\src> javac -d ..\classes -classpath;. com\employee\management\enums\*.java
```
To create a jar file of **common** folder: <br>
```bash
HR\common\classes> jar -cvf ..\dist\hr-common.jar com
```
The above line will create **hr-common.jar** file.


## DL: DATA LAYER
### Folder Structure
HR\DL>
<ul>
  <li>classes</li>
  <li>dist</li>
  <li>
    src
    <ul>
      <li>com
        <ul>
          <li>employee
            <ul>
              <li>management
                <ul>
                  <li>hr
                    <ul>
                      <li>dl
                        <ul>
                          <li>dao
                            <ul>
            <li>DesignationDAO</li>
            <li>EmployeeDAO</li>
          </ul>
    </li>
        <li>dto
          <ul>
            <li>DesignationDTO</li>
            <li>EmployeeDTO</li>
          </ul>
        </li>
        <li>exceptions
          <ul><li>DAOException.java</li></ul>
        </li>
        <li>interfaces
          <ul>
            <li>dao
              <ul>
                <li>DesignationDAOInterface.java</li>
                <li>EmployeeDAOInterface.java</li>              
              </ul>
            </li>
            <li>dto
              <ul>
                <li>DesignationDTOInterface.java</li>
                <li>EmployeeDTOInterface.java</li>              
              </ul>
            </li>
          </ul>
        </li>
        
  </ul>
    </li>
  </ul>
    </li>
  </ul>
    </li>
  </ul>
    </li>
  </ul>
    </li>
  </ul>
  </li>
  <li>testcases</li>
</ul>
<br>
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


