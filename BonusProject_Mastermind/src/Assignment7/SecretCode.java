package Assignment7;

import java.util.Random;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class SecretCode {
    private SecretPeg[] secretCode;

    public SecretCode() {

        this.secretCode = new SecretPeg[4];

        for ( int i = 0; i < 4; i += 1 ) {
            this.secretCode[i] = new SecretPeg();
            this.secretCode[i].setxyPeg(25 + 100*i , 135);
        }
        generateSecretCode();
    }

    /**
     * For testing purposes only!
     * @param secretCode
     */
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
    private void generateSecretCode() {
        Random random = new Random();
        RoundPegColor[] colorPallet = { RoundPegColor.blue, RoundPegColor.yellow, RoundPegColor.green,
                RoundPegColor.red, RoundPegColor.orange, RoundPegColor.purple };

        for ( int i = 0; i < 4; i += 1 ) {
            secretCode[i].setColor(colorPallet[random.nextInt(6)]);
        }
    }

    /**
     * Displays the auto-generated secret code
     */
    public void displaySecretCode(){
        for(int i = 0; i < 4; i+=1){

            System.out.println((secretCode[i].getColor()));
        }
    }
}
