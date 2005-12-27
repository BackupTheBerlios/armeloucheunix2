package gid_metier;


import java.sql.*;
import java.util.ArrayList;

/**
 * <p>Repr&eacute;sente une ligne budg&eacute;taire </p>
 * 
 */
public class LigneBudgetaire extends ObjetPersistant {

/**
 * <p>Represente le libelle de la ligne budgetaire</p>
 * 
 */
    private String libelle;

/**
 * <p>Retourne le libelle de la ligne budgetaire</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle de la ligne budgetaire</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

/**
 * <p>Retourne les chapitres associ&eacute;s a la ligne budgetaire</p>
 * <p>Cette operation correspond &agrave; une transaction</p>
 * 
 * 
 * @return 
 */
    public java.util.List retournerChapitres() {        
        // your code here
        return null;
    } 
    
/**
 * <p>Charge (depuis le SGBD) l'objet correspondant a l'identifiant pass&eacute; en param&egrave;tre.</p>
 * 
 * <p>Cette op&eacute;ration correspont a une transaction.</p>
 * 
 * 
 * @param id l'identifiant de l'objet recherché
 * @throws Si une erreur survient pendant la transaction
 */
    public void chargeParId(int id) throws Exception{
    	
    	String req;
    	Statement s;
    	ResultSet rs;
    	
    	req = "SELECT * FROM \"LIGNE_BUDGETAIRE\" ";
    	req+= "WHERE \"ID\" = "+id;
    	req+= ";";
    	
    	if (id == 0)
    		throw new Exception ("Ne peut pas charger la ligne budgetaire - identifiant non défini");
    	
    	try{
    		Class.forName("org.postgresql.Driver");
    		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid","postgres","postgres");
    		conn.setAutoCommit(false);	
    		
    		s = conn.createStatement();
    		
    		rs = s.executeQuery(req);
    		if (rs.next()){
    			libelle = rs.getString("LIBELLE");	
    		}
    		    		
    		conn.commit();
    	}

    	catch(SQLException sqle){
    		throw new Exception("Erreur dans la transaction - chargement de l'objet impossible");
    	}
    	
    }

/**
 * <p>Enregistre l'objet dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public void sauver() throws Exception{
    	
    	String req;
    	Statement s;
    	
    	req = "INSERT INTO \"LIGNE_BUDGETAIRE\" ";
    	if (id == 0)	// insertion
    		req+= "VALUES (default,'"+libelle+"') ";
    	else			// modification
    		req+= "VALUES ('"+id+"','"+libelle+"') ";
    	
    	req+= ";";
    	
    	
    	try{
    		Class.forName("org.postgresql.Driver");
    		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid","postgres","postgres");
    		conn.setAutoCommit(false);	
    		
    		s = conn.createStatement();
    		
    		s.executeUpdate(req);
    		
    		conn.commit();
    	}
    	catch(ClassNotFoundException cnfe){cnfe.printStackTrace();}
    	catch(SQLException sqle){sqle.printStackTrace();}
    	
    }

/**
 * <p>Supprime l'objet du SGBD.</p>
 * <p>Cette operation correspond a une transaction.</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public void supprimer() throws Exception{
    	
    	String req;
    	Statement s;
    	
    	req = "DELETE FROM \"LIGNE_BUDGETAIRE\" ";
    	req+= "WHERE \"ID\" = "+id;
    	req+= ";";
    	
    	
    	try{
    		Class.forName("org.postgresql.Driver");
    		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid","postgres","postgres");
    		conn.setAutoCommit(false);	
    		
    		s = conn.createStatement();
    		
    		s.executeUpdate(req);
    		
    		conn.commit();
    	}
    	catch(ClassNotFoundException cnfe){cnfe.printStackTrace();}
    	catch(SQLException sqle){sqle.printStackTrace();}
    	
    }

/**
 * <p>Retourne tous les objets (du type courant) stockes dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @return Une collection de tous les objets
 * @throws Si une erreur survient pendant la transaction
 */
    public static java.util.ArrayList retournerTous() throws Exception{
    	String req;
    	Statement s;
    	ResultSet rs;
    	ArrayList ret = new ArrayList();
    	
    	req = "SELECT * FROM \"LIGNE_BUDGETAIRE\" ";
    	req+= ";";
    	
    	
    	try{
    		Class.forName("org.postgresql.Driver");
    		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid","postgres","postgres");
    		conn.setAutoCommit(false);	
    		
    		s = conn.createStatement();
    		
    		rs = s.executeQuery(req);
    		
    		while (rs.next()){
    			
    			LigneBudgetaire lb = new LigneBudgetaire();
    			lb.id = rs.getInt("ID");
    			lb.libelle = rs.getString("LIBELLE");
    			
    			ret.add(lb);
    		}
    		
    		conn.commit();
    	}
    	catch(ClassNotFoundException cnfe){cnfe.printStackTrace();}
    	catch(SQLException sqle){sqle.printStackTrace();}
    
    	return ret;
    }

 }
