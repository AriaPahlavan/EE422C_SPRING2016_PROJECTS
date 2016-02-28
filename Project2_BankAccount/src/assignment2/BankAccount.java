/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

/**
 * Model for general bank account object. The purpose is to record money, and
 * allow for various financial transactions to be performed over the life of a
 * specific bank account.
 * 
 * @author ee422c teaching team
 */
public class BankAccount {
	// instance variables (protected to allow inheriting them)
	/**
	 * A unique number that identifies the account
	 */
	protected long accountNumber;
    private final double FEE_CHARGE = 20.00;

	/**
	 * The name of the person that this account belongs to
	 */
	protected String ownersName;

	/**
	 * the current value (in dollars) of the money in this account
	 */
	protected double balance;

	// constructors
	/**
	 * Create an account with an initial balance.
	 * 
	 * @param initialBalance
	 *            The initial balance of the account
	 */
	public BankAccount(double initialBalance) {
		accountNumber = -1;
		balance = initialBalance;
	}

	/**
	 * Create an account with initial parameters.
	 * 
	 * @param acct
	 *            The account number
	 * @param owner
	 *            The owner of the account
	 * @param initBalance
	 *            The initial balance of the account
	 */
	public BankAccount(long acct, String owner, double initBalance) {
		accountNumber = acct;
		ownersName = owner;
		if (initBalance <= 0)
			System.out.println("The initial balance is invalid (less than $0.01)");

		else
			balance = initBalance;
	}

	// balance changing methods
	/**
	 * Updates the current balance by adding in a given amount. Post condition:
	 * the new balance is increased by the amount.
	 * 
	 * @param amount
	 *            The amount to add
	 */
	public void deposit(double amount) {
		balance = balance + amount;
	}

	/**
	 * Update the current balance by subtracting the given amount. Precondition:
	 * the current balance must have at least the amount in it. Postcondition:
	 * the new balance is decreased by the given amount.
	 * 
	 * @param amount
	 *            The amount to subtract
	 */
	public void withdraw(double amount) {
        if (amount <= 0.00)
            System.out.println("The amount is invalid (less than $0.01)");

        if (balance >= amount) {
            balance = balance - amount;
            System.out.println("Succeeded: $" +amount+ " withdraw.");
        }
        else {
            System.out.println("Failed: $" +amount+ " withdraw.");
        }
    }

    /**
     * This withdraw is a special one because we can also withdraw from saving account if necessary
     *
     * @param amount
     */
    public void specialWithdraw(double amount, SavingAccount savingAccount){

        if (amount <= 0)
            System.out.println("The amount is to withdraw must be greater that $0.00");
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println("Succeeded: $" +amount+ " withdraw.");
        }
        else
        if ( (savingAccount.getBalance() + balance) - (amount + FEE_CHARGE) >= 0.00){
            double savingAccRemainder = (savingAccount.getBalance() + balance) - (amount + FEE_CHARGE);
            savingAccount.setBalance(savingAccRemainder);
            balance = 0.00;
            System.out.println("Succeeded: $" +amount+ " withdraw. Your saving account was charged $20.00");
        }
        else{
            System.out.println("Failed: $" +amount+ " withdraw.");
        }

    }

	// get and set methods
	/**
	 * @return The available balance.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return The account number.
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return The owner's name.
	 */
	public String getOwner() {
		return ownersName;
	}

	// set: postconditions- these all are used to set new values for the
	// instance variables
	/**
	 * Set the balance.
	 * 
	 * @param newBalance
	 *            The new balance.
	 */
	public void setBalance(double newBalance) {
		if (newBalance <= 0)
			System.out.println("The new balance is invalid (less than $0.01)");
		else
			balance = newBalance;
	}

	/**
	 * Set the account number.
	 * 
	 * @param newAcctNumber
	 *            The new account number.
	 */
	public void setAccountNumber(long newAcctNumber) {
		if (newAcctNumber < 1)
			System.out.println("The account numver is invalid.");
		
		else
			accountNumber = newAcctNumber;
	}

	/**
	 * Set the new owner of the account.
	 * 
	 * @param newOwner
	 */
	public void setOwner(String newOwner) {
		ownersName = newOwner;
	}


}
