import java.util.List;
import java.util.ArrayList;


/**
 *  Classe qui implemente l'interface Conteneur, la genericite est utilisee dans cette classe
 *  La classe est abstraite car certaines fonctions devront etre implementees dans les classes heritants de celle-ci
 *  
 *  L'unique attribut de la classe est une List qui va permettre de contenir tout type d'objets comme des Pieces,
 *  des ObjetZ ou encore des Joueur.
 *
 * @author Thomas Ciman
 * @since  December 2018
 * @version 2.0
 */
public abstract class ArrayListConteneur<E> implements Conteneur<E>{
    
    /*@
      @ invariant getArray() != null;
      @*/
    
    
    // List d'Objets de type indefini
    protected ArrayList<E> Objets;
    
    
    
    /**
     *  Initialise l'attribut de class comme une ArrayList E vide
     */
    /*@
      @ ensures getNbObjets() == 0;
      @ ensures Objets != null;
      @*/
    public ArrayListConteneur(){
        this.Objets = new ArrayList<E>();
    }
    
    
    
    /**
     *  Constructeur de la classe abstraite ArrayListConteneur, avec une List en parametre
     *  Objets va etre initialise en tant que ArrayList puis on va inserer tous les elements de 
     *  l'ArrayList passee en parametre tout en verifiant qu'aucun d'entre eux n'est null
     *
     *  @throws NullPointerException si L'ArrayList 'lesObjets' passee en parametre est null
     *  @throws NullPointerException si Un des objets de l'ArrayList est null
     * 
     *  @param lesObjets ArrayList dont chaque objet va etre ajouter a l'attribut de class Objets
     */
    /*@
      @ requires lesObjets != null;
      @ requires (\forall int i; 0 <= i && i < lesObjets.size(); lesObjets.get(i) != null);
      @ 
      @ ensures getArray() != null;
      @ ensures getNbObjets() == lesObjets.size();
      @*/
    public ArrayListConteneur(ArrayList<E> lesObjets){
        if(lesObjets == null){
            throw new NullPointerException("L'ArrayList \"lesObjets\" ne doit pas être null");
        }
        this.Objets = new ArrayList<E>();
        
        for(E o : lesObjets){
            if(o == null){
                throw new NullPointerException("Un objet de l'ArrayList passée en paramètre est null");
            }
            this.Objets.add((E)o);
        }
    }
    
    
    
    /**
     * Renvoie l'objet contenu a l'indice index dans l'ArrayList Objets
     *
     * @throws IllegalArgumentException si l'index passe en parametre est inferieur a 0
     *
     * @param index L'indice de l'ArrayList ou se trouve l'objet que l'on veut renvoyer
     * @return l'objet contenue a l'indice index
     *
     */
	/*@
	  @ requires index >= 0;
      @
      @ ensures (\result != null);
      @
	  @ pure
	  @*/
    public E getObjets(int index){
        if(index < 0){
            throw new IllegalArgumentException("l'index doit être supérieur à 0");
        }
        return this.Objets.get(index);
    }    
    
    
    
     /**
     * Permet de connaitre le nombre d'occurence d'un objet de type E dans la List Objets
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet indique l'instance de la classe de type E dont on veut savoir le nombre d'exemplaire dans la List Objets
     * @return Renvoie un entier indiquant le nombre d'occurrences equivalent a l'objet de type E passe en parametre
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures \result >= 0;
      @ ensures !contient(objet) ==> \result == 0;
      @ ensure contient(objet) ==> \result > 0; 
      @ ensures \result == (\num_of int i; (i>=0) && (i < getNbObjets())); Objets.get(i).equals(objet);
      @
      @ pure
      @*/
    public int contientCombienDe(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet doit être non null");
        }
        // compteur
        int compteur = 0;
        
        for(E oz: this.Objets){
            if(objet.equals(oz)){
                compteur++;
            }
        }
        return compteur;
    }
    
    
    /**
     * Methode permettant de renvoyer un objet en fonction de son nom, comme l'attribut nom dans ObjetZ 
     * ou l'attribut description dans Piece
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom Le nom dont on veut tester l'egalite ou non 
     *
     * @return l'objet qui a le nom passe en parametre ou null
     */
    /*@
      @ requires nom != null;
      @*/
    public abstract E getObjetNom(String nom);

    
    
     /**
     * Renvoie une boolean indique si l'objet de type E passe en parametre est
     * contenu dans la List Objets
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     * @param  objet   l'objet dont on veut connaitre l'existence ou non dans la List Objets 
     * @return renvoie true si l'objet est dans la List, false sinon       
     *
     */
    /*@
      @ requires objet != null;
      @
      @ ensures \result ==> contientCombienDe(objet) > 0; 
      @ ensures !\result ==> contientCombienDe(objet) == 0;
      @ ensures \result ==> getNbObjets() > 0;
      @
      @ pure
      @*/   
    public boolean contient(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre doit être non null");
        }
        return this.Objets.contains(objet);
    }
    
    
    
    /**
     * Retire un objet de l'ArrayList Objets.
     * La fonction renvoie true si l'objet a ete retirer, elle renvoie false sinon
     *
     * @param objet       Specifie l'objet qui doit etre retire de l'ArrayList Objets
     * @return             true si l'objet a ete retire, false sinon
     *
     * @throws NullPointerException Si objet passe en parametre est null
     *
     */
    /*@
      @ requires objet != null;
      @
      @ ensures contient(objet) ==> contientCombienDe(objet) == \old(contientCombienDe(objet)) - 1;
      @ ensures contient(objet) ==> getNbObjets() == \old(getNbObjets()) - 1;
      @ ensures !contient(objet) ==> contientCombienDe(objet) == \old(contientCombienDe(objet));
      @ ensures !contient(objet) ==> getNbObjet() == \old(getNbObjets());
      @*/
    public boolean retirer(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre doit être non null");
        }
        return this.Objets.remove(objet);
    }

    
    
    /**
     * Ajoute un objet a l'ArrayList Objets contenant tous les objets de type E. 
     * La fonction ne renvoie rien
     *   
     *  
     * @param objet est un objet qu'on ajoute a l'ArrayList Objets
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures getNbObjets() == \old(getNbObjets()) + 1;
      @ ensures contient(objet) == true;
      @ ensures contientCombienDe(objet) == \old(contientCombienDe(objet)) + 1;
      @*/
    public void ajouter(E objet){
        if(objet == null){
            throw new NullPointerException("Le nom ne doit pas être null");
        }
        this.Objets.add(objet);
    }
    
    
    
    /**
     * Renvoie le nombre d'objets de type E contenus dans l'attribut de classe Objets de type ArrayList 
     *
     * @return Le nombre d'objets dans l'ArrayList Objets     
     *
     */
    /*@
      @ pure
      @
      @ ensures (\result >= 0);
      @*/
    public int getNbObjets(){
        return Objets.size();
    }
    
    
    /**
     * Methode permettant de renvoyer l'attribut de class Objet, renvoie l'ArrayList de type E
     *
     * @return renvoie l'ArrayList correspondant a l'attribut Objets
     */
    /*@
      @ ensures (\result != null);
      @
      @ pure
      @*/
    public ArrayList<E> getArrayL(){
        ArrayList<E> array = new ArrayList<E>();
        for(int i = 0; i < this.Objets.size(); i++){
            array.add((E)this.Objets.get(i));
        }
        return array;
    }
    
    
    /**
     * Fonction permettant de savoir si un objet peut etre ajoute au conteneur, la principale raison 
     * pouvant empecher cela est que le joueur risque de transporter un poids trop important une fois l'objet en
     * sa possession. L'implementation n'est pas faite ici car il n'y a pas seulement la classe Joueur qui va
     * heriter de cette classe abstraite.
     *
     *
     * @param objet l'objet qu'on veut tenter d'ajouter a l'ArrayList
     *
     * @return true si l'objet peut etre ajoute, false sinon
     */
    /*@
      @ requires objet != null;
      @ pure
      @*/
    public abstract boolean ajoutPossible(E objet);
    
    
    
    /**
     * Methode equals pour la class ArrayListConteneur
     * Cette methode test si l'ArrayList Objets de l'instance appelante est la meme que celle de l'instance 
     * passe en parametre
     *
     * @throws NullPointerException Si l'array passe en parametre est null
     *
     * @return true si l'ArrayList de l'instance appelante est egale en tout objet qu'elle contient a celle de array passe en parametre
     */
    /*@
      @ requires array != null;
      @
      @ pure
      @*/
    @Override
    public boolean equals(Object array){
        if(array == null){
            throw new NullPointerException("L'array passée en paramètre ne doit pas être null");
        }
        if(array instanceof ArrayListConteneur){
            ArrayListConteneur<E> array1 = (ArrayListConteneur<E>) array;
            return this.Objets.equals(array1.getArrayL());
        }
        return false;
    }
    
    
    
    /**
     * Methode hashCode pour la class ArrayListConteneur
     *
     * @return le hashCode de l'instance
     */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        return this.Objets.hashCode();
    }
    
    
}