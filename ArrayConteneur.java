import java.util.ArrayList;

/**
 * Classe qui implemente l'interface Conteneur, la genericite est utilisee dans cette classe
 * La classe est abstraite car certaines fonctions devront etre implementees dans les classes heritants de celle-ci
 * comme getObjetNom()   
 *
 * Cette classe contient deux attributs, un tableau de type E appele Objet qui va permettre de contenir 
 * tout type d'objets comme des Pieces, Joueurs, ou encore des ObjetZ.
 * L'attribut nbObjets quant a lui permet de connaitre le nombre d'objetZ du tableau
 *
 * @author Thomas Ciman
 * @since  December 2018
 * @version 2.0
 *
 */
public abstract class ArrayConteneur<E> implements Conteneur<E>{
    
    /*@
      @ invariant getNbObjets() >= 0; 
      @ invariant getArrayL() != null;
      @*/
    
    // Le tableau des Objets
    private E Objets[];
    // Le nombre d'Objets
    private int nbObjets;
    
    
    /**
     * Constructeur de ArrayConteneur sans parametre. Initialise un nouveau tableau generique vide 
     * et set le nombre d'objets qu'il contient.
     */
    /*@
      @ ensures getArrayL() != null;
      @ ensures getNbObjets() == 0; 
      @*/
    public ArrayConteneur(){
        this.nbObjets = 0;
        this.Objets = (E[]) (new Object[20]);
    }
    

    /**
     * Constructeur prenant un tableau ainsi qu'un nombre d'objet en parametre. Le constructeur 
     * initialise le tableau ainsi que nbObjets qui indique le nombre d'objets contenus dans le tableau.
     * Lever d'exception si le parametre nbObjets est illegal ou si le tableau et null ou si pour chaque objet 
     * du tableau, un objet est null
     *
     * @throws NullPointerException Si le tableau passe en parametre est null
     * @throws NullPointerException Si pour tout objet du tableau, un est null
     * @throws IllegalArgumentException Si nbObjets est inferieur a 0
     *
     * @param nbObjets Le nombre d'objets contenus dans le tableau tab
     * @param tab Le tableau contenant les objets que l'on va ajouter a Objets, l'attribut de classe
     *
     */
    /*@
      @ requires tab != null;
      @ requires nbObjets >= 0;
      @ requires (\forall int i; (0 <= i) && (i < tab.length()); tab[i] != null);
      @
      @ ensures getArrayL() != null;
      @ ensures (\forall int i; (0 <= i) && (i < getNbObjets(); getObjets(i) != null);
      @ ensures getNbObjets() >= 0;
      @*/
    public ArrayConteneur(int nbObjets, E tab[]){
        if(tab == null){
            throw new NullPointerException("Le tableau passé en paramètre ne doit pas être null");
        }
        if(nbObjets < 0){
            throw new IllegalArgumentException("nbObjets représentant le nombre d'objets du tableau ne doit pas être null");
        }
        for(int i = 0; i < nbObjets; i++){
            if(tab[i] == null){
                throw new NullPointerException("Un objet de tableau est null or aucun ne doit l'être");
            }
        }
        this.nbObjets = nbObjets;
        for(int i = 0; i < nbObjets; i++){
            this.Objets[i] = (E)tab[i];
        }
    }
    
    
    /**
     * Constructeur de la classe ArrayConteneur a partir d'une ArryList, on recupere les elements 
     * de la list et set le nombre d'objet du tableau au nombre d'objets de la ArryList ,
     * l'ArryListe ne doit pas etre null.
     * 
     * @param liste List d'objets que l'on va mettre dans le tableau
     * 
     * @throws NullPointerException Si la liste passe en parametre est null
     */
    /*@
      @ requires liste != null;
      @ requires (\forall int i; (0 <= i) && (i < liste.size()); liste.get(i) != null); 
      @ 
      @ ensures getArrayL() != null;
      @ ensures (\forall int i; (0 <= i) && (i < getNbObjets(); getObjets(i) != null);
      @ ensures getNbObjets() >= 0;
      @*/
    public ArrayConteneur(ArrayList<E> liste){
        if(liste == null){
            throw new NullPointerException("La liste passée en paramètre ne doit pas être null");
        }
        
        // compteur
        int i = 0;
        
        // initialisation du tableau
        this.Objets = (E[]) (new Object[20]);
        for(E o : liste){
            if(o == null){
                throw new NullPointerException("Aucun objet de doit être null");
            }
            this.Objets[i] = o;
            i++;
        }
        this.nbObjets = i;
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
     * Ajoute un objet au tableau Objets contenant tous les objets de type E. 
     * La fonction ne renvoie rien
     *  
     * @param objet est un objet qu'on ajoute a au tableau Objets
     *
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures (getNbObjets() == \old(getNbObjets()) + 1);
      @ ensures contient(objet);
      @ ensures contientCombienDe(objet) == \old(contientCombienDe(objet)) + 1;
      @ ensures this.Objets.length >= getNbObjets();
      @*/
    public void ajouter(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre ne doit pas être null");
        }
        
        // Si le tableau est plein
        if(this.Objets.length == this.nbObjets){
            int nombre = this.Objets.length;
            nombre*=2;
            // On double la taille du tableau
            E Obj[] = (E[]) (new Object[nombre]);
            
            for(int i = 0; i < this.nbObjets; i++){
                Obj[i] = this.Objets[i];
            }
            this.Objets = Obj;
        }
        this.Objets[nbObjets] = objet;
        this.nbObjets+=1;
    }
    
    
    /**
     * Retire un objet de l'Array Objets.
     * La fonction renvoie true si l'objet a ete retirer, elle renvoie false sinon
     *
     * @param objet       Specifie l'objet qui doit etre retire du tableau Objets
     * @return             true si l'objet a ete retire, false sinon
     *
     * @throws NullPointerException Si objet passe en parametre est null
     *
     */
    /*@
      @ requires objet != null;
      @
      @ ensures contient(objet) ==> (contientCombienDe(objet) == \old(contientCombienDe(objet)) - 1);
      @ ensures contient(objet) ==> (getNbObjets() == \old(getNbObjets()) - 1);
      @*/
    public boolean retirer(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre ne doit pas être null");
        }
        if(nbObjets == 0){
            return false;    
        }
        if(!(contient(objet))){
            return false;
        }
        
        for(int i = 0; i < nbObjets;i++){
            if(Objets[i].equals(objet)){
                Objets[i] = Objets[nbObjets - 1];
                nbObjets--;
                return true;
            }
        }
        
        return false;
    }
    

     /**
     * Permet de connaitre le nombre d'occurence d'un objet de type E dans le tableau Objets
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param objet indique l'instance de la classe de type E dont on veut savoir le nombre d'exemplaire dans le tableau Objets
     *
     * @return Renvoie un entier indiquant le nombre d'occurrences equivalent a l'objet de type E passe en parametre
     * 
     */
    /*@
      @ requires objet != null;
      @ 
      @ ensures \result >= 0;
      @ ensures !contient(objet) ==> \result == 0;
      @ ensure contient(objet) ==> \result > 0; 
      @ ensures \result == (\num_of int i; (i<=0) && (i < getNbObjets())); Objets.get(i).equals(objet);
      @
      @ pure
      @*/
    public int contientCombienDe(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre ne doit pas être null");
        }
        int compteur = 0;
        
        for(int i = 0; i < this.nbObjets; i++){
            if(this.Objets[i].equals(objet)){
                compteur++;
            }
        }
        return compteur;
    }
    

     /**
     * Renvoie une boolean indique si l'objet de type E passe en parametre est
     * contenu dans la List Objets
     * 
     * @throws NullPointerException Si l'objet passe en parametre est null
     *
     * @param  objet   l'objet dont on veut connaitre l'existence ou non dans la List Objets
     *
     * @return renvoie true si l'objet est dans la List, false sinon       
     *
     */
    /*@
      @ requires objet != null;
      @
      @ ensures \result ==> contientCombienDe(objet) > 0; 
      @ ensures !\result ==> contientCombienDe(objet) == 0;
      @
      @ pure
      @*/  
    public boolean contient(E objet){
        if(objet == null){
            throw new NullPointerException("L'objet passé en paramètre ne doit pas être null");
        }
        for(int i = 0; i < this.nbObjets; i++){
            if(this.Objets[i].equals(objet)){
                return true;
            }
        }
        return false;
    }
    


    /**
     * Renvoie le nombre d'objets de type E contenus dans l'attribut de classe Objets qui est un tableau 
     *
     * @return Le nombre d'objets dans la piece          
     */
    /*@
      @ pure 
      @*/
    public int getNbObjets(){
        return this.nbObjets;
    }
    
    

    /**
     * Renvoie l'objet contenu a l'indice index dans l'Array Objets
     *
     * @throws IllegalArgumentException si l'index passe en parametre est inferieur a 0
     *
     * @param  index    L'indice de l'Array ou se trouve l'objet que l'on veut renvoyer
     * @return          l'objet contenue a l'indice index
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
        if(index < 0 || index > nbObjets){
            throw new NullPointerException("L'index passé en paramètre doit impérativement être supérieur ou égal à 0");
        }
        return (E)this.Objets[index];
    }
    

    /**
     * Methode permettant de renvoyer l'attribut de class Objet sous forme d'ArrayList de type E
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
        
        for(int i = 0; i < getNbObjets(); i++){
            array.add((E)this.Objets[i]);
        }
        
        return array;
    }
    
    
    /**
     * Fonction permettant de savoir si un objet peut etre ajoute au tableau, la principale raison 
     * pouvant empecher cela est que le joueur risque de transporter un poids trop important une fois l'objet en
     * sa possession. L'implementation n'est pas faite ici car il n'y a pas seulement la classe Joueur qui va
     * heriter de cette classe abstraite.
     *
     *
     * @param objet l'objet qu'on veut tenter d'ajouter a l'Array
     *
     * @return true si l'objet peut etre ajoute, false sinon
     */
    public abstract boolean ajoutPossible(E objet);
    
    
    /**
    * Redefinition de equals pour la class ArrayConteneur
    *
    * @throws NullPointerException Si le parametre array de type Object passe en parametre est null
    * @param array l'instance dont on veut tester l'egalite en tout points avec l'instance appelante
    * @return un boolean indique si l'instance appelante a tout ses attributs de class egaux au param array
    */
    /*@
      @ requires array != null;
      @ 
      @ pure 
      @*/
    @Override
    public boolean equals(Object array){
        if(array == null){
            throw new NullPointerException("l'Object passé en paramètre ne doit pas être null");
        }
        if(array instanceof ArrayConteneur){
            ArrayConteneur<E> arrayFinal = (ArrayConteneur<E>) array;
            boolean succes = true;
            
            for(int i = 0; i < this.nbObjets; i++){
                if(!(this.Objets[i].equals(arrayFinal.getObjets(i)))){
                    succes = false;
                }
            }
            return arrayFinal.getNbObjets() == this.nbObjets && succes;
        }
        return false;
    }
    
    
    /**
    * Redefinition de hashCode() pour la class ArrayConteneur 
    *
    * @return le hashCode de l'instance de ArrayConteneur
    */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        return this.nbObjets * 5 + this.Objets.hashCode();
    }
    
    
    /**
    * Redefinition de toString() pour la class ArrayConteneur
    *
    * @return un objet String decrivant l'instance d'ArrayConteneur
    */
    /*@
      @ ensures (\result != null);
      @ pure
      @*/
    @Override
    public String toString(){
        return "Cette instance d'ArrayConteneur contient " + this.nbObjets + "objets.";
    }
    
}