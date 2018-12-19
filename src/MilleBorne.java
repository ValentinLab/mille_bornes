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
	 * Convertir le numéro de la carte en nom
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
	 * @param carte Numéro de la carte
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

	/**
	 * Afficher une carte
	 *
	 * @param carte Numéro de la carte à afficher
	 */
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
	 * @param j1 Premier joueur
	 * @param j2 Deuxième joueur
	 */
	public static void choisirActionCarte(int carte, Joueur j1, Joueur j2) {
		// affichage de la carte
		afficherTirageCarte(carte);

		// traitement
		if(carte < 20) { // carte Attaque
			ajouterAttaque(carte, j1, j2);
		} else {
			if(carte < 30) { // carte Defense
				ajouterDefense(carte, j1);
			} else { // carte Distance
				ajouterKm(carte, j1);
			}
		}
	}

	/**
	 * Vérifier que le joueur n'est pas déjà bloqué par une carte
	 *
	 * @param jr Joueur qui doit être vérifié
	 * @param carte Numéro de la carte
	 * @return Vrai si le joueur est bloqué
	 */
	public static boolean estBloque(Joueur jr) {
		// dévlaration des données
		boolean bloque = false;

		// traitement
		if(jr.carteAcc || jr.cartePde || jr.carteCre) {
			bloque = true;
		}

		return bloque;
	}

	/**
	 * Stocker une carte defense pour un joueur
	 *
	 * @param carte Numéro de la carte à stocker
	 * @param jr Joueur qui enregistre la carte
	 */
	public static void stockerCarte(int carte, Joueur jr) {
		char rep;

		Ecran.afficher("Souhaitez-vous stocker votre carte pour l'utiliser plus tard ? (o/n) ");
		rep = Clavier.saisirChar();
		while(rep != 'o' && rep != 'O' && rep != 'n' && rep != 'N') {
			Ecran.afficher("Souhaitez-vous stocker votre carte pour l'utiliser plus tard ? (o/n) ");
			rep = Clavier.saisirChar();
		}

		if(rep == 'o' || rep == 'O') {
			Ecran.afficherln("Carte stocké !");
			switch(carte) {
				case 21:
					jr.carteRpt = true;
					break;
				case 22:
					jr.carteEss = true;
					break;
				case 23:
					jr.carteRds = true;
					break;
			}
		} else {
			Ecran.afficherln("La carte n'est pas stocké.");
		}
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

	/**
	 * Saisir les noms des joueurs
	 *
	 * @param j1 Premier joueur
	 * @param j2 Deuxième joueur
	 */
	public static void saisirNom(Joueur j1, Joueur j2) {
		Ecran.afficher("Saisir le nom du joueur 1: ");
		j1.nom = Clavier.saisirString();
		Ecran.afficher("Saisir le nom du joueur 2: ");
		j2.nom = Clavier.saisirString();
	}

	/**
	 * Afficher les informations d'un joueur
	 *
	 * @param jr Joueur à afficher
	 */
	public static void afficherJoueur(Joueur jr) {
		Ecran.afficherln(jr.nom, " - ", jr.km, "km");

		Ecran.afficher(" - Carte attaque: ");
		if(jr.carteAcc)
			Ecran.afficher("Accident");
		if(jr.cartePde)
			Ecran.afficher("Panne d'essence");
		if(jr.carteCre)
			Ecran.afficher("Crevaison");
		if(!jr.carteAcc && !jr.cartePde && !jr.carteCre)
			Ecran.afficher("aucune");

		Ecran.afficher("\n - Carte défense: ");
		if(jr.carteRpt)
			Ecran.afficher("Réparation ");
		if(jr.carteEss)
			Ecran.afficher("Essence ");
		if(jr.carteRds)
			Ecran.afficher("Roue de secours");
		if(!jr.carteRpt && !jr.carteEss && !jr.carteRds)
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
		/** Vérifier que le joueur n'est pas bloqué par une carte attaque */
		if(!estBloque(jr)) {
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
		} else {
			Ecran.afficher("Vous êtes déjà bloqué par une carte attaque.");
		}
	}

	/**
	 * Ajouter une attaque du Joueur 1 au Joueur 2
	 *
	 * @param carte Numéro de la carte
	 * @param j1 Joueur qui joue
	 * @param j2 Joueur adverse
	 */
	public static void ajouterAttaque(int carte, Joueur j1, Joueur j2) {
		/** Vérifie si le joueur adverse est déjà bloqué par une carte attaque */
		if(!estBloque(j2)) {
			if(carte == 11) { // carte accident
				j2.carteAcc = true;
			} else {
				if(carte == 12) { // carte panne d'essence
					j2.cartePde = true;
				} else { // carte crevaison
					j2.carteCre = true;
				}
			}
		} else {
			Ecran.afficherln("Le joueur ", j2.nom, "possède déjà cette carte. Vous ne pouvez pas lui en donner une seconde.");
		}
	}

	/**
	 * Ajouter une défense à un joueur
	 *
	 * @param carte Numéro de la carte
	 * @param jr Joueur qui joue
	 */
	public static void ajouterDefense(int carte, Joueur jr) {
		if(estBloque(jr)) {
			switch(carte) {
				case 21:
					if(jr.carteAcc) {
						Ecran.afficher("La carte réparation annule la carte accident.");
						jr.carteAcc = false;
					} else {
						Ecran.afficherln("Vous n'êtes pas bloqué par une carte accident pour le moment.");
						stockerCarte(carte, jr);
					}
					break;
				case 22:
					if(jr.cartePde) {
						Ecran.afficher("La carte essence annule la carte panne d'essence.");
						jr.cartePde = false;
					} else {
						Ecran.afficherln("Vous n'êtes pas bloqué par une carte accident pour le moment.");
						stockerCarte(carte, jr);
					}
					break;
				case 23:
					if(jr.carteCre) {
					Ecran.afficher("La carte roue de secours annule la carte crevaison.");
					jr.carteCre = false;
				} else {
					Ecran.afficherln("Vous n'êtes pas bloqué par une carte crevaison pour le moment.");
					stockerCarte(carte, jr);
					}
				break;
			}
		} else {
			Ecran.afficherln("Vous n'êtes pas bloqué par une carte attaque pour le moment.");
			stockerCarte(carte, jr);
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

		// saisie des noms
		saisirNom(j1, j2);

		/*
		 * DEBUG
		 */

		// tour de jeu (pour Joueur 1)
		Ecran.sautDeLigne();
		Ecran.afficherln(j1.nom, " commence à jouer...");
		if(estBloque(j1)) {
			Ecran.afficherln("Vous êtes bloqué par une carte.");
		} else {
			int carte = tirerCarte(crt);
			choisirActionCarte(carte, j1, j2);
		}

		// affichage des joueurs
		Ecran.sautDeLigne();
		afficherJoueur(j1);
		Ecran.sautDeLigne();
		afficherJoueur(j2);

		// CHANGEMENT DE JOUEUR

		// tour de jeu (pour Joueur 2)
		Ecran.sautDeLigne();
		Ecran.sautDeLigne();
		Ecran.afficherln(j2.nom, " joue,..");
		if(estBloque(j2)) {
			Ecran.afficherln("Vous êtes bloqué par une carte.");
		} else {
			int carte = tirerCarte(crt);
			choisirActionCarte(carte, j2, j1);
		}

		// affichage des joueurs
		Ecran.sautDeLigne();
		afficherJoueur(j1);
		Ecran.sautDeLigne();
		afficherJoueur(j2);
	}
}
