package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	/**
	 * Méthode statique vérifiant s'il y a une collision entre sprite1 et sprite2
	 * @param missile
	 * @param envahisseur
	 * @return vrai si les deux sprites sont en collision
	 */
	public static boolean detecterCollision(Sprite missile, Sprite envahisseur){

//		for(int abscisse = sprite1.abscisseLaPlusAGauche(); abscisse <= sprite1.abscisseLaPlusADroite(); abscisse++){
//			for(int ordonnee = sprite1.ordonneeLaPlusBasse(); ordonnee <= sprite2.ordonneeLaPlusHaute(); ordonnee++){
//				if(sprite2.occupeLaPosition(abscisse, ordonnee)){
//					System.out.println("Abscisse = "+abscisse+", ordonnée = "+ordonnee);
//					return true;
//				}
//			}
//		}
//		return false;
		
		//sprite1 = missile
		//sprite2 = envahisseur
		boolean collisionAbscisse = false;
		boolean collisionOrdonnee = false;

		//Si sprite1 et sprite2 ont au moins un point d'abscisse en commun
		if((envahisseur.abscisseLaPlusAGauche() >= missile.abscisseLaPlusAGauche() &&
				envahisseur.abscisseLaPlusAGauche() <= missile.abscisseLaPlusADroite()) ||
				envahisseur.abscisseLaPlusADroite() >= missile.abscisseLaPlusAGauche() &&
				envahisseur.abscisseLaPlusADroite() <= missile.abscisseLaPlusADroite())
			collisionAbscisse = true;
		
		if((missile.abscisseLaPlusAGauche() >= envahisseur.abscisseLaPlusAGauche() &&
				missile.abscisseLaPlusAGauche() <= envahisseur.abscisseLaPlusADroite()) ||
				missile.abscisseLaPlusADroite() >= envahisseur.abscisseLaPlusAGauche() &&
				missile.abscisseLaPlusADroite() <= envahisseur.abscisseLaPlusADroite())
			collisionAbscisse = true;
		
		//Si sprite1 et sprite2 ont au moins un point de coordonée en commun
		if((envahisseur.ordonneeLaPlusBasse() >= missile.ordonneeLaPlusBasse() &&
				envahisseur.ordonneeLaPlusBasse() <= missile.ordonneeLaPlusHaute()) ||
				envahisseur.ordonneeLaPlusHaute() >= missile.ordonneeLaPlusBasse() &&
				envahisseur.ordonneeLaPlusHaute() <= missile.ordonneeLaPlusHaute())
			collisionOrdonnee = true;
		
		if((missile.ordonneeLaPlusBasse() >= envahisseur.ordonneeLaPlusBasse() &&
				missile.ordonneeLaPlusBasse() <= envahisseur.ordonneeLaPlusHaute()) ||
				missile.ordonneeLaPlusHaute() >= envahisseur.ordonneeLaPlusBasse() &&
				missile.ordonneeLaPlusHaute() <= envahisseur.ordonneeLaPlusHaute())
			collisionOrdonnee = true;
		
		return collisionAbscisse&&collisionOrdonnee;
	}
	
}
