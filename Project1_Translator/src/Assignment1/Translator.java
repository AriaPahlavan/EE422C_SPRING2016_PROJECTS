/*	Assignment 1
 *	English to PigLatin Translator
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

	public String translate (String inputString){
		System.out.println("_______________________________________________");
		System.out.println("The input phrase is:");
		System.out.println(inputString);
		// modify the following code. Add/delete anything after this point.
		//stringBuilder helps me translate each line to piglatine :)
		StringBuilder stringBuilder = new StringBuilder();
		int previousIndex = 0, startIndex = 0, endIndex = 0;


		//pattern and matcher help me separate eligible words from the naughty ones :)

		//I'm looking for anything that starts with chars a-z or A-Z or 0-9
		Pattern pattern = Pattern.compile("[^ \t\n\f\r]+");

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


		System.out.println("The pig latin equivalent phrase is:");
		//type case from builder to regular string and return
		return stringBuilder.toString();
	}

	private String piglatinMachine(String substring) {
		StringBuilder stringBuilder = new StringBuilder();
		int indexOfVowel = 0, endIndex = 0;
		//I first use pattern match to see if I'm dealing with a numeric value
		//if so I'm just gonna return.... I promise :)

		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(substring);

		//just a conditional statement to see if I can find a naughty number!!
		if(matcher.find()){
			//just return the number back!!
			return substring;
		}
		
		
		

		//I now use pattern match to see if I'm dealing with a non-alphabetic character
				//if so I'm just gonna return.... I promise :)

				Pattern pattern3 = Pattern.compile("[^'a-zA-Z]");
				Matcher matcher3 = pattern3.matcher(substring);

				//just a conditional statement to see if I can find a naughty number!!
				if(matcher3.find()){
					if(matcher3.start() != substring.length()-1){
					//just return the number back!!
					return substring;
					}
					else {
						//Now I'll change the pattern to catch a vowel or two :)
						Pattern pattern2 = Pattern.compile("[aeiouAEIOU]");
						Matcher matcher2 = pattern2.matcher(substring);


						//using a while loop to find the index position of the first vowel in the string
						if (matcher2.find()){
							indexOfVowel = matcher2.start();

							//first append the part starting with the vowel
							stringBuilder.append(substring.substring(indexOfVowel, substring.length()-1));

							//If the word starts with a vowel, I'll just append "yay" to the end
							if(indexOfVowel == 0){
								stringBuilder.append("yay");
							}

							//else I move the first part to the end and the append "ay"
							else {
								stringBuilder.append(substring.substring(0, indexOfVowel));
								stringBuilder.append("ay");
							}
							stringBuilder.append(substring.substring(substring.length()-1));
						}


						return stringBuilder.toString();
					}
				}
		

		//Now I'll change the pattern to catch a vowel or two :)
		Pattern pattern2 = Pattern.compile("[aeiouAEIOU]");
		Matcher matcher2 = pattern2.matcher(substring);


		//using a while loop to find the index position of the first vowel in the string
		if (matcher2.find()){
			indexOfVowel = matcher2.start();

			//first append the part starting with the vowel
			stringBuilder.append(substring.substring(indexOfVowel));

			//If the word starts with a vowel, I'll just append "yay" to the end
			if(indexOfVowel == 0){
				stringBuilder.append("yay");
			}

			//else I move the first part to the end and the append "ay"
			else {
				stringBuilder.append(substring.substring(0, indexOfVowel));
				stringBuilder.append("ay");
			}
		}


		return stringBuilder.toString();
	}

}
