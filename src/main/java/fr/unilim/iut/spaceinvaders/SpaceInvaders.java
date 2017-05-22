package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurJeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurJeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class SpaceInvaders implements Jeu{
	
	private final int hauteur;
	private final int longueur;
	private Vaisseau vaisseau;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	public void initialiserJeu(){
		this.positionnerUnNouveauVaisseau(new Dimension(Constante.VAISSEAU_LONGUEUR,Constante.VAISSEAU_HAUTEUR) ,new Position(this.longueur/2, this.hauteur-1));
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < this.hauteur; y++) {
			for (int x = 0; x < this.longueur; x++) {
				espaceDeJeu.append(this.recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}
	
	public Vaisseau recupererVaisseau(){
		return this.vaisseau;
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && this.vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return this.vaisseau != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < this.longueur)) && ((y >= 0) && (y < this.hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1))
			vaisseau.seDeplacerVersLaDroite();
	}

	public void deplacerVaisseauVersLaGauche() {
		 if (vaisseau.abscisseLaPlusAGauche() > 0) 
			 vaisseau.seDeplacerVersLaGauche();
	}
	
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException(
					"La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(longueurVaisseau, hauteurVaisseau);
		vaisseau.positionner(x, y);
	}
	

	public void evoluer(Commande commandeUser) {
		int nbDeplacementGauche = 5;
		if (commandeUser.gauche) {
			for (int i = 0; i < nbDeplacementGauche; i++) {
				deplacerVaisseauVersLaGauche();
			}
		}

		if (commandeUser.droite) {
			for (int i = 0; i < nbDeplacementGauche; i++) {
				deplacerVaisseauVersLaDroite();
			}
		}
	}

	public boolean etreFini() {
		return false;
	}
}
