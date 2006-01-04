
package gid_metier;

/**
 * <p>Classe abstraite qui represente les acteurs de GID (proc&eacute;dure P1O5)</p>
 * 
 */
public abstract class Acteur extends ObjetPersistant {

/**
 * <p>Represente le login de l'acteur</p>
 * 
 */
    protected String login;

/**
 * <p>Represente le mot de passe de l'acteur</p>
 * 
 */
    protected String mdp;

/**
 * <p>Represente le nom de l'acteur</p>
 * 
 */
    protected String nom;

/**
 * <p>Represente le prenom de l'acteur</p>
 * 
 */
    protected String prenom;
/**
 * <p>Represente les ordonnances qui reclament une action de l'acteur.<br/>
 * Une fois prises en charge elles sont consid&eacute;r&eacute;es comme trait&eacute;es.<br/>
 * Transmettre une ordonnance &agrave; un acteur revient &agrave; la placer dans la liste des ordonnances aTraiter du destinataire.</p>
 * 
 * 
 * @poseidon-type gid_metier.OrdonnanceDelegation
 */
    protected java.util.Vector aTraiter = new java.util.Vector();
    
    protected java.util.Vector ordonnances_archives = new java.util.Vector();
/**
 * <p>Represente la comptabilit&eacute; personnelle de l'acteur.</p>
 * 
 */
   
    private gid_metier.Comptabilite comptaPerso;

/**
 * <p>Retourne le login de l'acteur</p>
 * 
 * 
 * @return login
 */
    public String getLogin() {        
        return login;
    } 

/**
 * <p>Modifie le login de l'acteur</p>
 * 
 * 
 * @param _login nouveau login
 */
    public void setLogin(String _login) {        
        login = _login;
    } 

/**
 * <p>Retourne le mot de passe de l'acteur</p>
 * 
 * 
 * @return mdp
 */
    public String getMdp() {        
        return mdp;
    } 

/**
 * <p>Modifie le mot de passe de l'acteur</p>
 * 
 * 
 * @param _mdp nouveau mot de passe
 */
    public void setMdp(String _mdp) {        
        mdp = _mdp;
    } 

/**
 * <p>Retourne le nom de l'acteur</p>
 * 
 * 
 * @return nom
 */
    public String getNom() {        
        return nom;
    } 

/**
 * <p>Modifie le nom de l'acteur</p>
 * 
 * 
 * @param _nom nouveau nom
 */
    public void setNom(String _nom) {        
        nom = _nom;
    } 

/**
 * <p>Retourne le prenom de l'acteur</p>
 * 
 * 
 * @return prenom
 */
    public String getPrenom() {        
        return prenom;
    } 

/**
 * <p>Modifie le prenom de l'acteur</p>
 * 
 * 
 * @param _prenom nouveau prenom
 */
    public void setPrenom(String _prenom) {        
        prenom = _prenom;
    } 

/**
 * <p>Prend en charge l'ordonnance en parametre (qui doit faire partie de aTraiter) et effectue les traitements n&eacute;cessaires.<br/>
 *  L'ordonnance est ensuite effac&eacute;e de la liste aTraiter.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance l'ordonnance à prendre en charge
 */
    public abstract void prendreOrdonnanceEnCharge(gid_metier.OrdonnanceDelegation ordonnance) throws Exception;

/**
 * <p>Inscrit le cr&eacute;dit de l'ordonnance &agrave; la comptabilit&eacute; de l'acteur (si cela n'a pas d&eacute;j&agrave; &eacute;t&eacute; fait).</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance l'ordonnance qui mouvemente la comptabilité
 */
    public abstract void majComptabilite(gid_metier.OrdonnanceDelegation ordonnance) throws Exception;

/**
 * <p>Transmet l'ordonnance de d&eacute;l&eacute;gation &agrave; un autre acteur en la pla&ccedil;ant dans sa liste des ordonnances aTraiter.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param ordonnance ordonnance à transmettre
 * @param destinataire destinataire
 */
    public abstract void transmettreOrdonnance(gid_metier.OrdonnanceDelegation ordonnance, gid_metier.Acteur destinataire) throws Exception;

/**
 * <p>V&eacute;rifie que l'utilisateur est identifi&eacute; a partir de son couple login/mot de passe.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @return true si l'acteur est identifié, sinon false
 */
    public abstract boolean identifie();

    /** @poseidon-generated */
    public java.util.Vector getATraiters() {
        return aTraiter;
    }
    /** @poseidon-generated */
    public void addATraiter(gid_metier.OrdonnanceDelegation ordonnanceDelegation) {
        if (! this.aTraiter.contains(ordonnanceDelegation)) this.aTraiter.addElement(ordonnanceDelegation);
    }
    /** @poseidon-generated */
    public void removeATraiter(gid_metier.OrdonnanceDelegation ordonnanceDelegation) {
        this.aTraiter.remove(ordonnanceDelegation);
    }

    /** @poseidon-generated */
    public java.util.Vector getArchives() {
        return ordonnances_archives;
    }
    /** @poseidon-generated */
    public void addArchives(gid_metier.OrdonnanceDelegation ordonnanceDelegation) {
        if (! this.ordonnances_archives.contains(ordonnanceDelegation)) this.ordonnances_archives.addElement(ordonnanceDelegation);
    }
    /** @poseidon-generated */
    public void removeArchives(gid_metier.OrdonnanceDelegation ordonnanceDelegation) {
        this.ordonnances_archives.remove(ordonnanceDelegation);
    }
    
    /** @poseidon-generated */
    public gid_metier.Comptabilite getComptaPerso() {
        return comptaPerso;
    }
    /** @poseidon-generated */
    public void setComptaPerso(gid_metier.Comptabilite comptabilite) {
        this.comptaPerso = comptabilite;
    }
 }
