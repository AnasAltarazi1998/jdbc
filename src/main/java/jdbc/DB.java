package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    // JDBC driver name and database URL
   private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   private static final String DB_URL = "jdbc:mysql://localhost/EMP?createDatabaseIfNotExist=true";

   //  Database credentials
   private static final String USER = "root";
   private static final String PASS = "";
   private Connection conn = null;
   private PreparedStatement stmt = null;

   public DB()
   {
       init();
   }
   private void init()
   {
       try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
   }
   public Employe addEmployee(Employe e) throws SQLException,Exception
   {
       if(conn == null) throw new Exception("can not connect with database");

        stmt = conn.prepareStatement("insert into employees values (?,?,?,?)");
        stmt.setInt(1, e.getId());
        stmt.setString(2, e.getName());
        stmt.setString(3, e.getEmail());
        stmt.setString(4, e.getPassword());
        int rows = stmt.executeUpdate();
        if(rows == 1)
        {
            stmt = conn.prepareStatement("select * from employees where id = ?");
            stmt.setInt(1, e.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Employe()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"));
        }else{
            throw new Exception("can not insert this user");
        }
   }
   public Employe findById(int id) throws SQLException,Exception
   {
    if(conn == null) throw new Exception("can not connect with database");
    stmt = conn.prepareStatement("select * from employees where id = ?");
    stmt.setInt(1, id);
    ResultSet rs = stmt.executeQuery();
    rs.next();
    return new Employe()
        .id(rs.getInt("id"))
        .name(rs.getString("name"))
        .email(rs.getString("email"))
        .password(rs.getString("password"));
   }

   public Employe findByName(String name) throws SQLException,Exception
   {
    if(conn == null) throw new Exception("can not connect with database");
    stmt = conn.prepareStatement("select * from employees where name = ?");
    stmt.setString(1, name);
    ResultSet rs = stmt.executeQuery();
    rs.next();
    return new Employe()
        .id(rs.getInt("id"))
        .name(rs.getString("name"))
        .email(rs.getString("email"))
        .password(rs.getString("password"));
   }

   

   public void close() throws SQLException
   {
       if(conn != null)
        conn.close();
   }
}
