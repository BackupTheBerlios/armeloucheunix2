
package gid_metier;

import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * <p>Repr&eacute;sente une op&eacute;ration comptable qui est suceptible de mouvementer la comptabilit&eacute; des acteurs</p>
 * 
 */
public class Operation extends ObjetPersistant {

/**
 * <p>Represente le libelle de l'operation</p>
 * 
 */
    private String libelle;

/**
 * <p>Represente le montant de l'operation</p>
 * 
 */
    private int montant;

/**
 * <p>Represente la date de l'operation</p>
 * 
 */
    private java.util.Date date;

/**
 * <p>Represente le type d'operation (d&eacute;bit ou cr&eacute;dit)</p>
 * 
 */
    private String type;

    private OrdonnanceDelegation ordon;
    
    public Operation() throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
    }
    
    public OrdonnanceDelegation getOrdonnance()
    {
        return ordon;
    }
    public void setOrdonnance(OrdonnanceDelegation ordon)
    {
        this.ordon = ordon;
    }
    
    private Comptabilite comptabilite;
    public Comptabilite getComptabilite()
    {
        return comptabilite;
    }
    public void setComptabilite(Comptabilite comptabilite)
    {
        this.comptabilite = comptabilite;
    }
/**
 * <p>retourne le libelle de l'op&eacute;ration</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle de l'op&eacute;ration</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

/**
 * <p>Retourne le montant&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @return montant
 */
    public int getMontant() {        
        return montant;
    } 

/**
 * <p>Modifie le montant&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @param _montant nouveau montant
 */
    public void setMontant(int _montant) {        
        montant = _montant;
    } 

/**
 * <p>Retourne la date&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @return date
 */
    public java.util.Date getDate() {        
        return date;
    } 

/**
 * <p>Modifie la date&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @param _date nouvelle date
 */
    public void setDate(java.util.Date _date) {        
        date = _date;
    } 

/**
 * <p>Retourne le type&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @return type
 */
    public String getType() {        
        return type;
    } 

/**
 * <p>Modifie le type&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @param _type nouveau type
 */
    public void setType(String _type) {        
        type = _type;
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
			String query;
			res = s.executeQuery("SELECT * FROM operation WHERE id='" + id + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
		        setLibelle(res.getString("libelle"));
		        setMontant(res.getInt("montant"));
		        setDate(res.getDate("date"));
		        setType(res.getString("type"));
		        Comptabilite compta = new Comptabilite();
		        compta.chargeParId(res.getInt("comptabilite_id"));
		        setComptabilite(compta);
		        OrdonnanceDelegation ordonnance = new OrdonnanceDelegation();
		        ordonnance.chargeParId(res.getInt("ordonnance_id"));
		        setOrdonnance(ordonnance);
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
    
    public void chargeParIdCompta(int id) throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query;
			res = s.executeQuery("SELECT * FROM operation WHERE comptabilite_id='" + id + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
		        setLibelle(res.getString("libelle"));
		        setMontant(res.getInt("montant"));
		        setDate(res.getDate("date"));
		        setType(res.getString("type"));
		        Comptabilite compta = new Comptabilite();
		        compta.chargeParId(res.getInt("comptabilite_id"));
		        setComptabilite(compta);
		        OrdonnanceDelegation ordonnance = new OrdonnanceDelegation();
		        ordonnance.chargeParId(res.getInt("ordonnance_id"));
		        setOrdonnance(ordonnance);
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
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
		String query="";
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			if (getId()==0)
			{
			    query = "INSERT INTO operation(libelle, montant, date, type, comptabilite_id, ordonnance_id) VALUES ('" + getLibelle() + "', '" + getMontant() + "', '" + getDate() + "', '" + getType() + "', '" + getComptabilite().getId() + "', '" + getOrdonnance().getId() + "')";
			}
			else
			{
			    query = "UPDATE operation set libelle='" + getLibelle() + "', montant='" + getMontant() + "', date='" + getDate() + "', type='" + getType() + "', comptabilite_id='" + getComptabilite().getId() + "', ordonnance_id='" + getOrdonnance().getId() + "' WHERE id='" + getId() + "'";
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
       /* Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("DELETE FROM operation WHERE id='" + getId() + "'");
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
    public Vector retournerTous() throws Exception
    {
       /* Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
		String query="";
		Vector tous = new Vector();
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "SELECT id FROM Operation ORDER BY libelle";
			res = s.executeQuery(query);
			while(res.next())
			{
			    Operation ope = new Operation();
			    ope.chargeParId(res.getInt("id"));
			    tous.addElement(ope);
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
