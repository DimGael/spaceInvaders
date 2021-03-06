package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.Vaisseau;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvadersTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	// Test de positionnement d'un vaisseau
	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		// Assert
		assertEquals("" + "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n",
				spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {

		// Act
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(7,9), 1);

		// Assert
		assertEquals("" + "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ ".......V.......\n",
				spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......VVV.....\n" + 
		".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
    
    @Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2), new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4), new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
			
	}

    @Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {
	
	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "..........VVV..\n" + 
	       "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
       spaceinvaders.deplacerVaisseauVersLaGauche();

       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "....VVV........\n" + 
       "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
    
	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
		
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(12,9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"............VVV\n" + 
		"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {
		
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(0,9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"VVV............\n" + 
		"VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
       spaceinvaders.deplacerVaisseauVersLaDroite();
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "............VVV\n" + 
       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
    
    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
       spaceinvaders.deplacerVaisseauVersLaGauche();

       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "VVV............\n" + 
       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }
    
    @Test
    public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
	   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

      assertEquals("" + 
      "...............\n" + 
      "...............\n" +
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      ".......MMM.....\n" + 
      ".......MMM.....\n" + 
      ".....VVVVVVV...\n" + 
      ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
    
	@Test(expected = MissileException.class)
	public void test_LongueurMissileSuperieureALongueurVaisseau_UneExceptionEstLevee() throws Exception {
		Vaisseau vaisseau = new Vaisseau(new Dimension(5, 2), new Position(5, 9), 1);
		vaisseau.tirerUnMissile(new Dimension(7, 2), 1);
	}
	
	@Test
	public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);

		spaceinvaders.deplacerMissile();

		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		".......MMM.....\n" + 
		".......MMM.....\n" + 
		"...............\n" + 
		"...............\n" + 
		".....VVVVVVV...\n" + 
		".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	   public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		   spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
		   for (int i = 1; i <=6 ; i++) {
			   spaceinvaders.deplacerMissile();
		   }
		   
		   spaceinvaders.deplacerMissile();
		   
	       assertEquals("" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
	@Test
	public void test_unNouvelEnvahisseurEstCorrectementPositionneDansEspaceJeu(){
		
		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
	
		// Assert
				assertEquals("" + ".......WW......\n" 
								+ ".......WW......\n"
								+ "...............\n" 
								+ "...............\n"
								+ "...............\n" 
								+ "...............\n"
								+ "...............\n" 
								+ "...............\n"
								+ "...............\n" 
								+ "...............\n",
						spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
	}
	
	@Test
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {
	
		// Arrange
	    spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
	    spaceinvaders.deplacerEnvahisseurVersLaDroite();
	       
		// Assert
		assertEquals("" + "........WW.....\n" 
						+ "........WW.....\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n",
				spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {
	
		// Arrange
	    spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
	    spaceinvaders.deplacerEnvahisseurVersLaGauche();
	       
		// Assert
		assertEquals("" + "......WW.......\n" 
						+ "......WW.......\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n"
						+ "...............\n" 
						+ "...............\n",
				spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaDroite() {
		
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(13,1), 1);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + 
		".............WW\n" + 
		".............WW\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {
		
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(0,1), 1);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();
		assertEquals("" + 
		"WW.............\n" + 
		"WW.............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaDroite() {
		
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(12,1), 3);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + 
		".............WW\n" + 
		".............WW\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaGauche() {
		
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(2,1), 3);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();
		assertEquals("" + 
		"WW.............\n" + 
		"WW.............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurChangeDeDirectionDeDroiteAGauche(){
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(12,1), 1);
		for (int i = 0; i<2; i++){
			spaceinvaders.deplacerEnvahisseur();
		}
		
		assertEquals("" + 
		"............WW.\n" + 
		"............WW.\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurChangeDeDirectionDeGaucheADroite(){
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(12,1), 1);
		for (int i = 0; i<15; i++){
			spaceinvaders.deplacerEnvahisseur();
		}
		
		assertEquals("" + 
		".WW............\n" + 
		".WW............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_LeJeuEstFini(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,5), 1);
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		spaceinvaders.deplacerMissile();
		
		assertEquals(true, spaceinvaders.etreFini());
	}
	
	@Test
	public void test_LeJeuNEstPasFini(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,5), 1);
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		
		assertEquals(false, spaceinvaders.etreFini());
	}

}
