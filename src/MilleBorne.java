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
		int nbCartes = 102;
		int carteAttaque = 18;
		int carteDefense = 38;
		int carteDistance = 46;
	}

	/**
	 * Tirer au sort une carte dans le paquet
	 * @param crt Paquet de carte
	 * @return Le type de carte retiré
	 */
	public static int tirerCarte(Carte crt) {
		// déclaration des données
		int typeCarte;

		// tirage au sort d'un type de carte
		typeCarte = nbHasard(1, 3);

		// tirage précis de la carte
		switch(typeCarte) {

		}

		return 0;
	}

	// ******************************
	//  Fonctions & Actions
	// ******************************

	public static int nbHasard(int borne_inf, int borne_sup) {
		return(borne_inf + (int)(Math.random() * (borne_sup - borne_inf + 1)));
	}

	// ******************************
	//  Main
	// ******************************

	public static void main(String args[]) {
		Carte crt = new Carte();
	}
}
