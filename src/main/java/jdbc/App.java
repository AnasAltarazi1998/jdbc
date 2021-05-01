package jdbc;

import java.sql.SQLException;

class App{
   public static void main(String[] args) {
      DB db = new DB();
      try {
         Employe e = db.addEmployee(new Employe().id(1).email("email").name("name").password("password"));
         System.out.println(e.toString());
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      } catch (Exception e1) {
         e1.printStackTrace();
      }
   }
}