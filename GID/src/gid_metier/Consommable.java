
package gid_metier;

import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * <p>Represente les consommables pouvant etre achet&eacute;s par le cr&eacute;dit de l'ordonnance</p>
 * 
 */
public class Consommable extends ObjetPersistant {

/**
 * <p>Represente le libelle du consommable</p>
 * 
 */
    private String libelle;

/**
 * <p>Represente le prix du consommable</p>
 * 
 */
    private int prix;

/**
 * <p>Represente l'unit&eacute; dans laquelle se lit le prix du consommable (ex : &euro;/L, &euro;/Kg...)</p>
 * 
 */
    private String unite;

/**
 * <p>Represente le nombre d'unit&eacute;s de ce consommable pr&eacute;vu par l'ordonnance de d&eacute;l&eacute;gation.</p>
 * 
 */
    private int quantite;

/**
 * <p>Retourne le libelle du consommable</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle du consommable</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

/**
 * <p>Retourne le prix du consommable</p>
 * 
 * 
 * @return prix
 */
    public int getPrix() {        
        return prix;
    } 

/**
 * <p>Modifie le prix du consommable</p>
 * 
 * 
 * @param _prix nouveau prix
 */
    public void setPrix(int _prix) {        
        prix = _prix;
    } 

/**
 * <p>Retourne l'unite du prix du consommable</p>
 * 
 * 
 * @return unite
 */
    public String getUnite() {        
        return unite;
    } 

/**
 * <p>Modifie l'unite du prix du consommable</p>
 * 
 * 
 * @param _unite nouvelle unite
 */
    public void setUnite(String _unite) {        
        unite = _unite;
    } 

/**
 * <p>Retourne la quantite de ce consommable pr&eacute;vue par l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 * 
 * @return quantite
 */
    public int getQuantite() {        
        return quantite;
    } 

/**
 * <p>Modifie la quantite de ce consommable pr&eacute;vue par l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 * 
 * @param _quantite nouvelle quantite
 */
    public void setQuantite(int _quantite) {        
        quantite = _quantite;
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
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT * FROM consommable WHERE id='" + id + "'");
			if(res.next())
			{
			    setId(res.getInt("id"));
			    setLibelle(res.getString("libelle"));
			    setPrix(res.getInt("prix"));
			    setUnite(res.getString("unite"));
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
			    query = "INSERT INTO consommable(libelle, prix, unite) VALUES ('" + getLibelle() + "', '" + getPrix() + "', '" + getUnite() + ")";
			}
			else
			{
			    query = "UPDATE consommable set libelle='" + getLibelle() + "', prix='" + getPrix() + "', unite='" + getUnite() + "' WHERE id='" + getId() + "'";
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
			query = "DELETE FROM consommable WHERE id='" + getId() + "'";
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
        Vector tous = new Vector();
    	Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT * FROM consommable ORDER BY libelle");
			while(res.next())
			{
			    Consommable c = new Consommable();
			    c.setId(res.getInt("id"));
			    c.setLibelle(res.getString("libelle"));
			    c.setPrix(res.getInt("prix"));
			    c.setUnite(res.getString("unite"));
			    tous.addElement(c);
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
