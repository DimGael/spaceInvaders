package fr.unilim.iut.spaceinvaders.moteurJeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * classe qui represente un controleur en lien avec un KeyListener
 * 
 * @author vthomas
 * 
 */
public class Controleur implements KeyListener {

	/**
	 * commande en cours
	 */
	private Commande commandeEnCours;
	/**
	 * commande a retourner la difference avec la commandeencours vient du fait
	 * qu'on veut memoriser une touche appuyee
	 */
	private  Commande commandeARetourner;

	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public Controleur() {
		this.commandeEnCours = new Commande();
		this.commandeARetourner = new Commande();
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Commande getCommande() {
		Commande aRetourner = this.commandeARetourner;
		this.commandeARetourner = new Commande(this.commandeEnCours);
		return (aRetourner);
	}

	
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyChar()) {
			case 'q':
				this.commandeEnCours.gauche = true;
				this.commandeARetourner.gauche = true;
				break;
			case 'd':
				this.commandeEnCours.droite = true;
				this.commandeARetourner.droite = true;
				break;
			case KeyEvent.VK_SPACE:
				this.commandeEnCours.tir = true;
				this.commandeARetourner.tir = true;
			default:break;
		}

	}

	
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'q':
			this.commandeEnCours.gauche = false;
			break;
		case 'd':
			this.commandeEnCours.droite = false;
			break;

		case KeyEvent.VK_SPACE:
			this.commandeEnCours.tir = false;
			break;
		default:break;
		}

	}

	
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
