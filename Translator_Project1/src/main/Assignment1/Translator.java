/*	Assignment 1
 *	Piglatin to English Translator
 *	Aria Pahlavan
 *	02/01/2016
 */

package Assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator 
{
	
	public static void main (String args[]) 
	{ 
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile (args[0]);
		
	}

	/******************************************************************************
	* Method Name: processLinesInFile                                             *
	* Purpose: Opens the file specified in String filename, reads each line in it *
	*          Invokes translate () on each line in the file, and prints out the  *
	*          translated piglatin string.                                        *
	* Returns: None                                                               *
	******************************************************************************/
	public static void processLinesInFile (String filename) 
	{ 

		Translator translator = new Translator(); 
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String pigLatin = translator.translate(s);
				System.out.println(pigLatin);
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/******************************************************************************
	* Method Name: translate                                                      *
	* Purpose: Converts String inputString into piglatin based on rules specified *
	*          in your assignment write-up.                                       *
	* Returns: String object containing the piglatin translation of               *
	*          String inputString                                                 *
	******************************************************************************/
	
	public String translate (String inputString) 
	{ 
		// modify the following code. Add/delete anything after this point.
		//stringBuilder helps me translate each line to piglatine :)
		StringBuilder stringBuilder = new StringBuilder();
		int previousIndex = 0, startIndex = 0, endIndex = 0;


		//pattern and matcher help me separate eligible words from the naughty ones :)

		//I'm looking for anything that starts with chars a-z or A-Z or 0-9
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");

		//matchers takes each like and will let me compare my pattern with the string line
		Matcher matcher = pattern.matcher(inputString);

		//Now I use a look at each word (anything basically) until there's nothing left :)
		while(matcher.find()){
			//Ask matcher for the starting and ending index of the eligible word
			startIndex = matcher.start();
			endIndex = matcher.end();

			//anything beffor the eligible word is whitespace
			stringBuilder.append(inputString.substring(previousIndex, startIndex));

			//update the previous index to be the curr end
			previousIndex = endIndex;

			//append the eligibile word to the output after it's translated
			stringBuilder.append(piglatinMachine(inputString.substring(startIndex, endIndex)));
		}
		//append any remaining whitespace
		stringBuilder.append(inputString.substring(previousIndex));

		//type case from builder to regular string and return
		return stringBuilder.toString();
	}

	private String piglatinMachine(String substring) {
		//I first check to see if there


		return null;
	}

}
