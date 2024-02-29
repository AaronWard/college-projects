# System Requirements and Specification


## Java Development Kit (JDK)
The Java development kit, which contains the Java Runtime Environment and the Java Virtual Machine, are required for the development of this project as it allows the code to be compiled and executed.


## MySQL
MySQL is a relational database management system used in order to save information in a database. In this case, should a user register with the program a user ID, username and some other additional information may need to be stored. This persistent storage is ideal as it allows a user to retrieve important items created by a user should the program be shut down. SQL is a computer query language used to store, update and obtain data from the database. Data is stored in tables which consist of 2 attributes, table rows and table columns. When an SQL query command is executed, the RDMS determines what the most efficient way to carry out the command would be and the SQL engines determines how to interpret the task that must be carried out. SQL commands have a simple English syntax for querying data (see example below):

```
    SELECT * FROM table WHERE id = '123' 
```
The command above retrieves all the data from the table where the data in the id column is equal to '123'. This approach of storing and retrieving data in a database proves beneficial during the implementation stage as it allows for clear and intuitive ways of carrying out a task that is needed.

### Java Database Connectivity

The Java Database Connectivity (JDBC) is an application program interface that allows java programs to connect to database. The JDBC contains the ```java.sql``` package which supports SQL statements. The ```Statement``` interface allows static SQL statement to be executed during runtime.
```
        Statement stmt = null;
        stmt = conn.createStatement( );
```
The ```PreparedStatment``` interface provides a means to execute dynamic SQL queries at runtime. This provides more flexibility for specific querying of a database.

```
        String SQL = "SELECT * FROM users WHERE name = 'John'";
        PreparedStatement statment = conn.prepareStatement(SQL);
```
The ```ResultSet``` object is a table of data retrieved from a database. This collection of data is made by execution of an SQL statement. The data contained in the ```ResultSet``` object is access by iterating through the given table in a while loop. (See code below):

```
        ResultSet resultSet = stmt.executeQuery();
    
        //Loop through the result set
        while (resultSet.next()) 
        {
            String userID = res.getString("id");
        }
```
Considering that the JDBC supports programmatical querying of a database, it is an fitting to incorporate into the system as it satisfied the proposed system requirement of storing data.


## User Interface Specifications

In terms of a graphical user interface, from the proposed system suggested it is ideal to provide a clean and intuitive user interface to allow ease of use to a user. The minimal use of buttons and lack of visual noise proves beneficial during use of a program as it doesn't confuse a user on how the program should work. Furthermore, the Java swing libraries shall be utilised as they offer fast and efficient GUI development. The GUI should have a section for a user to login, register, show registered users, make a cake and view cakes that the user has already made. This is achieved by providing a navigation bar to change the view accordingly. Additionally, the user interface should use multiple layout manager interfaces to achieve a smooth and clean user interface.

