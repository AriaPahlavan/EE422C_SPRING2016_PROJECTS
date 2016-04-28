package Mastermind;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class SecretPeg extends Peg{
    private boolean wasChecked;

    public SecretPeg() {
        super();
        this.wasChecked = false;
    }

    public SecretPeg(boolean wasChecked) {
        super();
        this.wasChecked = wasChecked;
    }

    public SecretPeg(RoundPegColor roundPegColor, int position, boolean wasChecked1) {
        super(roundPegColor, position);
        this.wasChecked = wasChecked1;
    }
}
