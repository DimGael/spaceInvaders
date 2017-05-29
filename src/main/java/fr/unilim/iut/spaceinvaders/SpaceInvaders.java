package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurJeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurJeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu{
	
	private final int hauteur;
	private final int longueur;
	private Vaisseau vaisseau;
	private Missile missile;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
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
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && this.missile.occupeLaPosition(x, y);
	}
	
	public boolean aUnMissile() {
		return this.missile != null;
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
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}
	
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

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
		
		vaisseau = new Vaisseau(dimension, position, vitesse);
	}
	

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		
		   if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
							
		   this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
    }

	public Missile recupererMissile() {
		return this.missile;
	}

	public void deplacerMissile() {
		if (this.aUnMissile()) {
			this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);if (!estDansEspaceJeu(this.missile.abscisseLaPlusADroite(), this.missile.ordonneeLaPlusBasse())) {
				// On regarde si l'ordonn�e la plus basse est dans l'espace de
				// jeu car abscisse = 0 correspond � la ligne la plus en haut
				this.missile = null;
			}
		}

	}

	public boolean etreFini() {
		return false;
	}

	public void evoluer(Commande commandeUser) {
		if (commandeUser.gauche) {
				deplacerVaisseauVersLaGauche();
		}
	
		if (commandeUser.droite) {
				deplacerVaisseauVersLaDroite();
		}
		
		if (commandeUser.tir && !this.aUnMissile()){
	           tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
						Constante.MISSILE_VITESSE);
		   }
		
		if (this.aUnMissile()){
			this.deplacerMissile();
		}
	}

}
