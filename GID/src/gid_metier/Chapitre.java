
package gid_metier;


import java.sql.*;

/**
 * <p>Represente le chapitre (d'une ordonnance)</p>
 * 
 */
public class Chapitre extends ObjetPersistant {

/**
 * <p>Represente le code du chapitre dans la nomenclature GID</p>
 * 
 */
    private String code;

/**
 * <p>Represente le libelle du chapitre</p>
 * 
 */
    private String libelle;
/**
 * <p>Represente la ligne budg&eacute;taire &agrave; laquelle est rattach&eacute; le chapitre</p>
 * 
 */
    private gid_metier.LigneBudgetaire ligneBudgetaire;

/**
 * <p>Retourne le code du chapitre</p>
 * 
 * 
 * @return code
 */
    public String getCode() {        
        return code;
    } 

/**
 * <p>Modifie le code du chapitre</p>
 * 
 * 
 * @param _code nouveau code
 */
    public void setCode(String _code) {        
        code = _code;
    } 

/**
 * <p>Retourne le libelle du chapitre</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle du chapitre</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

    /** @poseidon-generated */
    public gid_metier.LigneBudgetaire getLigneBudgetaire() {
        return ligneBudgetaire;
    }
    /** @poseidon-generated */
    public void setLigneBudgetaire(gid_metier.LigneBudgetaire ligneBudgetaire) {
        this.ligneBudgetaire = ligneBudgetaire;
    }
    
    
/**
 * <p>Charge (depuis le SGBD) l'objet correspondant a l'identifiant pass&eacute; en param&egrave;tre.</p>
 * <p>Cette op&eacute;ration correspont a une transaction.</p>
 * 
 * 
 * @param id l'identifiant de l'objet recherché
 * @throws Si une erreur survient pendant la transaction
 */
    public void chargeParId(int id) throws Exception{
    	
    	String req;
    	Statement s;
    	ResultSet rs;
    	
    	req = "SELECT * FROM \"CHAPITRE\" ";
    	req+= "WHERE \"ID\" = "+id;
    	req+= ";";
    	
    	if (id == 0)
    		throw new Exception ("Ne peut pas charger le chapitre - identifiant non défini");
    	
    	try{
    		
    		Class.forName("org.postgresql.Driver");
    		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid","postgres","postgres");
    		conn.setAutoCommit(false);	
    		
    		s = conn.createStatement();
    		
    		rs = s.executeQuery(req);
    		if (rs.next()){
    			code = rs.getString("CODE");
    			libelle = rs.getString("LIBELLE");	
    			ligneBudgetaire.setId(rs.getInt("LIGNE_BUDGETAIRE_ID"));
    		}
    		
    		System.out.println("hého !"+ligneBudgetaire.getId());		
    		conn.commit();
    		
    		System.out.println(ligneBudgetaire.getId());
    		//ligneBudgetaire.chargeParId(ligneBudgetaire.id);
    		
    	}

    	catch(SQLException sqle){
    		throw new Exception("Erreur dans la transaction - chargement de l'objet impossible");
    	}
    	
    	
    }

/**
 * <p>Enregistre l'objet dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public void sauver() throws Exception{}

/**
 * <p>Supprime l'objet du SGBD.</p>
 * <p>Cette operation correspond a une transaction.</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public void supprimer() throws Exception{}

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

 }
