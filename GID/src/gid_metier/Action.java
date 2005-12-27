
package gid_metier;

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
    
    
/**
 * <p>Charge (depuis le SGBD) l'objet correspondant a l'identifiant pass&eacute; en param&egrave;tre.</p>
 * <p>Cette op&eacute;ration correspont a une transaction.</p>
 * 
 * 
 * @param id l'identifiant de l'objet recherché
 * @return L'objet si il est trouvé, sinon null
 * @throws Si une erreur survient pendant la transaction
 */
    public void chargeParId(int id) throws Exception{}

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
