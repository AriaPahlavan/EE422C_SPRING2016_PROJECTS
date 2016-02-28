package VirtualCafe;

public class VirtualCafe {

	public static void main(String[] args) {
		VirtualCoffeeCup cup1 = new VirtualCoffeeCup("Italian", 180);
    	VirtualCoffeeCup cup2 = new VirtualCoffeeCup("French", 150); 
    	VirtualCoffeeCup cup3 = new VirtualCoffeeCup("Persian", 200); 

    	VirtualPerson customer = new VirtualPerson(175, 185);
    	VirtualCafe.serveCustomer(customer, cup1);
    	VirtualCafe.serveCustomer(customer, cup2);
    	VirtualCafe.serveCustomer(customer, cup3);
	}
	public static void serveCustomer(VirtualPerson person, VirtualCoffeeCup cup){
		try{
			person.drinkCoffee(cup);
		} catch(TooHotException the){
				System.out.println("Sorry, please don't sue!! :(");
		} catch (TooColdException tce) {
				System.out.println("Let me warm that up for you.");
		} finally {
				System.out.println("Final");
		}
	}
}
