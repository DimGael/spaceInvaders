package fr.unilim.iut.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurJeu.DessinJeu;

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
	}
	
	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage image){
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		g2d.setColor(Color.GRAY);
		g2d.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.getDimension().longueur(), vaisseau.getDimension().hauteur());
	}

}
