
package gid_metier;

/**
 * <p>Repr&eacute;sente une op&eacute;ration comptable qui est suceptible de mouvementer la comptabilit&eacute; des acteurs</p>
 * 
 */
public class Operation extends ObjetPersistant {

/**
 * <p>Represente le libelle de l'operation</p>
 * 
 */
    private String libelle;

/**
 * <p>Represente le montant de l'operation</p>
 * 
 */
    private int montant;

/**
 * <p>Represente la date de l'operation</p>
 * 
 */
    private java.util.Date date;

/**
 * <p>Represente le type d'operation (d&eacute;bit ou cr&eacute;dit)</p>
 * 
 */
    private String type;

/**
 * <p>retourne le libelle de l'op&eacute;ration</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle de l'op&eacute;ration</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

/**
 * <p>Retourne le montant&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @return montant
 */
    public int getMontant() {        
        return montant;
    } 

/**
 * <p>Modifie le montant&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @param _montant nouveau montant
 */
    public void setMontant(int _montant) {        
        montant = _montant;
    } 

/**
 * <p>Retourne la date&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @return date
 */
    public java.util.Date getDate() {        
        return date;
    } 

/**
 * <p>Modifie la date&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @param _date nouvelle date
 */
    public void setDate(java.util.Date _date) {        
        date = _date;
    } 

/**
 * <p>Retourne le type&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @return type
 */
    public String getType() {        
        return type;
    } 

/**
 * <p>Modifie le type&nbsp;de l'op&eacute;ration</p>
 * 
 * 
 * @param _type nouveau type
 */
    public void setType(String _type) {        
        type = _type;
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
