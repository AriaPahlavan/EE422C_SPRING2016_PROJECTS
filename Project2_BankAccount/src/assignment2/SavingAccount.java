/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

/**
 * Created by Aria Pahlavan
 */
public class SavingAccount extends BankAccount {

	public SavingAccount(double initialBalance) {
		super(initialBalance);
	}

	public SavingAccount(long acct, String owner, double initBalance) {
		super(acct, owner, initBalance);
	}
	
	
	/**
	 * Compute and add in interest for a saving account
	 * 
	 * @author Aria Pahlavan
	 */
	public boolean intrestCalculator() {
		//calculate and add in interest iff the min balance is $1000, otherwise report the failure
		if(balance >= 1000) {
			balance = (balance * 104) / 100;
			return true;
		}
		else 
			return false;
	}


}
