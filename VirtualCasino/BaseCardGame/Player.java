import java.io.*;
import java.util.*;

/**
 * This class loads player info from a file, then uses the info (if it is the correct user info), 
 * creates a new user if not, and saves after the user is finished in the casino
 * @author Jordan Henkelman
 *
 */

public class Player {

	private int balance;
	private String name;

	/**
	 * searches for a file by the name of CasinoInfo.txt
	 * @throws FileNotFoundException if the file is not found, doesn't cause an error 
	 */
	public void findFile() throws FileNotFoundException {

		Scanner kb = new Scanner(System.in);
		boolean valid = false;

		//declaring a file 
		File inFile = new File ("CasinoInfo.txt");
		//calling information from the file
		Scanner in = new Scanner (inFile);
		System.out.println("File found");
		System.out.println(inFile);


		System.out.print("Is your name on file? ");
		String answer = kb.next();
		System.out.println();
		

		while(!valid) {
			if(answer.equalsIgnoreCase("yes")) {

				while(in.hasNext()) {

					//loading employee information from the file
					name = in.next();
					balance = in.nextInt();
				}

				valid = true;
				in.close();
			}

			else if(answer.equalsIgnoreCase("no")) {


				System.out.print("Please enter your name: ");
				name = kb.next();
				System.out.println();
				balance = 1000;
				valid = true;
			}

			else {
				System.out.println("Invalid input.");
				System.out.print("Does your file exist in the directory? Type 'Yes' or 'No': ");
				answer = kb.next();
				System.out.println();

			}
		}
	}

	/**
	 * saving the users information to a CasinoInfo.txt file
	 * @throws FileNotFoundException
	 */
	public void save() throws FileNotFoundException {

		//creating out file
		PrintWriter outFile = new PrintWriter("CasinoInfo.txt");
		//processing

		//outputting onto file
		outFile.print(saveFormat());

		//closing out file
		outFile.close();
		//output
		System.out.println("Save successful.");
	}

	/**
	 * converts the info on file into a more readable string for printing 
	 * @return the string of proper format 
	 */
	public String toString() {

		return String.format("%s, %d coins", name, balance);
	}

	/**
	 * converts the info on file into the proper format for saving
	 * @return the string of proper format 
	 */
	public String saveFormat() {
		return String.format("%s %n%d", name, balance);
	}
	
	/**
	 * getter method 
	 * @return the users balance 
	 */
	public int getBalance() {

		return balance;
	}

	/**
	 * setter method 
	 * @param balance sets the users balance 
	 */
	public void setBalance(int balance) {
		this.balance=balance;
	}

}
