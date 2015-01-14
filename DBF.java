/**************************************************************************/
//                                                                         /
//    Program koji komunicira sa bazom podataka u preduzecu                /
//    Ispitni zadatak iz predmeta Baze podataka II                         /
//                                                                         /
//    Dejan Acanski, C3                                                    /
//                                                                         /	
/**************************************************************************/
//                                                                         /	
//     Klasa DBF sadrzi metode za direktnu komunikaciju sa bazom           /	
//                                                                         /	
/**************************************************************************/




package preduzece;

import java.util.*;
import java.sql.*;

public class DBF {



 // metoda kojom se iz baze podataka citaju indetifikacioni podaci preduzeca
	
 public static Vector getPreduzece (){
    Vector res = new Vector();
    PreparedStatement pstmt = null;
    try {
      pstmt = preduzece.DBConnection.getConnection().prepareStatement("SELECT IDP FROM Preduzece");
      ResultSet rs = pstmt.executeQuery();
      Vector temp = null;
      while(rs.next()){			// dok ima novih podataka
        temp = new Vector();
        temp.add(new Integer(rs.getInt(1)));        
        res.add(temp);
      }
      rs.close();
      pstmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try{
        if (pstmt != null){
          pstmt.close();
        }
        return res;
      }
      catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
  }



  // metoda koja iz baze cita podatke o radnicima odredjene filijale
	
  public static Vector getRadnici(Integer IDPreduzeca, Integer IDFilijale, Integer IDPoslovnice){
	
    Vector res = new Vector();
    PreparedStatement pstmt = null;
    try {   
        pstmt = DBConnection.getConnection().prepareStatement("SELECT DISTINCT r.MBR, r.IMER, r.PREZR, "+
        "r.ADR, r.MESTO, r.BRTEL, r.DATZ, r.DATR, r.POL "+
        "FROM Radnik r "+
        "WHERE (r.IDP = "+IDPreduzeca+") AND (r.IDF= "+IDFilijale+") AND (r.IDPOS= "+IDPoslovnice+")");

      ResultSet rs = pstmt.executeQuery();
		
      Vector temp = null;
		
      while(rs.next()){
        temp = new Vector();
        temp.add(new Integer(rs.getInt(1))); 
        temp.add(rs.getString(2));
		  temp.add(rs.getString(3));
        temp.add(rs.getString(4));
		  temp.add(rs.getString(5));
        temp.add(rs.getString(6));
		  temp.add(rs.getDate(7));
		  temp.add(rs.getDate(8));	 
		  temp.add(rs.getString(9));
        res.add(temp);
			
      }
      rs.close();
      pstmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try{
        if (pstmt != null){
          pstmt.close();
        }
        return res;
      }
      catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
  }



  // upisivanje filijale u preduzece
	
	public static String insertFilijala(Integer IDPreduzeca,
													Integer IDFilijale,
                                      	String adresa,
                                      	String mesto,
                                      	String telefon){
    
		String res = null;
		
		// proveravamo da li data filijala vec postoji		
		try{
			String provera = "SELECT IDF FROM Filijala WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+")";
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(provera);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				res = "U ovom preduzecu vec postoji filijala sa tim identifikacionim brojem!";
				return res;
			}
			rs.close();
			pstmt.close();			
		}									
		catch(Exception ex){
			ex.printStackTrace();
		}	
		
		// sve je u redu, ubacujemo filijalu		
    	try{
	      PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(
	          "INSERT INTO Filijala (IDP, IDF, ADRF, MESF, BRTELF) VALUES (?,?,?,?,?)");
	      pstmt.setInt(1, IDPreduzeca.intValue());
	      pstmt.setInt(2, IDFilijale.intValue());
	      pstmt.setString(3, adresa);
	      pstmt.setString(4, mesto);
	      pstmt.setString(5, telefon);
	      pstmt.executeUpdate();
	      pstmt.close();
	   }
		catch(Exception ex){
	      ex.printStackTrace();
	      res = "Unos filijale nije uspeo!";
	   }
    	return res;
 	}



	// brisanje filijale iz preduzeca
	
	public static String deleteFilijala(Integer IDPreduzeca, Integer IDFilijale){
	
   	String res = null;
		
		// proveravamo da li data filijala postoji		
		try{
                    String provera = "SELECT IDF FROM Filijala WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+")";
                    PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(provera);
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()){
                            res = "U ovom preduzecu ne postoji filijala sa tim identifikacionim brojem";
                            return res;
                    }
                    rs.close();
                    pstmt.close();			
		}									
		catch(Exception ex){
			ex.printStackTrace();
		}			
		
				
		// sa filijalom brisemo i sve njene poslovnice i radnike u njima		
	 	try{ 
			// iskljucujemo automatsko potvrdjivanje transakcija			
                    DBConnection.getConnection().setAutoCommit(false); 
			
			// prvo iz baze brisemo decu zaposlenih (zbog identifikacione zavisnosti od roditelja)			
                    String upit = "DELETE FROM Dete d "+
                            "WHERE EXISTS (SELECT * FROM Radnik r WHERE (r.IDP = "+IDPreduzeca+") "+
                            "AND (r.IDF = "+IDFilijale+") AND (r.MBR = d.MBR))";
                    PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(upit);
                    pstmt.executeUpdate();
	  
			// zatim brisemo radnike zaposlene u filijali			
                    upit = "DELETE FROM Radnik WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+")";
                    pstmt = DBConnection.getConnection().prepareStatement(upit);
                    pstmt.executeUpdate();
	  
			// posle toga brisemo poslovnice (identifikaciono zavisne od filijale)					
                    upit = "DELETE FROM Poslovnica WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+")";
                    pstmt = DBConnection.getConnection().prepareStatement(upit);
                    pstmt.executeUpdate();
	  
			// na kraju brisemo samu filijalu			
                    upit = "DELETE FROM Filijala WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+")";
                    pstmt = DBConnection.getConnection().prepareStatement(upit);
                    pstmt.executeUpdate();
			
                    pstmt.close();
                    DBConnection.getConnection().commit();	// eksplicitno potvrdjujemo sve prethodne transakcije
	    }
	    catch(Exception ex){
	    	ex.printStackTrace(); 
	      res = "Brisanje filijale nije uspelo!";
	      try{
	        DBConnection.getConnection().rollback(); // vracamo bazu u stanje pre brisanja
	      }
	      catch(Exception exRB){
	        exRB.printStackTrace();
	      }
			
 		}
 		return res;
  	}
	


	// upisivanje poslovnice u preduzece	
	public static String insertPoslovnica(Integer IDPreduzeca,
            Integer IDFilijale,
            Integer IDPoslovnice,
            String adresa,                                      	 
            String telefon){
            String res = null;

            // proveravamo da li data poslovnica vec postoji		
            try{
                String provera = "SELECT IDPOS FROM Poslovnica WHERE (IDP = "+IDPreduzeca+") "+
                                    "AND (IDF = "+IDFilijale+") AND (IDPOS = "+IDPoslovnice+")";
                PreparedStatement pstmt1 = DBConnection.getConnection().prepareStatement(provera);
                ResultSet rs = pstmt1.executeQuery();
                if (rs.next()){
                    res = "U ovom preduzecu vec postoji poslovnica sa identifikacionim brojem koji ste uneli!";
                    return res;
                }
                rs.close();
                pstmt1.close();			

            }									
            catch(Exception ex){
                    ex.printStackTrace();			
            }		

            // ubacujemo poslovnicu		
            try{
                PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(
                    "INSERT INTO Poslovnica (IDP, IDF, IDPOS, ADRPOS, BRTELPOS) VALUES (?,?,?,?,?)");
                pstmt.setInt(1, IDPreduzeca.intValue());    
                pstmt.setInt(2, IDFilijale.intValue());
                pstmt.setInt(3, IDPoslovnice.intValue());  
                pstmt.setString(4, adresa);
                pstmt.setString(5, telefon);
                pstmt.executeUpdate();
                pstmt.close();
            }
            catch(Exception ex){
                    ex.printStackTrace();
                    res = "Unos poslovnice nije uspeo!";
            }
            return res;
	}



	// brisanje poslovnice iz preduzeca	
	public static String deletePoslovnica(Integer IDPreduzeca, Integer IDFilijale, Integer IDPoslovnice){
	
   	String res = null;
		
            // proveravamo da li data poslovnica postoji		
            try{
                String provera = "SELECT IDPOS FROM Poslovnica WHERE (IDP = "+IDPreduzeca+") "+
                                    "AND (IDF = "+IDFilijale+") AND (IDPOS = "+IDPoslovnice+")";
                PreparedStatement pstmt1 = DBConnection.getConnection().prepareStatement(provera);
                ResultSet rs = pstmt1.executeQuery();
                if (!rs.next()){
                    res = "U ovom preduzecu i datoj filijali ne postoji poslovnica sa tim identifikacionim brojem!";
                    return res;	
                }	
                rs.close();
                pstmt1.close();			
            }									
            catch(Exception ex){
                    ex.printStackTrace();
            }			


            // pocinjemo brisanje		
            try{ 
            // iskljucujemo automatsku potvrdu transakcija			
                DBConnection.getConnection().setAutoCommit(false);

                // prvo brisemo decu svih radnika iz te poslovnice (jer su identifikaciono zavisna od radnika)			
                String upit = "DELETE FROM Dete d "+
                            "WHERE EXISTS (SELECT * FROM Radnik r WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+") "+
                            "AND (r.MBR = d.MBR))";
                PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(upit);
                pstmt.executeUpdate();

                    // brisemo radnike			
                upit = "DELETE FROM Radnik WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+") "+
                        "AND (IDPOS = "+IDPoslovnice+")";
                pstmt = DBConnection.getConnection().prepareStatement(upit);
                pstmt.executeUpdate();
	  
                // na kraju brisemo poslovnicu			
                upit = "DELETE FROM Poslovnica WHERE (IDP = "+IDPreduzeca+") AND (IDF = "+IDFilijale+") "+
                        "AND (IDPOS = "+IDPoslovnice+")";
                pstmt = DBConnection.getConnection().prepareStatement(upit);
                pstmt.executeUpdate();
	  			
                pstmt.close();
                DBConnection.getConnection().commit();		// eksplicitna potvrda transakcije
	   }
	   catch(Exception ex){
	   	ex.printStackTrace();
                res = "Brisanje poslovnice nije uspelo!";
                    try{
                        DBConnection.getConnection().rollback();
			}
                    catch(Exception exRB){
                        exRB.printStackTrace();
                    }
			
            }
 		return res;
  	}
	


	// zaposljavanje radnika	
	public static String insertRadnik(Integer mbr, // maticni broj												
	                                	String ime,												
	                                	String prezime,												
	                                	String adresa,
	                                	String mesto,
	                                	String telefon,
											  	java.sql.Date datumZaposlenja,
											  	java.sql.Date datumRodjenja,
											  	String pol,
				                       	Integer IDPoslovnice,
	 										  	Integer IDFilijale,
											  	Integer IDSluzbe,
											  	Integer IDSektora,	
	                                	Integer IDPreduzeca){
		String res = null;	
		
		// proveravamo da li dati radnik postoji		
		try{
			String provera = "SELECT MBR FROM Radnik WHERE (MBR = "+mbr+")";
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(provera);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
	  			res = "U ovom preduzecu vec postoji radnik sa maticnim brojem koji ste uneli";
				return res;
			}
			rs.close();
			pstmt.close();
			return res;
		}									
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		// unosimo podatke o radniku
		try{
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement("INSERT INTO Radnik "+
				"(MBR, IMER, PREZR, ADR, MESTO, BRTEL, DATZ, DATR, POL, IDPOS, IDF, IDSL, IDS, IDP, Sluzba_IDP) "+
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					
			pstmt.setInt(1, mbr.intValue());
			pstmt.setString(2, ime);
			pstmt.setString(3, prezime);
			pstmt.setString(4, adresa);
			pstmt.setString(5, mesto);
			pstmt.setString(6, telefon);
			pstmt.setDate(7, datumZaposlenja);
			pstmt.setDate(8, datumRodjenja);
			pstmt.setString(9, pol);  
			pstmt.setInt(10, IDPoslovnice.intValue());
			pstmt.setInt(11, IDFilijale.intValue());
			pstmt.setInt(12, IDSluzbe.intValue());
			pstmt.setInt(13, IDSektora.intValue());      
			pstmt.setInt(14, IDPreduzeca.intValue()); // ID preduzeca se nasledjuje dva puta
			pstmt.setInt(15, IDPreduzeca.intValue()); // jednom kroz sluzbe, jednom kroz filijale
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
			res = "Unos radnika nije uspeo!";
		}
		return res;
  }



	// brisanje radnika iz preduzeca	
	public static String deleteRadnik(Integer IDPreduzeca, Integer maticniBroj){
	
   	String res = null;
		
		// proveravamo da li je dati radnik zaposlen u tom preduzecu
		try{
			String provera = "SELECT MBR FROM Radnik WHERE (MBR = "+maticniBroj+") AND (IDP = "+IDPreduzeca+")";
			PreparedStatement pstmt1 = DBConnection.getConnection().prepareStatement(provera);
			ResultSet rs = pstmt1.executeQuery();
			if (!rs.next()){
		  		res = "U ovom preduzecu ne postoji radnik sa tim maticnim brojem!";
				return res;
			}
			rs.close();
			pstmt1.close();			
		}									
		catch(Exception ex){
			ex.printStackTrace();
		}			
		

	 	try{ 
			//prvo brisemo decu radnika
			DBConnection.getConnection().setAutoCommit(false);			
			String upit = "DELETE FROM Dete WHERE MBR = "+maticniBroj;
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(upit);
	      pstmt.executeUpdate();
	  
   		upit = "DELETE FROM Radnik WHERE MBR = "+maticniBroj;
	      pstmt = DBConnection.getConnection().prepareStatement(upit);
	      pstmt.executeUpdate();
	  		 			
	      pstmt.close();
		   DBConnection.getConnection().commit();		
		}			
		catch(Exception ex){
			ex.printStackTrace();
			res = "Brisanje radnika nije uspelo!";
			try{
				DBConnection.getConnection().rollback();
	      }
			catch(Exception exRB){
				exRB.printStackTrace();
	      }
			
 		}
 		return res;
  	}

}
