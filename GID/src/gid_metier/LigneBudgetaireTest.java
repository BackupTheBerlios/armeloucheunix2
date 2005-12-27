package gid_metier;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

public class LigneBudgetaireTest extends TestCase {

	/*
	 * Test method for 'gid_metier.LigneBudgetaire.chargeParId(int)'
	 */
	public void testChargeParId() {

		LigneBudgetaire lb = new LigneBudgetaire();
		try{
			lb.chargeParId(10);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println(lb.getLibelle());
		
	}

	/*
	 * Test method for 'gid_metier.LigneBudgetaire.sauver()'
	 */
	public void testSauver() {
	/*	LigneBudgetaire lb = new LigneBudgetaire();
		lb.setLibelle("mission");
		try{
			lb.sauver();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}*/
	}

	/*
	 * Test method for 'gid_metier.LigneBudgetaire.supprimer()'
	 */
	public void testSupprimer() {
		
		LigneBudgetaire lb = new LigneBudgetaire();
		lb.setId(8);
		try{
			lb.supprimer();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Test method for 'gid_metier.LigneBudgetaire.retournerTous()'
	 */
	public void testRetournerTous() {
		ArrayList al;
		al = null;
		try{
			
			al = LigneBudgetaire.retournerTous();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		Iterator iterat = al.iterator();
		while(iterat.hasNext()){
			LigneBudgetaire current = (LigneBudgetaire)iterat.next();
			System.out.print(current.getId());
			System.out.print("      ");
			System.out.println(current.getLibelle());
		}
	
	}

	/*
	 * Test method for 'gid_metier.LigneBudgetaire.retournerChapitres()'
	 */
	public void testRetournerChapitres() {

	}

}
