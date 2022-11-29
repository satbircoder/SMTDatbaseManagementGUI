***************************SQL Statment for Database Creation*********************
System.out.println("Creating Database Please Wait");
            System.out.println("******************");
            query = "DROP DATABASE IF EXISTS smtbiz;";
            stmt.executeUpdate(query);
            query = "CREATE DATABASE smtbiz;";
            stmt.executeUpdate(query);
            query = "USE smtbiz;";
            stmt.executeUpdate(query);
            query ="""
                   CREATE TABLE Customer(
                   Customer_ID INTEGER(6) NOT NULL AUTO_INCREMENT,
                   Customer_Name VARCHAR(80),
                   Email_Address VARCHAR(80),
                   Phone_Number VARCHAR(80),
                   PRIMARY KEY(Customer_ID)
                   )SELECT LEFT(CAST(RAND()*1000000000 AS INT),6) as Customer_ID
                   ;""";
            stmt.executeUpdate(query);
            query = """
                    insert into Customer
                    (Customer_Name,Email_Address,Phone_Number)
                    values
                    ('John','sat@gmail.com','0451115081'),
                    ('Alex','Alex@gmail.com','0451114586'),
                    ('Josua','sat@gmail.com','04511154584'),
                    ('SCott','scott@gmail.com','0449632587'),
                    ('JOJO','JOJO@gmail.com','0452226987');
                    """;
            stmt.executeUpdate(query);
            query = "SELECT * FROM Customer;";
            result = stmt.executeQuery(query);
        }




