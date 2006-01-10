
package gid_metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * <p>Repr&eacute;sente un acteur de la TR ou de la TP qui participe &agrave; proc&eacute;dure de d&eacute;l&eacute;gation de cr&eacute;dit</p>
 * 
 */
public class TR_TP extends Acteur {
 


/**
 * <p>Charge (depuis le SGBD) l'objet correspondant a l'identifiant pass&eacute; en param&egrave;tre.</p>
 * <p>Cette op&eacute;ration correspont a une transaction.</p>
 * 
 * 
 * @param id l'identifiant de l'objet recherch�
 * @return L'objet si il est trouv�, sinon null
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
			res = s.executeQuery("SELECT tr_tp.* FROM tr_tp WHERE id='" + id + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
			    Comptabilite compta = new Comptabilite();
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
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
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
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query;
			if (getId()==0)
			{
			    query = "INSERT INTO tr_tp(login,mdp,nom,prenom) VALUES ('" + getLogin() + "', '" + getMdp() + "', '" + getNom() + "', " + getPrenom() + "')";
			}
			else
			{
			    query = "UPDATE tr_tp set login = '" + getLogin() + "', mdp = '" + getMdp() + "', nom = '" + getNom() + "', prenom = '" + getPrenom() + "' WHERE id='" + getId() + "'";
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
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("DELETE FROM tr_tp WHERE id='" + getId() + "'");
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
    public java.util.ArrayList retournerTous() throws Exception{
    	return null;
    }


/**
 * <p>Prend en charge l'ordonnance en parametre (qui doit faire partie de aTraiter) et effectue les traitements n&eacute;cessaires.<br/>
 *  L'ordonnance est ensuite effac&eacute;e de la liste aTraiter.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance l'ordonnance � prendre en charge
 */
    public void prendreOrdonnanceEnCharge(OrdonnanceDelegation ordonnance) throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
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
 * @param ordonnance l'ordonnance qui mouvemente la comptabilit�
 */
    public void majComptabilite(OrdonnanceDelegation ordonnance) throws Exception
    {
        Operation operation = new Operation();
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
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
 * @param ordonnance ordonnance � transmettre
 * @param destinataire destinataire
 */
    public void transmettreOrdonnance(OrdonnanceDelegation ordonnance, Acteur destinataire) throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("INSERT INTO a_traiter(ordonnance_id, acteur_id) VALUES ('" + ordonnance.getId() + "', '" + destinataire.getId() + "'");
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
 * @return true si l'acteur est identifi�, sinon false
 */
    public boolean identifie(){
        return !(getLogin()== null && getMdp()==null);
    }

    public void authentifie(String login, String password) throws NamingException, Exception
	{

        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT tr_tp.* FROM tr_tp WHERE login='" + login + "' AND mdp='" + password + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
			    Comptabilite compta = new Comptabilite();
			    compta.chargeParIdActeur(getId());
			    setComptaPerso(compta);
			 }
			
			 try
			 {
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				res2 = s2.executeQuery("SELECT ordonnance_id FROM a_traiter WHERE a_traiter.acteur_id='" + getId() + "' AND ordonnance_id NOT IN (SELECT ordonnance_id FROM a_traiter WHERE acteur_id!='" + getId() + "')");
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
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
				String q = "SELECT id FROM ordonnance WHERE etat='4' AND delegataire_id IN (SELECT id FROM sousordonnateur WHERE tr_tp_id='" + getId() + "') ORDER BY date DESC";
				res2 = s2.executeQuery(q);
				
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
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
				String q = "SELECT id FROM ordonnance WHERE etat='3' AND delegataire_id IN (SELECT id FROM sousordonnateur WHERE tr_tp_id='" + getId() + "') AND id NOT IN (SELECT ordonnance_id FROM a_traiter where acteur_id='" + getId() +"') ORDER BY date DESC";
				res2 = s2.executeQuery(q);
				
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
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
    public boolean peut_clore(OrdonnanceDelegation ordon) throws NamingException
    {
        boolean r=false;
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query="SELECT ordonnance_id FROM action WHERE ordonnance_id='" + ordon.getId() + "' AND participant_id IN (SELECT cped_id FROM sousordonnateur WHERE tr_tp_id='" + getId() + "' AND sousordonnateur.id IN (SELECT delegataire_id FROM ordonnance WHERE ordonnance.id='" + ordon.getId() + "'))";
			res = s.executeQuery(query);
			if (res.next())
			{
			    r = true;
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
		return r;
    }
}
