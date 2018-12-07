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
	static class Carte {
		// nombre global de cartes
		int nbCartes = 100;

		// nombre de carte par catégorie
		int carteAttaque = 20;
		int carteDefense = 35;
		int carteDistance = 45;
	}

	/**
	 * Tirer au sort une carte dans le paquet
	 *
	 * @param crt Paquet de carte
	 * @return Le type de carte retiré (numéro)
	 */
	public static int tirerCarte(Carte crt) {
		// déclaration des données
		int typeCarte, numCarte = 0;

		// tirage d'un type de carte
		typeCarte = nbHasard(1, 100);

		// tirage de la carte selon son type
		if(typeCarte >= 1 && typeCarte <= 20) { // Type 1

		} else {
			if(typeCarte >= 21 && typeCarte <= 55) { // Type 2

			} else { // Type 3

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
		Carte crt = new Carte();
		Ecran.afficherln(tirerCarte(crt));
	}
}
