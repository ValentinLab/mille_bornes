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
		// nombre global de cartes
		int nbCartes = 100;
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
	public static void choisirActionCarte(int carte, Joueur jr) {
		if(carte < 20) { // carte Attaque
			Ecran.afficherln("Attaque !");
		} else {
			if(carte < 30) { // carte Defense
				Ecran.afficherln("Défense !");
			} else { // carte Distance
				ajouterKm(carte, jr);
			}
		}
	}

	// ******************************
	//  Type agrégé Joueur
	// ******************************

	/**
	 * Modélisation d'un joueur.
	 * Le type agrégé contient le nom et le nombre de kilomètres
	 */
	public static class Joueur {
		String nom;
		int km = 0;
	}

	/**
	 * Ajouter des kilomètres à un joueur
	 *
	 * @param carte Numéro de la carte
	 * @param jr Joueur qui joue
	 */
	public static void ajouterKm(int carte, Joueur jr) {
		if(carte == 31) {
			jr.km += 25;
		} else {
			if(carte == 32) {
				jr.km += 50;
			} else {
				jr.km += 100;
			}
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
		j1.nom = "Valentin";

		// DEBUG
		int carte = tirerCarte(crt);
		choisirActionCarte(carte, j1);
		Ecran.afficherln(j1.km);
	}
}
