/**
* La class Pnj permet la creation de personnages non jouables, elle sert de class mere pour les class 
* marchand et ennemis.
* Seul Bog, l'ennemi a detruire est une instance de Pnj.
* Le pnj a un nom, une phrase qui permet de lui parler ainsi qu'un boolean indiquant sa mobilite ou non
*
* @author Thomas Ciman
* @since December 2018
* @version 2.0
*/ 
public class Pnj extends ArrayListConteneur<ObjetZ>{
    
    /*@
      @ invariant getNom() != null;
      @ invariant getPhrase() != null;
      @*/
    
    private String nom;
    // Indique si le joueur peut se déplacer
    private boolean mobile;
    // La phrase typique du pnj
    private String phrase; 
    
    
    /**
     * Constructeur de Pnj sans parametres
     */
    /*@
      @ ensures getNom() != null; 
      @ ensures getPhrase() != null;
      @ ensures getMobile() == false;
      @*/
    public Pnj(){
        this.nom = "Billy";
        this.mobile = false;
        this.phrase = "je suis un pnj";
    }
    
    
    /**
     * Constructeur de Pnj avec un String indiquant le nom du Pnj, un boolean indiquant si le Pnj peut se deplacer
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom Indique le nom a attribuer au Pnj
     * @param mobile Indique si le Pnj est mobile ou non, true si mobile, false sinon
     */
    /*@
      @ requires nom != null;
      @
      @ ensures getNom() != null;
      @ ensures getPhrase() != null;
      @*/
    public Pnj(String nom, boolean mobile){
        if(nom == null){
            throw new NullPointerException("Le nom passé en paramètre ne doit pas être null");
        }
        this.nom = nom;
        this.mobile = mobile;
        this.phrase = "je suis un pnj";
    }
    

    /**
     * Constructeur de Pnj avec un String indiquant le nom du Pnj, un boolean indiquant si le Pnj peut se deplacer
     * et un phrase etant la phrase typique du pnj
     *
     * @throws NullPointerException Si la phrase passe en parametre est null
     * @throws NullPointerException si le nom du pnj passe en parametre est null
     *
     * @param nom Indique le nom a attribuer au Pnj
     * @param mobile Indique si le Pnj est mobile ou non, true si mobile, false sinon
     * @param phrase Indique la phrase a attribuer au pnj
     */
    /*@
      @ requires nom != null;
      @ requires phrase != null;
      @
      @ ensures getPhrase() != null;
      @ ensures getNom() != null;
      @*/
    public Pnj(String nom, boolean mobile, String phrase){
        if(nom == null){
            throw new NullPointerException("Le nom passé en paramètre ne doit pas être null");
        }
        if(phrase == null){
            throw new NullPointerException("La phrase passée en paramètre ne doit pas être null");
        }
        this.phrase = phrase;
        this.nom = nom;
        this.mobile = mobile;
    }

    
    /**
     * Methode permettant de savoir si le pnj est mobile ou non, true si mobile, false sinon
     *
     * @return true si le pnj est mobile, false sinon
     */
    /*@
      @ pure
      @*/
    public boolean getMobile(){
        return this.mobile;
    }
    
    
    /**
     * Renvoie la phrase du pnj dans l'attribut de class
     *
     * @return la phrase du joueur
     */
    /*@
      @ ensures (\result != null);
      @ pure
      @*/
    public String getPhrase(){
        return this.phrase;
    }

    
    /**
     * Fonction permettant de renvoyer le nom d'un pnj, sert surtout pour les personnages non jouables
     * 
     * @return Le nom de l'instance de la Class Pnj appelant
     */
    /*@
      @ ensures (\result != null);
      @ pure
      @*/
    public String getNom() {
    	return this.nom;
    }
    

    /**
     * Methode permettant de renvoyer un objet en fonction de son nom, comme l'attribut nom dans 
     * ObjetZ ou l'attribut description dans Piece
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom Le nom dont on veut tester l'egalite ou non 
     *
     * @return l'objet qui a le nom passe en parametre ou null
     */
    /*@
      @ also
      @
      @ requires nom != null;
      @ ensures (\result != null ==> getObjets(i) != null);
      @ pure
      @*/
    public ObjetZ getObjetNom(String nom){
        if(nom == null){
            throw new NullPointerException("Le nom passé en paramètre ne doit pas être null");
        }
        
        for(int i = 0; i < getNbObjets(); i++){
            if(getObjets(i).getNom().equals(nom)){
                return getObjets(i);
            }
        }
        return null;
    }
    
    
    
    /**
     * Fonction permettant de savoir si un objet peut etre ajoute a l'inventaire du pnj, renvoie true si oui
     * false sinon
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objetz l'objet que l'on veut ajouter a l'inventaire du joueur
     *
     * @return Un boolean permettant de savoir si l'objetZ passe en parametre peut etre ajoute a l'inventaire du pnj
     */
    /*@
      @ also
      @
      @ requires objetz != null;
      @*/
    @Override
    public boolean ajoutPossible(ObjetZ objetz){
        if(objetz == null){
            throw new NullPointerException("LobjetZ o en paramètre est null");
        }
        else{
            return true;
        }
    }
    
    
    /**
    * Redefinition de equals() pour la class Pnj
    *
    * @return true si la class appelante est egale en tout attribut a l'instance passee en parametre
    */
    /*@
      @ requires o != null;
      @
      @ pure
      @*/
    @Override
    public boolean equals(Object o){
        if(o instanceof Pnj){
            Pnj pnj = (Pnj) o;
            return this.mobile == pnj.mobile && this.nom.equals(pnj.nom) && this.phrase.equals(pnj.phrase) && super.equals(pnj);
        }
        return false;
    }
    
    
    /**
    * Redefinition de toString() pour la class Pnj, on prend en compte si le pnj est mobile ou non
    *
    * @return un String decrivant l'instance de la class appelante
    */
    /*@
      @ pure
      @*/
    @Override
    public String toString(){
        if(mobile == true){
            return "Je suis un pnj nommé " + this.nom + " je suis mobile, ma phrase est " + this.phrase + ".";
        }
        else{
            return "Je suis un pnj nommé " + this.nom + " je suis immmobile, ma phrase est " + this.phrase + ".";
        }
    }
    
    
    /**
    * Redefinition de hashCode() pour la class Pnj
    *
    * @return le hashCode de l'instance appelante
    */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        if(this.mobile){
            return this.nom.hashCode() + this.phrase.hashCode() + super.hashCode() + 1;
        }
        return this.nom.hashCode() + this.phrase.hashCode() + super.hashCode();
    }
    
}