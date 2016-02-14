package VirtualCafe;

public class VirtualCoffeeCup {
	String roast;
	int temperature;

	VirtualCoffeeCup(){
		roast = "";
		temperature = 175;
	}

	VirtualCoffeeCup(String r, int t){
		this.roast = r;
		this.temperature = t;
	}

	public String getRoast(){
		return roast;
	}

	public int getTemperature(){
		return temperature;
	}
}
