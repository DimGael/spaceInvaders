package fr.unilim.iut.spaceinvaders;

public class SpaceInvaders {

	private final int hauteur;
	private final int longueur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	@Override
	public String toString() {
		String espaceDeJeu = "";
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.longueur; j++) {
				espaceDeJeu += ".";
			}
			espaceDeJeu += "\n";
		}
		return espaceDeJeu;
	}

}
