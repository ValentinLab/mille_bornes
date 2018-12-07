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
		int typeCarte, numCarte, carte = 0;

		// initialisation des variables
		/** le type de carte (Attaque, Défense, Distance) est compris entre 1 et 100
		 *  c'est une des 100 cartes possibles */
		typeCarte = nbHasard(1, 100); // type de carte
		/** chaque type de carte contient 5 cartes différentes */
		numCarte = nbHasard(1, 5); // numéro de la carte

		// traitement
		if(typeCarte >=1 && typeCarte <= 20) { // cartes Attaque
			typeCarte = 1;
		} else {
			if(typeCarte >= 21 && typeCarte <= 55) { // cartes Defense
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
	 * Modélisation d'un joueur.
	 * Le type agrégé contient le nom et le nombre de kilomètres
	 */
	public static class Joueur {
		String nom;
		int km = 0;
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
		if(carte > 30) {
			if(carte == 31) {
				j1.km += 25;
			} else {
				if(carte == 32) {
					j1.km += 50;
				} else {
					if(carte == 33) {
						j1.km += 75;
					} else {
						if(carte == 34) {
							j1.km += 100;
						} else {
							j1.km += 200;
						}
					}
				}
			}
		}
		Ecran.afficherln(j1.km);
	}
}
