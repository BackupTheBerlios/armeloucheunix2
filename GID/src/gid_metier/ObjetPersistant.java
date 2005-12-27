
package gid_metier;

/**
 * <p>Repr&eacute;sente la classe des objets bas&eacute;s sur un support persistant, dans le cas pr&eacute;sent un SGBD.</p>
 * <p>La classe ObjetPersistant fournit ainsi les outils qui permettent d'impl&eacute;menter les interactions de base entre un objet java et son support (chargement, sauvegarde, suppression...).</p>
 * 
 */
public abstract class ObjetPersistant {

/**
 * <p>Represente l'identifiant de l'objet persistant, c'est &agrave; dire sa cl&eacute; primaire dans le support SGBD.</p>
 * 
 */
    protected int id;

/**
 * <p>Represente l'objet de connexion avec le SGBD, par lequel passent toutes les transactions de notre application.</p>
 * 
 */
    protected static java.sql.Connection conn;

/**
 * <p>Retourne id</p>
 * 
 * 
 * @return id
 */
    public int getId() {        
        return id;
    } 

/**
 * <p>Modifie id</p>
 * 
 * 
 * @param _id nouvel id
 */
    public void setId(int _id) {        
        id = _id;
    } 

/**
 * <p>Retourne la connexion au SGBD</p>
 * 
 * 
 * @return conn
 */
    public static java.sql.Connection getConn() {        
        return conn;
    } 

/**
 * <p>Modifie la connexion au SGBD</p>
 * 
 * 
 * @param _conn Nouvelle connexion
 */
    public static void setConn(java.sql.Connection _conn) {        
        conn = _conn;
    } 

/**
 * <p>Charge (depuis le SGBD) l'objet correspondant a l'identifiant pass&eacute; en param&egrave;tre.</p>
 * <p>Cette op&eacute;ration correspont a une transaction.</p>
 * 
 * 
 * @param id l'identifiant de l'objet recherch�
 * @return L'objet si il est trouv�, sinon null
 * @throws Si une erreur survient pendant la transaction
 */
    public abstract void chargeParId(int id) throws Exception;

/**
 * <p>Enregistre l'objet dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public abstract void sauver() throws Exception;

/**
 * <p>Supprime l'objet du SGBD.</p>
 * <p>Cette operation correspond a une transaction.</p>
 * 
 * 
 * @throws Si une erreur survient pendant la transaction
 */
    public abstract void supprimer() throws Exception;

/**
 * <p>Retourne tous les objets (du type courant) stockes dans le SGBD.</p>
 * <p>Cette operation correspond a une transaction</p>
 * 
 * 
 * @return Une collection de tous les objets
 * @throws Si une erreur survient pendant la transaction
 */
    //public abstract java.util.ArrayList retournerTous() throws Exception;
 }
