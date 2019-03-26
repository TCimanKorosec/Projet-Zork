/**
* Un joueur dans le jeu d'aventure Zork
* Cette classe fait partie d'un jeu d'aventure Zork
*
* Un "Joueur" represente les caracteristiques du joueur jouant au jeu ou des pnjs (personnages non jouables)
* qui seront presents dans la prochaine version.
* Le Joueur peut contenir des objets et interagir avec les objets presents dans les pieces ou avec les marchands
* par exemple
* Le Joueur a un nom pour le differencier d'autre joueurs et pour que le joueur puisse customiser l'univers Zork
* Le Joueur peut egalement recuperer des objets dans les pieces et ces objets sont stockes dans un tableau dynamique
* Le Joueur a egalement une restriction vis a vis du poids total des objets qu'il peut porter 
*
* @author   Thomas Ciman
* @version  2.0
* @since    October 2018
*/
public class Joueur extends ArrayListConteneur<ObjetZ> {
    /*@
      @ invariant getNbObjetZ() >= 0;
      @ invariant getNom() != null;
      @ invariant getPoidsMax() == 64;
      @ invariant getZorkCoins() >= 0;
      @*/
	
    
    // Le nom du personnage, pnj ou joueur
    private String nom;
    
    // poids maximal que le joueur peut porter
    private final int poidsMax;
    
    // la monnaie du jeu
    private int zorkCoins;
    
    
    /**
     * Constructeur primaire de la classe Joueur, le nom est defini par defaut ainsi que le poids total
     * que le joueur peut porter
     * L'ArrayList inventaire est egalement declare
     */
    /*@
      @ ensures getPoidsMax() == 64;
      @ ensures getNbObjets() == 0;
      @ ensures getNbZorkCoins() == 50;
      @ ensures getNom() != null;
      @ ensures getArrayL() != null;
      @*/
    public Joueur(){
        super();
        this.nom = "Billy";
        this.poidsMax = 64;
        this.zorkCoins = 50;
    }
    
    
    /**
     * Constructeur de la classe Joueur qui va definir le nom du joueur, creer un inventaire vide
     * et definir le poids maximal que peut transporter le joueur ainsi que le nombre de zorkCoins que celui-ci
     * possede
     * 
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom indique le nom du joueur
     */
    /*@
      @ requires nom != null;
      @
      @ ensures getNom().equals(nom);
      @ ensures getNom() != null;
      @ ensures getZorkCoins() == 50;
      @ ensures getPoidsMax() == 64;
      @ ensures getArrayL() != null;
      @ ensures getNbObjets() == 0;
      @*/
    public Joueur(String nom){
        super();
        if(nom == null){
            throw new NullPointerException("Le nom doit être non null");
        }
        this.nom = nom;
        this.poidsMax = 64;
        this.zorkCoins = 50;
    }
    
    
    //                                        GETTERS
    
    
    /**
     * Renvoie le poids total que le joueur peut porter sur lui. Ce poids est defini lors de la creation du joueur
     * 
     * @return Le poids maximal que le joueur peut porter
     */
    /*@
      @ ensures \result == 64;
      @
      @ pure
      @*/
    public int getPoidsMax(){
        return this.poidsMax;
    }
    
    
    /**
     * Methode renvoyant le nombre de zorkCoins que possede le joueur, les zorkCoins vont permettre au joueur
     * d'acquerir des objets
     *
     * @return le nombre de zorkCoins que detient le joueur
     */
    /*@
      @ ensures \result >= 0); 
      @
      @ pure
      @*/
    public int getZorkCoins(){
        return this.zorkCoins;
    }
    
    
    
    /**
     * Fonction permettant de renvoyer le nom d'un joueur
     * 
     * @return Le nom de l'instance de la Classe Joueur appelante
     */
    /*@
      @ ensures \result != null;
      @
      @ pure
      @*/
    public String getNom() {
    	return this.nom;
    }
    
    
    /**
     * Renvoie le nom de l'objet present a l'indice "index" de l'ArrayList inventaire
     * 
     * @throws IllegalArgumentException Si l'index passe en parametre est inferieur a 0
     *
     * @param index la position dans l'ArrayList a laquelle on veut connaitre le nom de l'objetZ
     * 
     * @return le nom de l'objetZ present a cet indice de l'ArrayList
     */
    /*@
      @ requires index >= 0;
      @
      @ ensures \result != null;
      @
      @ pure
      @*/
    public String getNomObjets(int index){
        if(index < 0){
            throw new IllegalArgumentException("L'index passé en paramètre ne doit pas être null");
        }
        return this.getObjets(index).getNom();
    }
    
    
    
    /**
     * Renvoie le poids total de l'inventaire. Le poids de l'inventaire va servir a savoir si le joueur 
     * peut prendre un objet supplementaire ou non
     * 
     * @throws NullPointerException Si un des Objets de du conteneur est null
     *
     * @return le poids total de tous les objetsZ dans l'inventaire du joueur
     */
    /*@
      @ requires (\forall int i; (0 <= i) && (i < getNbObjets()); getObjets(i) != null); 
      @
      @ ensures (\result >= 0);
      @
      @ pure
      @*/
    public int getPoidsObjets(){
        // Set le poids a 0
        int poidsInv = 0;
        
        // On va ajouter le poids de chaque objet du conteneur dans la variable poidsInv
        for(int i = 0; i < getNbObjets(); i++){ 
            if(getObjets(i) == null){
                throw new NullPointerException("Aucun objetZ de l'ArrayList ne doit être null");
            }
            poidsInv += getObjets(i).getPoids();
        }
        return poidsInv;
    }
    
    
    
    /**
     * Methode permettant de renvoyer un objet en fonction de son nom, comme l'attribut nom dans ObjetZ 
     * ou l'attribut description dans Piece
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     * @throws NullPointerException Si un des objets de l'inventaire du joueur est null
     *
     * @param nom Le nom dont on veut tester l'egalite ou non 
     *
     * @return l'objet qui a le nom passe en parametre ou null
     */
    /*@
      @ requires (\forall int i; (0 <= i) && (i <= getNbObjets()); getObjets(i) != null);
      @ requires nom != null;
      @
      @ pure
      @*/
    @Override
    public ObjetZ getObjetNom(String nom){
        if(nom == null){
            throw new NullPointerException("Le nom passé en paramètre ne doit pas être null");
        }
        for(int i = 0; i < getNbObjets(); i++){
            // On verifie qu'aucun objet n'est null
            if(getObjets(i) == null){
                throw new NullPointerException("Aucun objet du joueur ne doit pas être null");
            }
            if(getObjets(i).getNom().equals(nom)){
                return getObjets(i);
            }
        }
        return null;
    }
    
    
    
    /**
     * Indique si le joueur a assez d'argent pour pouvoir acquerir l'objetZ
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet L'objetZ dont on veut connaitre la valeur afin de savoir si le joueur a assez de zorkCoins
     * pour acquerir l'objet
     *
     * @return un boolean true si le joueur a assez de ZorkCoins, false sinon
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures (\result == true ==> (getZorkCoins() - objet.getValeur()) >= 0);
      @ 
      @ pure
      @*/
    public boolean assezDeCoins(ObjetZ objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre ne doit pas être null");
        }
        // On test si le joueur a assez de zorkCoins pour acquerir un objet
        if((this.zorkCoins - objet.getValeur()) < 0 ){
            return false;
        }
        return true;
    }
    
    
    
    /**
     * Fonction permettant de savoir si un objet peut etre ajoute a l'inventaire du joueur, renvoie true si oui
     * false sinon
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objetz l'Objet que l'on veut ajouter a l'inventaire du joueur
     *
     * @return Un boolean permettant de savoir si l'objetZ passe en parametre peut etre ajoute a l'inventaire
     */
    /*@
      @ requires objetz != null;
      @*/
    @Override
    public boolean ajoutPossible(ObjetZ objetz){
        if(objetz == null){
            throw new NullPointerException("LobjetZ o en paramètre est null");
        }
        else{
            boolean succes;
            succes =  objetz.getPoids() + this.getPoidsObjets() <= this.getPoidsMax();
            return succes;
        }
    }
    
    
    
    /**
     * Methode permettant de retirer des zorkCoins au nombre de zorkCoins que possède le joueurPrincipal
     * Le parametre valeur indique la valeur a retirer.
     *
     * @throws IllegalArgumentException Si la valeur passee en parametre est inferieur a 0
     *
     * @param valeur la valeur qui va etre supprime au nombre total de zorkCoins du joueur
     */
    /*@
      @ requires valeur >= 0;
      @
      @ ensures getZorkCoins() >= 0;
      @*/
    public void retirerZorkCoins(int valeur){
        if(valeur < 0){
            throw new IllegalArgumentException("La valeur passée en paramètre ne doit pas être négative");
        }
        this.zorkCoins -= valeur;
        if(this.zorkCoins < 0){
            this.zorkCoins = 0;
        }
    }
    
    
    
    /**
     * Methode permettant d'ajouter des zorkCoins au nombre de zorkCoins que possède le joueurPrincipale
     * Le parametre valeur indique la valeur a ajouter.
     *
     * @throws IllegalArgumentException Si la valeur passee en parametre est inferieur a 0
     *
     * @param valeur la valeur qui va etre ajoutee au nombre total de zorkCoins du joueur
     */
    /*@
      @ requires valeur >= 0;
      @
      @ ensures getZorkCoins() >= 0;
      @*/
    public void ajouterZorkCoins(int valeur){
        if(valeur < 0){
            throw new IllegalArgumentException("la valeur passée en paramètre ne doit pas être négative");
        }
        this.zorkCoins += valeur;
    }
    
    
    /**
    * Redefinition de equals pour la class Joueur
    *
    * @param objet l'objet dont on veut  
    * 
    * @return true si les deux instance ont les memes valeurs dans tous leurs attributs, false sinon
    */
    /*@
      @ requires objet != null;
      @
      @ pure
      @*/
    @Override
    public boolean equals(Object objet){
        if(objet == null){
            throw new NullPointerException("L'objetz doit être non null");
        }
        if(objet instanceof Joueur){
            Joueur player = (Joueur)objet;
            
            return this.nom == player.nom && this.poidsMax == player.poidsMax && this.getArrayL().equals(player.getArrayL()) && this.zorkCoins == player.zorkCoins;
        }
        return false;
    }
    
    
    /**
    * Redefinition de toString() dans la class Joueur
    *
    * @return une chaine de caractere representant l'instance appelante de la class
    */
    /*@
      @ also
      @ ensures (\result != null);
      @
      @ pure
      @*/
    @Override
    public String toString(){
        return "Le joueur appelé " + this.nom + " est en possession de " + this.getNbObjets() + " objets qui pèsent au total " + this.getPoidsObjets() + " octets et peut transporter en tout " + this.poidsMax + " octets. Son solde est de " + this.zorkCoins + " ZorkCoins.";
    }
    
    
    /**
    * Redefinition de hashCode() pour la class Joueur
    *
    * @return le hashCode de l'instance appelante cette class
    */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        return this.nom.hashCode() + 42 * this.poidsMax + this.getArrayL().hashCode() + this.zorkCoins * 4;
    }
}