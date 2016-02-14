package VirtualCafe;

public class VirtualPerson {
	int tooHot;
	int tooCold;

	VirtualPerson(int tooCold, int tooHot){
		this.tooHot = tooHot;
		this.tooCold = tooCold;
	}


	public void drinkCoffee(VirtualCoffeeCup cup) throws TooColdException, TooHotException{
		if(cup.getTemperature() > tooHot){
			throw new TooHotException();
		} else if(cup.getTemperature() < tooCold){
			throw new TooColdException();
		} else 
			System.out.println("Ahhh...");
	}
}
