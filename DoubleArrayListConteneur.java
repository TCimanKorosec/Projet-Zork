import java.util.List;
import java.util.ArrayList;

/**
 * La class DoubleArrayListConteneur contient deux ArrayList Objets1 et Objets2.
 * la genericite est utilisee dans cette classe
 * La classe est abstraite car certaines fonctions devront etre implementees dans les classes heritants de celle-ci
 *  
 * La class contient deux ArrayList Objets1 et Objets2 permettant de contenir des instances des types d'objets
 * E et F, Objets1 contiendra les instances du type E et Objets2 contiendra les instances du type F.
 * Toutes les fonctions finissant par 1 modifient ou traitent de l'AL Objets1 et les fonctions terminant par 2
 * traitent et modifient l'AL Objets2.
 *
 * Cette class est utile car dans le cas de class qui ont besoins de contenir deux types d'objets differents
 *
 * @author Thomas Ciman
 * @since  December 2018
 * @version 2.0
 *
 */
public abstract class DoubleArrayListConteneur<E, F>{
    
    /*@
      @ invariant getArray1() != null;
      @ invariant getArray2() != null;
      @*/
    
    
    // ArrayList d'instances de type E
    private ArrayList<E> Objets1;
    
    // ArrayList d'instance de type F
    private ArrayList<F> Objets2;
     
    
    /**
     *  Initialise les deux attributs de class de type List avec des ArrayList vide
     */
    /*@
      @ ensures getArray1() != null;
      @ ensures getArray2() != null;
      @*/
    public DoubleArrayListConteneur(){
        this.Objets1 = new ArrayList<E>();
        this.Objets2 = new ArrayList<F>();
    }
    
    
    
    /**
     * Constructeur de la classe abstraite ArrayListConteneur, avec une List en parametre
     * Objets va etre initialise en tant que ArrayList puis on va inserer tous les elements de 
     * l'ArrayList passee en parametre tout en verifiant qu'aucun d'entre eux n'est null
     *
     * @throws NullPointerException si L'ArrayList 'lesObjets1' passee en parametre est null
     * @throws NullPointerException si L'ArrayList 'lesObjets2' passee en parametre est null
     * @throws NullPointerException si Un des objets de l'ArrayList Objets1 est null
     * @throws NullPointerException si un des objets de l'ArrayList Objets2 est null
     * 
     * @param lesObjets1 ArrayList d'objets de type E qui seront un a un ajouter a l'attribut de class Objets1
     * @param lesObjets2 ArrayList d'objets de type F qui seront un a un ajouter a l'attribut de class Objets2
     */
    /*@
      @ requires lesObjets2 != null;
      @ requires lesObjets1 != null;
      @ requires (\forall int i; (0 <= i) && (i < lesObjets2.size()); lesObjets2.get(i) != null);
      @ requires (\forall int i; (0 <= i) && (i < lesObjets1.size()); lesObjets1.get(i) != null);
      @
      @ ensures getArrayL1() != null;
      @ ensures getArrayL2() != null;
      @ ensures (\forall int i; (0 <= i) && (i < getNbObjets1()); getObjets1(i) != null);
      @ ensures (\forall int i; (0 <= i) && (i < getNbObjets2()); getObjets2(i) != null);
      @
      @*/
    public DoubleArrayListConteneur(ArrayList<E> lesObjets1, ArrayList<F> lesObjets2){
        if(lesObjets1 == null){
            throw new NullPointerException("L'ArrayList \"lesObjets1\" ne doit pas être null");
        }
        if(lesObjets2 == null){
            throw new NullPointerException("L'ArrayList \"les Objets2\" ne doit pas être null");
        }
        this.Objets1 = new ArrayList<E>();
        this.Objets2 = new ArrayList<F>();
        
        for(E o : lesObjets1){
            if(o == null){
                throw new NullPointerException("Un objet de l'ArrayList lesObjets1 passée en paramètre est null");
            }
            this.Objets1.add((E)o);
        }

        for(F o : lesObjets2){
            if(o == null){
                throw new NullPointerException("Un objet de l'ArrayList lesObjets2 passée en paramètre est null");
            }
            this.Objets2.add((F)o);
        }
    }
    
    
    
    /**
     * Renvoie l'objet contenu a l'indice index dans l'ArrayList Objets1
     *
     * @throws IllegalArgumentException si l'index passe en parametre est inferieur a 0
     *
     * @param index L'indice de l'ArrayList ou se trouve l'objet que l'on veut renvoyer
     *
     * @return l'objet contenue a l'indice index
     */
	/*@
	  @ requires index >= 0;
      @
      @ ensures (\result != null);
      @
	  @ pure
	  @*/
    public E getObjets1(int index){
        if(index < 0){
            throw new IllegalArgumentException("l'index doit être supérieur à 0");
        }
        return this.Objets1.get(index);
    }    
    
    
    /**
     * Renvoie l'objet contenu a l'indice index dans l'ArrayList Objets2
     *
     * @throws IllegalArgumentException si l'index passe en parametre est inferieur a 0
     *
     * @param index L'indice de l'ArrayList ou se trouve l'objet que l'on veut renvoyer
     *
     * @return l'objet contenue a l'indice index
     */
	/*@
	  @ requires index >= 0;
      @
      @ ensures (\result != null);
      @
	  @ pure
	  @*/
    public F getObjets2(int index){
        if(index < 0){
            throw new IllegalArgumentException("l'index doit être supérieur à 0");
        }
        return this.Objets2.get(index);
    } 
    
    
     /**
     * Permet de connaitre le nombre d'occurence d'un objet de type E dans la List Objets1
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet indique l'instance de la classe de type E dont on veut savoir le nombre d'exemplaire dans l'ArryList Objets1
     *
     * @return Renvoie un entier indiquant le nombre d'occurrences equivalent a l'objet de type E passe en parametre
     * 
     */
    /*@
      @ requires objet != null;
      @ requires (\forall int i; (0 <= i) && (i < getNbObjets1()); getObjets1(i) != null);
      @ 
      @ ensures \result >= 0;
      @ ensures !contient1(objet) ==> (\result == 0);
      @ ensure contient1(objet) ==> (\result > 0); 
      @ ensures \result == (\num_of int i; (i <= 0) && (i < getNbObjets1())); Objets1.get(i).equals(objet);
      @
      @ pure
      @*/
    public int contientCombienDe1(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet doit être non null");
        }
        // compteur
        int compteur = 0;
        for(E oz: this.Objets1){
            if(objet.equals(oz)){
                compteur++;
            }
        }
        return compteur;
    }
    

     /**
     * Permet de connaitre le nombre d'occurence d'un objet de type F dans la List Objets2
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet indique l'instance de la classe de type F dont on veut savoir le nombre d'exemplaire dans l'ArrayList Objets2
     *
     * @return Renvoie un entier indiquant le nombre d'occurrences equivalent a l'objet de type F passe en parametre
     */
    /*@
      @ requires objet != null;
      @ requires \forall int i; (0 <= i) && (i < getNbObjets2()); getObjets2(i) != null;
      @
      @ ensures \result >= 0;
      @ ensures !contient2(objet) ==> \result == 0;
      @ ensure contient2(objet) ==> \result > 0; 
      @ ensures \result == (\num_of int i; (i <= 0) && (i < getNbObjets2())); Objets2.get(i).equals(objet);
      @
      @ pure
      @*/
    public int contientCombienDe2(F objet){
        if(objet == null){
            throw new NullPointerException("L'objet doit être non null");
        }
        
        // compteur
        int compteur = 0;
        for(F oz: this.Objets2){
            if(objet.equals(oz)){
                compteur++;
            }
        }
        return compteur;
    }

    
    /**
     * Methode permettant de renvoyer un objet de Objets1 en fonction de son nom, comme l'attribut nom dans
     * ObjetZ ou l'attribut description dans Piece
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
    public abstract E getObjetNom1(String nom);

    
    /**
     * Methode permettant de renvoyer un objet de Objet2 en fonction de son nom, comme l'attribut nom dans 
     * ObjetZ ou l'attribut description dans Piece
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
    public abstract F getObjetNom2(String nom);
    
    
     /**
     * Renvoie une boolean indique si l'objet de type E passe en parametre est
     * contenu dans l'ArrayList Objets1
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     * @param  objet   l'objet dont on veut connaitre l'existence ou non dans la List Objets1 
     * @return renvoie true si l'objet est dans la List, false sinon       
     *
     */
    /*@
      @ requires objet != null;
      @
      @ ensures \result ==> contientCombienDe1(objet) > 0; 
      @ ensures !\result ==> contientCombienDe1(objet) == 0;
      @ ensures \result ==> getNbObjets1() > 0;
      @
      @ pure
      @*/   
    public boolean contient1(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre doit être non null");
        }
        return this.Objets1.contains(objet);
    }
    
    
     /**
     * Renvoie une boolean indique si l'objet de type F passe en parametre est
     * contenu dans l'ArrayList Objets2
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     * @param  objet   l'objet dont on veut connaitre l'existence ou non dans la List Objets2 
     * @return renvoie true si l'objet est dans la List, false sinon       
     */
    /*@
      @ requires objet != null;
      @
      @ ensures \result ==> contientCombienDe2(objet) > 0; 
      @ ensures !\result ==> contientCombienDe2(objet) == 0;
      @ ensures \result ==> getNbObjets2() > 0;
      @
      @ pure
      @*/   
    public boolean contient2(F objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre doit être non null");
        }
        return this.Objets2.contains(objet);
    }
    
    
    
    /**
     * Retire un objet de l'ArrayList Objets1 de type generique E.
     * La fonction renvoie true si l'objet a ete retirer, elle renvoie false sinon
     *
     * @param objet       Specifie l'objet qui doit etre retire de l'ArrayList Objets
     * @return             true si l'objet a ete retire, false sinon
     *
     * @throws NullPointerException Si objet passe en parametre est null
     */
    /*@
      @ requires objet != null;
      @
      @ ensures contient1(objet) ==> contientCombienDe1(objet) == \old(contientCombienDe1(objet)) - 1;
      @ ensures contient1(objet) ==> getNbObjets1() == \old(getNbObjets1()) - 1;
      @ ensures !contient1(objet) ==> getNbObjets1() == \old(getNbObjets1());
      @ ensures !contient1(objet) ==> contientCombienDe1(objet) == \old(contientCombienDe1(objet));
      @
      @*/
    public boolean retirer1(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre doit être non null");
        }
        return this.Objets1.remove(objet);
    }

    
    /**
     * Retire un objet de l'ArrayList Objets2 de type generique F.
     * La fonction renvoie true si l'objet a ete retirer, elle renvoie false sinon
     *
     * @param objet       Specifie l'objet qui doit etre retire de l'ArrayList Objets2
     * @return             true si l'objet a ete retire, false sinon
     *
     * @throws NullPointerException Si objet passe en parametre est null
     */
    /*@
      @ requires objet != null;
      @
      @ ensures contient2(objet) ==> contientCombienDe2(objet) == \old(contientCombienDe2(objet)) - 1;
      @ ensures contient2(objet) ==> getNbObjets2() == \old(getNbObjets2()) - 1;
      @ ensures !contient2(objet) ==> getNbObjets2() == \old(getNbObjets2());
      @ ensures !contient2(objet) ==> contientCombienDe2(objet) == \old(contientCombienDe2(objet));
      @
      @*/
    public boolean retirer2(F objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre doit être non null");
        }
        return this.Objets2.remove(objet);
    }
    
    
    /**
     * Ajoute un objet a l'ArrayList Objets1 contenant tous les objets de type E. 
     * La fonction ne renvoie rien
     *   
     *  
     * @param objet       est un objet qu'on ajoute a l'ArrayList Objets1
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures getNbObjets1() == \old(getNbObjets1()) + 1;
      @ ensures contient1(objet);
      @ ensures contientCombienDe1(objet) == \old(contientCombienDe1(objet)) + 1;
      @*/
    public void ajouter1(E objet){
        if(objet == null){
            throw new NullPointerException("Le nom ne doit pas être null");
        }
        this.Objets1.add(objet);
    }
    
    
    /**
     * Ajoute un objet a l'ArrayList Objets2 contenant tous les objets de type F. 
     * La fonction ne renvoie rien
     *   
     *  
     * @param objet       est un objet qu'on ajoute a l'ArrayList Objets2
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures getNbObjets2() == \old(getNbObjets2() + 1);
      @ ensures contient2(objet);
      @ ensures contientCombienDe2(objet) == \old(contientCombienDe2(objet)) + 1;
      @*/
    public void ajouter2(F objet){
        if(objet == null){
            throw new NullPointerException("Le nom ne doit pas être null");
        }
        this.Objets2.add(objet);
    }
    
    
    
    /**
     * Renvoie le nombre d'objets de type E contenus dans l'attribut de classe Objets1 de type ArrayList 
     *
     * @return Le nombre d'objets de l'attribut Objets1         
     */
    /*@
      @ pure
      @*/
    public int getNbObjets1(){
        return Objets1.size();
    }
    
    
    /**
     * Renvoie le nombre d'objets de type F contenus dans l'attribut de classe Objets2 de type ArrayList 
     *
     * @return Le nombre d'objets de l'attribut Objets2        
     *
     */
    /*@
      @ pure
      @*/
    public int getNbObjets2(){
        return Objets2.size();
    }
    
    
    /**
     * Methode permettant de renvoyer l'attribut de class Objets1, renvoie l'ArrayList de type E
     *
     * @return renvoie l'ArrayList correspondant a l'attribut Objets
     */
    /*@
      @ requires (\forall int i; (0 <= i) && (i < getNbObjets1()); getObjets1(i) != null);
      @
      @ pure
      @*/
    public ArrayList<E> getArrayL1(){
        ArrayList<E> array = new ArrayList<E>();
        for(int i = 0; i < this.Objets1.size(); i++){
            array.add((E)this.Objets1.get(i));
        }
        return array;
    }
    

    /**
     * Methode permettant de renvoyer l'attribut de class Objets2, renvoie l'ArrayList de type F
     *
     * @return renvoie l'ArrayList correspondant a l'attribut Objets
     */
    /*@
      @ requires (\forall int i; (0 <= i) && (i < getNbObjets2()); getObjets2(i) != null);
      @
      @ pure
      @*/
    public ArrayList<F> getArrayL2(){
        ArrayList<F> array = new ArrayList<F>();
        for(int i = 0; i < this.Objets2.size(); i++){
            array.add((F)this.Objets2.get(i));
        }
        return array;
    }
    
    
    /**
     * Fonction permettant de savoir si un objet peut etre ajoute a l'ArrayList de type E, la principale raison 
     * pouvant empecher cela est que le joueur risque de transporter un poids trop important une fois l'objet en
     * sa possession. L'implementation n'est pas faite ici car il n'y a pas seulement la classe Joueur qui va
     * heriter de cette classe abstraite.
     *
     *
     * @param objet l'objet qu'on veut tenter d'ajouter a l'ArrayList
     * @return true si l'objet peut etre ajoute, false sinon
     *
     */
    /*@
      @ pure
      @*/
    public abstract boolean ajoutPossible1(E objet);
    

    /**
     * Fonction permettant de savoir si un objet peut etre ajoute a l'ArrayList de type F, la principale raison 
     * pouvant empecher cela est que le joueur risque de transporter un poids trop important une fois l'objet en
     * sa possession. L'implementation n'est pas faite ici car il n'y a pas seulement la classe Joueur qui va
     * heriter de cette classe abstraite.
     *
     *
     * @param objet l'objet qu'on veut tenter d'ajouter a l'ArrayList
     * @return true si l'objet peut etre ajoute, false sinon
     *
     */
    /*@
      @ pure
      @*/
    public abstract boolean ajoutPossible2(F objet);

    
    /**
    * Redefinition de equals pour DoubleArrayListConteneur
    *
    * @throws NullPointerException Si l'instance passee en parametre est null
    * @return true si l'instance appelante est egale en tout points a l'instance passee en parametre
    */
    /*@
      @ pure
      @*/
    @Override
    public boolean equals(Object array){
        if(array == null){
            throw new NullPointerException("l'Object passé en paramètre ne doit pas être null");
        }
        if(array instanceof DoubleArrayListConteneur){
            DoubleArrayListConteneur<E, F> array1 = (DoubleArrayListConteneur<E, F>) array;
            return this.Objets1.equals(array1.getArrayL1()) && this.Objets2.equals(array1.getArrayL2());
        }
        return false;
    }
    
    
    /**
    * Redefinition de hashCode() pour la class DoubleArryListConteneur
    *
    * @return le hashCode de l'instance appelante
    */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        return this.Objets1.hashCode() + this.Objets2.hashCode();
    }
    
    
}