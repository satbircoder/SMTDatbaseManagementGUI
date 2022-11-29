package smtdatabaseapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CustomerInfoUtil {
    private static final String URL_DB = "jdbc:mysql://localhost:3306/smtbiz";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection con = null;
    
    public static void connectDatabase(){
        try{
            con = DriverManager.getConnection(URL_DB, USER, PASSWORD);
            con.setAutoCommit(false);
        }
        catch(SQLException ex){
            System.out.println("SqlException occured in database connection: " +ex.getMessage());
        }
    }
    public static void closeDatabase(){
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
        }
        catch(SQLException ex){
            System.out.println("Sql Exception occured in database close: "+ex.getMessage());
        }
    }
    public static ResultSet executeQuery(String queryStmt){
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;
        try{
            connectDatabase();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        }
        catch(SQLException ex){
            System.out.println("SQL Exception Occured on execute Query: "+ex.getMessage());
        }
        finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                closeDatabase();
            }
            catch(SQLException ex){
                System.out.println("Sql Exception occured on closing Database: "+ex.getMessage());
            }
        }
        return crs;
    }
    public static int executeUpdate(String sqlStmt){
        Statement stmt = null;
        int count;
        try{
            connectDatabase();
            stmt = con.createStatement();
            count = stmt.executeUpdate(sqlStmt);
            con.commit();
            return count;
        }
        catch(SQLException ex){
            System.out.println("SQLException occured on execute update: "+ex.getMessage());
            return 0;
        }
        finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                closeDatabase();
            }
            catch(SQLException ex){
                System.out.println("Sql Exception occured on database closing: "+ex.getMessage());
            }
        }
    }
    
}
