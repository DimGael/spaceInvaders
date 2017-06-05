package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	/**
	 * Méthode statique vérifiant s'il y a une collision entre sprite1 et sprite2
	 * @param sprite1
	 * @param sprite2
	 * @return vrai si les deux sprites sont en collision
	 */
	public static boolean detecterCollision(Sprite sprite1, Sprite sprite2) {
		return sprite1EtSprite2OntAuMoinsUnPointDabscisseCommun(sprite1, sprite2)
				&& sprite1EtSprite2OntAuMoinsUnPointDordonneeCommun(sprite1, sprite2);
	}

	public static boolean sprite1EtSprite2OntAuMoinsUnPointDordonneeCommun(Sprite sprite1, Sprite sprite2) {
		return ordonneeLaPlusBasseDeSprite1EstDansOrdonneesSprite2(sprite2, sprite1) ||
				ordonneeLaPlusHauteDeSprite1EstDansOrdonneesSprite2(sprite2, sprite1);
	}

	public static boolean sprite1EtSprite2OntAuMoinsUnPointDabscisseCommun(Sprite sprite1, Sprite sprite2) {
		return abscisseAGaucheDeSprite1EstDansLesAbscissesSprite2(sprite1, sprite2) ||
				abscisseADroiteDeSprite1EstDansLesAbscissesSprite2(sprite1, sprite2);
	}

	public static boolean ordonneeLaPlusHauteDeSprite1EstDansOrdonneesSprite2(Sprite sprite1, Sprite sprite2) {
		return sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusBasse() &&
		sprite1.ordonneeLaPlusHaute() <= sprite2.ordonneeLaPlusHaute();
	}

	public static boolean ordonneeLaPlusBasseDeSprite1EstDansOrdonneesSprite2(Sprite sprite1, Sprite sprite2) {
		return sprite1.ordonneeLaPlusBasse() >= sprite2.ordonneeLaPlusBasse() &&
				sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusHaute();
	}

	public static boolean abscisseADroiteDeSprite1EstDansLesAbscissesSprite2(Sprite sprite1, Sprite sprite2) {
		return sprite2.abscisseLaPlusADroite() >= sprite1.abscisseLaPlusAGauche() &&
		sprite2.abscisseLaPlusADroite() <= sprite1.abscisseLaPlusADroite();
	}

	public static boolean abscisseAGaucheDeSprite1EstDansLesAbscissesSprite2(Sprite sprite2, Sprite sprite1) {
		return sprite1.abscisseLaPlusAGauche() >= sprite1.abscisseLaPlusAGauche() &&
				sprite1.abscisseLaPlusAGauche() <= sprite1.abscisseLaPlusADroite();
	}
	
}
