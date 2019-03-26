import java.util.ArrayList;

/**
 *  Interface Conteneur permettant de définir des prototypes de methodes utiles pour contenir differents 
 *  types d'objets
 *  Les deux class filles sont ArrayListConteneur et ArrayConteneur, ainsi on se retrouve avec un conteneur
 *  sous forme d'array, (de tableau) et un autre sous forme d'ArrayList.
 *  
 * @author      Thomas Ciman
 * @since       December 2018
 * @version     2.0
 */
public interface Conteneur<E>{
    
    
    /**
     * Renvoie le nombre d'objets contenus dans le conteneur
     *
     * @return Le nombre d'objets du conteneur         
     *
     */
    /*@
      @pure
      @*/
    public int getNbObjets();
    
    
    
    /**
     * Ajoute un objet au conteneur
     * La fonction ne renvoie rien
     * Prise en compte de la genericite en prenant un objet de type E
     *  
     * @param objet est un objet qu'on ajoute au conteneur
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures getNbObjets() == getNbObjets + 1;
      @ ensures contient(objet);
      @ ensures contientCombienDe(objet) == \old contientCombienDe(objet) + 1;
      @
      @*/
    public void ajouter(E objet);
    

    
    /**
     * Methode permettant de renvoyer un objet en fonction de son nom, comme l'attribut nom dans ObjetZ 
     * ou l'attribut description dans Piece. dans les class implementant Conteneur, la methode sera
     * abstraite car pour get le nom il faut connaitre le prototype de la methode propre a cela selon le type
     * qui sera a la place de E. exemple getNom() dans Joueur, descriptionCourte() dans Piece
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom Le nom dont on veut tester l'egalite ou non avec un element du Conteneur
     *
     * @return l'objet qui a le nom passe en parametre ou null
     */
    /*@
      @ requires nom != null;
      @*/
    public E getObjetNom(String nom);
    
    
    
    /**
     * Retire un objet du conteneur
     * La fonction renvoie true si l'objet a ete retirer, elle renvoie false sinon
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet Specifie l'objet qui doit être retire du conteneur
     *
     * @return true si l'objet a ete retire, false sinon
     *
     */
    /*@
      @ requires objet != null;
      @
      @ ensures contient(objet) ==> contientCombienDe(objet) == \old(contientCombienDe(objet)) - 1;
      @ ensures contient(objet) ==> getNbObjets() == getNbObjets() - 1;
      @*/
    public boolean retirer(E objet);
    
    
    
    /**
     * Renvoie l'objet contenue à l'indice index dans le conteneur, prise en compte de la genericite avec
     * le type E
     *
     * @throws IllegalArgumentException Si l'index est inferieur a 0
     * @param index L'indice du conteneur ou se trouve l'objet que l'on veut renvoyer
     * @return l'objet contenue à l'indice index
     *
     */
	/*@
	  @ requires index >= 0;
	  @ 
	  @ pure
	  @*/
    public E getObjets(int index);
    



    
    /**
     * Renvoie une boolean indique si l'objet de type E passe entre en parametre est contenu dans le conteneur
     *  
     * @throws NullPointerException Si l'objet passe en parametre est null
     * @param objet l'objet dont on veut connaitre l'existence ou non dans le conteneur  
     * @return renvoie true si l'objet est dans le conteneur, false sinon       
     *
     */
    /*@
      @ requires objet != null;
      @
      @ ensures (\result ==> contientCombienDe(objet) > 0); 
      @ ensures (!\result ==> contientCombienDe(objet) == 0);
      @
      @ pure
      @*/
    public boolean contient(E objet);
    
    
    
     /**
     * Permet de connaitre le nombre d'occurence d'un objet de type E dans le Conteneur
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     * @param objet indique l'objet de type E dont on veut savoir le nombre d'exemplaire dans le conteneur
     * @return Renvoie un entier indiquant le nombre d'occurrences equivalent a l'objet de type E passe en parametre
     * 
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures (\result >= 0);
      @ ensures (!contient(objet) ==> (\result == 0));
      @ ensure (contient(objet) ==> (\result > 0)); 
      @
      @ pure
      @*/
    public int contientCombienDe(E objet);
    
    
    /**
     * Methode permettant de renvoyer l'attribut de class Objets sous forme d'ArrayList de type E
     * Cette methode clonera l'attribut Objets dans une ArrayList et renvera celle-ci
     *
     * @return renvoie l'ArrayList correspondant a l'attribut Objets
     */
    public ArrayList<E> getArrayL();

    
    /**
     * Fonction permettant de savoir si un objet peut etre ajoute au conteneur, la principale raison 
     * pouvant empecher cela est que le joueur risque de transporter un poids trop impoortant une fois l'objet en
     * sa possession, dans la piece, l'ajout est toujours possible
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     * @param objet l'objet qu'on veut tenter d'ajouter au conteneur
     * @return true si l'objet peut etre ajoute, false sinon
     */
    public boolean ajoutPossible(E objet);
    
} 