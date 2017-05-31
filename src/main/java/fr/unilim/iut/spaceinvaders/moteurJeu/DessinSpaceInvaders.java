package fr.unilim.iut.spaceinvaders.moteurJeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.Vaisseau;

public class DessinSpaceInvaders implements DessinJeu {

	private SpaceInvaders jeu;
	
	
	
	/**
	 * 
	 */
	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.jeu = spaceInvaders;
	}



	public void dessiner(BufferedImage image) {
		if (this.jeu.aUnVaisseau()){
			Vaisseau vaisseau = this.jeu.recupererVaisseau();
			this.dessinerUnVaisseau(vaisseau, image);
		}
		
		if (this.jeu.aUnMissile()){
			Missile missile = this.jeu.recupererMissile();
			this.dessinerUnMissile(missile, image);
		}
		
		if (this.jeu.aUnEnvahisseur()){
			Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
			this.dessinerUnEnvahisseur(envahisseur, image);
		}
	}
	
	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage image){
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.getDimension().longueur(), vaisseau.getDimension().hauteur());
	}
	
	private void dessinerUnMissile(Missile missile, BufferedImage image){
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		g2d.setColor(Color.BLUE);
		g2d.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.getDimension().longueur(), missile.getDimension().hauteur());
	}
	
	private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage image){
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		g2d.setColor(Color.RED);
		g2d.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.getDimension().longueur(), envahisseur.getDimension().hauteur());
	}
}
