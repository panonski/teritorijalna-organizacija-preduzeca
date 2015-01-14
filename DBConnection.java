/**************************************************************************/
//                                                                         /
//    Program koji komunicira sa bazom podataka u preduzecu                /
//    Ispitni zadatak iz predmeta Baze podataka II                         /
//                                                                         /
//    Dejan Acanski, C3                                                    /
//                                                                         /	
/**************************************************************************/
//                                                                         /	
//		Klasa DBConnection ostvaruje vezu sa bazom                           /
//                                                                         /	
/**************************************************************************/



package preduzece;

import java.sql.*;

public class DBConnection {

	
  public DBConnection(){
  }

  // osnovne metode za rad sa konekcikcijom
	
  public static boolean testConnection(){
    try{
      Statement s = conn.createStatement();
      s.executeQuery("select table_name from user_tables");
      s.close();
      return true;
    }
    catch(Exception e){
      return false;
    }
  }

  public static Connection getConnection() {
    return conn;
  }

  public static void closeConnection() {
    try{
      conn.close();
    }
    catch(SQLException e){
      System.out.println("Neuspešno zatvaranje konekcije");
      e.printStackTrace();
    }
  }


  private static Connection conn = null;

  // kreiranje konekcije
  static{
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); // zelimo oracle-ov drajver
      conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","system","manager");
		// zadali smo URL, korisnicko ime i sifru koje cemo koristiti
    }
    catch(Exception e){
      System.out.println("Database down");
      e.printStackTrace();
    }
  }
}
