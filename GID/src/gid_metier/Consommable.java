
package gid_metier;

/**
 * <p>Represente les consommables pouvant etre achet&eacute;s par le cr&eacute;dit de l'ordonnance</p>
 * 
 */
public class Consommable extends ObjetPersistant {

/**
 * <p>Represente le libelle du consommable</p>
 * 
 */
    private String libelle;

/**
 * <p>Represente le prix du consommable</p>
 * 
 */
    private int prix;

/**
 * <p>Represente l'unit&eacute; dans laquelle se lit le prix du consommable (ex : &euro;/L, &euro;/Kg...)</p>
 * 
 */
    private String unite;

/**
 * <p>Represente le nombre d'unit&eacute;s de ce consommable pr&eacute;vu par l'ordonnance de d&eacute;l&eacute;gation.</p>
 * 
 */
    private int quantite;

/**
 * <p>Retourne le libelle du consommable</p>
 * 
 * 
 * @return libelle
 */
    public String getLibelle() {        
        return libelle;
    } 

/**
 * <p>Modifie le libelle du consommable</p>
 * 
 * 
 * @param _libelle nouveau libelle
 */
    public void setLibelle(String _libelle) {        
        libelle = _libelle;
    } 

/**
 * <p>Retourne le prix du consommable</p>
 * 
 * 
 * @return prix
 */
    public int getPrix() {        
        return prix;
    } 

/**
 * <p>Modifie le prix du consommable</p>
 * 
 * 
 * @param _prix nouveau prix
 */
    public void setPrix(int _prix) {        
        prix = _prix;
    } 

/**
 * <p>Retourne l'unite du prix du consommable</p>
 * 
 * 
 * @return unite
 */
    public String getUnite() {        
        return unite;
    } 

/**
 * <p>Modifie l'unite du prix du consommable</p>
 * 
 * 
 * @param _unite nouvelle unite
 */
    public void setUnite(String _unite) {        
        unite = _unite;
    } 

/**
 * <p>Retourne la quantite de ce consommable pr&eacute;vue par l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 * 
 * @return quantite
 */
    public int getQuantite() {        
        return quantite;
    } 

/**
 * <p>Modifie la quantite de ce consommable pr&eacute;vue par l'ordonnance de d&eacute;l&eacute;gation</p>
 * 
 * 
 * @param _quantite nouvelle quantite
 */
    public void setQuantite(int _quantite) {        
        quantite = _quantite;
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
