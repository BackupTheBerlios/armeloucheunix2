
package gid_metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.GregorianCalendar;

/**
 * <p>Repr&eacute;sente un acteur du CCED, acteurs qui participent &agrave; proc&eacute;dure de d&eacute;l&eacute;gation de cr&eacute;dit</p>
 * 
 */
public class CCED extends Acteur {

    
    public CCED ()
    {
    }
    public CCED (DataSource ds)
    {
		this.ds = ds;
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
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT cced.* FROM  cced WHERE id='" + id + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
			    Comptabilite compta = new Comptabilite(ds);
			    compta.chargeParIdActeur(getId());
			    setComptaPerso(compta);
			 }
			
			 try
			 {
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				res2 = s2.executeQuery("SELECT ordonnance_id FROM a_traiter WHERE a_traiter.acteur_id='" + getId() + "'");
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation(ds);
				    ordon.chargeParId(res2.getInt("ordonnance_id"));
					addATraiter(ordon);
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
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query;
			if (getId()==0)
			{
			    query = "INSERT INTO cced(login,mdp,nom,prenom) VALUES ('" + getLogin() + "', '" + getMdp() + "', '" + getNom() + "', " + getPrenom() + "')";
			}
			else
			{
			    query = "UPDATE cced set login = '" + getLogin() + "', mdp = '" + getMdp() + "', nom = '" + getNom() + "', prenom = '" + getPrenom() + "' WHERE id='" + getId() + "'";
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
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("DELETE FROM cced WHERE id='" + getId() + "'");
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
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT * FROM cced ORDER BY nom,prenom");
			while(res.next())
			{
			    CCED ced = new CCED(ds);
			    ced.setId(res.getInt("id"));
			    ced.setNom(res.getString("nom"));
			    ced.setPrenom(res.getString("prenom"));
			    tous.addElement(ced);
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


/**
 * <p>Prend en charge l'ordonnance en parametre (qui doit faire partie de aTraiter) et effectue les traitements n&eacute;cessaires.<br/>
 *  L'ordonnance est ensuite effac&eacute;e de la liste aTraiter.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance l'ordonnance à prendre en charge
 */
    public void prendreOrdonnanceEnCharge(OrdonnanceDelegation ordonnance) throws Exception
    {
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("DELETE FROM a_traiter WHERE ordonnance_id='" + ordonnance.getId() + "' AND acteur_id='" + this.getId() + "'");
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
		this.removeATraiter(ordonnance);
    }

/**
 * <p>Inscrit le cr&eacute;dit de l'ordonnance &agrave; la comptabilit&eacute; de l'acteur (si cela n'a pas d&eacute;j&agrave; &eacute;t&eacute; fait).</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance l'ordonnance qui mouvemente la comptabilité
 */
    public void majComptabilite(OrdonnanceDelegation ordonnance) throws Exception
    {
        Operation operation = new Operation(ds);
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query="SELECT operation.id FROM operation, comptabilite WHERE ordonnance_id='" + ordonnance.getId() + "' AND comptabilite_id=comptabilite.id AND acteur_id='" + getId() + "'";
			res = s.executeQuery(query);
			if (res.next())
			{
			    operation.chargeParId(res.getInt("id"));
			    if(operation.getMontant() != ordonnance.getMontant())
			    {
			        getComptaPerso().setSolde(getComptaPerso().getSolde() - operation.getMontant() + ordonnance.getMontant());
			        operation.setMontant(ordonnance.getMontant());
			        getComptaPerso().sauver(this);
			        operation.sauver();
			    }
			}
			else
			{
			    operation.setLibelle(ordonnance.getLibelle());
		        operation.setMontant(ordonnance.getMontant());
		        GregorianCalendar date = new GregorianCalendar();
		        operation.setDate(new java.util.Date(date.get(GregorianCalendar.YEAR) - 1900, date.get(GregorianCalendar.MONTH), date.get(GregorianCalendar.DAY_OF_MONTH)));
		        operation.setType("C");
		        operation.setComptabilite(getComptaPerso());
		        operation.setOrdonnance(ordonnance);
		        getComptaPerso().setSolde(getComptaPerso().getSolde() + ordonnance.getMontant());
		        getComptaPerso().sauver(this);
		        operation.sauver();
		        getComptaPerso().addOperation(operation);
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
 * <p>Transmet l'ordonnance de d&eacute;l&eacute;gation &agrave; un autre acteur en la pla&ccedil;ant dans sa liste des ordonnances aTraiter.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance ordonnance à transmettre
 * @param destinataire destinataire
 */
    public void transmettreOrdonnance(OrdonnanceDelegation ordonnance, Acteur destinataire) throws Exception
    {
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("INSERT INTO a_traiter(ordonnance_id, acteur_id) VALUES ('" + ordonnance.getId() + "', '" + destinataire.getId() + "')");
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
 * <p>V&eacute;rifie que l'utilisateur est identifi&eacute; a partir de son couple login/mot de passe.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @return true si l'acteur est identifié, sinon false
 */
    public boolean identifie()
    {
        return !(getLogin() == null && getMdp() == null);
    }

    public void authentifie(String login, String password) throws NamingException, Exception
	{
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT cced.* FROM  cced WHERE login='" + login + "' AND mdp='" + password + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
			    Comptabilite compta = new Comptabilite(ds);
			    compta.chargeParIdActeur(getId());
			    setComptaPerso(compta);
			 }
			 try
			 {
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				res2 = s2.executeQuery("SELECT ordonnance_id FROM a_traiter WHERE a_traiter.acteur_id='" + getId() + "'");
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation(ds);
				    ordon.chargeParId(res2.getInt("ordonnance_id"));
				    addATraiter(ordon);
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
			try
			{
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				String q = "SELECT id FROM ordonnance WHERE etat='4' AND initiateur_id IN (SELECT id FROM ordonnateur WHERE controleur_id='" + getId() + "') ORDER BY date DESC";
				System.out.println(q);
				res2 = s2.executeQuery(q);
				
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation(ds);
				    ordon.chargeParId(res2.getInt("id"));
					addArchives(ordon);
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
			try
			{
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				String q = "SELECT id FROM ordonnance WHERE etat!='4' AND initiateur_id IN (SELECT id FROM ordonnateur WHERE controleur_id='" + getId() + "') AND id NOT IN (SELECT ordonnance_id FROM a_traiter where acteur_id='" + getId() +"') ORDER BY date DESC";
				res2 = s2.executeQuery(q);
				
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation(ds);
				    ordon.chargeParId(res2.getInt("id"));
					addEnCours(ordon);
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
 * <p>Does ...</p>
 * 
 * 
 * @param ordonnance 
 */
    public void viser(gid_metier.OrdonnanceDelegation ordonnance) throws Exception, Exception
    {        
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			GregorianCalendar dateVisa = new GregorianCalendar();
			s.executeQuery("UPDATE ordonnance set etat='" + ordonnance.getEtat() + "', visacced='" + ordonnance.isVisaCCED() + "', ref_visacced='" + ordonnance.getRefVisaCCED() + "', date_visacced='" + dateVisa.get(GregorianCalendar.YEAR) + "-" + (dateVisa.get(GregorianCalendar.MONTH)+1) + "-" + dateVisa.get(GregorianCalendar.DAY_OF_MONTH) + "' WHERE id='" + ordonnance.getId() + "'");
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
 }
