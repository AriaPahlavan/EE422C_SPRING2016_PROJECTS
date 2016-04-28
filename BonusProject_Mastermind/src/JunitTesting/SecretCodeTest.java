package JunitTesting;

import Assignment7.SecretCode;
import org.junit.Test;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class SecretCodeTest {

    @Test
    public void testSecretCodeTest(){
        SecretCode secretCode = new SecretCode();
        for(int i = 0; i < 4; i+=1){

            switch (secretCode.getSecretCode()[i].getColor()) {
                case blue:
                    System.out.println("blue");
                    break;
                case green:
                    System.out.println("green");
                    break;
                case orange:
                    System.out.println("orange");
                    break;
                case purple:
                    System.out.println("purple");
                    break;
                case red:
                    System.out.println("red");
                    break;
                case yellow:
                    System.out.println("yellow");
                    break;
                case none:
                    break;
            }
        }
    }
}