
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
 * <p>Repr&eacute;sente un acteur de la TG, acteurs qui participent &agrave; proc&eacute;dure de d&eacute;l&eacute;gation de cr&eacute;dit</p>
 * 
 */
public class TG extends Acteur {

	

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
			res = s.executeQuery("SELECT tg.* FROM  tg WHERE id='" + id + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
			    Comptabilite compta = new Comptabilite();
			    try
			    {
			        compta.chargeParIdActeur(getId());
			    }
			    catch(Exception e)
			    {
			        
			    }
			 }
			
			 try
			 {
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				res2 = s2.executeQuery("SELECT ordonnance_id FROM a_traiter WHERE a_traiter.acteur_id='" + getId() + "'");
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
				    try
				    {
				        ordon.chargeParId(res2.getInt("ordonnance_id"));
				    }
				    catch(Exception e)
				    {
				        
				    }
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
	    /*try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT tg.*, comptabilite.id as comptabilite_id, solde , acteur_id FROM tg  LEFT JOIN comptabilite ON (acteur_id = tg.id)  WHERE tg.id='" + id + "'");
			if (res.next())
			{
			    this.setId(res.getInt("id"));
				this.setLogin(res.getString("login"));
				this.setMdp(res.getString("mdp"));
				this.setNom(res.getString("nom"));
				this.setPrenom(res.getString("prenom"));
				if(res.getString("comptabilite_id")!=null)
				{
				    Comptabilite compta = new Comptabilite();
				    compta.setId(res.getInt("comptabilite_id"));
				    compta.setSolde(res.getInt("solde"));
				    try
					{
					    res = s.executeQuery("SELECT * FROM operation WHERE comptabilite_id='" + res.getInt("comptabilite_id") + "' ORDER BY date DESC");
					    while(res.next())
					    {
					        Operation op = new Operation();
					        op.setId(res.getInt("id"));
					        op.setLibelle(res.getString("libelle"));
					        op.setMontant(res.getInt("montant"));
					        op.setDate(res.getDate("date"));
					        op.setType(res.getString("type"));
					        compta.addOperation(op);
					    }
					    this.setComptaPerso(compta);
					}
				    catch (SQLException e)
					{
				        System.out.println(e.getMessage()+ "tutu");
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
				try
				{
				    conn = ds.getConnection();
					s = conn.createStatement();
				    System.out.println(getId());
				    res = s.executeQuery("SELECT ordonnance.*, o.nom as nom_initiateur, o.prenom as prenom_initiateur, d.nom as nom_delegataire, d.prenom as prenom_delegataire, tg.nom as nom_comptable, tg.prenom as prenom_comptable FROM a_traiter, ordonnance, ordonnateur o, sousordonnateur d, tg WHERE a_traiter.ordonnance_id=ordonnance.id AND a_traiter.acteur_id='" + getId() + "' AND o.id=ordonnance.initiateur_id AND d.id=ordonnance.delegataire_id AND tg.id=ordonnance.comptable_id");
					while(res.next())
					{
					    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
						Ordonnateur initiateur = new Ordonnateur();
						SousOrdonnateur delegataire = new SousOrdonnateur();
						//int deleg = res.getInt("delegataire_id");
						ordon.setId(res.getInt("id"));
						ordon.setLibelle(res.getString("libelle"));
						ordon.setDate(res.getDate("date"));
						ordon.setMontant(res.getInt("montant"));
						ordon.setEtat(res.getInt("etat"));
						ordon.setVisaCCED(res.getBoolean("visacced"));
						ordon.setRefVisaCCED(res.getString("ref_visacced"));
						ordon.setDateVisaCCED(res.getDate("date_visacced"));
						ordon.setVisaTG(res.getBoolean("visatg"));
						ordon.setRefVisaTG(res.getString("ref_visatg"));
						ordon.setDateVisaTG(res.getDate("date_visatg"));
						
						initiateur.setNom(res.getString("nom_initiateur"));
						initiateur.setPrenom(res.getString("prenom_initiateur"));
						ordon.setInitiateur(initiateur);
						
						delegataire.setNom(res.getString("nom_delegataire"));
						delegataire.setPrenom(res.getString("prenom_delegataire"));
						ordon.setDelegataire(delegataire);
						
						comptable.setId(res.getInt("comptable_id"));
						comptable.setNom(res.getString("nom_comptable"));
						comptable.setPrenom(res.getString("prenom_comptable"));
						ordon.setComptable(comptable);
						
						addATraiter(ordon);
					}
				}
			    catch (SQLException e)
				{
			        System.out.println(e.getMessage()+ "titi");
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
		}*/
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
			if (getId()!=0)
			{
			    query = "INSERT INTO tg(login,mdp,nom,prenom) VALUES ('" + getLogin() + "','" + getMdp() + "','" + getNom() + "','" + getPrenom() + "')";
			}
			else
			{
			    query = "UPDATE tg set login = '" + getLogin() + "', mdp = '" + getMdp() + "', nom = '" + getNom() + "', prenom = '" + getPrenom() + "' WHERE id='" + getId() + "'";
			}
			System.out.println(query);
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
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("DELETE FROM tg WHERE id='" + getId() + "'");
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
			res = s.executeQuery("SELECT * FROM tg ORDER BY nom,prenom");
			while(res.next())
			{
			    TG tg = new TG();
			    tg.setId(res.getInt("id"));
			    tg.setNom(res.getString("nom"));
			    tg.setPrenom(res.getString("prenom"));
			    tous.addElement(tg);
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
 * @param ordonnance l'ordonnance qui mouvemente la comptabilité
 */
    public void majComptabilite(OrdonnanceDelegation ordonnance) throws Exception{}

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
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
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
    public boolean identifie(){
        return !(getLogin()== null && getMdp()==null);
    }


	public void authentifie(String login, String password) throws NamingException
	{
	    Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
	    /*try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT tg.*, comptabilite.id as comptabilite_id, solde , acteur_id FROM tg  LEFT JOIN comptabilite ON (acteur_id = tg.id)  WHERE login='" + login + "' AND mdp='" + password + "'");
			if (res.next())
			{
			    this.setId(res.getInt("id"));
				this.setLogin(res.getString("login"));
				this.setMdp(res.getString("mdp"));
				this.setNom(res.getString("nom"));
				this.setPrenom(res.getString("prenom"));
				if(res.getString("comptabilite_id")!=null)
				{
				    Comptabilite compta = new Comptabilite();
				    compta.setId(res.getInt("comptabilite_id"));
				    compta.setSolde(res.getInt("solde"));
				    try
					{
					    res = s.executeQuery("SELECT * FROM operation WHERE comptabilite_id='" + res.getInt("comptabilite_id") + "' ORDER BY date DESC");
					    while(res.next())
					    {
					        Operation op = new Operation();
					        op.setId(res.getInt("id"));
					        op.setLibelle(res.getString("libelle"));
					        op.setMontant(res.getInt("montant"));
					        op.setDate(res.getDate("date"));
					        op.setType(res.getString("type"));
					        compta.addOperation(op);
					    }
					    this.setComptaPerso(compta);
					}
				    catch (SQLException e)
					{
				        System.out.println(e.getMessage()+ "tutu");
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
				try
				{
				    conn = ds.getConnection();
					s = conn.createStatement();
				    System.out.println(getId());
				    res = s.executeQuery("SELECT ordonnance.*, o.nom as nom_initiateur, o.prenom as prenom_initiateur, d.nom as nom_delegataire, d.prenom as prenom_delegataire, tg.nom as nom_comptable, tg.prenom as prenom_comptable FROM a_traiter, ordonnance, ordonnateur o, sousordonnateur d, tg WHERE a_traiter.ordonnance_id=ordonnance.id AND a_traiter.acteur_id='" + getId() + "' AND o.id=ordonnance.initiateur_id AND d.id=ordonnance.delegataire_id AND tg.id=ordonnance.comptable_id");
					while(res.next())
					{
					    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
						Ordonnateur initiateur = new Ordonnateur();
						SousOrdonnateur delegataire = new SousOrdonnateur();
						TG comptable = new TG();
						
						//int deleg = res.getInt("delegataire_id");
						ordon.setId(res.getInt("id"));
						ordon.setLibelle(res.getString("libelle"));
						ordon.setDate(res.getDate("date"));
						ordon.setMontant(res.getInt("montant"));
						ordon.setEtat(res.getInt("etat"));
						ordon.setVisaCCED(res.getBoolean("visacced"));
						ordon.setRefVisaCCED(res.getString("ref_visacced"));
						ordon.setDateVisaCCED(res.getDate("date_visacced"));
						ordon.setVisaTG(res.getBoolean("visatg"));
						ordon.setRefVisaTG(res.getString("ref_visatg"));
						ordon.setDateVisaTG(res.getDate("date_visatg"));
						
						initiateur.setId(res.getInt("initiateur_id"));
						initiateur.setNom(res.getString("nom_initiateur"));
						initiateur.setPrenom(res.getString("prenom_initiateur"));
						ordon.setInitiateur(initiateur);
						
						delegataire.setId(res.getInt("delegataire_id"));
						delegataire.setNom(res.getString("nom_delegataire"));
						delegataire.setPrenom(res.getString("prenom_delegataire"));
						ordon.setDelegataire(delegataire);

						comptable.setId(res.getInt("comptable_id"));
						comptable.setNom(res.getString("nom_comptable"));
						comptable.setPrenom(res.getString("prenom_comptable"));
						ordon.setComptable(comptable);
						try
						{
						    conn2 = ds.getConnection();
							s2 = conn.createStatement();
							res2 = s2.executeQuery("SELECT * FROM consomme,consommable  WHERE ordonnance_id='" + ordon.getId() + "' AND consommable_id=id");
							while(res2.next())
							{
							    Consommable conso = new Consommable();
							    conso.setId(res2.getInt("id"));
							    conso.setLibelle(res2.getString("libelle"));
							    conso.setPrix(res2.getInt("prix"));
							    conso.setQuantite(res2.getInt("quantite"));
							    ordon.addConsommable(conso);
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
						this.addATraiter(ordon);
					}
				}
			    catch (SQLException e)
				{
			        System.out.println(e.getMessage()+ "titi");
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
	    catch (SQLException e)
		{
	        System.out.println(e.getMessage()+ "tata");
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
		}*/
		try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			res = s.executeQuery("SELECT tg.* FROM  tg WHERE login='" + login + "' AND mdp='" + password + "'");
			if (res.next())
			{
			    setId(res.getInt("id"));
				setLogin(res.getString("login"));
				setMdp(res.getString("mdp"));
				setNom(res.getString("nom"));
				setPrenom(res.getString("prenom"));
				
			    Comptabilite compta = new Comptabilite();
			    try
			    {
			        compta.chargeParIdActeur(getId());
			    }
			    catch(Exception e)
			    {
			        
			    }
			 }
			
			 try
			 {
			    conn2 = ds.getConnection();
				s2 = conn2.createStatement();
				res2 = s2.executeQuery("SELECT ordonnance_id FROM a_traiter WHERE a_traiter.acteur_id='" + getId() + "' ORDER BY date DESC");
				while(res2.next())
				{
				    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
				    try
				    {
				        ordon.chargeParId(res2.getInt("ordonnance_id"));
				    }
				    catch(Exception e)
				    {
				        
				    }
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
			String q = "SELECT id FROM ordonnance WHERE etat='4' AND comptable_id='" + getId() + "' AND id NOT IN (SELECT ordonnance_id FROM a_traiter) ORDER BY date DESC";
			System.out.println(q);
			res2 = s2.executeQuery(q);
			
			while(res2.next())
			{
			    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
			    try
			    {
			        ordon.chargeParId(res2.getInt("id"));
			    }
			    catch(Exception e)
			    {
			        System.out.println(e.getMessage());
			    }
				addArchives(ordon);
				System.out.println(ordon.getId());
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
			String q = "SELECT id FROM ordonnance WHERE etat!='4' AND comptable_id='" + getId() + "' AND id NOT IN (SELECT ordonnance_id FROM a_traiter where acteur_id='" + getId() +"') ORDER BY date DESC";
			System.out.println(q);
			res2 = s2.executeQuery(q);
			
			while(res2.next())
			{
			    OrdonnanceDelegation ordon  =  new OrdonnanceDelegation();
			    try
			    {
			        ordon.chargeParId(res2.getInt("id"));
			    }
			    catch(Exception e)
			    {
			        System.out.println(e.getMessage());
			    }
				addEnCours(ordon);
				System.out.println(ordon.getId());
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
 * <p>Does ...</p>
 * 
 * 
 * @param ordonnance 
 */
    public void viser(OrdonnanceDelegation ordonnance) throws Exception
    {        
        Context initCtx = new InitialContext();
		ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
	    try
		{
	        conn = ds.getConnection();
			s = conn.createStatement();
			GregorianCalendar dateVisa = new GregorianCalendar();
			s.executeQuery("UPDATE ordonnance set etat='" + ordonnance.getEtat() + "', visatg='" + ordonnance.isVisaTG() + "', ref_visatg='" + ordonnance.getRefVisaTG() + "', date_visatg='" + dateVisa.get(GregorianCalendar.YEAR) + "-" + (dateVisa.get(GregorianCalendar.MONTH)+1) + "-" + dateVisa.get(GregorianCalendar.DAY_OF_MONTH) + "'");
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
