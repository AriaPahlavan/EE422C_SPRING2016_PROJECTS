/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

/**
 * Created by Aria Pahlavan
 *
 */
public class StudentLoanRepayment extends BankAccount {

	/**
	 * @param initialBalance
	 */
	public StudentLoanRepayment(double initialBalance) {
		super(initialBalance);
	}

	/**
	 * @param acct
	 * @param owner
	 * @param initBalance
	 */
	public StudentLoanRepayment(long acct, String owner, double initBalance) {
		super(acct, owner, initBalance);
	}

}
