/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

/**
 * @author Aria Pahlavan
 *
 */
public class AutoLoanRepaymentAccount extends BankAccount {

	/**
	 * @param initialBalance
	 */
	public AutoLoanRepaymentAccount(double initialBalance) {
		super(initialBalance);
	}

	/**
	 * @param acct
	 * @param owner
	 * @param initBalance
	 */
	public AutoLoanRepaymentAccount(long acct, String owner, double initBalance) {
		super(acct, owner, initBalance);
	}

}
