/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

/** 
 * Contains information of a customer
 *
 * Created by Aria Pahlavan
 */
public class Customer {

	private String customerName;
	private long customerNumber;
	private String customerAddress;
	private CheckingAccount checkingAccount;
	private SavingAccount savingAccount;
	private AutoLoanRepaymentAccount autoLoanRepaymentAccount;	
	private StudentLoanRepayment studentLoanRepayment;
	

	//Constructors	
	/**
	 * Default constructor
	 */
	public Customer() {
		this.customerName = "Unknown";
		this.customerNumber = -1;
		this.customerAddress = "Unknown";
		this.checkingAccount = null;
		this.savingAccount = null;
		this.autoLoanRepaymentAccount = null;
		this.studentLoanRepayment = null;
	}


	/**
	 * @param customerName
	 * @param customerNumber
	 */
	public Customer(String customerName, long customerNumber) {
		this();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
	}

	/**
	 * @param customerName
	 * @param customerNumber
	 * @param studentLoanRepayment
	 */
	public Customer(String customerName, long customerNumber, StudentLoanRepayment studentLoanRepayment) {
		this();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.studentLoanRepayment = new StudentLoanRepayment(customerNumber, customerName, 0.00);
	}

	/**
	 * @param customerName
	 * @param customerNumber
	 * @param autoLoanRepaymentAccount
	 */
	public Customer(String customerName, long customerNumber, AutoLoanRepaymentAccount autoLoanRepaymentAccount) {
		this();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.autoLoanRepaymentAccount = new AutoLoanRepaymentAccount(customerNumber, customerName, 0.00);
	}

	/**
	 * @param customerName
	 * @param customerNumber
	 * @param savingAccount
	 */
	public Customer(String customerName, long customerNumber, SavingAccount savingAccount) {
		this();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.savingAccount = new SavingAccount(customerNumber, customerName, 0.00);
	}

	/**
	 * @param customerName
	 * @param customerNumber
	 * @param checkingAccount
	 */
	public Customer(String customerName, long customerNumber, CheckingAccount checkingAccount) {
		this();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.checkingAccount = new CheckingAccount(customerNumber, customerName, 0.00);
	}


	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress
	 *            the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the customerNumber
	 */
	public long getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(long customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @return the checkingAccount
	 */
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	/**
	 * @param checkingAccount the checkingAccount to set
	 */
	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	/**
	 * @return the savingAccount
	 */
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}

	/**
	 * @param savingAccount the savingAccount to set
	 */
	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}

	/**
	 * @return the autoLoanRepaymentAccount
	 */
	public AutoLoanRepaymentAccount getAutoLoanRepaymentAccount() {
		return autoLoanRepaymentAccount;
	}

	/**
	 * @param autoLoanRepaymentAccount the autoLoanRepaymentAccount to set
	 */
	public void setAutoLoanRepaymentAccount(AutoLoanRepaymentAccount autoLoanRepaymentAccount) {
		this.autoLoanRepaymentAccount = autoLoanRepaymentAccount;
	}

	/**
	 * @return the studentLoanRepayment
	 */
	public StudentLoanRepayment getStudentLoanRepayment() {
		return studentLoanRepayment;
	}

	/**
	 * @param studentLoanRepayment the studentLoanRepayment to set
	 */
	public void setStudentLoanRepayment(StudentLoanRepayment studentLoanRepayment) {
		this.studentLoanRepayment = studentLoanRepayment;
	}

}
