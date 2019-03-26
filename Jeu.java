import java.util.HashMap;

/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure tres rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se deplacer parmi les différentes pieces.
 *  Ce jeu necessite vraiment d'etre enrichi pour devenir interessant!</p> <p>
 *
 *  Pour jouer a ce jeu, creer une instance de cette classe et appeler sa
 *  methode "jouer". </p> <p>
 *
 *  Cette classe cree et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, cree l'analyseur syntaxique et demarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @author     Thomas Ciman
 * @version    2.0
 * @since      March 2000
 */


public class Jeu extends ArrayConteneur<Piece>{
    /*@
      @ invariant getPieceCourante() != null;
      @ invariant getJoueurPrincipal() != null;
      @ invariant getPiecePrecedente() != null;
      @ invariant getAllocations() >= 0;
      @ invariant getAnalyseurSyntaxique() != null;
      @*/
    
    // L'analyseur synthaxique qui va permettre de prendre les commandes utilisateur
	private AnalyseurSyntaxique analyseurSyntaxique;
    
    // Indique la pièce dans laquelle est le joueur
    private Piece pieceCourante;
    
    // Indique le joueur jouant au jeu
    private Joueur joueurPrincipal;
    
    // Pour la fonction retour
    private Piece piecePrecedente;
    
    // Le nombre d'allocations effectue
    private int allocations;
   
    
	/**
	 * Cree le jeu et initialise la carte du jeu (i.e. les pieces).
	 */
    /*@
      @ ensures getAnalyseurSyntaxique() != null;
      @*/
	public Jeu() {
        // Appel a super() et création de l'ArrayList
        super();
        creerJeu();
		analyseurSyntaxique = new AnalyseurSyntaxique();
	}


	/**
	 * Cree toutes les pieces et relie leurs sorties les unes aux autres, la fonction creee egalement
	 * le joueur joue par l'utilisateur ainsi que l'ennemi
     *
     */
	/*@
// JE VIENS DE ME RENDRE COMPTE TROP TARD QUE LES ENSURES NE SONT PAS BONS ET QU'IL AURAIT FALLUT ECRIRE  
// getObjets(0).contient1(ZC1) à la place de centre.contient1(ZC1) 
      @ ensures centre != null;
      @ ensures classJoueur != null;
      @ ensures classPiece != null;
      @ ensures classJeu != null;
      @ ensures classCommande != null;
      @ ensures pointeur != null;
      @ ensures classMarchand != null;
      @ ensures classEnnemis != null;
      @
      @ ensures malloc != null;
      @ ensures malloc2 != null;
      @ ensures malloc3 != null;
      @ ensures malloc4 != null;
      @ ensures malloc5 != null;
      @ ensures malloc6 != null;
      @ ensures malloc7 != null;
      @ ensures malloc8 != null;
      @
      @ ensures lettre != null;
      @
      @ ensures ZC1 !=  null;
      @ ensures ZC2 != null;
      @ ensures ZC3 != null;
      @ ensures ZC4 != null;
      @ ensures ZC5 != null;
      @ ensures ZC6 != null;
      @ ensures ZC7 != null;
      @ ensures ZC8 != null;
      @ ensures ZC9 != null;
      @ ensures ZC10 != null;
      @
      @ ensures getArrayL().contains(centre);
      @ ensures getArrayL().contains(classJoueur);
      @ ensures getArrayL().contains(classPiece);
      @ ensures getArrayL().contains(classJeu);
      @ ensures getArrayL().contains(classCommande);
      @ ensures getArrayL().contains(pointeur);
      @ ensures getArrayL().contains(classMarchand);
      @ ensures getArrayL().contains(classEnnemis);
      @
      @ ensures indicLettre != null;
      @
      @ ensures centre.contient1(ZC1);
      @ ensures centre.contient1(ZC2);
      @ ensures classJoueur.contient1(ZC3);
      @ ensures classCommandes.contient1(ZC4);
      @ ensures classMarchand.contient1(ZC5);
      @ ensures classMarchand.contient1(ZC6);
      @ ensures classPiece.contient1(ZC7);
      @ ensures classPiece.contient1(ZC8);
      @ ensures classEnnemis.contient1(ZC9);
      @ ensures classCommande.contient1(ZC10);
      @
      @ ensures centre.contient2(Marchand);
      @ ensures classJoueur.contient2(Marchand1);
      @ ensures classPiece.contient2(Marchand2);
      @ ensures classCommande.contient2(Marchand3);
      @
      @ ensures getJoueurPrincipal() != null;
      @ ensures getPieceCourante() != null;
      @ ensures getPiecePrecedente() != null;
      @ ensures getAllocations() == 0;
      @*/
	public void creerJeu() {
		
		// Aucune allocation mémoire n'a ete effectue pour l'instant
		this.allocations = 0;
        
		// Creation du joueur
		Joueur joueur;
        
        // Creation des pieces
        Piece centre;
		Piece classJoueur;
		Piece classPiece;
		Piece classJeu;
		Piece classCommande;
        Piece pointeur;
        Piece classMarchand;
        Piece classEnnemis;
        
        
		// Creation des objetZ
        ObjetZ malloc;
        ObjetZ malloc2;
        ObjetZ malloc3;
        ObjetZ malloc4;
        ObjetZ malloc5;
        ObjetZ malloc6;
        ObjetZ malloc7;
        ObjetZ malloc8;

        ObjetZ lettre;
        
        // Creation des ZorkCoins qui sont des ObjetZ
        ObjetZ ZC1;
        ObjetZ ZC2;
        ObjetZ ZC3;
        ObjetZ ZC4;
        ObjetZ ZC5;
        ObjetZ ZC6;
        ObjetZ ZC7;
        ObjetZ ZC8;
        ObjetZ ZC9;
        ObjetZ ZC10;
        
		// creation du plan
		centre = new Piece("Centre de controle");
		classJoueur = new Piece("Class Joueur");
		classPiece = new Piece("Class Piece");
		classJeu = new Piece("Class Jeu");
		classCommande = new Piece("Class Commande");
        pointeur = new Piece("Pointeur");
        classMarchand = new Piece("Class Marchand");
        classEnnemis = new Piece("Class Ennemis");
        
        // Ajoute les pieces à l'ArrayList pieces
        super.ajouter(centre);
        super.ajouter(classJoueur);
        super.ajouter(classPiece);
        super.ajouter(classJeu);
        super.ajouter(classCommande);
        super.ajouter(pointeur);
        super.ajouter(classEnnemis);
        super.ajouter(classMarchand);
        
        
		// initialise les sorties des pieces
		centre.setSorties(pointeur, classJoueur, classJeu, classPiece);
		classJoueur.setSorties(null, null, null, centre);
		classPiece.setSorties(null, centre, classEnnemis, null);
		classJeu.setSorties(centre, classCommande, null, null);
		classCommande.setSorties(null, null, classMarchand, classJeu);
        classMarchand.setSorties(classCommande, null, null, null);
        classEnnemis.setSorties(classPiece, null, null, null);
        
        
        // On cree le joueur que jouera l'utilisateur
        joueur = new Joueur("Quanticus");
        
        
        joueurPrincipal = joueur;
        
        
        // Creation des Pnj Marchand
        Pnj Marchand = new Marchand("Billy", true, "\"Oyé jeune voyageur, que puis-je pour vous\"");
        Pnj Marchand1 = new Marchand("Rocky", true, "\"Je vends plus rapidement qu'un SSD\"");
        Pnj Marchand2 = new Marchand("Quanty", true, "\"J'ai du bon malloc, si ca t'intéresse\"");
        Pnj Marchand3 = new Marchand("Memory", false, "\"Rien n'est cher, comme sur AliExpress\"");
        
        
        // Initialisation d'ObjetZ
        String indicLettre = "\nBonjour Quanticus, nous venons de détecter un gros Bog dans un espace mémoire\n non attribué, il va falloir que tu alloues la mémoire puis que tu detruises le Bog.\n";
        
        lettre = new ObjetZ(0, "lettre", true, indicLettre, 0);
        malloc = new ObjetZ(30, "malloc", true, 45);
        malloc2 = new ObjetZ(30, "malloc", true, 50);
        malloc3 = new ObjetZ(30, "malloc", true, 50);
        malloc4 = new ObjetZ(30, "malloc", true, 20);
        malloc5 = new ObjetZ(30, "malloc", true, 20);
        malloc6 = new ObjetZ(30, "malloc", true, 5);
        malloc7 = new ObjetZ(30, "malloc", true, 45);
        malloc8 = new ObjetZ(30, "malloc", true, 45);
        
        ZC1 = new ObjetZ(1, "zorkCoins", true, 15);
        ZC2 = new ObjetZ(2, "zorkCoins", true, 20);
        ZC3 = new ObjetZ(0, "zorkCoins", true, 5);
        ZC4 = new ObjetZ(0, "zorkCoins", true, 8);
        ZC5 = new ObjetZ(4, "zorkCoins", true, 45);
        ZC6 = new ObjetZ(3, "zorkCoins", true, 35);
        ZC7 = new ObjetZ(6, "zorkCoins", true, 60);
        ZC8 = new ObjetZ(2, "zorkCoins", true, 20);
        ZC9 = new ObjetZ(7, "zorkCoins", true, 70);
        ZC10 = new ObjetZ(1, "zorkCoins", true, 10);
        
        
        // On ajoute les ObjetsZ aux Marchands
        Marchand.ajouter(malloc);
        Marchand.ajouter(malloc2);
        Marchand.ajouter(malloc3);
        
        Marchand1.ajouter(malloc4);
        Marchand1.ajouter(malloc5);
        
        Marchand2.ajouter(malloc6);
        
        Marchand3.ajouter(malloc7);
        Marchand3.ajouter(malloc8);
        
        
        // On ajoute les ObjetZ aux pièces
        centre.ajouter1(lettre);
        
        
        // Ajout des zorkCoins
        centre.ajouter1(ZC1);
        centre.ajouter1(ZC2);
        classJoueur.ajouter1(ZC3);
        classCommande.ajouter1(ZC4);
        classMarchand.ajouter1(ZC5);
        classMarchand.ajouter1(ZC6);
        classPiece.ajouter1(ZC7);
        classPiece.ajouter1(ZC8);
        classEnnemis.ajouter1(ZC9);
        classCommande.ajouter1(ZC10);
        
        
        // On ajoute les Pnj dans les pieces
        centre.ajouter2(Marchand);
        classJoueur.ajouter2(Marchand1);
        classPiece.ajouter2(Marchand2);
        classCommande.ajouter2(Marchand3);
        
        
        
		// le jeu commence au centre
        piecePrecedente = centre;
		pieceCourante = centre;
        joueurPrincipal = joueur;
	}
    
    
    //                                          GETTERS
    
    
    
    /**
    * Getter pour l'attribut de class analyseurSyntaxique
    *
    * @return la reference vers l'attribut de class analyseurSyntaxique
    */
    /*@
      @ ensures \result != null;
      @
      @ pure
      @*/
    public AnalyseurSyntaxique getAnalyseurSyntaxique(){
        return this.analyseurSyntaxique;
    }
    
    
    /**
     * permet de savoir le nombres d'allocations qui ont deja ete effectuees dans le Jeu
     *
     * @return le nombre d'allocations qui ont ete effectuer dans le Jeu a present
     */
    /*@
      @ ensures \result >= 0;
      @
      @ pure
      @*/
    public int getAllocations(){
        return this.allocations;
    }
    
    
    /**
    * Getter pour l'attribut de class pieceCourante
    *
    * @return la reference vers l'attribut de class pieceCourante
    */
    /*@
      @ ensures \result != null;
      @
      @ pure
      @*/
    public Piece getPieceCourante(){
        return this.pieceCourante;
    }
    
    
    /**
    * Getter pour l'attribut de class piecePrecedente
    *
    * @return la reference vers l'attribut de class piecePrecedente
    */
    /*@
      @ ensures \result != null;
      @
      @ pure
      @*/
    public Piece getPiecePrecedente(){
        return this.piecePrecedente;
    }
    
    
    /**
    * Getter pour l'attribut de class joueurPrincipal
    *
    * @return la reference vers l'attribut de class joueurPrincipal
    */
    /*@
      @ ensures \result != null;
      @
      @ pure
      @*/
    public Joueur getJoueurPrincipal(){
        return this.joueurPrincipal;
    }
    
    
	/**
	 * Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();
		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
        
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
		}
		System.out.println("Merci d'avoir joué.  Au revoir.");
	}


	/**
	 * Affiche le message d'accueil pour le joueur.
	 */
    /*@ 
      @ pure
      @*/
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvenue dans le monde des Zorkdinateurs !\n");
		System.out.println("En 2049, le premier ordinateur organique fut créé");
        System.out.println("Ce type d'ordinateur n'est pas comme les autres.");
        System.out.println("Il est peuplé d'entités qui veillent au bon fonctionnement de celui ci.");
        System.out.println("Vous êtes une de ces entités, vous vous appelez Quanticus et votre but est d'éliminer les bogs");
        System.out.println("Ce jeu ne reflète pas la réalité et les quantités évoqués sont purement fictives \net non représentatives.");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}
    
    
    
	/**
	 * Execute la commande specifiee. Si cette commande termine le jeu, la valeur
	 * true est renvoyee, sinon false est renvoyee
	 *
     * @throws NullPointerException Si la commande passe en parametre est null
     *
	 * @param  commande  La commande a executer
     *
	 * @return true si cette commande termine le jeu, false sinon.
	 */
	/*@
	  @ requires commande != null;
      @
      @ 
	  @*/
	public boolean traiterCommande(Commande commande) {
        if(commande == null){
            throw new NullPointerException("la commande ne doit pas être null");
        }
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) {
			afficherAide();
		} 
        else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);
		}
        else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
        else if (motCommande.equals("retour")){
            retour(commande);   
        }
        else if (motCommande.equals("examiner")){
            examiner(commande);
        }
        
        else if(motCommande.equals("prendre")){
            prendre(commande);
        }
        
        else if(motCommande.equals("infos")){
            infos(commande);   
        }
        
        else if(motCommande.equals("jeter")){
            jeter(commande);
        }
        else if(motCommande.equals("lire")){
            lire(commande);
        }
        else if(motCommande.equals("malloc")){
            malloc(commande);
        }
        else if(motCommande.equals("free")){
            return free(commande);
        }
        else if(motCommande.equals("parler")){
            parler(commande);
        }
        else if(motCommande.equals("solde")){
            solde(commande);
        }
        else if (motCommande.equals("catalogue")){
            catalogue(commande);
        }
        else if(motCommande.equals("acheter")){
            acheter(commande);
        }
        
		return false;
	}
    

	//                         IMPLEMENTATION DES COMMANDES UTILISATEURS
    

    
    /**
    * Cette methode permet de consulter le catalogue d'un marchand, il faut que la commande contienne strictement
    * deux mots. la methode va recuperer le nom du marchand tester si il est dans la piece, afficher le catalogue
    * si ou (via appel a la methode afficherCatalogue() de la class Marchand)
    *
    * @throws NullPointerException Si la commande passee en parametre est null
    * 
    * @param commande La commande entree par l'utilisateur
    */
    /*@
      @ requires commande != null;
      @ 
      @ pure
      @*/
    public void catalogue(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande passée en paramètre ne doit pas être null");
        }
        if(!commande.aSecondMot()){
            System.out.println("De quel marchand souhaitez vous afficher le catalogue ?");
            
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande ne contient pas de troisieme mot ( catalogue <nom_marchand> )");
            
            return ;
        }
        // On recupère le nom du marchand dont on souhaite afficher le catalogue
        String leMarchand = commande.getSecondMot();
        
        for(int i = 0; i < pieceCourante.getNbObjets2(); i++){
            if(pieceCourante.getObjets2(i).getNom().equals(leMarchand)){
                Marchand marchand = (Marchand) pieceCourante.getObjets2(i);
                marchand.afficherCatalogue();
                
                // Fin de la fonction
                return ;
            }
        }
        System.out.println("\nLe marchand dont vous souhaitez consulter le catalogue n'est pas dans cette pièce\n");
    }
    
    
    /**
    * Cette Methode permet d'acquerir un objet que detient un marchand, le solde du joueur doit etre superieur au
    * prix de l'objet qu'il souhaite acheter et l'ajout doit etre possible c'est a dire que le poids de l'objet 
    * ajoute au poids des objets que detient le joueur doit etre inferieur a la capacite de port du joueur
    *
    * @throws NullPointerException Si la commande passe en parametre est null
    *
    * @param commande la commande saisie par l'utilisateur, contient de 1 a 3 mots
    */
    /*@
      @ requires commande != null;
      @*/
    public void acheter(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande passée en paramètre ne doit pas être null");
        }
        if(commande == null){
            throw new NullPointerException("La commande passée en paramètre ne doit pas être null");
        }
        if(!commande.aSecondMot()){
            System.out.println("A qui voulez vous acheter un objet ?");
            
            return ;
        }
        if(!commande.aTroisiemeMot()){
            System.out.println("Que voulez vous acheter ?");
            
            return ;
        }
        
        // On recupere l'objet que l'on veut acheter et le nom du marchand a qui acheter
        String leMarchand = commande.getTroisiemeMot();
        String numeroChoix = commande.getSecondMot();
        int numeroChoixInt = 0;
        try{
            numeroChoixInt = Integer.parseInt(numeroChoix);
        }catch(NumberFormatException e){
            System.out.println("\nLe deuxieme mot de la commande doit être un entier");
        }
        
        boolean succes;
        
        
        for(int i = 0; i < pieceCourante.getNbObjets2(); i++){
            
            if(pieceCourante.getObjets2(i).getNom().equals(leMarchand)){
                
                if(pieceCourante.getObjets2(i) instanceof Marchand){
                    Marchand marchand = (Marchand) pieceCourante.getObjets2(i);                    
                    
                    if(marchand.getDispo() > 3){
                        System.out.println("\nCe marchand n'est plus disponible, il faudrait penser à ne pas trop parler aux marchands\n");
                        
                        // Arret de la fonction
                        return ;
                    }
                    
                    if((numeroChoixInt > 0) && (numeroChoixInt <= marchand.getNbObjets())){
                        
                        // On veut savoir si le Joueur a assez de zorkCoins pour acquerir l'objet
                        succes = (this.joueurPrincipal.getZorkCoins() - marchand.getObjets(numeroChoixInt - 1).getValeur()) >= 0;
                        
                        
                        if(this.joueurPrincipal.ajoutPossible(marchand.getObjets(numeroChoixInt - 1)) && succes){
                            this.joueurPrincipal.ajouter(marchand.getObjets(numeroChoixInt - 1));
                            this.joueurPrincipal.retirerZorkCoins(marchand.getObjets(numeroChoixInt - 1).getValeur());
                            
                            // Affichage de la réussite de l'achat
                            System.out.println("\nL'objet " + marchand.getObjets(numeroChoixInt - 1).getNom() + " est désormais dans votre inventaire et vous avez été débité de " + marchand.getObjets(numeroChoixInt - 1).getValeur() + " zorkCoins.\n");
                            
                            // On retire l'objet de l'étalage du marchand
                            marchand.retirer(marchand.getObjets(numeroChoixInt - 1));
                        }
                        else if(succes == false){
                             System.out.println("\nVous n'avez pas assez de zorkCoins pour acquerir cet objet\n");
                            
                            return ;
                        }
                        else if(succes == true && this.joueurPrincipal.ajoutPossible(marchand.getObjets(numeroChoixInt - 1)) == false){
                            
                            System.out.println("\nVous ne pouvez pas prendre cet objet, vous seriez trop lourd, vous pouvez jeter certains objets afin de pouvoir acquérir cet objet\n");
                        }
                    }
                    else{
                        System.out.println("\nLe numéro de l'objet que vous souhaitez acquérir n'est pas dans le catalogue\n");
                        
                        return ;
                    }
                }
            }
        }
    }
    
    
    /**
     * Methode permettant de connaitre le solde en ZorkCoins du joueur, cette methode affiche le porte 
     * monnaie du joueur
     *
     * @throws NullPointerException Si la commande passee en parametre est null
     *
     * @param commande La commande qu'a entre l'utilisateur
     *
     */
    /*@
      @ requires commande != null;
      @
      @ pure
      @*/
    public void solde(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande passée en paramètre ne doit pas être null");
        }
        if(commande.aSecondMot()){
            System.out.println("Cette commande n'a pas de second mot");
                
            return ;
        }
        
        System.out.println("\n******************** VOTRE SOLDE ********************\n");
        System.out.println("\t\t   ZorkCoins : " + this.joueurPrincipal.getZorkCoins());
        System.out.println("\n*****************************************************\n");
    }
    
    
	/**
	 * Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	/*@
      @ pure
      @*/
	public void afficherAide() {
		System.out.println("\nVous etes perdu. Vous etes seul. Vous errez dans la mémoire du zorkdinateur.");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}
    
    
    
	/**
	 * Tente d'aller dans la direction specifiee par la commande. Si la piece
	 * courante possede une sortie dans cette direction, la piece correspondant a
	 * cette sortie devient la piece courante, dans les autres cas affiche un
	 * message d'erreur.
	 *
     * @throws NullPointerException Si la commande passe en parametre est null
     *
	 * @param  commande  Commande dont le second mot specifie la direction a suivre
	 * 
	 */
	/*@
	  @ requires commande != null;
	  @ 
      @ ensures pieceSuivante != null ==> piecePrecedente == pieceCourante && pieceCourante == pieceSuivante;
	  @*/
	public void deplacerVersAutrePiece(Commande commande) {
		if(commande == null){
            throw new NullPointerException("La commande ne doit pas être null");
        }
        
        if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return ;
		}
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande ne prend pas de troisieme mot");
            
            return ;
        }

		String direction = commande.getSecondMot();

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
            piecePrecedente = pieceCourante;
			pieceCourante = pieceSuivante;
            
            if(pieceCourante.descriptionCourte().equals("Pointeur")){
                System.out.println("Vous êtes arrivé dans un pointeur pointant vers une autre case mémoire.");
                System.out.println("Vous allez être téléporté dans une autre case mémoire");
                System.out.println("\n******************** TELEPORTATION ********************\n");
                
                boolean telep = false;
                
                while(telep == false){
                    telep = teleportation();
                }
            }
            
            // Les pnj se déplacent uniquement lorsque le joueur change de piece
            deplacementPnj();
            
            System.out.println(pieceCourante.descriptionLongue());
		}
	}
    
    
    /**
    * Cette methode permet aux Pnjs presents dans les pieces de se deplacer de maniere aleatoire.
    * Cette methode parcourt toutes les pieces presentes dans le Jeu, puis dans chaque piece, tous les pnjs
    * et les fait se deplacer de maniere aleatoire
    * 
    *
    */
    public void deplacementPnj(){
        
        for(int i = 0; i < getNbObjets(); i++){
            for(int j = 0; j < getObjets(i).getNbObjets2(); j++){
                if(getObjets(i).getObjets2(j).getMobile() == true){
                    
                    int alea = (int)(Math.random() * 4);
                    String direction = new String();

                    switch(alea){
                        case 1:
                            direction = "nord";
                            break;
                        case 2:
                            direction = "est";
                            break;
                        case 3:
                            direction = "ouest";
                            break;
                        case 4:
                            direction = "sud";
                            break;
                        default:
                            break;
                    }
                    
                    Piece pieceTmp = getObjets(i).pieceSuivante(direction);
                    
                    if((pieceTmp != null) && (!pieceTmp.descriptionCourte().equals("Pointeur"))){
    
                        // AFFICHAGE PERMETTANT DE TESTER LE DEPLACEMENT DES PNJ
                        
//                        System.out.println(piece.getObjets2(i).getNom() + " la piece actuelle est " + piece.descriptionCourte() + ". la piece suivante est "  + pieceTmp.descriptionCourte() + ".");
                        
                        
                        pieceTmp.ajouter2(getObjets(i).getObjets2(j));
                        getObjets(i).retirer2(getObjets(i).getObjets2(j));
                    }
                }
            }
        }
    }
    
    
	/**
     * Methode permettant de teleporter un joueur dans un piece aleatoire, l'ArrayList pieces contient
     * toutes les pieces du projet
     *
     * @return true si la teleportation a reussi, false sinon
     */
    /*@
      @ ensures \result == true ==> getPieceCourante() != \old(getPieceCourante());
      @ ensures getPiecePrecedente().equals(\old(getPieceCourante()));
      @*/
    public boolean teleportation(){
        int alea = (int)(Math.random() * super.getNbObjets());
        
        Piece tmp = pieceCourante;
        piecePrecedente = tmp;
        pieceCourante = super.getObjets(alea);
        
        // On test si on s'est téléporté vers un pointeur
        
        if(pieceCourante.descriptionCourte().equals("Pointeur")){
            System.out.println("\n      Oups Vous êtes à nouveau dans un pointeur ...");            
            System.out.println("\n\n******************** RETELEPORTATION ********************\n");
            return false;
        }
        else{
            return true;
        }
    }
    
    
    
    /**
     * Methode permettant au joueur de parler aux pnj, le pnj a une phrase et celle ci sera dite si le joueur
     * vient lui parler
     *
     * @throws NullPointerException Si la commande passee en parametre est null
     *
     * @param commande La commande qui contient d'autres et qui permet de choisir a qui parler
     *
     */
    /*@
      @ requires commande != null;
      @
      @ pure
      @*/
    public void parler(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande passée en paramètre ne doit pas être null");
        }
        if(!commande.aSecondMot()){
            System.out.println("Parler à qui ?");
            
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande n'a pas de troisième mot");
            
            return ;
        }
        
        // On recupere le potentiel nom de l'interlocuteur
        String speaker = commande.getSecondMot();
        
        // Verification de la présence de l'interlocuteur
        for(int i = 0; i < this.pieceCourante.getNbObjets2(); i++){
            
            // Si il existe, on println sa phrase et return
            if(pieceCourante.getObjets2(i).getNom().equals(speaker)){
                // On veut faire en sorte que si l'on parle trop a un Marchand celui ci s'enerve
                if(pieceCourante.getObjets2(i) instanceof Marchand){
                    Marchand marchand = (Marchand) pieceCourante.getObjets2(i);
                    marchand.setDispo(marchand.getDispo() + 1);
                    // Si le joueur a trop parlé au marchand
                    if(marchand.getDispo() >= 4){
                        System.out.println("\"Tu m'ennuie ! Je ne veux plus avoir affaire à toi !\"");
                        
                        return ;
                    }
                }
                System.out.println(this.pieceCourante.getObjets2(i).getPhrase());
                
                return ;
            }
        }
        System.out.println("Le pnj auquel vous voulez parler n'est pas dans la piece.");
    }
    
    
    
    
	/**
	 * Permet au joueur d'examiner son inventaire ou la piece selon ses envies cette methode fait appel a la methode examiner_piece()
	 * ou a la methode examiner_joueur() en fonction de ce que le joueur veut examiner, les cas ou le joueur ne rentre pas de second mot
	 * ou si le second mot ne correspond pas a piece ou joueur est pris en compte
 	 * 
     * @throws NullPointerException Si la commande est null
     *
	 * @param commande permet de savoir ce que le joueur veut examiner, il a le choix entre examiner la piece courante ou son inventaire
	 */
	/*@
	  @ requires commande != null;
      @
	  @ pure
	  @*/
    public void examiner(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande doit être non null");
        }
        if(!commande.aSecondMot()){
            System.out.println("Examiner quoi ?");
            
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande n'a pas de troisième mot");
            
            return ;
        }
        
        
        if(!commande.getSecondMot().equals("piece") && !commande.getSecondMot().equals("inventaire") && !commande.getSecondMot().equals("pnj")){
            System.out.println("Que voulez vous examiner ? piece/inventaire/pnj.");
        }
        else if(commande.getSecondMot().equals("inventaire")){
            System.out.println(" * Vous décidez d'examiner tous les objets contenus dans l'inventaire * ");
            examiner_inventaire();
        }
        else if(commande.getSecondMot().equals("piece")){
            System.out.println(" * Vous décidez d'examiner tous les objets contenus dans la case mémoire * ");
            examiner_piece();
        }
        else if(commande.getSecondMot().equals("pnj")){
            System.out.println(" * Vous décidez d'examiner tous les pnj contenus dans la case mémoire * ");
            examiner_pnj();
        }
    }
    
    
    /**
    * Permet d'examiner en detail les pnjs contenus dans la piece en affichant leurs specificités
    */
    /*@
      @ pure
      @*/
    public void examiner_pnj(){
        for(int i = 0; i < pieceCourante.getNbObjets2(); i++){
            System.out.println("\n      " + (i+1) + "      " + pieceCourante.getObjets2(i).getNom() + " : " + pieceCourante.getObjets2(i).toString() + "\n");
        }
        
    }
    
    
    /**
     * Permet d'examiner le contenu de la piece courante en affichant tous les ObjetsZ contenus dans 
     * celle-ci.
     */
    /*@
      @ pure
      @*/
    public void examiner_piece(){
        System.out.println("\n\n" + pieceCourante.toString());
        
        if(pieceCourante.getNbObjets1() == 0 && pieceCourante.getNbObjets2() == 0){
            System.out.println("\nCette pièce est vide\n");
            
            return ;
        }
        
        // On affiche d'abord les objetsZ
        System.out.println("\n                                       OBJETS         \n");
        for(int i = 0; i < pieceCourante.getNbObjets1(); i++){
            System.out.println("\n      " + (i+1) + "      " + pieceCourante.getObjets1(i).getNom() + " : " + pieceCourante.getObjets1(i).toString() + "\n");
        }
        // On affiche maintenant les Joueurs
        System.out.println("\n                                        PNJ(s)         \n");
        // On recupère ce qu'est le Pnj 
        String type = new String();
        
        for(int j = 0; j < pieceCourante.getNbObjets2(); j++) {
            if(pieceCourante.getObjets2(j) instanceof Marchand){
                type = "Marchand";
            }
//            else if(pieceCourante.getObjets2(j) instanceof Ennemis){
//                type = "Ennemi";
//            }
        		System.out.println("\n      " + (j+1) + "      " + pieceCourante.getObjets2(j).getNom() + " : " + type +"\n");
        }
        
    }
    
    
    
    /**
     *  Permet d'examiner la piece en y affichant tous les objets presents
     */
    /*@
      @ pure
      @*/
    public void examiner_inventaire(){
        if(joueurPrincipal.getNbObjets() == 0){
            System.out.println("\nVous n'avez aucun objet dans votre inventaire.\n");
            
            return ;
        }
        System.out.println("\n                                       OBJETS         \n");
        for(int i = 0; i < joueurPrincipal.getNbObjets(); i++){
            System.out.println("\n      " + (i+1) + "       " + joueurPrincipal.getNomObjets(i) + "  : " + joueurPrincipal.getObjets(i).toString() + "\n");
        }
    }
    
    
    
    /**
     * Cette methode permet au joueur de prendre un objet present dans la piece courante.
     * La fonction va ajouter l'objet souhaite dans l'inventaire du joueur et le retirer de la piece
     * uniquement si celui-ci est présent dans la piece et si le joueur ne portera pas une charge trop lourde
     * une fois l'objet dans l'inventaire.
     *
     * @throws NullPointerException Si la commande passe en parametre est null
     *
     * @param  commande Commande dont le second mot specifie l'objet à prendre.
     *
     */
    /*@
      @ requires commande != null;
      @*/
    public void prendre(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande doit être non null");
        }
        if(!commande.aSecondMot()){
            //Si la commande ne spécifie pas l'objet à prendre
            System.out.println("Prendre quoi ?");
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande n'a pas de troisième mot.");
            
            return ;
        }
        
        String objetAPrendre = commande.getSecondMot();
        boolean succes = false;
        int j = 0;
        
        // on va defiler tous les objetsZ de la piece
        while(j < pieceCourante.getNbObjets1()){
            // Si l'objetZ souhaite est dans la piece
            if(pieceCourante.getObjets1(j).getNom().equals(objetAPrendre)){
                ObjetZ objetTmp = pieceCourante.getObjets1(j);
                int poidsObjet = objetTmp.getPoids();
                
                // Si de par sa nature l'objetZ ne peut etre transporte
                if(objetTmp.getEstTransportable() == false){
                    System.out.println("Cet Objet ne peut pas être transporté");
                    
                    return ;
                }
                
                // Si le poids maximal que peut porter le joueur est inferieur au poids qu'il porte deja dans l'inventaire
                // plus le poids de l'objet qu'il veut prendre
                if(joueurPrincipal.ajoutPossible(objetTmp) == true){
                    if(objetTmp.getNom().equals("zorkCoins")){
                        System.out.println("\nBravo, Vous venez de récuperer " + objetTmp.getValeur() + " zorkCoins, la valeur de ces zorkCoins va être ajoutée à votre solde\n");
                        joueurPrincipal.ajouterZorkCoins(objetTmp.getValeur());
                        pieceCourante.retirer1(objetTmp);
                        return ;
                    }
                    else{
                        joueurPrincipal.ajouter(objetTmp);
                        pieceCourante.retirer1(objetTmp);
                        succes = true;
                    }
                }
                else{
                    int poidsFinal = joueurPrincipal.getPoidsObjets() + poidsObjet;
                    // Message indiquant que l'objets ne peut être pris car sa prise indiquerai une surcharge du joueur
                    System.out.println("\nVous porter déjà " + joueurPrincipal.getPoidsObjets() + " octets. Vous ne pouvez pas prendre " + objetTmp.getNom() + " car celui ci pèse " + objetTmp.getPoids() + " octets et que vous porteriez donc " + poidsFinal + " octets. Or je vous le rapelle, la charge maximale que vous pouvez porter est : " + joueurPrincipal.getPoidsMax() +" octet. Vous pouvez vous débarasser de certains objets si vous le souhaitez avec la commande jeter.\n");
                    
                    return ;
                }
            }
            j++;
        }
        // Si toute les objets de la piece ont ete examines et que aucun ne correspond au second Mot rentre par le joueur 
        if(j == pieceCourante.getNbObjets1() && succes == false){
            System.out.println("Cet objet n'est pas dans la pièce ou n'existe pas.");
            
            return ;
        }
        // On affiche si le joueur a reussi a prendre l'objet ou non 
        if(succes == true){
            System.out.println("L'Objet est désormais dans votre inventaire.");
        }
        else{
            System.out.println("Vous n'avez pas réussi à prendre l'objet.");
        }
    }
    
    
    
    /**
     * Methode qui permet au joueur de se debarasser d'un objetZ present dans son inventaire, si celui ci est trop lourd par exemple
     * La methode jeter va se servir d'une variable temporaire pour retirer l'objet de l'inventaire pour 
     * ensuite l'ajouter a la piece dans laquelle le joueur se trouve.
     * J'ai prefere utiliser une variable temporaire, je trouve cela plus propre
     * 
     * @throws NullPointerException Si la commande passe en parametre est null
     *
     * @param commande commande entree par l'utilisateur dont le second mot specifie l'objet a jeter
     */
    /*@
      @ requires commande != null;
      @*/
    public void jeter(Commande commande){
        if(commande == null){
            throw new NullPointerException("la commande ne doit pas être null");
        }
        if(!commande.aSecondMot()){
            // Si l'utilisateur ne spécifie pas ce qu'il veut jeter
            System.out.println("Jeter quoi ?");
            
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande n'a pas de troisième mot.");
            
            return ;
        }
        
        String objetAJeter = commande.getSecondMot();
        boolean succes = false;
        int i = 0;
        
        while(i < joueurPrincipal.getNbObjets()){
            
            if(joueurPrincipal.getNomObjets(i).equals(objetAJeter)){
                ObjetZ objetTmp = joueurPrincipal.getObjets(i);
                
                joueurPrincipal.retirer(objetTmp);
                pieceCourante.ajouter1(objetTmp);
                succes = true;
            }
            i++;
        }
        
        if(i == joueurPrincipal.getNbObjets() && succes == false){
            System.out.println("\nL'objet n'est pas dans votre inventaire ou n'existe pas.\n");
        }
        
        if(succes == true){
            System.out.println("Vous avez jeté l'objet avec succès.");
        }
        else{
            System.out.println("Vous n'avez pas réussi à jeter l'objet.\n");
        }
    }
    
    
    
    
    
    
    /**
     * Cette methode va permettre au joueur de modeliser lui meme le plan du jeu qu'il veut. 
     * Le joueur va  pouvoir malloc de nouvelles pieces.
     * On va recuperer les sorties de la piece courante pour ensuite tester si la piece que le joueur veut malloc
     * n'existe pas deja, si elle n'existe pas, la methode va appeler la methode  allocation pour creer une nouvelle piece 
     * et l'inserer dans les sorties de la piece courante
     *
     * @throws NullPointerException Si la commande passe en parametre est null
     * 
     * @param commande le second mot indique la direction dans laquelle le joueur veut allouer une piece
     */
    /*@
      @ requires commande != null;
      @*/
    public void malloc(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande ne doit pas être null");
        }
        
        
        if(joueurPrincipal.getObjetNom("malloc") == null){
            System.out.println("\nVous n'avez pas l'objet permettant de réaliser cette commande.\n");
            
            return ;
        }
        
        
        // On tente la réalisation du malloc
        
        if(joueurPrincipal.contientCombienDe(joueurPrincipal.getObjetNom("malloc")) > 0){
            if(!commande.aSecondMot()){
                System.out.println("Malloc quoi ?");
                // Si le joueur n'indique pas l'endroit qu'il veut malloc
                return ;
            }
            if(commande.aTroisiemeMot()){
                System.out.println("Cette commande n'a pas de troisième mot.");
                
                return ;
            }
            
            
            String zone = commande.getSecondMot();
            
            
            //On recupère la direction à malloc
            if(!zone.equals("nord") && !zone.equals("sud") && !zone.equals("est") && !zone.equals("ouest")){
                System.out.println("Cette zone n'existe pas.");
                
                return ;
            }
            
            // On récupère les sorties de la pieceCourante
            HashMap<String, Piece> sortiesTmp = pieceCourante.getSorties();
            
            // On créé des variables temporaires pour récupérer toutes les pièces de la hashMap
            Piece pieceTmpNord = (Piece)sortiesTmp.get("nord");
            Piece pieceTmpSud = (Piece)sortiesTmp.get("sud");
            Piece pieceTmpEst = (Piece)sortiesTmp.get("est");
            Piece pieceTmpOuest = (Piece)sortiesTmp.get("ouest");
            
            
            if(zone.equals("nord")){
                //On test si il n'existe pas déjà une pièce au nord
                if(pieceTmpNord != null){
                    System.out.println("La mémoire est déjà allouée à cet endroit");
                    
                    return ;
                }
             // On recupere la piece nouvellement creer et on la "racroche" a la piece dans laquelle est le joueur
                
                pieceTmpNord = allocation(zone);
                pieceCourante.setSorties(pieceTmpNord, pieceTmpEst, pieceTmpSud, pieceTmpOuest);
                
                System.out.println("\nAllocation de mémoire réalisée avec succès\n");
            }
            else if(zone.equals("est")){
                //On test si il n'existe pas déjà une pièce a l'est
                if(pieceTmpEst != null){
                    System.out.println("La mémoire est déjà allouée à cet endroit");
                    
                    return ;
                }
             // On recupere la piece nouvellement creer et on la "racroche" a la piece dans laquelle est le joueur
                pieceTmpEst = allocation(zone);
                pieceCourante.setSorties(pieceTmpNord, pieceTmpEst, pieceTmpSud, pieceTmpOuest);
                
                System.out.println("Allocation de mémoire réalisée avec succès");
            }
            else if(zone.equals("sud")){
                //On test si il n'existe pas déjà une pièce au sud
                if(pieceTmpSud != null){
                    System.out.println("La mémoire est déjà allouée à cet endroit");
                    
                    return ;
                }
             // On recupere la piece nouvellement creer et on la "racroche" a la piece dans laquelle est le joueur
                pieceTmpSud = allocation(zone);
                pieceCourante.setSorties(pieceTmpNord, pieceTmpEst, pieceTmpSud, pieceTmpOuest);
                
                System.out.println("Allocation de mémoire réalisée avec succès");
            }
            else if(zone.equals("ouest")){
                //On test si il n'existe pas déjà une pièce a l'ouest
                if(pieceTmpOuest != null){
                    System.out.println("La mémoire est déjà allouée à cet endroit");
                    
                    return ;
                }
                // On recupere la piece nouvellement creer et on la "racroche" a la piece dans laquelle est le joueur
                pieceTmpOuest = allocation(zone);
                
                pieceCourante.setSorties(pieceTmpNord, pieceTmpEst, pieceTmpSud, pieceTmpOuest);
                
                System.out.println("Allocation de mémoire réalisée avec succès");
            }
            
            joueurPrincipal.retirer(joueurPrincipal.getObjetNom("malloc"));
        }
        
    }
    
    
    
    
    /**
     * Cette methode est en complement de la methode malloc, celle ci va permettre l'allocation d'une nouvelle piece en fonction du nombre d'allocation 
     * precedemment effectuee. Effectivement, le jeu est scripte, cest a dire que selon l'allocation, 
     * il y aura certains objetZ dans la piece.
     * Ceci devrait etre rendu plus aleatoire dans la prochaine version du projet.
     *
     * @throws NullPointerException Si la zonePrecedente passe en parametre est null
     * 
     * @param zonePrecedente va permettre de mettre une nouvelle sortie dans la piece allouee, la sortie correspond a la piece depuis laquelle on a malloc la nouvelle piece
     *
     * @return renvoie la piece nouvellement creee avec ses sorties
     */
    /*@ 
      @ requires zonePrecedente != null;
      @ 
      @ ensures getNbObjets() == \old(getNbObjets()) + 1;
      @ ensures (\result != null);
      @*/
    public Piece allocation(String zonePrecedente){
        if(zonePrecedente == null){
            throw new NullPointerException("La zonePrecedente ne doit pas être null");
        }
        // Ici on va tester le nombre d'allocations de piece deja effectuees
        
    	if(this.allocations == 0) {
        	
            Piece allouée = new Piece("Allocation1");
            ObjetZ debris = new ObjetZ(5, "debris" , false, 0);
            allouée.ajouter1(debris);
        		
            // On set les sorties de la nouvelle pièce
            if(zonePrecedente.equals("nord")){
	            allouée.setSorties(null, null, piecePrecedente, null);
	        }
	        if(zonePrecedente.equals("est")){
	            allouée.setSorties(null, null, null, piecePrecedente);
	        }
	        if(zonePrecedente.equals("sud")){
	            allouée.setSorties(piecePrecedente, null, null, null);
	        }
	        if(zonePrecedente.equals("ouest")){
	            allouée.setSorties(null, piecePrecedente, null, null);
	        }
	        allocations++;
	        return allouée;
        }
        
        // Si une allocation a deja ete effectuée
        if(this.allocations == 1) {
            Piece allouée = new Piece("Allocation2");
            this.ajouter(allouée);
            ObjetZ free = new ObjetZ(30, "free", true, 10);
	    		
            String indicationFragment = "Vous y êtes presque, Bog est passé par ici";
            ObjetZ fragment = new ObjetZ(5, "fragment" , true, indicationFragment, 0);
            
            // On ajoute les ObjetZ a la piece
            allouée.ajouter1(free);
            allouée.ajouter1(fragment);
	    		
	    		// On set les sorties de la nouvelle pièce
            if(zonePrecedente.equals("nord")){
	            allouée.setSorties(null, null, piecePrecedente, null);
	        }
	        if(zonePrecedente.equals("est")){
	            allouée.setSorties(null, null, null, piecePrecedente);
	        }
	        if(zonePrecedente.equals("sud")){
	            allouée.setSorties(piecePrecedente, null, null, null);
	        }
	        if(zonePrecedente.equals("ouest")){
	            allouée.setSorties(null, piecePrecedente, null, null);
	        }
	        allocations++;
	        return allouée;
    }
        if(this.allocations == 2) {
            Piece allouée = new Piece("Allocation3");
        
            Pnj Bog = new Pnj("Bog", false, "\"ERROR SYSTEM ERROR SYSTEM ERROR SYSTEM\"");
            allouée.ajouter2(Bog);
            
            // On ajoute la piece a au conteneur de pièces afin de pouvoir se teleporter a l'interieur 
            this.ajouter(allouée);
	    		// On set les sorties de la nouvelle pièce
            if(zonePrecedente.equals("nord")){
                allouée.setSorties(null, null, piecePrecedente, null);
	        }
	        if(zonePrecedente.equals("est")){
	            allouée.setSorties(null, null, null, piecePrecedente);
	        }
	        if(zonePrecedente.equals("sud")){
	            allouée.setSorties(piecePrecedente, null, null, null);
	        }
	        if(zonePrecedente.equals("ouest")){
	            allouée.setSorties(null, piecePrecedente, null, null);
	        }
	        allocations++;
	        return allouée;
        }
        // Si il y a plus d'allocation que prevues par le script
        Piece allouée = new Piece();
        
		if(zonePrecedente.equals("nord")){
            allouée.setSorties(null, null, piecePrecedente, null);
        }
        if(zonePrecedente.equals("est")){
            allouée.setSorties(null, null, null, piecePrecedente);
        }
        if(zonePrecedente.equals("sud")){
            allouée.setSorties(piecePrecedente, null, null, null);
        }
        if(zonePrecedente.equals("ouest")){
            allouée.setSorties(null, piecePrecedente, null, null);
        }
        
        allocations++;
        
        return allouée;
    }
    
    
    
    
    /**
     * Permet d'eliminer des Bugs contenus dans les cases memoires (ou pieces), cette methode permet, dans cette premiere partie du projet de gagner
     * en eliminant le seul adversaire du jeu, mais dans les prochaines versions, 
     * elle eliminera tous les prochains adversaire que l'utilisateur pourra rencontrer
     * Pour utiliser la fonction il faut que le joueur possede l'objetZ free qu'il peut trouver dans es pieces.
     * 
     * @throws NullPointerException Si la commande passe en parametre est null
     *
     * @param commande indique le nom du joueur adverse que le joueur veut eliminer
     *
     * @return renvoie un boolean qui permet de terminer la partie si Bog a ete free, en cas d'erreur, la partie continue
     */
    /*@
      @ requires commande != null;
      @*/
    public boolean free(Commande commande) {
            if(commande == null){
                throw new NullPointerException("La commande ne doit pas être null");
            }
    		if(!commande.aSecondMot()) {
    			System.out.println("Qui voulez-vous free ?");
    			
    			return false;
    		}
            if(commande.aTroisiemeMot()){
                System.out.println("Cette commande n'a pas de troisième Mot.");
                    
                return false;
            }
    		
    		ObjetZ freeTmp = new ObjetZ(30, "free", true, 10);
    		Pnj Bog = new Pnj("Bog", false);
	    	
    		if(joueurPrincipal.contientCombienDe(freeTmp) > 0) {
    			if(!commande.getSecondMot().equals("Bog")){
	    			System.out.println("Ce personnage n'existe pas ou ne peut pas être free");
	    			
	    			return false;
	    		}
	    		
	    		if(commande.getSecondMot().equals("Bog")) {
	    			System.out.println("	\n\t\t\t\tBRAVO VOUS AVEZ GAGNE\n\t\t\t\tVOUS AVEZ ELIMINE BOG\n");
	    			pieceCourante.retirer2(Bog);
	    			
	    			joueurPrincipal.retirer(freeTmp);
	    			return true;
	    		}
	    	}
	    	System.out.println("Il vous faut l'objet free afin de faire d'eliminer un ennemi.");
	    	
    		return false;
    }  
    
    
     /**
     * Methode permettant de renvoyer un objet en fonction de son nom, on test si une piece du conteneur a la meme
     * description que le nom passe en parametre 
     *
     * @throws NullPointerException Si le nom passe en parametre est null
     *
     * @param nom Le nom dont on veut tester l'egalite ou non 
     *
     * @return l'objet qui a le nom passe en parametre ou null
     */
    /*@
      @ requires nom != null;
      @ 
      @ ensures \result != null ==> \forall int i; (0 <= i) && (i < getNbObjets()); (\exist getObjets(i).getNom().equals(nom));
      @ pure
      @*/
    public Piece getObjetNom(String nom){
        if(nom == null){
            throw new NullPointerException("Le nom passé en paramètre ne doit pas être null");
        }
        
        for(int i = 0; i < getNbObjets(); i++){
            Piece o = getObjets(i);
            if(o.descriptionCourte().equals(nom)){
                return o;
            }
        }
        return null;
    }
    
    
    /**
     * Utilise une variable temporaire pour inverser la pieceCourante et la 
     * piecePrecedente afin d'implementer la fonction de retour dans la piece
     * precedente
     *
     * @throws NullPointerException Si la pieceCourante est null
     * @throws NullPointerException Si la piecePrecedente est null
     *
     * @param commande permet de prendre la commande entree par l'utilisateur et de verifier qu'il n'y a pas de second et de troisieme mots
     */
    /*@
      @ requires pieceCourante != null;
      @ requires piecePrecedente != null;
      @ requires commande != null;
      @ 
      @ ensures piecePrecedente == \old(getPieceCourante());
      @ ensures pieceCourante == \old(getPiecePrecedente());
      @*/
    public void retour(Commande commande){
        // On test ces exceptions même si normalement elles ne doivent jamais l'être
        if(commande.aSecondMot()){
            System.out.println("Cette commande n'a pas de second mot.");
            
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande n'a pas de troisième mot.");
            
            return ;
        }
        if(pieceCourante == null){
            throw new NullPointerException("La piece courante ne doit pas être null");
        }
        if(piecePrecedente == null){
            throw new NullPointerException("La piece precedente ne doit pas être null");
        }
        
        if(pieceCourante.equals(piecePrecedente)){
            System.out.println("Vous ne pouvez pas revenir en arrière.");
            
            return ;
        }
        
        // Creation d'une variable temporaire pour garder la piece courante
        Piece tmp = pieceCourante;
        pieceCourante = piecePrecedente;
        piecePrecedente = tmp;
        System.out.println("\n * Vous retournez dans la salle visitée précedemment *");
        
        System.out.println("\n" + pieceCourante.toString() + "\n");
        
        boolean telep = false;
        // Si le retour se fait dans un pointeur
        
        if(pieceCourante.descriptionCourte().equals("Pointeur")){                
            System.out.println("\nVOUS NE POUVEZ PAS RETOURNER DANS UN POINTEUR");
            retour(commande);
        }
    }
    
    
    
    
    
    /**
     * Permet d'afficher les informations du joueur ou de la piece dans laquelle il se trouve. 
     * En fonction du second mot tape par le joueur, la methode va faire un appel a la methode infos_joueur() ou
     * infos_piece() afin d'afficher selon le souhait du joueur les informations relatives a la piece ou au joueur lui meme
     * 
     * @throws NullPointerException Si la commande passee en parametre est null
     *
     * @param commande permet de savoir le deuxieme mot qu'a tape l'utilisateur apres la commande, si il a tape un second mot
     * 
     */
    /*@
      @ requires commande != null;
      @
      @ pure
      @*/
    public void infos(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande ne doit pas être null");
        }
        if(!commande.aSecondMot()){
            System.out.println("Quelles infos voulez vous avoir ? piece/joueur");
            
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande n'a pas de troisième mot");
            
            return ;
        }
        
        if(!commande.getSecondMot().equals("piece") && !commande.getSecondMot().equals("joueur")){
            System.out.println("Vous ne pouvez pas avoir d'information sur cet élement, vous pouvez seulement avoir des informations sur les pièces et les joueurs ?");
        }
        if(commande.getSecondMot().equals("piece")){
            infos_piece();
        }
        else if(commande.getSecondMot().equals("joueur")){
            infos_joueur();
        }
    }
    
    
    
    /*
    * Cette fonction permet de savoir si un ajout est possible dans le jeu, un ajout est toujours possible, 
    * si la piece p passee en parametre est null, on leve une exception, car aucune piece ne doit etre null
    *
    * @throws NullPointerException Si la piece passé en parametre est null
    *
    * @param p la piece que l'on veut ajouter
    *
    * @return true sauf si une exception est levée
    */
   /*@
     @ also
     @ requires p != null;
     @
     @ ensures \result == true;
     @
     @ pure
     @*/
    public boolean ajoutPossible(Piece p){
        if(p == null){
            throw new NullPointerException("la piece passé en argument ne doit pas être null");
        }
        return true;
    }
    
    
   
    /**
     * Permet de donner les informations sur le joueur en appelant la fonction toString
     */
    /*@
      @ pure
      @*/
    public void infos_joueur(){
        System.out.println("\n Infos joueur : " + joueurPrincipal.toString() + "\n");
    }
    
    
    
    /**
     * Permet de donner les informations sur la piece courante en appelant la fonction toString
     */
    /*@
      @ pure
      @*/
    public void infos_piece(){
        System.out.println("\n Infos pièce : " + pieceCourante.toString() + "\n");
    }
    
    
    
    /**
     * Permet a l'utilisateur de connaitre des informations sur l'objetZ qu'il detient, on va tester 
     * si la commande contient un deuxieme mot permettant de specifier l'objetZ. 
     * Ensuite la methode va recuperer l'indication de l'ObjetZ (attribut de la classe ObjetZ) afin de l'afficher.
     *
     * @throws NullPointerException Si la commande passee en parametre est null
     *
     * @param commande commande tape par l'utilisateur pour recuperer les mot qu'il a tape et savoir quoi lire
     *
     */
    /*@
      @ requires commande != null;
      @
      @ pure
      @*/
    public void lire(Commande commande){
        if(commande == null){
            throw new NullPointerException("La commande ne doit pas être null.");
        }
        if(!commande.aSecondMot()){
            System.out.println("Lire quoi ?");
            
            return ;
        }
        if(commande.aTroisiemeMot()){
            System.out.println("Cette commande n'a pas de troisième mot.");
            
            return ;
        }
        
        // on cree un  compteur et on recupere le nom de l'objetZ que le joueur veut "lire", on creer egalement un boolean
        
        // indiquant a la fin de l'a	ction si le joueur a reussi a lire les infos / ce qui ete inscrit sur l'ObjetZ
        
        int i = 0;
        String objetALire = commande.getSecondMot();
        boolean succes = false;
        
        // On cherche dans l'inventaire du joueur
        while(i < joueurPrincipal.getNbObjets()){
        		// Si on trouve l'objet dont le joueur veut les infos
            if(joueurPrincipal.getNomObjets(i).equals(objetALire)){
                System.out.println("\n" + joueurPrincipal.getObjets(i).getIndication() + "\n");
                succes = true;
            }
            i++;
        }
        if(succes == false){
            System.out.println("Vous n'avez pas réussi à lire les indication, l'objet n'est surement pas dans votre inventaire.");
        }
    }
}


