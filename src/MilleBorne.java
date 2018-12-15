/**
 * Jeu du Mille Bornes (décembre 2018) <br>
 * MilleBornes.java
 *
 * @author Valentin P
 * @version 1.0
 */

public class MilleBorne {

	// ******************************
	//  Type agrégé Carte
	// ******************************

	/**
	 * Modélisation d'un paquet de carte.
	 * La classe Carte contient: <br>
	 *     - nbCartes: le nombre de cartes totales <br>
	 *     - carteAttaque: le nombre de cartes "Attaque" (type 1) <br>
	 *     - carteDefense: le nombre de cartes "Defense" (type 2) <br>
	 *     - carteDistance: le nombre de cartes "Distance" (type 3)
	 */
	public static class Carte {
		int nbCartes = 100;
	}

	/**
	 * Afficher la carte tiré au sort
	 *
	 * 1* : Carte attaque
	 *  - 11 : Accident de la route
	 *  - 12 : Panne d'essence
	 *  - 13 : Crevaison
	 * 2* : Carte défense
	 *  - 21 : Réparation
	 *  - 22 : Essence
	 *  - 23 : Roue de secours
	 * 3* : Carte distance
	 *  - 31 : 25km
	 *  - 32 : 50km
	 *  - 33 : 100km
	 *
	 * @param carte
	 */
	public static String convertirCarte(int carte) {
		// déclaration des données
		String str = "";

		// traitement
		switch (carte) {
			case 11:
				str = "Accident de la route (Attaque)";
				break;
			case 12:
				str = "Panne d'essence (Attaque)";
				break;
			case 13:
				str = "Crevaison (Attaque)";
				break;
			case 21:
				str = "Réparation (Defense)";
				break;
			case 22:
				str = "Essence (Defense)";
				break;
			case 23:
				str = "Roue de secours (Defense)";
				break;
			case 31:
				str = "25km (Distance)";
				break;
			case 32:
				str = "50km (Distance)";
				break;
			case 33:
				str = "100km (Distance)";
				break;
		}

		return str;
	}

	public static void afficherTirageCarte(int carte) {
		// affichage
		Ecran.afficherln("Vous avez pioché la carte ", convertirCarte(carte), " !");
	}

	/**
	 * Tirer au sort une carte dans le paquet
	 *
	 * @param crt Paquet de carte
	 * @return Le type de carte retiré (numéro)
	 */
	public static int tirerCarte(Carte crt) {
		// déclaration des données
		int typeCarte, numCarte, carte;

		// initialisation des variables
		/** le type de carte (Attaque, Défense, Distance) est compris entre 1 et 75
		 *  c'est une des 100 cartes possibles */
		typeCarte = nbHasard(1, 75); // type de carte
		/** chaque type de carte contient 3 cartes différentes */
		numCarte = nbHasard(1, 3); // numéro de la carte

		// traitement
		if(typeCarte >=1 && typeCarte <= 15) { // cartes Attaque
			typeCarte = 1;
		} else {
			if(typeCarte >= 16 && typeCarte <= 39) { // cartes Defense
				typeCarte = 2;
			} else { // cartes Distance
				typeCarte = 3;
			}
		}
		carte = typeCarte*10 + numCarte;

		// retrait de la carte du paquet
		crt.nbCartes = crt.nbCartes - 1;

		return carte;
	}

	/**
	 * Choisir l'action à réaliser en fonction de la carte tirée au sort
	 *
	 * @param carte Numéro de la carte
	 */
	public static void choisirActionCarte(int carte, Joueur j1, Joueur j2) {
		// affichage de la carte
		afficherTirageCarte(carte);

		// traitement
		if(carte < 20) { // carte Attaque
			ajouterAttaque(carte, j1, j2);
		} else {
			if(carte < 30) { // carte Defense
				Ecran.afficherln("Vous avez pioché une carte défense !");
			} else { // carte Distance
				ajouterKm(carte, j1);
			}
		}
	}

	/**
	 * Vérifier que le joueur n'est pas déjà bloqué par cette carte
	 *
	 * @param jr Joueur qui doit être vérifié
	 * @param carte Numéro de la carte
	 * @return Vrai si le joueur est bloqué
	 */
	public static boolean estBloque(Joueur jr, int carte) {
		// dévlaration des données
		boolean bloque = false;

		// traitement
		switch(carte) {
			case 11:
				bloque = jr.carteAcc;
				break;
			case 12:
				bloque = jr.carteCre;
				break;
			case 13:
				bloque = jr.carteEss;
				break;
		}

		return bloque;
	}

	// ******************************
	//  Type agrégé Joueur
	// ******************************

	/**
	 * Modélisation d'un joueur.
	 * Le type agrégé contient le nom, le nombre de kilomètres et la posséssion des cartes Attaques/Défense
	 */
	public static class Joueur {
		String nom;
		int km = 0;

		boolean carteAcc = false; // carte accident
		boolean cartePde = false; // carte panne d'essence
		boolean carteCre = false; // carte crevaison

		boolean carteRpt = false; // carte réparation
		boolean carteEss = false; // carte essence
		boolean carteRds = false; // carte roue de secours
	}

	public static void afficherJoueur(Joueur jr) {
		Ecran.afficherln(jr.nom, " - ", jr.km, "km");

		Ecran.afficher(" - Carte attaque: ");
		if(jr.carteRpt)
			Ecran.afficher("Accident,");
		if(jr.carteEss)
			Ecran.afficher("Panne d'essence ");
		if(jr.carteRds)
			Ecran.afficher("Crevaison ");
		if(!jr.carteRpt && !jr.carteEss && !jr.carteRds)
			Ecran.afficher("aucune");

		Ecran.afficher("\n - Carte défense: ");
		if(jr.carteAcc)
			Ecran.afficher("Réparation,");
		if(jr.cartePde)
			Ecran.afficher("Essence ");
		if(jr.carteCre)
			Ecran.afficher("Roue de secours ");
		if(!jr.carteAcc && !jr.cartePde && !jr.carteCre)
			Ecran.afficher("aucune");
	}

	/**
	 * Ajouter des kilomètres à un joueur
	 *
	 * @param carte Numéro de la carte
	 * @param jr Joueur qui joue
	 */
	public static void ajouterKm(int carte, Joueur jr) {
		// déclaration des données
		int ajout;

		// ajout des km
		if(carte == 31) {
			ajout = 25;
		} else {
			if(carte == 32) {
				ajout = 50;
			} else {
				ajout = 100;
			}
		}
		jr.km += ajout;
	}

	/**
	 * Ajouter une attaque du Joueur 1 au Joueur 2
	 * @param carte
	 * @param j1
	 * @param j2
	 */
	public static void ajouterAttaque(int carte, Joueur j1, Joueur j2) {
		if(!estBloque(j2, carte)) {
			if(carte == 11) {
				Ecran.afficherln("Carte accident.");
			} else {
				if(carte == 12) {
					Ecran.afficherln("Carte panne d'essence.");
				} else {
					Ecran.afficherln("Carte crevaison.");
				}
			}
		} else {
			Ecran.afficherln("Le joueur ", j2.nom, "possède déjà cette carte. Vous ne pouvez pas lui en donner une seconde.");
		}
	}

	// ******************************
	//  Fonctions & Actions
	// ******************************

	/**
	 * Tirer un nombre au hasard dans un intervalle
	 *
	 * @param borne_inf Borne inférieure
	 * @param borne_sup Borne supérieure
	 * @return Le nombre tiré
	 */
	public static int nbHasard(int borne_inf, int borne_sup) {
		return(borne_inf + (int)(Math.random() * (borne_sup - borne_inf + 1)));
	}

	// ******************************
	//  Main
	// ******************************

	public static void main(String args[]) {
		// déclaration des données
		Carte crt = new Carte();
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		j1.nom = "Valentin";

		// saisie des noms
		Ecran.afficher("Saisir le nom du joueur 1: ");
		j1.nom = Clavier.saisirString();
		Ecran.afficher("Saisir le nom du joueur 2: ");
		j2.nom = Clavier.saisirString();


		// DEBUG
		Ecran.sautDeLigne();
		int carte = tirerCarte(crt);
		choisirActionCarte(carte, j1, j2);
		Ecran.sautDeLigne();
		afficherJoueur(j1);
	}
}
