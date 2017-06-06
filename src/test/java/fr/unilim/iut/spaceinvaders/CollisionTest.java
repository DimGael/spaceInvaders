package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class CollisionTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	@Test
	public void TestTirMissilePlusPetitQueLenvahisseurDoitDetecterCollision(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,5), 1);
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		spaceinvaders.deplacerMissile();
		
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"........WWW....\n" + 
				"........MWW....\n" + 
				"........M......\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
		assertEquals(true,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void TestTirMissilePlusGrosQueLenvahisseurDoitDetecterCollision(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(8,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7,5), 1);
		spaceinvaders.tirerUnMissile(new Dimension(6, 2), 1);
		spaceinvaders.deplacerMissile();
		
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				".......WWW.....\n" + 
				".......WMMMMMM.\n" + 
				"........MMMMMM.\n" + 
				"...............\n" + 
				".......VVVVVVVV\n" + 
				".......VVVVVVVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());

		assertEquals(true,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void TestTirMissilePlusPetitQueLenvahisseurNeDoitPasDetecterCollision(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,1), 1);
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		spaceinvaders.deplacerMissile();
		
		assertEquals("" + 
				"........WWW....\n" + 
				"........WWW....\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"........M......\n" + 
				"........M......\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
		assertEquals(false,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void TestAucuneCollisionDetectéCarPasDenvahisseur(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		
		for(int i = 0; i<6; i++){
			spaceinvaders.deplacerMissile();
		}
		
		assertEquals("" + 
				"........M......\n" + 
				"........M......\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
		assertEquals(false,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	
	
}