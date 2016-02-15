package assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BankAccountDriver {

	public static void main(String[] args) {
		JFrame jFrame = new JFrame("InputDialog Example #1");

		String input = JOptionPane.showInputDialog("Enter somthing");

		String[] parse = input.split(" ");
		for (String string : parse) {
			System.out.println(string);
		}

		Scanner scanner = new Scanner(JOptionPane.showInputDialog("Enter somthing"));

		while (scanner.hasNext()) {
			System.out.println("I'm here");
			if (scanner.hasNextInt()) {

				int i = scanner.nextInt();
				System.out.println("it is " + i);
			}
			else {
				scanner.next();
			}
		}

	}

}
