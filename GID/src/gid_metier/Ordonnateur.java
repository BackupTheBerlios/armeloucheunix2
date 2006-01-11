
package gid_metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.GregorianCalendar;
import java.util.Vector;
/**
 * <p>Repr&eacute;sente l'ordonnateur principal, acteur qui initie la proc&eacute;dure de d&eacute;l&eacute;gation de cr&eacute;dit</p>
 * 
 */
public class Ordonnateur extends Acteur {

/**
 * <p>Represente le controleur CED affect&eacute; &agrave; l'ordonnateur principal.</p>
 * 
 */
    private gid_metier.CCED controleur;
    
    public Ordonnateur() throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
    }

    /** @poseidon-generated */
    public gid_metier.CCED getControleur() {
        return controleur;
    }
    /** @poseidon-generated */
    public void setControleur(gid_metier.CCED cCED) {
        this.controleur = cCED;
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
			res = s.executeQuery("SELECT ordonnateur.*,cced.nom as nom_cced, cced.prenom as prenom_cced FROM ordonnateur, cced WHERE ordonnateur.id='" + id + "' AND ordonnateur.controleur_id=cced.id");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
				CCED cced = new CCED();
				cced.setId(res.getInt("controleur_id"));
				cced.setNom(res.getString("nom_cced"));
				cced.setPrenom(res.getString("prenom_cced"));
				setControleur(cced);
				
			    Comptabilite compta = new Comptabilite();
			    compta.chargeParIdActeur(getId());
			    setComptaPerso(compta);
			 }
			 try
			 {
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				res2 = s2.executeQuery("SELECT ordonnance_id FROM a_traiter WHERE a_traiter.acteur_id='" + getId() + "' ORDER BY date desc");
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
				String q = "SELECT id FROM ordonnance WHERE etat='4' AND initiateur_id='" + getId() + "' AND id NOT IN (SELECT ordonnance_id FROM a_traiter)";
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
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query;
			if (getId()==0)
			{
			    query = "INSERT INTO ordonnateur(login,mdp,nom,prenom,controleur_id) VALUES ('" + getLogin() + "', '" + getMdp() + "', '" + getNom() + "', " + getPrenom() + "', '" + getControleur().getId() + "')";
			}
			else
			{
			    query = "UPDATE ordonnateur set login = '" + getLogin() + "', mdp = '" + getMdp() + "', nom = '" + getNom() + "', prenom = '" + getPrenom() + "', controleur_id = '" + getControleur().getId() + "' WHERE id='" + getId() + "'";
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
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			s.executeQuery("DELETE FROM ordonnateur WHERE id='" + getId() + "'");
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
        Vector tous = new Vector();
       /* Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
		String query = "";
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "SELECT * FROM ordonnateur ORDER BY nom,prenom";
			res = s.executeQuery(query);
			while(res.next())
			{
			    Ordonnateur ordo = new Ordonnateur();
			    ordo.setId(res.getInt("id"));
			    ordo.setNom(res.getString("nom"));
			    ordo.setPrenom(res.getString("prenom"));
			    tous.addElement(ordo);
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
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
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
		removeATraiter(ordonnance);
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
        Operation operation = new Operation();
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
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
			        getComptaPerso().setSolde(getComptaPerso().getSolde() + operation.getMontant() - ordonnance.getMontant());
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
		        operation.setType("D");
		        operation.setComptabilite(getComptaPerso());
		        operation.setOrdonnance(ordonnance);
		        getComptaPerso().setSolde(getComptaPerso().getSolde() - ordonnance.getMontant());
		        System.out.println(getComptaPerso().getSolde());
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
       /* Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
		boolean deja_a_traiter = false;
		String query;
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "SELECT ordonnance_id FROM a_traiter WHERE (acteur_id='" + destinataire.getId() + "' OR acteur_id IN (SELECT tr_tp_id FROM sousordonnateur)) AND ordonnance_id='" + ordonnance.getId() + "'";
			res = s.executeQuery(query);
			if (res.next())
			{
			    deja_a_traiter = true;
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
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "INSERT INTO a_traiter(ordonnance_id, acteur_id) VALUES ('" + ordonnance.getId() + "', '" + destinataire.getId() + "')";
		    if(deja_a_traiter==false)
		    {
		        s.executeQuery(query);
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
 * <p>V&eacute;rifie que l'utilisateur est identifi&eacute; a partir de son couple login/mot de passe.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @return true si l'acteur est identifié, sinon false
 */
    public boolean identifie(){
        return !(getLogin() == null && getMdp() == null);
    }


    public void authentifie(String login, String password) throws NamingException, Exception
	{

        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT ordonnateur.*,cced.nom as nom_cced, cced.prenom as prenom_cced FROM ordonnateur, cced WHERE ordonnateur.login='" + login + "' AND ordonnateur.mdp='" + password + "' AND ordonnateur.controleur_id=cced.id");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
				CCED cced = new CCED();
				cced.setId(res.getInt("controleur_id"));
				cced.setNom(res.getString("nom_cced"));
				cced.setPrenom(res.getString("prenom_cced"));
				setControleur(cced);
				
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
		try
		{
		    conn2 = ds.getConnection();
			s2 = conn2.createStatement();
			String q = "SELECT id FROM ordonnance WHERE etat='4' AND initiateur_id='" + getId() + "' AND id NOT IN (SELECT ordonnance_id FROM a_traiter) ORDER BY date DESC";
			System.out.println(q);
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
			String q = "SELECT id FROM ordonnance WHERE etat!='4' AND initiateur_id='" + getId() + "' AND id NOT IN (SELECT ordonnance_id FROM a_traiter where acteur_id='" + getId() +"') ORDER BY date DESC";
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
/**
 * <p>Enregistre l'ordonnance nouvellement cr&eacute;e (v&eacute;rifie au pr&eacute;alable que toutes les informations n&eacute;cessaires sont saisies) et la transmet au CCED.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance la nouvelle ordonnance
 * @throws Si la vérification n'est pas satisfaite ou si les acces au SGBD ont retournés des erreurs
 */
    public void saisirOrdonnanceDelegation(gid_metier.OrdonnanceDelegation ordonnance) throws Exception
    {
        ordonnance.setLibelle(ordonnance.getLibelle().replaceAll("'", "&rsquo;").substring(0, (int)java.lang.Math.min(ordonnance.getLibelle().replaceAll("'", "&rsquo").length(), 29)));
        ordonnance.sauver();
        GregorianCalendar dateG = new GregorianCalendar();
	    java.util.Date date = dateG.getTime();
        Action act = new Action();
	    act.setLibelle("Autre");
	    act.setDate(date);
	    act.setType(4);
	    act.setParticipant(this);
	    act.setOrdonnance(ordonnance);
	    act.sauver();
	    act.setLibelle("Transmission");
	    act.setType(2);
	    act.sauver();
        try
		{
		    conn2 = ds.getConnection();
			s2 = conn2.createStatement();
			String q = "SELECT id FROM ordonnance WHERE date='" + ordonnance.getDate() + "' AND libelle='" + ordonnance.getLibelle() + "' AND initiateur_id='" + getId() + "'";
			res2 = s2.executeQuery(q);
			
			while(res2.next())
			{
			    ordonnance.chargeParId(res2.getInt("id"));
			    System.out.println(ordonnance.getConsommables());
			    getEnCours().add(0, ordonnance);
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

/**
 * <p>Supprime l'ordonnance de d&eacute;l&eacute;gation de cr&eacute;dit.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance L'ordonnance à supprimer
 * @throws Une exception si la suppression échoue
 */
    public void retirerOrdonnanceDelegation(gid_metier.OrdonnanceDelegation ordonnance) throws Exception
    {
        String q="";
        try
		{
		    conn = ds.getConnection();
			s = conn.createStatement();
			q = "SELECT DISTINCT comptabilite_id, id FROM operation WHERE ordonnance_id='" + ordonnance.getId() + "'";
			res = s.executeQuery(q);
			int i =0;
			System.out.println(q);
			while(res.next())
			{
			    System.out.println(i++);
			    Operation operation = new Operation();
			    operation.chargeParId(res.getInt("id"));
			    Comptabilite compta = new Comptabilite();
			    compta.chargeParId(res.getInt("comptabilite_id"));
			    if(compta.getId()!= getComptaPerso().getId())
			    {
			        compta.setSolde(compta.getSolde() - ordonnance.getMontant());
			    }
			    else
			    {
			        compta.setSolde(compta.getSolde() + ordonnance.getMontant());
			    }
			    compta.sauver();
			    operation.supprimer();
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
		q = "DELETE FROM action WHERE ordonnance_id='" + ordonnance.getId() + "'";
		try
		{
		    conn = ds.getConnection();
			s = conn.createStatement();
		    s.executeQuery(q);
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
		System.out.println(q);
		q = "DELETE FROM a_traiter WHERE ordonnance_id='" + ordonnance.getId() + "'";
		try
		{
		    conn = ds.getConnection();
			s = conn.createStatement();
		    s.executeQuery(q);
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
		System.out.println(q);
		q = "DELETE FROM consomme WHERE ordonnance_id='" + ordonnance.getId() + "'";
		System.out.println(q);
		try
		{
		    conn = ds.getConnection();
			s = conn.createStatement();
		    s.executeQuery(q);
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
		q = "DELETE FROM operation WHERE ordonnance_id='" + ordonnance.getId() + "'";
		System.out.println(q);
		try
		{
		    conn = ds.getConnection();
			s = conn.createStatement();
		    s.executeQuery(q);
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
        ordonnance.supprimer();
    } 

 }
