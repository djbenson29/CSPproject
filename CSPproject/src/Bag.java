
public class Bag {
	
	int weight;
	String bagName;
	String[] listOfItems = new String[100];
	int numItems;
	
	public Bag(int weight, String bagName){
		this.weight = weight;
		this.bagName = bagName;
		this.numItems = 0;
	}
	
	// Adds an item to the bag
	public void addItem(String item, int itemWeight){
		this.listOfItems[this.numItems] = item;
		this.weight += itemWeight;
		this.numItems++;
	}
	
	
}

