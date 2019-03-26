/**
* Class ObjetZ du jeu d'aventure Zork, cette class a pour but de definir
* l'ObjetZork qui represente dans le jeu un objet qui le joueur peut trouver
* dans les piece, ramasser et utiliser. Des pnj, notamment les Marchands possedent egalement des ObjetZ
* Ainsi, l'utilisateur peut acheter des objetZ aux marchands qu'il croise lors de son aventure.
*
* Ces ObjetsZork seront utilises tout le long du jeu par le joueur afin de faire
* avancer l'aventure et de pourquoi pas, gagner.
*
* @author       Thomas Ciman
* @version      2.0
* @since        Octobre 2018
*
*/
public class ObjetZ implements Cloneable{
	/*@
      @ invariant getValeur() >= 0;
	  @ invariant getPoids() >= 0;
	  @ invariant getNom() != null;
	  @ invariant getIndication() != null;
	  @*/
    
    // poids de l'objetZ
    private int poids;
    
    // indique le nom de l'ObjetZ
    private String nom;
    
    // indique si l'objet peut être transporté
    private boolean estTransportable;
    
    // indique ce à quoi sert l'objet ou a quoi celui ci peut servir
    private String indication;
    
    // indique le prix de l'objetZ
    private int valeur;
    
    /**
     * Cree une instance de la classe ObjetZ sans paramètre
     * Les variable sont alors initialisees de maniere neutre
     * Un objetZ pourra etre pris par le joueur, ainsi que jete
     *
     */
    /*@
      @ ensures getPoids() == 0;
      @ ensures getNom() != null;
      @ ensures getEstTransportable() == false;
      @*/
    public ObjetZ(){
        this.poids = 0;
        this.nom = "objet inconnu";
        this.estTransportable = false;
        this.indication = "Aucune indication";
        this.valeur = 0;
    }
    
    
    /**
     * Constructeur de la classe ObjetZ avec le plus de parametre possible, un objetZ pourra etres pris par le joueur
     * 
     *  
     * @throws IllegalArgumentException Si le poids passe en parametre est inferieur a 0
     * @throws NullPointerException Si le nom passe en parametre est null
     * @throws NullPointerException Si l'indication passe en parametre est null
     * @throws IllegalArgumentException Si le prix de l'objet est inferieur a 0
     *
     *
     * @param poids	poids qui sera attribue a la nouvelle instance de la classe ObjetZ creee
     *
     * @param nom nom de l'objetZ cree 
     *
     * @param estTransportable indique si l'objet qui sera cree est transportable ou non. true si l'objetZ est transportable, false sinon
     *
     * @param indication attribut des indication aux objetZ cree
     * 
     * @param valeur La valeur en zorkCoins de l'objetZ
     */
    /*@
      @ requires poids >= 0;
      @ requires nom != null;
      @ requires indication != null;
      @ requires prix >= 0;
      @ 
      @ ensures getPoids() >= 0;
      @ ensures getNom() != null;
      @ ensures getIndication() != null;
      @ ensures getPrix() >= 0;
      @
      @*/
    public ObjetZ(int poids, String nom, boolean estTransportable, String indication, int valeur){
        if(poids < 0){
            throw new IllegalArgumentException("Le poids doit être supérieur à 0");
        }
        if(nom == null){
            throw new NullPointerException("Le nom doit être non null");
        }
        if(indication == null){
            throw new NullPointerException("L'indication doit être non null");
        }
        if(valeur < 0){
            throw new IllegalArgumentException("Le prix de l'objet doit être supérieur ou égal à 0");
        }
        
        this.poids = poids;
        this.nom = nom;
        this.estTransportable = estTransportable;
        this.valeur = valeur;
        /*
         * Ici on test si le nom de l'objet est malloc ou free, car ce sont des objets speciaux qui implique de grandes responsabilites  
         * De plus il serait long et fastidieux de reecrire l'indication a chaque fois que l'on cree un new ObjetZ malloc ou free
         */
        if(nom.equals("malloc")){
            this.indication = "Malloc est un objet qui permet d'allouer de la mémoire, \nautrement dit, c'est un objet qui créé de nouvelles pièce.";
        }
        else if(nom.equals("free")){
            this.indication = "Free est un objet très puissant qui sert à effacer un ennemi de la mémoire, \nautrement dit cet objet éliminera vos adversaires.";
        }else if(nom.equals("zorkCoins")){
            this.indication = "Les zorkCoins sont la monnaie du jeu, vous pouvez les utiliser pour effectuer des échanges avec des marchands";
        }
        else{
            this.indication = indication;
        }
    }
    
    
    /**
     * Constructeur d'un objetZ sans l'indication. Ce constructeur est le plus utilise, il permet de creer un
     * objetZ contenant beaucoup de caracteristiques et l'indication s'adapte en fonction du nom de l'ObjetZ
     * 
     * @throws NullPointerException Si le nom passe en parametre est null
     * @throws IllegalArgumentException Si le poids passe en parametre est inferieur a O
     * @throws IllegalArgumentException Si le prix de l'objet est inferieur a 0
     * 
     * @param poids poids qui sera attribue a la nouvelle instance de la classe ObjetZ creee
     * @param nom nom de l'objetZ cree
     * @param estTransportable indique si l'objet qui sera cree est transportable ou non. true si l'objetZ est transportable, false sinon
     * @param valeur La valeur en zorkCoins de l'objetZ 
     */
    /*@
      @ requires poids >= 0;
      @ requires nom != null;
      @ requires valeur >= 0; 
      @
      @ ensures getPoids() >= 0;
      @ ensures getNom().equals(nom);
      @ ensures getPoids() == poids;
      @ ensures getValeur() >= 0;
      @ ensures getNom() != null;
      @*/
    public ObjetZ(int poids, String nom, boolean estTransportable, int valeur){
        if(nom == null){
            throw new NullPointerException("Le nom doit être non null");
        }
        if(poids < 0){
            throw new IllegalArgumentException("le poids doit être supérieur à 0");
        }
        if(valeur < 0){
            throw new IllegalArgumentException("Le prix doit être superieur ou egal à 0");
        }
        
        this.poids = poids;
        this.nom = nom;
        this.estTransportable = estTransportable;
        this.valeur = valeur;
        /*
         * Ici on test si le nom de l'objet est malloc ou free, car ce sont des objets speciaux qui implique de grandes responsabilites  
         * De plus il serait long et fastidieux de reecrire l'indication a chaque fois que l'on cree un new ObjetZ malloc ou free
         */
        if(nom.equals("malloc")){
            this.indication = "\nMalloc est un objet qui permet d'allouer de la mémoire, autrement dis, c'est un objet qui crée de nouvelles pièce.\n";
        }
        else if(nom.equals("free")){
            this.indication = "\nFree est un objet très puissant qui sert à effacer un ennemi de la mémoire, autrement dit cet objet éliminera vos adversaires.\n";
        }
        else if(nom.equals("zorkCoins")){
            this.indication = "Les zorkCoins sont la monnaie du jeu, vous pouvez les utiliser pour effectuer des échanges avec des marchands";
        }
        else{
            this.indication = "Aucune indication";
        }
    }
    
    
    //                                            GETTERS
    

    /**
     * Renvoie le poids de l'instance de la classe ObjetZ appelant
     * 
     * @return	Renvoie un entier correspondant au poids de l'ObjetZ
     */
    /*@
      @ ensures (\result >= 0);
      @
      @ pure
      @*/
    public int getPoids(){
        return this.poids;
    }
    
    
    
    /**
     * Renvoie le nom de l'ObjetZ appelant, le nom etant un attribut de la classe ObjetZ
     *
     * @return snom de l'instance de la classe ObjetZ appelant                      
     */
    /*@
      @ ensures \result != null
      @
      @ pure
      @*/
    public String getNom(){
        return this.nom;
    }
    
    
    /**
     * Getter renvoyant la valeur de l'objetZ appellant
     *
     * @return Renvoie la valeur de l'objetZ
     */
    /*@
      @ ensures (\result >= 0);
      @
      @ pure
      @*/
    public int getValeur(){
        return this.valeur;
    }
    
    
    /**
     * Indique si l'objet appelant est transportable ou non
     *
     * @return Renvoie true si l'objetZ est transportable, false sinon
     * 
     */
    /*@
      @ pure
      @*/
    public boolean getEstTransportable(){
        return this.estTransportable;
    }
    
    
    
    /**
     * Renvoie un objet String correspondant aux informations de l'objetZ ou a ce qu'il est ecrit dessus
     * 
     * @return	Renvoie les indications de l'instance de la classe ObjetZ appelant	
     */
    /*@
      @ pure
      @*/
    public String getIndication(){
        return this.indication;
    }
    
    
    //                                        SETTERS
    
    
    /** 
     * Modifie ou initialise le poids de l'instance de la classe ObjetZ appelant
     *  
     * @throws IllegalArgumentException Si le poids passe en parametre est inferieur a 0
     *
     * @param poids	nouveau poids de l'objet si celui-ci doit etres change
     *  
     */
    /*@
      @ requires poids >= 0;
      @ 
      @ ensures \result == getPoids();
      @ ensures getPoids() >= 0;
      @*/
    public void setPoids(int poids){
        if(poids < 0){
            throw new IllegalArgumentException("Le poids doit être supérieur à 0");
        }
        this.poids = poids;
    }    
    
    
    /**
     * Modifie ou initialise le nom de l'instance de la classe ObjetZ appelant
     *  
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom nom qui sera attribue a l'objet si celui-ci doit changer
     * 
     */
    /*@
      @ requires nom != null;
      @ 
      @ ensures getNom().equals(nom);
      @ ensures getNom() != null;
      @*/
    public void setNom(String nom){
        if(nom == null){
            throw new NullPointerException("Le nom ne doit pas être null");
        }
        this.nom = nom;
    }
    

    /**
    * Redefinition de equals() pour la class ObjetZ
    *
    * @return true si l'instance de la class appelant equals() a tous ses attributs egaux a l'instance de la class ObjetZ passee en parametre
    */
    /*@
      @ requires object != null
      @
      @ pure
      @*/
    @Override
    public boolean equals(Object object){
        if(object == null){
            throw new NullPointerException("L'object passé en paramètre ne doit pas être null");
        }
        if(object instanceof ObjetZ){
            ObjetZ objetZork = (ObjetZ)object;
            return this.poids == objetZork.poids && this.nom.equals(objetZork.nom) && this.estTransportable == objetZork.estTransportable && this.valeur == objetZork.valeur && this.indication.equals(objetZork.indication);
        }
        return false;
    }
    
    
    /**
    * Redefinition de hashCode() pour la class ObjetZ
    *
    * @return le hashCode de l'instance de la class ObjetZ appelant hashCode()
    */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        if(this.estTransportable == true){
            return 31 * this.poids + this.nom.hashCode() + this.indication.hashCode() * 2 + this.valeur * 5 + 1;
        }
        return 31 * this.poids + this.valeur * 5 + this.indication.hashCode() * 2 + this.nom.hashCode();
    }
    
    
    /**
    * Redefinition de toString() pour la class ObjetZ
    *
    */
    /*@
      @ ensures \result != null;
      @
      @ pure
      @*/
    @Override
    public String toString(){
        //On transforme le true en une phrase
        if(this.estTransportable == true){
            String remplaceBool = "est transportable.";
             return "Cet objet nommé " + this.nom + " pèse " + this.poids + " octets, a une valeur de " + this.valeur + " zorkCoins et est transportable.";
        }
        else if(this.estTransportable == false){
            return "Cet objet nommé " + this.nom + " pèse " + this.poids + " octets, a une valeur de " + this.valeur + " zorkCoins et n'est pas transportable.";
        }
        
        // Au cas ou il y a un problème, pour tout de même retourner quelque chose.
        return "Cet objet nommé " + this.nom + " pèse " + this.poids + " octets, a une valeur de " + this.valeur + " zorkCoins et est transportable : " + this.estTransportable + ".";
    }
    
    
    /**
    * Redefinition de clone() pour la class ObjetZ
    * 
    * @return un clone de l'instance de la class ObjetZ appelant clone(). Le clone est egal en tout attribut a l'instance depuis laquelle il a ete clone
    */
    @Override
    public ObjetZ clone(){
        ObjetZ clone = null;
        
        try{
            clone = (ObjetZ) super.clone();
        }catch(CloneNotSupportedException e){
            throw new InternalError("Erreur dans le clone de Object");
        }
        
        // clone de tous les attributs
        clone.poids = this.poids;
        clone.nom = this.nom;
        clone.estTransportable = this.estTransportable;
        clone.indication = this.indication;
        clone.valeur = this.valeur;
        // renvoi le clone
        return clone;
    }
}
