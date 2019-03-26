/**
 *  <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Classe repertoriant l'ensemble des mots-cle utilisables comme commande dans
 *  le jeu. Elle est utilisee pour verifier la validite des commandes de
 *  l'utilisateur.
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.5
 * @since      July 1999
 */

public class MotCleCommande {
	/**
	 * Un tableau constant contenant tous les mots-cle valides comme commandes.
	 */
	private final static String commandesValides[] = {"aller", "quitter", "aide", "retour", "prendre", "examiner", "jeter", "infos", "lire", "malloc", "free", "solde", "parler", "acheter", "catalogue"};


	/**
	 * Initialise la liste des mots-cle utilisables comme commande.
	 */
	public MotCleCommande() { }


	/**
	 * Teste si la chaine de caracteres specifiee est un mot-cle de commande
	 * valide. Check whether a given String is a valid command word. Return true
	 * if it is, false if it isn't.
	 *
     * @throws NullPointerException Si une des commande de commandesValide[] == null
     *
	 * @param aString Chaine de caractères a tester
	 * @return true si le paramètre est une commande valide, false sinon
	 */
    /*@
      @ requires aString != null;
      @ requires (\forall int i; 0 <= i && i < commandeValides.length; commandeValides[i] != null);
      @
      @ pure
      @*/
	public boolean estCommande(String aString) {
		for (int i = 0; i < commandesValides.length; i++) {
            if(commandesValides[i] == null){
                throw new NullPointerException("Aucune commande valide ne doit être null");
            }
			if (commandesValides[i].equals(aString)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Affiche toutes les commandes (i.e. les mots-cle) valides.
     *
     * throws NullPointerException Si une commande de commandeValides est null
	 */
    /*@
      @ requires (\forall int i; 0 <= i && i < commandeValides.length; commandeValides[i] != null); 
      @
      @ pure
      @*/
	public void afficherToutesLesCommandes() {
		for (int i = 0; i < commandesValides.length; i++) {
            if(commandesValides[i] == null){
                throw new NullPointerException("Aucune commande de commandeValides[] ne doit être null");
            }
			System.out.print(commandesValides[i] + "  ");
		}
		System.out.println();
	}
}

