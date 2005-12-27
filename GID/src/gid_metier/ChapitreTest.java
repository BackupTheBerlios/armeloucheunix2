package gid_metier;

import junit.framework.TestCase;

public class ChapitreTest extends TestCase {

	/*
	 * Test method for 'gid_metier.Chapitre.chargeParId(int)'
	 */
	public void testChargeParId() {
		
		Chapitre chap = new Chapitre();
		try{
			chap.chargeParId(13);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println(chap.getLigneBudgetaire().getLibelle());
	}

	/*
	 * Test method for 'gid_metier.Chapitre.sauver()'
	 */
	public void testSauver() {

	}

	/*
	 * Test method for 'gid_metier.Chapitre.supprimer()'
	 */
	public void testSupprimer() {

	}

	/*
	 * Test method for 'gid_metier.Chapitre.retournerTous()'
	 */
	public void testRetournerTous() {

	}

}
