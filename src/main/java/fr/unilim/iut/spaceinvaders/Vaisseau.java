package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	
	Position origine;
	Dimension dimension;

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}
	
	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this.dimension = new Dimension(longueur, hauteur);
		this.origine = new Position(x, y);
	}

	public Vaisseau(Dimension dimension, Position positionOrigine) {
		this.dimension = dimension;
		this.origine = positionOrigine;
	}
	
	public boolean occupeLaPosition(int x, int y) {
		if (estAbscisseCouverte(x) && estOrdonneeCouverte(y))
			return true;

		return false;
	}

	private boolean estOrdonneeCouverte(int y) {
		return (ordonneeLaPlusBasse() <= y) && (y <= ordonneeLaPlusHaute());
	}

	private int ordonneeLaPlusHaute() {
		return this.origine.ordonnee();
	}

	public int ordonneeLaPlusBasse() {
		return ordonneeLaPlusHaute() - this.dimension.hauteur() + 1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (this.abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.origine.abscisse() + this.dimension.longueur() - 1;
	}

	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}

	public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse() + 1);
	}
	
	public void seDeplacerVersLaGauche() {
		 this.origine.changerAbscisse(this.origine.abscisse()-1);
	}

	public void positionner(int x, int y) {
		this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);
	}
}