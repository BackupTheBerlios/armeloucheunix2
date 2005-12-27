
package gid_metier;

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


/**
 * <p>Prend en charge l'ordonnance en parametre (qui doit faire partie de aTraiter) et effectue les traitements n&eacute;cessaires.<br/>
 *  L'ordonnance est ensuite effac&eacute;e de la liste aTraiter.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance l'ordonnance à prendre en charge
 */
    public void prendreOrdonnanceEnCharge(OrdonnanceDelegation ordonnance) throws Exception{}

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
    public void transmettreOrdonnance(OrdonnanceDelegation ordonnance, Acteur destinataire) throws Exception{}

/**
 * <p>V&eacute;rifie que l'utilisateur est identifi&eacute; a partir de son couple login/mot de passe.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @return true si l'acteur est identifié, sinon false
 */
    public boolean identifie(){
    	return false;
    }


    
	    
/**
 * <p>Enregistre l'ordonnance nouvellement cr&eacute;e (v&eacute;rifie au pr&eacute;alable que toutes les informations n&eacute;cessaires sont saisies) et la transmet au CCED.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance la nouvelle ordonnance
 * @throws Si la vérification n'est pas satisfaite ou si les acces au SGBD ont retournés des erreurs
 */
    public void saisirOrdonnanceDelegation(gid_metier.OrdonnanceDelegation ordonnance) throws Exception {        
        // your code here
    } 

/**
 * <p>Supprime l'ordonnance de d&eacute;l&eacute;gation de cr&eacute;dit.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance L'ordonnance à supprimer
 * @throws Une exception si la suppression échoue
 */
    public void retirerOrdonnanceDelegation(gid_metier.OrdonnanceDelegation ordonnance) throws Exception {        
        // your code here
    } 

 }
