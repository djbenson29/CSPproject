
public class Bag {
	
	int weight;
	String[] listOfItems = new String[100];
	int numItems;
	
	public Bag(int weight, String[] listOfItems){
		this.weight = weight;
		this.listOfItems = listOfItems;
		this.numItems = 0;
	}
	
	// Adds an item to the bag
	public void addItem(String item, int itemWeight){
		this.listOfItems[this.numItems] = item;
		this.weight += itemWeight;
		this.numItems++;
	}
	
	
}

