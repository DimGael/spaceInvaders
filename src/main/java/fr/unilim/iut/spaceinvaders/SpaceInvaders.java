package fr.unilim.iut.spaceinvaders;

public class SpaceInvaders {

	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V';
	private final int hauteur;
	private final int longueur;
	private Vaisseau vaisseau;

	public SpaceInvaders(int longueur, int hauteur) {
		if (longueur <= 0 || hauteur <= 0) {
			throw new IllegalArgumentException(
					"La largeur et la hauteur doivent être supérieurs à 0");
		}
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	@Override
	public String toString() {
		return recupererEspaceDeJeuChaineASCII();
	}

	private String recupererEspaceDeJeuChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < this.hauteur; y++) {
			for (int x = 0; x < this.longueur; x++) {
				espaceDeJeu.append(this.recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = MARQUE_VAISSEAU;
		else
			marque = MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && this.vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return this.vaisseau != null;
	}

	public void positionnerUnNouveauVaisseau(int x, int y) {
		// Contrôler que les coordonnées du vaisseau soient corrects
		// (x<=longueur et y<=hauteur)
		this.vaisseau = new Vaisseau(x, y);
	}

}
