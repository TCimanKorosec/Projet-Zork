import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se deroule l'action du
 *  jeu. Elle est reliee a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont etiquettees "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possede une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @author     Thomas Ciman
 * @version    2.0
 * @since      August 2000
 */

public class Piece extends DoubleArrayListConteneur<ObjetZ, Pnj>{
	/*@
	  @ invariant descriptionCourte() != null;
	  @ invariant getNbObjetZ() >= 0;
	  @*/
	 
    // Donne le nom de la pièce
    private String description;
    
	// mémorise les sorties de cette piece.
	private HashMap sorties;



	/**
	 * Initialise une piece decrite par la chaine de caracteres specifiee.
	 * Initialement, cette piece ne possede aucune sortie. La description fournie
	 * est une courte phrase. les sorties sont egalement initialise de type hashMap, ainsi que l'ArrayList contenant les objets de la piece
	 * et l'Arraylist contenant les autres joueurs dans la piece
	 * 
	 * @throws NullPointerException Si la description passee en parametre est null
	 * @param  description  Description de la piece.
	 */
	/*@
	  @ requires description != null;
	  @ 
	  @ ensures getNbObjets1() == 0;
      @ ensures getNbObjets2() == 0;
      @ ensures getArrayL1() != null;
      @ ensures getArrayL2() != null;
	  @ ensures descriptionCourte().equals(description);
      @ ensures descriptionCourte() != null;
	  @ ensures getSorties() != null;
	  @*/
	public Piece(String description) {
        super();
        if(description == null){
            throw new NullPointerException("La description passée en paramètre ne doit pas être null");
        }
        this.description = description;
		sorties = new HashMap();
	}
    
    
	/**
	 * Initialise une piece sans parametre
	 * Initialement, cette piece ne possede aucune sortie.
	 * les sorties sont egalement initialise de type hashMap, ainsi que l'ArrayList contenant les objets de la piece
	 * et l'Arraylist contenant les autres joueurs dans la piece.
	 * La piece n'ayant aucune description on la considere comme piece inconnue
	 * 
	 */
	/*@
      @ ensures getArrayL1() != null;
      @ ensures getArrayL2() != null;
	  @ ensures getNbObjets1() == 0;
      @ ensures getNbObjets2() == 0;
      @ ensures sorties != null;
      @ ensures descriptionCourte() != null;
	  @*/
	public Piece(){
        super();
		this.description = "pièce inconnue";
		this.sorties = new HashMap();
	}
    
	
    /**
     * Renvoie les sorties de l'instance de la classe Piece appelant. Les sorties sont
     * de type hashMap
     *
     * @return      Les sorties de la piece
     *
     */
    /*@
      @ ensures \result != null;
      @
      @ pure
      @*/
    public HashMap getSorties(){
        return this.sorties;
    }
    
    
    /**
     * Fonction identique a descriptionCourte mais avec un nom universelle et plus facile d'utilisation
     *
     * @return le nom de la piece / la description de la piece
     */
    /*@
      @ ensures (\result != null); 
      @
      @ pure
      @*/
    public String getNom(){
        return this.description;
    }
    
    
	/**
	 * Definie les sorties de cette piece. A chaque direction correspond ou bien
	 * une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 * cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put("nord", nord);
		}
		if (est != null) {
			sorties.put("est", est);
		}
		if (sud != null) {
			sorties.put("sud", sud);
		}
		if (ouest != null) {
			sorties.put("ouest", ouest);
		}
	}

    
	/**
	 * Renvoie la description de cette piece (i.e. la description specifiee lors
	 * de la creation de cette instance).
	 *
	 * @return Description de cette piece
	 */
    /*@
      @ ensures (\result != null); 
      @
      @ pure
      @*/
	public String descriptionCourte() {
		return description;
	}


	/**
	 * Renvoie une description de cette piece mentionant ses sorties et
	 * directement formatee pour affichage, de la forme: <pre>
	 * Vous etes dans la bibliotheque.
	 * Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 * renvoyee par la méthode descriptionSorties pour decrire les sorties de
	 * cette piece.
	 *
	 * @return Description affichable de cette piece
     *
	 */
    /*@
      @ pure
      @*/
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}

    
	/**
	 * Renvoie une description des sorties de cette piece, de la forme: <pre>
	 * Sorties: nord ouest</pre> Cette description est utilisee dans la
	 * description longue d'une piece.
	 *
	 * @return Une description des sorties de cette piece.
     */
    /*@
      @ pure
      @*/
	public String descriptionSorties() {
		String resulString = "Sorties:";
		Set keys = sorties.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
			resulString += " " + iter.next();
		}
		return resulString;
	}
    
    
	/**
	 * Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 * dans la direction specifiee. Si cette piece ne possede aucune sortie dans cette direction,
	 * renvoie null.
	 *
	 * @param direction La direction dans laquelle on souhaite se deplacer
	 * @return Piece atteinte lorsque l'on se deplace dans la direction specifiee ou null.
	 */
    /*@
      @ requires direction != null;
      @
      @ pure
      @*/
	public Piece pieceSuivante(String direction) {
		return (Piece) sorties.get(direction);
	}
    
    
    /**
     * Fonction permettant de savoir si un ObjetZ peut être ajoute a la piece, renvoie true si oui
     * false sinon. Un objetZ peut toujours etre ajoute dans une piece donc la methode, mise a part en cas de lever 
     * d'exception renvoie toujours true
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet l'Objet que l'on veut ajouter à l'inventaire du joueur
     *
     * @return Un boolean permettant de savoir si l'objetZ passe en parametre peut etre ajoute a l'inventaire
     */
    /*@
      @
      @
      @*/
    public boolean ajoutPossible1(ObjetZ objet){
        if(objet == null){
            throw new NullPointerException("L'objetZ passé en paramètre est null");
        }
        else{
            return true;
        }
    }
    
    
    /**
     * Fonction permettant de savoir si un Pnj peut être ajoute a la piece, renvoie true si oui
     * false sinon. Un Pnj peut toujours etre ajoute dans une piece donc la methode, mise a part en cas de lever 
     * d'exception renvoie toujours true
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet l'Objet que l'on veut ajouter à l'inventaire du joueur
     *
     * @return Un boolean permettant de savoir si l'objetZ passe en parametre peut etre ajoute a l'inventaire
     */
    /*@
      @ requires objet != null;
      @
      @ ensures \result == true;
      @ 
      @ pure
      @*/
    public boolean ajoutPossible2(Pnj objet){
        if(objet == null){
            throw new NullPointerException("Le Pnj passé en paramètre est null");
        }
        else{
            return true;
        }
    }
    
    
    /**
     * Methode permettant de renvoyer un objet en fonction de son nom, ici getObjetNom1() permet de renvoyer
     * une instance de ObjetZ en fonction de son nom avec l'appel a getNom()
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom Le nom dont on veut tester l'egalite ou non 
     *
     * @return l'instance d'ObjetZ qui a le nom passe en parametre ou null
     */
    /*@
      @ requires nom != null;
      @*/
    public ObjetZ getObjetNom1(String nom){
        if(nom == null){
            throw new NullPointerException("Le nom passé en paramètre ne doit pas être null");
        }
        
        for(int i = 0; i < getNbObjets1(); i++){
            if(getObjets1(i).getNom().equals(nom)){
                return getObjets1(i);
            }
        }
        return null;
    }
    
    
    /**
     * Methode permettant de renvoyer un objet en fonction de son nom, ici getObjetNom2() permet de renvoyer
     * une instance de Pnj en fonction de son nom avec l'appel a getNom()
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom Le nom dont on veut tester l'egalite ou non 
     *
     * @return l'instance de Pnj qui a le nom passe en parametre ou null
     */
    /*@
      @ requires nom != null;
      @*/
    public Pnj getObjetNom2(String nom){
        if(nom == null){
            throw new NullPointerException("Le nom passé en paramètre ne doit pas être null");
        }
        
        for(int i = 0; i < getNbObjets2(); i++){
            if(getObjets2(i).getNom().equals(nom)){
                return getObjets2(i);
            }
        }
        return null;
    }
    
    
    
    /**
    * Redefinition de equals() pour la class Piece
    *
    * @return true si tous les attributs de class appelantes sont egaux aux attributs de l'instance passee en parametre
    */
    /*@
      @ requires o != null;
      @
      @ pure
      @*/
    @Override
    public boolean equals(Object o){
        if(o instanceof Piece){
            
            Piece piece = (Piece)o;
            return this.description.equals(piece.description) && this.sorties.equals(piece.sorties) && this.getArrayL1().equals(piece.getArrayL1()) && this.getArrayL2().equals(piece.getArrayL2());
        }
        return false;
    }
    
    
    /**
    * Redefinition de hashCode() pour la class Piece
    *
    * @return le hashCode de l'instance de class Piece appelante
    */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        return 2 * this.description.hashCode() + super.hashCode();
    }
    
    
    /**
    * Redefinition de toString() pour la class Piece
    *
    * @return un objet String permettant de donner des indications sur l'instance de class Piece appelante
    */
    /*@
      @ ensures \result != null; 
      @
      @ pure
      @*/
    @Override
    public String toString(){
        return "Cette pièce appelée " + this.description + " contient " + this.getNbObjets1() + " objets et " + getNbObjets2() + " pnj.";
    }
}
    

