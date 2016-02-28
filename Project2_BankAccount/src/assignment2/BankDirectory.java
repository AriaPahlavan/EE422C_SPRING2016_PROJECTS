/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Parses and processes the transaction using the corresponding tokens
 *
 * @author  Aria Pahlavan
 * @version 1.0.1
 */
public class BankDirectory {

    protected ArrayList<Customer> customersDirectory;
    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();


    /**
     * Default constructor - initializing member datum
     */
    public BankDirectory() {
        this.customersDirectory = new ArrayList<>();
    }

    /**
     * check to see if a customer with the corresponding account number exist or not.
     *
     * @param transaction contains the data for processing the operation
     */
    protected void processTransaction(Transaction transaction) {
        Customer tempCustomer;
        tempCustomer = customerExist(transaction.getAccType1(), transaction.getAccNumber());
        switch (transaction.getTransactionTypes()) {
            case DEPST:
                //if customer already exist,
                if (tempCustomer != null) {
                    BankAccount tempAccount = accountExist(transaction.getAccType1(), tempCustomer);
                    //and account already exist, go ahead and deposit the money
                    if (tempAccount != null) {
                        tempAccount.deposit(transaction.getAmount());
                        System.out.println("Glad to see you again! " + currencyFormatter.format(transaction.getAmount()) +" was deposited to your account.");
                    }
                        //but account doesn't exist, create one and then deposit the money
                    else {
                        createNewAccount(tempCustomer, transaction.getAccType1(), transaction.getAmount());
                        System.out.println("Glad to see you again! " + currencyFormatter.format(transaction.getAmount()) +" was deposited to your new account.");
                    }
                }
                //if customer doesn't exist, create one customer and an account for them, then deposit the money
                else {
                    tempCustomer = new Customer("New Customer", transaction.getAccNumber());
                    createNewAccount(tempCustomer, transaction.getAccType1(), transaction.getAmount());
                    customersDirectory.add(tempCustomer);
                    System.out.println("Welcome to ARIA bank! " + currencyFormatter.format(transaction.getAmount()) +" was deposited to your new account.");
                }
                break;
            case TRNSFR:
                //if customer already exist,
                if (tempCustomer != null) {
                    BankAccount tempAccount1 = accountExist(transaction.getAccType1(), tempCustomer);
                    BankAccount tempAccount2 = accountExist(transaction.getAccType2(), tempCustomer);

                    //and both accounts already exist, go ahead and transfer the money
                    if (tempAccount1 != null && tempAccount2 != null) {
                        if (tempAccount1.getBalance() >= transaction.getAmount()) {
                            tempAccount1.setBalance(tempAccount1.getBalance() - transaction.getAmount());
                            tempAccount2.setBalance(tempAccount2.getBalance() + transaction.getAmount());
                            System.out.println("Succeed: " +currencyFormatter.format(transaction.getAmount())+ " transferred to your account");
                        }
                        else
                            System.out.println("Failed: Transfer cannot be completed due to low balance.");
                    }
                    //but doesn't have the account, then report an error
                    else {
                        if (tempAccount1 == null)
                            System.out.println("Fail: Transfer Not Complete. The customer does not have the specified account");
                        else {
                            if (tempAccount2 == null) {
                                createNewAccount(tempCustomer, transaction.getAccType2(), transaction.getAmount());
                                tempAccount1.setBalance(tempAccount1.getBalance() - transaction.getAmount());
                                System.out.println("Succeed: " +currencyFormatter.format(transaction.getAmount())+ " transferred to your account");
                            }
                            else
                                System.out.println("Error: please retry your transaction.");
                        }
                    }
                }
                //if customer doesn't exist report an error
                else {
                    System.out.println("Fail: Transfer Not Complete. The customer does not exist in bank directory.");
                }

                break;
            case INTRST:
                //if customer already exist,
                if (tempCustomer != null) {
                    BankAccount tempAccount = accountExist(transaction.getAccType1(), tempCustomer);
                    //and account already exist, go ahead and output the interest
                    if (tempAccount != null) {
                        double interest = 0.00;
                        if(transaction.getAccType1().equals("S")) {
                            if (tempAccount.getBalance() > 1000) {
                                interest = tempAccount.getBalance() * 0.04;
                                tempAccount.setBalance( tempAccount.getBalance()  + interest);
                                System.out.println("An interest of " + currencyFormatter.format(interest) + " was added to your saving account.");
                            }
                            else
                                System.out.println("Failed: Calculate Interest. Balance must be greater than $1,000.");
                        }
                        else
                            System.out.println("Failed: Only saving accounts have can receive interest.");
                    }
                    //but doesn't have the account, then report an error
                    else {
                        System.out.println("Fail: Calculate Interest. The customer does not have the specified account");
                    }
                }
                //if customer doesn't exist report an error
                else {
                    System.out.println("Fail: Calculate Interest. The customer does not exist in bank directory.");
                }
                break;
            case WITHDRAW:
                //if customer already exist,
                if (tempCustomer != null) {
                    BankAccount tempAccount = accountExist(transaction.getAccType1(), tempCustomer);
                    //and account already exist, go ahead and withdraw the money
                    if (tempAccount != null) {
                        //If it's a checking account use the special withdraw method
                        if (transaction.getAccType1().equals("C")) {
                            if ( tempCustomer.getSavingAccount() != null )
                                tempAccount.specialWithdraw(transaction.getAmount(), tempCustomer.getSavingAccount());
                        }
                        else {
                                tempAccount.withdraw(transaction.getAmount());
                            }
                    }
                    //but doesn't have the account, then report an error
                    else {
                        System.out.println("Fail: "+currencyFormatter.format(transaction.getAmount())+ " withdraw. The customer does not have the specified account");
                    }
                }
                //if customer doesn't exist report an error
                else {
                    System.out.println("Fail: "+ currencyFormatter.format(transaction.getAmount())+ " withdraw. The customer does not exist in bank directory.");
                }
                break;
            case GET:
                //if customer already exist,
                if (tempCustomer != null) {
                    BankAccount tempAccount = accountExist(transaction.getAccType1(), tempCustomer);
                    //and account already exist, go ahead and output the balance
                    if (tempAccount != null) {
                        System.out.println("Your balance is " + currencyFormatter.format(tempAccount.getBalance()));
                    }
                    //but doesn't have the account, then report an error
                    else {
                        System.out.println("Fail: Balance Not Available. The customer does not have the specified account");
                    }
                }
                //if customer doesn't exist report an error
                else {
                    System.out.println("Fail: Balance Not Available. The customer does not exist in bank directory.");
                }
                break;
            case NONE:
                System.out.println("Error: Transaction Not Specified.");
                break;
        }
    }



    /**
     *Creates a new account depending on the type of account requested
     *
     * @param accType
     * @param tempCustomer
     * @param amount
     */
    private void createNewAccount(Customer tempCustomer, String accType, double amount) {
        switch (accType){
            case "C":
                tempCustomer.setCheckingAccount(new CheckingAccount(amount));
                break;
            case "S":
                tempCustomer.setSavingAccount(new SavingAccount(amount));
                break;
            case "A":
                tempCustomer.setAutoLoanRepaymentAccount(new AutoLoanRepaymentAccount(amount));
                break;
            case "L":
                tempCustomer.setStudentLoanRepayment(new StudentLoanRepayment(amount));
                break;
        }
    }

    /**
     * check to see if a customer has an account with the corresponding account number or not.
     *
     * @param accType
     * @param customer
     * @return BankAccount
     */
    private BankAccount accountExist(String accType, Customer customer) {
        switch (accType) {
            case "C":
                if (customer.getCheckingAccount() != null)
                    return customer.getCheckingAccount();
                break;
            case "S":
                if (customer.getSavingAccount() != null)
                    return customer.getSavingAccount();
                break;
            case "A":
                if (customer.getAutoLoanRepaymentAccount() != null)
                    return customer.getAutoLoanRepaymentAccount();
                break;
            case "L":
                if (customer.getStudentLoanRepayment() != null)
                    return customer.getStudentLoanRepayment();
                break;
        }
        return null;
    }

    /**
     * check to see if a customer with the corresponding account number exist or not.
     *
     * @param accType
     * @param accNumber
     * @return BankAccount
     */
    private Customer customerExist(String accType, long accNumber) {
        Customer tempCustomer;
        int size = customersDirectory.size();
        for (int i = 0; i < size; i++) {
            tempCustomer = customersDirectory.get(i);
            if (tempCustomer.getCustomerNumber() == accNumber)
                return tempCustomer;
        }
        return null;
    }


    /**
     * Prints out a summary of the bank directory
     */
    public void printSummary() {
        System.out.println("========================Bank Directory Summary=========================");
        if ( customersDirectory.size() == 0 )
            System.out.println("No Customer Found!");
        for (Customer customer : customersDirectory) {
            SavingAccount savingAccount = customer.getSavingAccount();
            CheckingAccount checkingAccount = customer.getCheckingAccount();
            AutoLoanRepaymentAccount autoLoanRepaymentAccount = customer.getAutoLoanRepaymentAccount();
            StudentLoanRepayment studentLoanRepayment = customer.getStudentLoanRepayment();
            double total = 0;

            System.out.println("Customer #" + customer.getCustomerNumber() +":");
            if(checkingAccount != null) {
                System.out.println("Checking account balance: " + currencyFormatter.format(checkingAccount.getBalance()));
                total += checkingAccount.getBalance();
            }
            if (savingAccount != null){
                System.out.println("Saving account balance: " + currencyFormatter.format(savingAccount.getBalance()));
                total += savingAccount.getBalance();
            }
            if (autoLoanRepaymentAccount != null) {
                System.out.println("Auto loan repayment account balance: " + currencyFormatter.format(autoLoanRepaymentAccount.getBalance()));
                total += autoLoanRepaymentAccount.getBalance();
            }
            if (studentLoanRepayment != null) {
                System.out.println("Student loan account balance: " + currencyFormatter.format(studentLoanRepayment.getBalance()));
                total += studentLoanRepayment.getBalance();
            }
            System.out.println("======> Total balance: " + currencyFormatter.format(total));
        }
        System.out.println("_______________________________________________________________________");
    }
}
