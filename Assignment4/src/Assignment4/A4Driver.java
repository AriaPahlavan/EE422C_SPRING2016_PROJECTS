package Assignment4;

import java.util.ArrayList;

public class A4Driver {
    private static final String DICTIONARY_PATH = "../A4words.dat";

    public static void main(String[] args) {

        try {

            // Create a file opener object
            FileOpener fileOpener = new FileOpener();

            // Create a word ladder solver object
            Assignment4Interface wordLadderSolver = new WordLadderSolver(fileOpener.openDictionaryFile(DICTIONARY_PATH));

            //getting the list of words to be matched
            ArrayList<String> tesCaseList = fileOpener.openTestFile(args[0]);

            for ( String testCase : tesCaseList ) {

                String statWord = testCase.substring(0, 5);
                String endWord = testCase.substring(5, 10);
                ArrayList<String> result = wordLadderSolver.computeLadder(statWord, endWord);
                boolean correct = wordLadderSolver.validateResult(statWord, endWord, result);
                if ( correct == false )
                    throw new NoSuchLadderException("Invalid Ladder");
            }

        } catch (NoSuchLadderException e) {
            e.printError();
        }
    }
}
