
package gid_metier;

import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.Vector;

/**
 * <p>Represente la comptabilit&eacute; des acteurs de GID</p>
 * 
 */
public class Comptabilite extends ObjetPersistant {

/**
 * <p>Represente le solde de la comptabilit&eacute;</p>
 * 
 */
    private int solde;
/**
 * <p>Liste des op&eacute;rations enregistr&eacute;es &agrave; la comptabilit&eacute;</p>
 * 
 * 
 * @poseidon-type gid_metier.Operation
 */
    private java.util.Vector operation = new java.util.Vector();
    
    public Comptabilite() 
    {
        try 
        {
            Context initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
        }
        catch (Exception e)
        {
            
        }
    }

/**
 * <p>Retourne le solde de la comptabilit&eacute;</p>
 * 
 * 
 * @return solde
 */
    public int getSolde() {        
        return solde;
    } 

/**
 * <p>Modifie le solde de la comptabilit&eacute;</p>
 * 
 * 
 * @param _solde nouveau solde
 */
    public void setSolde(int _solde) {        
        solde = _solde;
    } 

/**
 * <p>Inscrit une operation &agrave; la comptabilit&eacute; et actualise le solde.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param op operation qui mouvemente la comptabilité
 */
    public void mouvementer(gid_metier.Operation op) {        
        // your code here
    } 

    /** @poseidon-generated */
    public java.util.Vector getOperations() {
        return operation;
    }
    /** @poseidon-generated */
    public void addOperation(gid_metier.Operation operation) {
        if (! this.operation.contains(operation)) this.operation.addElement(operation);
    }
    /** @poseidon-generated */
    public void removeOperation(gid_metier.Operation operation) {
        this.operation.remove(operation);
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
			String query = "SELECT * FROM comptabilite WHERE id='" + id + "'" ;
			System.out.println(query);
			res = s.executeQuery(query);
			if (res.next())
			{
			    setId(res.getInt("id"));
			    setSolde(res.getInt("solde"));
			    try
				{
				    conn2 = ds.getConnection();
					s2 = conn2.createStatement();
					res2 = s2.executeQuery("SELECT id FROM operation WHERE comptabilite_id='" + getId() + "'");
					while(res2.next())
					{
					    Operation op = new Operation();
					    op.chargeParIdCompta(res2.getInt("id"));
					    addOperation(op);
					}
				}
				catch (SQLException e)
				{
			        System.out.println(e.getMessage());
				}
				finally
				{
					if (res2 != null)
					{
						try {
							res2.close();
						} catch (SQLException e) {}
						res2 = null;
					}
					if (s2 != null) {
						try {
							s2.close();
						} catch (SQLException e) {}
						s2 = null;
					}
					if (conn2 != null) {
						try {
							conn2.close();
						} catch (SQLException e) {}
						conn2 = null;
					}
				}
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
    
    public void chargeParIdActeur(int id) throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		operation = new Vector();
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query;
			res = s.executeQuery("SELECT * FROM comptabilite WHERE acteur_id='" + id + "'");
			if (res.next())
			{
			    this.setId(res.getInt("id"));
			    this.setSolde(res.getInt("solde"));
			    res = s.executeQuery("SELECT * FROM operation WHERE comptabilite_id='" + getId() + "'");
			    while (res.next())
			    {
			        Operation op = new Operation();
			        op.setId(res.getInt("id"));
			        op.setLibelle(res.getString("libelle"));
			        op.setMontant(res.getInt("montant"));
			        op.setDate(res.getDate("date"));
			        op.setType(res.getString("type"));
			        this.addOperation(op);
			    }
			}
			else
			{
			    setId(0);
			    setSolde(0);
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
    
    public void sauver() throws Exception
    {}
/**
 * <p>Enregistre l'objet dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public void sauver(Acteur acteur) throws Exception
    {
       /* Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query;
			if (getId()==0)
			{
			    query = "INSERT INTO comptabilite(solde,acteur_id) VALUES ('" + getSolde() + "', '" + acteur.getId() + "')";
			}
			else
			{
			    query = "UPDATE comptabilite set solde = '" + getSolde() + "', acteur_id='" + acteur.getId() + "' WHERE id='" + getId() + "'";
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
		chargeParIdActeur(acteur.getId());
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
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("DELETE FROM comptabilite WHERE id='" + getId() + "'");
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
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
		String query="";
		Vector tous = new Vector();
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "SELECT id FROM comptabilite";
			res = s.executeQuery(query);
			while(res.next())
			{
			    Comptabilite compta = new Comptabilite();
			    compta.chargeParId(res.getInt("id"));
			    tous.addElement(compta);
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
