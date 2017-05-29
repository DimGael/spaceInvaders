package fr.unilim.iut.spaceinvaders.moteurJeu;

/**
 * permet de representer une commande de l'utilisateur
 * 
 * @author vthomas
 *
 */
public class Commande {

	/**
	 * boolean representant la commande de l'utilisateur
	 */
	public boolean gauche;
	public boolean droite;
	public boolean tir;
	public boolean bas;

	public Commande()
	{
		
	}
	
	/**
	 * constructeur par copie
	 * copie la commande pour en creer une nouvelle
	 * @param commandeACopier
	 */
	public Commande(Commande commandeACopier)
	{
		this.gauche=commandeACopier.gauche;
		this.droite=commandeACopier.droite;
		this.bas=commandeACopier.bas;
		this.tir=commandeACopier.tir;
	}
	
}
