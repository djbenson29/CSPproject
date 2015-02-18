
public class Bag {
	
	int weight;
	String bagName;
	Item[] listOfItems = new Item[100];
	int numItems;
	
	public Bag(int weight, String bagName){
		this.weight = weight;
		this.bagName = bagName;
		this.numItems = 0;
	}
	
	// Adds an item to the bag
	public void addItem(Item item){
		this.listOfItems[this.numItems] = item;
		this.weight -= item.weight;
		this.numItems++;
	}
	
	
}

