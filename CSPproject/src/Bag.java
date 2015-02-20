
public class Bag {
	
	int weight = 0;
	String bagName;
	Item[] listOfItems = new Item[100];
	int numItems;
	int totalWeight;
	
	public Bag(int weight, String bagName){
		this.bagName = bagName;
		this.numItems = 0;
		this.totalWeight = weight;
	}
	
	// Adds an item to the bag
	public void addItem(Item item){
		this.listOfItems[this.numItems] = item;
		this.weight += item.weight;
		this.numItems++;
	}
}

