
package gid_metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * <p>Repr&eacute;sente un sous ordonnateur,est le destinataire de la proc&eacute;dure de d&eacute;l&eacute;gation de cr&eacute;dit.</p>
 * 
 */
public class SousOrdonnateur extends Acteur {
/**
 * <p>Represente le controleur du CPED affect&eacute; au sous ordonnateur.</p>
 * 
 */
    private gid_metier.CPED cped;
/**
 * <p>Represente l'acteur de la TR ou de la TP affect&eacute; au sous ordonnateur.</p>
 * 
 */
    private gid_metier.TR_TP tr_tp;

    
    public SousOrdonnateur()
    {
        try 
        {
            Context initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
        }
        catch (Exception e )
        {
            
        }
    }
    /** @poseidon-generated */
    public gid_metier.CPED getCped() {
        return cped;
    }
    /** @poseidon-generated */
    public void setCped(gid_metier.CPED cPED) {
        this.cped = cPED;
    }

    /** @poseidon-generated */
    public gid_metier.TR_TP getTr_tp() {
        return tr_tp;
    }
    /** @poseidon-generated */
    public void setTr_tp(gid_metier.TR_TP tR_TP) {
        this.tr_tp = tR_TP;
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
			res = s.executeQuery("SELECT sousordonnateur.*, cped.nom as nom_cped, cped.prenom as prenom_cped, tr_tp.nom as nom_tr_tp, tr_tp.prenom as prenom_tr_tp FROM  cped, tr_tp,sousordonnateur WHERE sousordonnateur.id='" + id + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
				CPED cped = new CPED();
				TR_TP tr_tp = new TR_TP();
				
				cped.setId(res.getInt("cped_id"));
				cped.setNom(res.getString("prenom_cped"));
				cped.setPrenom(res.getString("nom_cped"));
				setCped(cped);
				
				tr_tp.setId(res.getInt("tr_tp_id"));
				tr_tp.setNom(res.getString("nom_tr_tp"));
				tr_tp.setPrenom(res.getString("prenom_tr_tp"));
				setTr_tp(tr_tp);
				
			    Comptabilite compta = new Comptabilite();
			    compta.chargeParIdActeur(getId());
			    setComptaPerso(compta);
			 }
			 try
			 {
			    conn = ds.getConnection();
				s = conn.createStatement();
				res = s.executeQuery("SELECT ordonnance_id FROM a_traiter WHERE a_traiter.acteur_id='" + getId() + "'");
				while(res.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
					ordon.chargeParId(res.getInt("ordonnance_id"));
					addATraiter(ordon);
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
       /* Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String query;
			if (getId()==0)
			{
			    query = "INSERT INTO sousordonnateur(login,mdp,nom,prenom,cped_id,tr_tp_id) VALUES ('" + getLogin() + "', '" + getMdp() + "', '" + getNom() + "', " + getPrenom() + "', '" + getCped().getId() + "', '" + getTr_tp().getId() + "')";
			}
			else
			{
			    query = "UPDATE sousordonnateur set login = '" + getLogin() + "', mdp = '" + getMdp() + "', nom = '" + getNom() + "', prenom = '" + getPrenom() + "', cped_id = '" + getCped().getId() + "', tr_tp_id = " + getTr_tp().getId() + " WHERE id='" + getId() + "'";
			}
			res = s.executeQuery(query);
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
			s.executeQuery("DELETE FROM sousordonnateur WHERE id='" + getId() + "'");
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
    	/*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT * FROM sousordonnateur ORDER BY nom,prenom");
			while(res.next())
			{
			    SousOrdonnateur sousO = new SousOrdonnateur();
			    sousO.setId(res.getInt("id"));
			    sousO.setNom(res.getString("nom"));
			    sousO.setPrenom(res.getString("prenom"));
			    tous.addElement(sousO);
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
      /*  Context initCtx = new InitialContext();
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
        Operation operation = new Operation();
       /* Context initCtx = new InitialContext();
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
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String q = "INSERT INTO a_traiter(ordonnance_id, acteur_id) VALUES ('" + ordonnance.getId() + "', '" + destinataire.getId() + "')"; 
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
			res = s.executeQuery("SELECT sousordonnateur.*, cped.nom as nom_cped, cped.prenom as prenom_cped, tr_tp.nom as nom_tr_tp, tr_tp.prenom as prenom_tr_tp FROM  cped, tr_tp,sousordonnateur WHERE sousordonnateur.login='" + login + "' AND sousordonnateur.mdp='" + password + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
				CPED cped = new CPED();
				TR_TP tr_tp = new TR_TP();
				
				cped.setId(res.getInt("cped_id"));
				cped.setNom(res.getString("prenom_cped"));
				cped.setPrenom(res.getString("nom_cped"));
				setCped(cped);
				
				tr_tp.setId(res.getInt("tr_tp_id"));
				tr_tp.setNom(res.getString("nom_tr_tp"));
				tr_tp.setPrenom(res.getString("prenom_tr_tp"));
				setTr_tp(tr_tp);
				
			    Comptabilite compta = new Comptabilite();
			    compta.chargeParIdActeur(getId());
			    setComptaPerso(compta);
			 }
			
			 try
			 {
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				String query = "SELECT a.ordonnance_id FROM a_traiter a WHERE a.acteur_id='" + getId() + "' AND a.ordonnance_id IN (SELECT ordonnance.id FROM ordonnance, action, a_traiter WHERE ordonnance.id=action.ordonnance_id AND action.participant_id!='" + getId() + "' AND (etat='2' AND a_traiter.acteur_id IN (SELECT comptable_id FROM ordonnance)) OR (etat='3' AND a_traiter.acteur_id IN (SELECT initiateur_id FROM ordonnance)))";
				res2 = s2.executeQuery(query);
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
			String q = "SELECT id FROM ordonnance WHERE etat='4' AND delegataire_id='" + getId() + "' AND id NOT IN (SELECT ordonnance_id FROM a_traiter) ORDER BY date DESC";
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
			String q = "SELECT id FROM ordonnance WHERE id IN (SELECT action.ordonnance_id FROM action WHERE participant_id='" + getId() + "' AND type='1') EXCEPT (SELECT ordonnance_id FROM a_traiter WHERE acteur_id='" + getId() + "' UNION SELECT a.ordonnance_id FROM a_traiter a, a_traiter b, ordonnance o WHERE a.ordonnance_id=b.ordonnance_id AND a.ordonnance_id=o.id AND b.acteur_id='" + getId() + "' AND ((a.acteur_id=o.initiateur_id AND o.etat='3') OR a.acteur_id=o.comptable_id))";
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
    
    public boolean verifOrdoEnvoye(OrdonnanceDelegation ordon)throws NamingException
    {
        boolean r=false;
        /*Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");*/
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			String q ="SELECT ordonnance_id FROM action WHERE ordonnance_id='" + ordon.getId() + "' AND ordonnance_id IN (SELECT ordonnance_id FROM action WHERE type='1' AND participant_id='" + getCped().getId() + "')";
			res = s.executeQuery(q);
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
