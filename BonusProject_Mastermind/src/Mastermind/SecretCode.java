package Mastermind;

import java.util.Random;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class SecretCode {
    private SecretPeg[] secretCode;

    public SecretCode() {
        this.secretCode = new SecretPeg[4];
    }

    public SecretCode(SecretPeg[] secretCode) {
        this.secretCode = secretCode;
    }

    public SecretPeg[] getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(SecretPeg[] secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Generates a secret code consisting of a sequence of
     * colored pegs for the player to guess!
     */
    private void generateSecretCode(){
        Random random = new Random();

        for ( int i = 0; i < 4; i+=1 ){
            
        }
    }
}
