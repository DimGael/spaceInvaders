package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpaceInvadersTest {

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		// Création de l'espace de jeu
		SpaceInvaders spaceInvaders = new SpaceInvaders(15, 10);
		
		// Assert
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceInvaders.recupererEspaceDeJeuChaineASCII());
	}
	
	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		// Arrange
		SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
		
		// Act
		spaceinvaders.positionnerUnNouveauVaisseau(7,9);
		
		// Assert
		assertEquals("" + 
		"...............\n" +
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......V.......\n" , spaceinvaders.recupererEspaceDeJeuChaineASCII());
	}

}
