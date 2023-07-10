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

## DL: DATA LAYER
### Folder Structure
HR\DL>
<ul>
  <li>classes</li>
  <li>dist</li>
  <li>src
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
          <ul><li>DAOException</li></ul>
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
To compile code in **src** folder
