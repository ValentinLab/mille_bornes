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
	 * @return Le type de carte retiré (numéro)
	 */
	public static int tirerCarte(Carte crt) {
		// déclaration des données
		int typeCarte = 0, numCarte = 0;

		// tirage au sort d'un type de carte
		typeCarte = nbHasard(1, 3);

		// tirage précis de la carte
		int nb;
		switch(typeCarte) {
			case 1: // carte de type 1
				nb = nbHasard(1, 18);
				if(nb >= 1 && nb <= 3) {
					numCarte = 11;
				} else {
					if(nb >= 4 && nb <= 6) {
						numCarte = 12;
					} else {
						if(nb >= 7 && nb <= 9) {
							numCarte = 13;
						} else {
							if(nb >= 10 && nb <= 13) {
								numCarte = 14;
							} else {
								numCarte = 15;
							}
						}
					}
				}
				break;
			case 2: // carte de type 2
				nb = nbHasard(1, 38);
				if(nb >= 1 && nb <= 6) {
					numCarte = 21;
				} else {
					if(nb >= 7 && nb <= 12) {
						numCarte = 22;
					} else {
						if(nb >= 13 && nb <= 18) {
							numCarte = 23;
						} else {
							if(nb >= 19 && nb <= 24) {
								numCarte = 24;
							} else {
								numCarte = 25;
							}
						}
					}
				}
				break;
			case 3: // carte de type 3
				nb = nbHasard(1, 46);
				if(nb >= 1 && nb <= 10) {
					numCarte = 31;
				} else {
					if(nb >=11 && nb <= 20) {
						numCarte = 32;
					} else {
						if(nb >= 21 && nb <= 30) {
							numCarte = 33;
						} else {
							if(nb >= 31 && nb <= 42) {
								numCarte = 34;
							} else {
								numCarte = 35;
							}
						}
					}
				}
				break;
		}

		return numCarte;
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
