
package gid_metier;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.Vector;
/**
 * <p>Represente l'ordonnance de d&eacute;l&eacute;gation de cr&eacute;dit.</p>
 * <p>L'ordonnance est le t&eacute;moin que se transmettent les acteurs de GID au long de la proc&eacute;dure P105, et a partir de laquelle ils mettent &agrave; jour leur comptabilit&eacute;s personnelles.</p>
 * 
 */
public class OrdonnanceDelegation extends ObjetPersistant {

/**
 * <p>Represente le libelle</p>
 * 
 */
    private String libelle;

/**
 * <p>Represente la date</p>
 * 
 */
    private java.util.Date date;

/**
 * <p>Represente le montant</p>
 * 
 */
    private int montant;

/**
 * <p>Represente l'&eacute;tat de l'ordonnance de d&eacute;l&eacute;gation.</p>
 * <ol>
 * <li>Saisie par l'ordonnateur</li>
 * <li>Vis&eacute;e par le CCED</li>
 * <li>Vis&eacute;e par la TG</li>
 * <li>Proc&eacute;dure termin&eacute;e</li>
 * </ol>
 * <p></p>
 * 
 */
    private int etat;

/**
 * <p>Represente le visa du CCED</p>
 * 
 */
    private boolean visaCCED;

/**
 * <p>Represente les references du visa du CCED</p>
 * 
 */
    private String refVisaCCED;

/**
 * <p>Represente la date du visa du CCED</p>
 * 
 */
    private java.util.Date dateVisaCCED;

/**
 * <p>Represente le visa de la TG</p>
 * 
 */
    private boolean visaTG;

/**
 * <p>Represente les references du visa de la TG</p>
 * 
 */
    private String refVisaTG;

/**
 * <p>Represente la date du visa de la TG</p>
 * 
 */
    private java.util.Date dateVisaTG;

/**
 * <p>Represente l'&eacute;tat 1 de l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 */
    public static final int SAISIE = 1;

/**
 * <p>Represente l'&eacute;tat 2 de l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 */
    public static final int VISEE_CCED = 2;

/**
 * <p>Represente l'&eacute;tat 3 de l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 */
    public static final int VISEE_TG = 3;

/**
 * <p>Represente l'&eacute;tat 4 de l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 */
    public static final int TERMINEE = 4;
/**
 * <p>Represente les consommables a acheter avec le cr&eacute;dit accord&eacute;</p>
 * 
 * 
 * @poseidon-type gid_metier.Consommable
 */
    private Vector consommable = new Vector();
/**
 * <p>Represente le chapitre concern&eacute; par l'ordonnance de d&eacute;l&eacute;gation de cr&eacute;dit</p>
 * 
 */
    private gid_metier.Chapitre chapitre;
/**
 * <p>Represente le trac&eacute; d&eacute;taill&eacute; du parcours effectu&eacute; par l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 * 
 * @poseidon-type gid_metier.Action
 */
    private java.util.Collection action = new java.util.TreeSet();

/**
 * <p>Retourne le libelle</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

/**
 * <p>Retourne la date</p>
 * 
 * 
 * @return date
 */
    public java.util.Date getDate() {        
        return date;
    } 

/**
 * <p>modifie la date</p>
 * 
 * 
 * @param _date nouvelle date
 */
    public void setDate(java.util.Date _date) {        
        date = _date;
    } 

/**
 * <p>Retourne le montant</p>
 * 
 * 
 * @return montant
 */
    public int getMontant() {        
        return montant;
    } 

/**
 * <p>Modifie le montant</p>
 * 
 * 
 * @param _montant nouveau montant
 */
    public void setMontant(int _montant) {        
        montant = _montant;
    } 

/**
 * <p>Retourne l'&eacute;tat</p>
 * 
 * 
 * @return etat
 */
    public int getEtat() {        
        return etat;
    } 

/**
 * <p>Modifie l'&eacute;tat de l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 * 
 * @param _etat nouvel état
 */
    public void setEtat(int _etat) {        
        etat = _etat;
    } 

/**
 * <p>Retourne true si le visa est accord&eacute;</p>
 * 
 * 
 * @return visaCCED
 */
    public boolean isVisaCCED() {        
        return visaCCED;
    } 

/**
 * <p>Modifie le visa du CCED</p>
 * 
 * 
 * @param _visaCCED nouveau visaCCED
 */
    public void setVisaCCED(boolean _visaCCED) {        
        visaCCED = _visaCCED;
    } 

/**
 * <p>Retourne les r&eacute;f&eacute;rences du visa du CCED</p>
 * 
 * 
 * @return refVISACCED
 */
    public String getRefVisaCCED() {        
        return refVisaCCED;
    } 

/**
 * <p>Modifie les r&eacute;f&eacute;rences du visa du CCED</p>
 * 
 * 
 * @param _refVisaCCED nouvelles références 
 */
    public void setRefVisaCCED(String _refVisaCCED) {        
        refVisaCCED = _refVisaCCED;
    } 

/**
 * <p>Retourne la date du visa du CCED</p>
 * 
 * 
 * @return 
 */
    public java.util.Date getDateVisaCCED() {        
        return dateVisaCCED;
    } 

/**
 * <p>Modifie la date du visa du CCED</p>
 * 
 * 
 * @param _dateVisaCCED nouvelle date
 */
    public void setDateVisaCCED(java.util.Date _dateVisaCCED) {        
        dateVisaCCED = _dateVisaCCED;
    } 

/**
 * <p>Retourne true si le visa de la TG est accord&eacute;</p>
 * 
 * 
 * @return visaTG
 */
    public boolean isVisaTG() {        
        return visaTG;
    } 

/**
 * <p>Modifie le visa de la TG</p>
 * 
 * 
 * @param _visaTG nouveau visa
 */
    public void setVisaTG(boolean _visaTG) {        
        visaTG = _visaTG;
    } 

/**
 * <p>Retourne les r&eacute;f&eacute;rences du visa de la TG</p>
 * 
 * 
 * @return refVISATG
 */
    public String getRefVisaTG() {        
        return refVisaTG;
    } 

/**
 * <p>Modifie les r&eacute;f&eacute;rences du visa de la TG</p>
 * 
 * 
 * @param _refVisaTG nouvelles références
 */
    public void setRefVisaTG(String _refVisaTG) {        
        refVisaTG = _refVisaTG;
    } 

/**
 * <p>Retourne a date du visa de la TG</p>
 * 
 * 
 * @return dateVISATG
 */
    public java.util.Date getDateVisaTG() {        
        return dateVisaTG;
    } 

/**
 * <p>Modife la date du visa de la TG</p>
 * 
 * 
 * @param _dateVisaTG nouvelle date
 */
    public void setDateVisaTG(java.util.Date _dateVisaTG) {        
        dateVisaTG = _dateVisaTG;
    } 
/**
 * <p>Represente le sous ordonnateur destinataire du cr&eacute;dit</p>
 * 
 */
    private gid_metier.SousOrdonnateur delegataire;
/**
 * <p>Represente le comptable charg&eacute; d'accord&eacute; le visa de la TG</p>
 * 
 */
    private gid_metier.TG comptable;
/**
 * <p>Represente l'initiateur de l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 */
    private gid_metier.Ordonnateur initiateur;

    /** @poseidon-generated */
    public Vector getConsommables() {
        return consommable;
    }
    /** @poseidon-generated */
    public void addConsommable(gid_metier.Consommable consommable) {
        if (! this.consommable.contains(consommable)) this.consommable.addElement(consommable);
    }
    /** @poseidon-generated */
    public void removeConsommable(gid_metier.Consommable consommable) {
        this.consommable.removeElement(consommable);
    }

    /** @poseidon-generated */
    public gid_metier.SousOrdonnateur getDelegataire() {
        return delegataire;
    }
    /** @poseidon-generated */
    public void setDelegataire(gid_metier.SousOrdonnateur sousOrdonnateur) {
        this.delegataire = sousOrdonnateur;
    }

    /** @poseidon-generated */
    public gid_metier.TG getComptable() {
        return comptable;
    }
    /** @poseidon-generated */
    public void setComptable(gid_metier.TG tG) {
        this.comptable = tG;
    }

    /** @poseidon-generated */
    public gid_metier.Ordonnateur getInitiateur() {
        return initiateur;
    }
    /** @poseidon-generated */
    public void setInitiateur(gid_metier.Ordonnateur ordonnateur) {
        this.initiateur = ordonnateur;
    }

    /** @poseidon-generated */
    public gid_metier.Chapitre getChapitre() {
        return chapitre;
    }
    /** @poseidon-generated */
    public void setChapitre(gid_metier.Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    /** @poseidon-generated */
    public java.util.Collection getActions() {
        return action;
    }
    /** @poseidon-generated */
    public void addAction(gid_metier.Action action) {
        if (! this.action.contains(action)) this.action.add(action);
    }
    /** @poseidon-generated */
    public void removeAction(gid_metier.Action action) {
        this.action.remove(action);
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
			query = "SELECT o.*, d.prenom as delegataire_prenom, d.nom as delegataire_nom, i.nom as initiateur_nom, i.prenom as initiateur_prenom, c.nom as comptable_nom, c.prenom as comptable_prenom FROM ordonnance o, ordonnateur i, sousordonnateur d, tg c WHERE o.id='" + id + "' AND i.id=o.initiateur_id AND c.id=comptable_id AND d.id=delegataire_id";
			res = s.executeQuery(query);
			if(res.next())
			{
			    setId(res.getInt("id"));
			    setLibelle(res.getString("libelle"));
			    setDate(res.getDate("date"));
			    setMontant(res.getInt("montant"));
			    setEtat(res.getInt("etat"));
			    setVisaCCED(res.getBoolean("visacced"));
			    setRefVisaCCED(res.getString("ref_visacced"));
			    setDateVisaCCED(res.getDate("date_visacced"));
			    setVisaTG(res.getBoolean("visatg"));
			    setRefVisaTG(res.getString("ref_visatg"));
			    setDateVisaTG(res.getDate("date_visatg"));
			    
			    Ordonnateur initiateur = new Ordonnateur();
			    initiateur.setId(res.getInt("initiateur_id"));
			    initiateur.setNom(res.getString("initiateur_nom"));
			    initiateur.setPrenom(res.getString("initiateur_prenom"));
			    
			    SousOrdonnateur delegataire = new SousOrdonnateur();
			    delegataire.setId(res.getInt("delegataire_id"));
			    delegataire.setNom(res.getString("delegataire_nom"));
			    delegataire.setPrenom(res.getString("delegataire_prenom"));
			    
			    TG comptable = new TG();
			    comptable.setId(res.getInt("comptable_id"));
			    comptable.setNom(res.getString("comptable_nom"));
			    comptable.setPrenom(res.getString("comptable_prenom"));
			    
			    setInitiateur(initiateur);
			    setDelegataire(delegataire);
			    setComptable(comptable);
			    try
				{
				    conn2 = ds.getConnection();
					s2 = conn.createStatement();
					res2 = s2.executeQuery("SELECT * FROM consomme,consommable  WHERE ordonnance_id='" + getId() + "' AND consommable_id=id");
					while(res2.next())
					{
					    Consommable conso = new Consommable();
					    conso.chargeParId(res2.getInt("id"));
					    conso.setQuantite(res2.getInt("quantite"));
					    addConsommable(conso);
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

    
    public void clore() throws Exception
    {
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		String query="UPDATE ordonnance set etat='4' WHERE id='" + getId() + "'";
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
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
		
		if(getId()==0)
		{
		    query = "INSERT INTO ordonnance(libelle, date, montant, etat, visacced, ref_visacced, visatg, ref_visatg, initiateur_id, comptable_id, delegataire_id) VALUES ('" + getLibelle() + "', '" + getDate() + "', '" + getMontant() + "', '" + getEtat() + "', '" + isVisaCCED() + "', '" + getRefVisaCCED() + "', '" + isVisaTG() + "', '" + getRefVisaTG() + "', '" + getInitiateur().getId() + "', '" + getComptable().getId() + "', '" + getDelegataire().getId() + "')";
		}
		else
		{
		    if(getDateVisaCCED()!=null)
		    {
		        query = "UPDATE ordonnance set libelle='" + getLibelle() + "', date='" + getDate() + "', montant='" + getMontant() + "', etat='" + getEtat() + "', visacced='" + isVisaCCED() + "', date_visacced='" + getDateVisaCCED() +  "', ref_visacced='" + getRefVisaCCED() + "', visatg='" + isVisaTG() + "', ref_visatg='" + getRefVisaTG() + "', initiateur_id='" + getInitiateur().getId() + "', comptable_id='" + getComptable().getId() + "', delegataire_id='" + getDelegataire().getId() + "' WHERE id='" + getId() + "'";
		    }
		    if(getDateVisaTG()!=null)
		    {
		        query = "UPDATE ordonnance set libelle='" + getLibelle() + "', date='" + getDate() + "', montant='" + getMontant() + "', etat='" + getEtat() + "', visacced='" + isVisaCCED() + "', date_visacced='" + getDateVisaCCED() +  "', ref_visacced='" + getRefVisaCCED() + "', visatg='" + isVisaTG() + "', date_visatg='" + getDateVisaTG() + "', ref_visatg='" + getRefVisaTG() + "', initiateur_id='" + getInitiateur().getId() + "', comptable_id='" + getComptable().getId() + "', delegataire_id='" + getDelegataire().getId() + "' WHERE id='" + getId() + "'";
		    }
		}
        try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
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
		try
		{
		    conn = ds.getConnection();
			s = conn.createStatement();
		    query = "SELECT id from ordonnance WHERE libelle='" + getLibelle() + "' AND date='" + getDate() + "' AND montant='" + getMontant() + "'";
		    System.out.println(query);
			res = s.executeQuery(query);
			if (res.next())
			{
			    setId(res.getInt("id"));
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
		Consommable conso;
		for (int i=0; i<getConsommables().size(); i++)
		{
		    conso = (Consommable)(getConsommables()).elementAt(i);
			query = "INSERT INTO consomme(consommable_id, ordonnance_id, quantite) VALUES ('" + conso.getId() + "', '" + getId()  + "', '" + conso.getQuantite() + "')";
			System.out.println(query);
	        try 
	        {
	            conn = ds.getConnection();
				s = conn.createStatement();
				res = s.executeQuery(query);
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
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
			res = s.executeQuery("DELETE FROM ordonnancedelegation WHERE id='" + getId() + "'");
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
			res = s.executeQuery("SELECT id FROM ordonnance ORDER BY libelle");
			while(res.next())
			{
			    OrdonnanceDelegation ordo = new OrdonnanceDelegation();
			    ordo.chargeParId(res.getInt("id"));
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
}
