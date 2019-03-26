/**
* La class Marchand herite de la class Pnj car un Marchand est un personnage non jouable, le Marchand contient
* tous les attributs de la class Pnj, c'est a dire une phrase typique, un nom, une ArrayList d'ObjetZ, ainsi qu'une
* valeur de mobilite, mais le Marchand contient egalement une valeur de disponibilite qui permet de pouvoir 
* echanger ou non avec lui
*
*
* @author Thomas Ciman
* @since January 2019
* @version 2.0
*/ 
public class Marchand extends Pnj{
    
    /*@
      @ invariant getNom() != null;
      @ invariant getPhrase() != null;
      @ invariant getDispo() >= 0;
      @*/
    
    // Disponibilite du marchand
    private int dispo;
    
    
    
    /**
     * Constructeur de marchand avec un nom qui sera attribue grace a super() car un Marchand est un Pnj
     * 
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom le nom du marchand
     */
    /*@
      @ requires nom != null;
      @
      @ ensures getDispo() == 0;
      @ ensures getMobile() == true;
      @*/
    public Marchand(String nom){
        super(nom, true, "je suis un marchand");
        this.dispo = 0;
    }
    
    
    
    /**
     * Constructeur le plus utiliser qui initialiser une marchand avec une disponibilite, qui peut varier,
     * une phrase et une valeur de mobilite
     * 
     *
     * @throws NullPointerException Si la phrase passe en parametre est null
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom    indique le nom du marchand
     * @param mobile indique la mobilite du marchand, true ou false
     * @param phrase indique la phrase du marchand
     */
    /*@
      @ requires phrase != null;
      @ requires nom != null;
      @
      @ ensures getPhrase() != null;
      @ ensures getNom() != null;
      @ ensures getDispo() == 0;
      @*/
    public Marchand(String nom ,boolean mobile, String phrase){
        super(nom, mobile, phrase);
        if(phrase == null){
            throw new NullPointerException("La phrase passée en paramètre ne doit pas être null");
        }
        this.dispo = 0;
    }
    
    
    /**
     * Methode permettant de savoir la valeur de disponibilite du marchand, lorsque cette valeur depasse 3
     * le marchand n'est plus disponible et plus aucun echange n'est possible. 
     *
     * @return un int indiquant la valeur de disponibilite du marchand
     */
    /*@
      @ ensures (\result >= 0);
      @
      @ pure
      @*/
    public int getDispo(){
        return this.dispo;    
    }
    
    
    /**
     * Cette methode est un setter pour l'attribut de class dispo, elle permet notamment d'incrementer l'attribut
     * dispo indiquant si le marchand est disponible ou non.
     *
     * @throws IllegalArgumentException Si dispo est inferieur a 0
     *
     * @param dispo permet d'indiquer si le marchand est disponible ou non
     */
    /*@
      @ requires dispo >= 0;
      @
      @ ensures getDispo() >= 0;
      @*/
    public void setDispo(int dispo){
        if(dispo < 0){
            throw new IllegalArgumentException("dispo passé en paramètre doit être superieur ou egal à 0");
        }
        this.dispo = dispo;
    }
    
    
    /**
    * Methode permettant d'afficher tous les ObjetZ present dans l'inventaire du Marchand, ces objetZ 
    * pourront ensuite  etre acheter par l'utilisateur
    *
    */
    /*@
      @ pure
      @*/
    public void afficherCatalogue(){
        if(!(this.dispo >= 4)){
            if(this.getNbObjets() == 0){
                System.out.println("\n\"Je n'ai plus rien en stock....\"\n");
            }
            
            else{
                System.out.println("\n\"Oh Oh Oh, Voici ce que j'ai en stock\"\n");
                
                for(int i = 0; i < this.getNbObjets();i++){
                    System.out.println((i + 1) + " :  " + getObjets(i).getNom() + " : " + getObjets(i).toString() + "\n");
                }
            }
        }
        else{
            System.out.println("\nCe marchand n'est plus disponible\n");
        }
    }
    
    /**
    * Cette fonction permet de savoir si l'instance de l'objet passe en parametre est detenue par le marchand
    * la methode appelle contains sur l'ArrayList de la class mere.
    *
    * @throws NullPointerException Si l'instance passee en parametre est null
    *
    * @param objet l'objet dont on veut connaitre la presence ou non dans le conteneur
    *
    * @return true si objet passe en parametre est present dans le conteneur
    */
    /*@
      @ requires objet != null;
      @ 
      @ pure
      @*/
    public boolean detient(ObjetZ objet){
        if(objet == null){
            throw new NullPointerException("l'objet passé en paramètre ne doit pas être null");
        }
        return this.getArrayL().contains(objet);
    }
    
    
    /**
    * Redefinition de hashCode() pour la class Marchand
    *
    * @return le hashCode de l'instance appelante
    */
    /*@
      @ pure
      @*/
    @Override
    public int hashCode(){
        return super.hashCode() + this.dispo * 4;
    }
    
    
    /**
    * Redefinition de equals() pour la class Marchand
    *
    * @return true si l'instance appelante est egale dans tous ses attributs a l'instance passee en parametre
    */
    /*@
      @ pure
      @*/
    @Override
    public boolean equals(Object o){
        if(o == null){
            throw new NullPointerException("L'objet passé en paramètre est null");
        }
        if(o instanceof Marchand){
            Marchand marchand = (Marchand) o;
            
            return this.getMobile() == marchand.getMobile() && this.getNom().equals(marchand.getNom()) && this.getPhrase().equals(marchand.getPhrase()) && this.dispo == marchand.dispo && getArrayL().equals(marchand.getArrayL());
        }
        return false;
    }
    
    
    /**
    * Redefinition de toString() pour la class Marchand
    *
    * @return une objet String permettant de definir l'instance appelante
    */
    /*@
      @ pure
      @*/
    @Override
    public String toString(){
        if(getMobile() == true){
            return "Je suis un marchand nommé " + this.getNom() + " je suis mobile et ma phrase est " + this.getPhrase() + "." ;
        }
        else{
            return "Je suis un marchand nommé " + this.getNom() + " je suis immmobile et ma phrase est " + this.getPhrase() + ".";
        }
    }
}