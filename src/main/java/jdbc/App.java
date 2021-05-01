package jdbc;
//STEP 1. Import required packages
import java.sql.*;

public class App {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/EMP?createDatabaseIfNotExist=true";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "delete from employees where 1";
      int rows = stmt.executeUpdate(sql);
      System.out.println(rows + " rows effected");
      stmt.close();
      conn.close();
   }catch(SQLException se){
      if(se.getMessage().contains("Table") && se.getMessage().contains("doesn't exist"))
      {
         try {
            String[] values = se.getMessage().split("'*'");
            for (String string : values) {
               System.out.println("the value of matched strings is : "+string);
            }
            stmt.executeUpdate("CREATE TABLE "+values[1]+" (id int,name varchar(255),email varchar(255),password varchar(255))");
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample