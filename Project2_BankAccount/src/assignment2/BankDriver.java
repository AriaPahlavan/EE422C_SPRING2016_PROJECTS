/** 
 * Assignment 2: Bank Account
 * @author Aria Pahlavan
 * @version 1.0
 * Spring 2016
 */
package assignment2;

import javax.swing.*;

/** 
 * Driver - Inputs transactions and process them.
 *	
 * @author Aria Pahlavan
 * @version 1.0
 */
public class BankDriver {

	public static void main(String[] args) {

		try {

            BankDirectory myBank = new BankDirectory();

            String transactionInput = JOptionPane.showInputDialog("Please enter your transaction:");

			while (true) {

				Transaction newTransaction = new Transaction(transactionInput);
                myBank.processTransaction(newTransaction);


				transactionInput = JOptionPane.showInputDialog("Please enter your next transaction (Enter N to exit):");
                if (transactionInput.toLowerCase().equals("n"))
                    break;
			}

            //Summary:
            myBank.printSummary();
        } catch (Exception e) {
			System.out.println("Error: Invalid Transaction Format. Please try again!");
		}

	}
}
