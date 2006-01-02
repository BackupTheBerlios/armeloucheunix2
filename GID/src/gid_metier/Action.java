
package gid_metier;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.Vector;

/**
 * <p>Represente une action effectu&eacute;e sur une ordonnance de d&eacute;l&eacute;gation de cr&eacute;dit.</p>
 * 
 */
public class Action extends ObjetPersistant {

/**
 * <p>Represente la date de l'action</p>
 * 
 */
    private java.util.Date date;

/**
 * <p>Represente le type d'action</p>
 * <ol>
 * <li>Prise en charge</li>
 * <li>Transmission</li>
 * <li>Visa</li>
 * <li>Autre</li>
 * </ol>
 * 
 */
    private int type;

/**
 * <p>Represente&nbsp; le libelle de l'action</p>
 * 
 */
    private String libelle;

/**
 * <p>Represente le type 1 d'action</p>
 * 
 */
    public static final int PRISE_EN_CHARGE = 1;

/**
 * <p>Represente le type 2 d'action</p>
 * 
 */
    public static final int TRANSMISSION = 2;

/**
 * <p>Represente le type 3 d'action</p>
 * 
 */
    public static final int VISA = 3;

/**
 * <p>Represente le type 4 d'action</p>
 * 
 */
    public static final int AUTRE = 4;
/**
 * <p>Represente l'acteur &agrave; l'origine de l'action</p>
 * 
 */
    private gid_metier.Acteur participant;

    
    private gid_metier.OrdonnanceDelegation ordonnance;
/**
 * <p>Retourne la date de l'action</p>
 * 
 * 
 * @return date
 */
    public java.util.Date getDate() {        
        return date;
    } 

/**
 * <p>Modifie la date de l'action</p>
 * 
 * 
 * @param _date nouvelle date
 */
    public void setDate(java.util.Date _date) {        
        date = _date;
    } 

/**
 * <p>Retourne le type de l'action</p>
 * 
 * 
 * @return type
 */
    public int getType() {        
        return type;
    } 

/**
 * <p>Modifie le type de l'action</p>
 * 
 * 
 * @param _type nouveau type
 */
    public void setType(int _type) {        
        type = _type;
    } 

/**
 * <p>Retourne&nbsp; le libelle de l'action</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie&nbsp;le libelle de l'action</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

    /** @poseidon-generated */
    public gid_metier.Acteur getParticipant() {
        return participant;
    }
    /** @poseidon-generated */
    public void setParticipant(gid_metier.Acteur acteur) {
        this.participant = acteur;
    }
    
    public gid_metier.OrdonnanceDelegation getOrdonnance() {
        return ordonnance;
    }
    
    public void setOrdonnance(gid_metier.OrdonnanceDelegation ordonnance) {
        this.ordonnance = ordonnance;
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
			res = s.executeQuery("SELECT action.*, role FROM action, (SELECT id, 'Ordonnateur' as role FROM ordonnateur UNION SELECT id, 'SousOrdonnateur' as role FROM sousordonnateur UNION SELECT id, 'CCED' as role FROM cced UNION SELECT id, 'CPED' as role FROM cped UNION SELECT id, 'TG' as role FROM tg UNION SELECT id, 'TR_TP' as role FROM tr_tp) as foo  WHERE action.id='" + id + "' AND action.participant_id = foo.id");
			if (res.next())
			{
			    setId(res.getInt("id"));
		        setDate(res.getDate("date"));
		        setType(res.getInt("type"));
		        setLibelle(res.getString("libelle"));
		        OrdonnanceDelegation ordo = new OrdonnanceDelegation();
		        ordo.chargeParId(res.getInt("ordonnance_id"));
		        setOrdonnance(ordo);
		        Acteur participant=null;
		        if (res.getString("role").equalsIgnoreCase("TG"))
			    {
		            participant = new TG();
					participant.chargeParId(res.getInt("participant_id"));
			    }
			    else if (res.getString("role").equalsIgnoreCase("cced"))
			    {
			        participant = new CCED();
			        participant.chargeParId(res.getInt("participant_id"));
			    }
			    else if (res.getString("role").equalsIgnoreCase("cped"))
			    {
			        participant = new CPED();
			        participant.chargeParId(res.getInt("participant_id"));
			    }
			    else if (res.getString("role").equalsIgnoreCase("ordonnateur"))
			    {
			        participant = new Ordonnateur();
			        participant.chargeParId(res.getInt("participant_id"));
			    }
			    else if (res.getString("role").equalsIgnoreCase("sousordonnateur"))
			    {
			        participant = new SousOrdonnateur();
			        participant.chargeParId(res.getInt("participant_id"));
			    }
			    else if (res.getString("role").equalsIgnoreCase("tr_tp"))
			    {
			        participant = new TR_TP();
			        participant.chargeParId(res.getInt("participant_id"));
			    }
			    setParticipant(participant);
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
			    query = "INSERT INTO action(date, type, libelle, ordonnance_id, participant_id) VALUES ('" + getDate() + "', '" + getType() + "', '" + getLibelle() + "', '" + getOrdonnance().getId() + "', '" + getParticipant().getId() + "')";
			}
			else
			{
			    query = "UPDATE action set date='" + getDate() + "', type='" + getType() + "', libelle='" + getLibelle() + "', ordonnance_id='" + getOrdonnance().getId() + "', participant_id='" + getParticipant().getId() + "' WHERE id='" + getId() + "'";
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
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		String query="";
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "DELETE FROM action WHERE id='" + getId() + "'";
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
 * <p>Retourne tous les objets (du type courant) stockes dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @return Une collection de tous les objets
 * @throws Si une erreur survient pendant la transaction
 */
    public Vector retournerTous() throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		String query="";
		Vector tous = new Vector();
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			query = "SELECT id FROM action ORDER BY date";
			res = s.executeQuery(query);
			while(res.next())
			{
			    Action act = new Action();
			    act.chargeParId(res.getInt("id"));
			    tous.addElement(act);
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
