/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

/**
 *
 * @author Aria Pahlavan
 * @version 1.0
 */
public class CheckingAccount extends BankAccount {


	public CheckingAccount(double initialBalance) {
		super(initialBalance);
	}

	/**
	 * @param acct
	 * @param owner
	 * @param initBalance
     */
    public CheckingAccount(long acct, String owner, double initBalance) {
		super(acct, owner, initBalance);

	}


}
