package Assignment4;

import java.util.ArrayList;

public class A4Driver
{
    private static final String DICTIONARY_NAME = "../A4words.dat";
    public static void main(String[] args)
    {

        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver(DICTIONARY_NAME);

        try 
        {
            ArrayList<String> result = wordLadderSolver.computeLadder("money", "honey");
            boolean correct = wordLadderSolver.validateResult("money", "honey", result);
            if ( correct==false )
                throw new NoSuchLadderException("Invalid Ladder");

            System.out.println("It's all good!");
        }
        catch (NoSuchLadderException e) 
        {
            e.printError();
        }
    }
}
