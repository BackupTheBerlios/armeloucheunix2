
package gid_metier;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.Vector;

/**
 * <p>Represente le chapitre (d'une ordonnance)</p>
 * 
 */
public class Chapitre extends ObjetPersistant {

/**
 * <p>Represente le code du chapitre dans la nomenclature GID</p>
 * 
 */
    private String code;

/**
 * <p>Represente le libelle du chapitre</p>
 * 
 */
    private String libelle;
/**
 * <p>Represente la ligne budg&eacute;taire &agrave; laquelle est rattach&eacute; le chapitre</p>
 * 
 */
    private gid_metier.LigneBudgetaire ligneBudgetaire;

/**
 * <p>Retourne le code du chapitre</p>
 * 
 * 
 * @return code
 */
    public String getCode() {        
        return code;
    } 

/**
 * <p>Modifie le code du chapitre</p>
 * 
 * 
 * @param _code nouveau code
 */
    public void setCode(String _code) {        
        code = _code;
    } 

/**
 * <p>Retourne le libelle du chapitre</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle du chapitre</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

    /** @poseidon-generated */
    public gid_metier.LigneBudgetaire getLigneBudgetaire() {
        return ligneBudgetaire;
    }
    /** @poseidon-generated */
    public void setLigneBudgetaire(gid_metier.LigneBudgetaire ligneBudgetaire) {
        this.ligneBudgetaire = ligneBudgetaire;
    }
    
    
/**
 * <p>Charge (depuis le SGBD) l'objet correspondant a l'identifiant pass&eacute; en param&egrave;tre.</p>
 * <p>Cette op&eacute;ration correspont a une transaction.</p>
 * 
 * 
 * @param id l'identifiant de l'objet recherché
 * @return L'objet si il est trouvé, sinon null
 * @throws Si une erreur survient pendant la transaction
 */
    public void chargeParId(int id) throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		String query="";
		
		 try
		 {
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "SELECT * FROM chapitre WHERE id='" + id + "'";
			res = s.executeQuery(query);
			if(res.next())
			{
			    setId(res.getInt("id"));
			    setCode(res.getString("code"));
			    setLibelle(res.getString("libelle"));
			    ligneBudgetaire.chargeParId(res.getInt("ligne_budgetaire_id"));
			}
		}
	    catch (SQLException e)
		{
	        System.out.println(e.getMessage());
		}
		finally
		{
			if (res != null)
			{
				try {
					res.close();
				} catch (SQLException e) {}
				res = null;
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {}
				s = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
				conn = null;
			}
		}
    }

/**
 * <p>Enregistre l'objet dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public void sauver() throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		String query="";
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			if (getId()==0)
			{
			    query = "INSERT INTO chapitre(code,libelle, ligne_budgetaire_id) VALUES ('" + getCode() + "', '" + getLibelle() + "', " + getLigneBudgetaire().getId() + ")";
			}
			else
			{
			    query = "UPDATE chapitre set code='" + getCode() + "', libelle='" + getLibelle() + "', ligne_budgetaire_id='" + getLigneBudgetaire().getId() + "' WHERE id='" + getId() + "'";
			}
			s.executeQuery(query);
			
		}
	    catch (SQLException e)
		{
	        System.out.println(e.getMessage());
		}
		finally
		{
			if (res != null)
			{
				try {
					res.close();
				} catch (SQLException e) {}
				res = null;
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {}
				s = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
				conn = null;
			}
		}  
    }

/**
 * <p>Supprime l'objet du SGBD.</p>
 * <p>Cette operation correspond a une transaction.</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public void supprimer() throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		String query="";
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "DELETE FROM chapitre WHERE id='" + getId() + "'";
			s.executeQuery(query);
		}
	    catch (SQLException e)
		{
	        System.out.println(e.getMessage());
		}
		finally
		{
			if (res != null)
			{
				try {
					res.close();
				} catch (SQLException e) {}
				res = null;
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {}
				s = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
				conn = null;
			}
		}  
    }

/**
 * <p>Retourne tous les objets (du type courant) stockes dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @return Une collection de tous les objets
 * @throws Si une erreur survient pendant la transaction
 */
    public java.util.Vector retournerTous() throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		String query="";
		Vector tous = new Vector();
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "SELECT id FROM chapitre";
			res = s.executeQuery(query);
			while(res.next())
			{
			    Chapitre chap = new Chapitre();
			    chap.chargeParId(res.getInt("id"));
			    tous.addElement(chap);
			}
		}
	    catch (SQLException e)
		{
	        System.out.println(e.getMessage());
		}
		finally
		{
			if (res != null)
			{
				try {
					res.close();
				} catch (SQLException e) {}
				res = null;
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {}
				s = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
				conn = null;
			}
		}  
		return tous;
    }

 }
