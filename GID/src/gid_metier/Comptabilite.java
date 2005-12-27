
package gid_metier;

/**
 * <p>Represente la comptabilit&eacute; des acteurs de GID</p>
 * 
 */
public class Comptabilite extends ObjetPersistant {

/**
 * <p>Represente le solde de la comptabilit&eacute;</p>
 * 
 */
    private int solde;
/**
 * <p>Liste des op&eacute;rations enregistr&eacute;es &agrave; la comptabilit&eacute;</p>
 * 
 * 
 * @poseidon-type gid_metier.Operation
 */
    private java.util.Collection operation = new java.util.TreeSet();

/**
 * <p>Retourne le solde de la comptabilit&eacute;</p>
 * 
 * 
 * @return solde
 */
    public int getSolde() {        
        return solde;
    } 

/**
 * <p>Modifie le solde de la comptabilit&eacute;</p>
 * 
 * 
 * @param _solde nouveau solde
 */
    public void setSolde(int _solde) {        
        solde = _solde;
    } 

/**
 * <p>Inscrit une operation &agrave; la comptabilit&eacute; et actualise le solde.</p>
 * <p>Cette op&eacute;ration correspond &agrave; une transaction.</p>
 * 
 * 
 * @param op operation qui mouvemente la comptabilité
 */
    public void mouvementer(gid_metier.Operation op) {        
        // your code here
    } 

    /** @poseidon-generated */
    public java.util.Collection getOperations() {
        return operation;
    }
    /** @poseidon-generated */
    public void addOperation(gid_metier.Operation operation) {
        if (! this.operation.contains(operation)) this.operation.add(operation);
    }
    /** @poseidon-generated */
    public void removeOperation(gid_metier.Operation operation) {
        this.operation.remove(operation);
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
