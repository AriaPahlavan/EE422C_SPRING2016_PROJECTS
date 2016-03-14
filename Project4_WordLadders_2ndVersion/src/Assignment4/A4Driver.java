package Assignment4;

import java.util.ArrayList;

public class A4Driver {
    private static final String DICTIONARY_PATH = "/home/aria/EE422C_Ubuntu/Project4_WordLadders_2ndVersion/A4words.dat";

    public static void main(String[] args) {

        try {

            // Create a file opener object
            FileReader fileReader = new FileReader();

            // Create a word ladder solver object
            WordLadderSolver wordLadderSolver = new WordLadderSolver(fileReader.readDictionaryFile(DICTIONARY_PATH));

            //getting the list of words to be matched
            ArrayList<String> tesCaseList = fileReader.readTestFile(args[0]);

            for ( String testCase : tesCaseList ) {
                wordLadderSolver.reset();

                String statWord = testCase.substring(0, 5);
                String endWord = testCase.substring(6, 11);
                ArrayList<String> result = wordLadderSolver.computeLadder(statWord, endWord);
                boolean correct = wordLadderSolver.validateResult(statWord, endWord, result);
                if ( correct == false ) {
                    System.out.println("Invalid Ladder: between " + statWord + " and " + endWord);
                    System.out.println("**********");
                }
                else {
                    System.out.println("Word ladder found is (" + (result.size()-1) +"):");

                    for ( String string : result )
                        System.out.println(string);

                    System.out.println("**********");
                }

            }

        } catch (NoSuchLadderException e) {
            e.printStackTrace();
            e.printError();
        }
    }
}
