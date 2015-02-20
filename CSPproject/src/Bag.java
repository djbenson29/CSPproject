
/*
 * Daniel Benson djbenson@wpi.edu
 * Rafael Angelo rlangelo@wpi.edu
 * CS 4341 Project 4: CSP's
 * This class contains the Bag object.
 * 
 */

public class Bag {
	
	//Variables pertaining to the Bag Object
	int weight = 0; //Its weight
	String bagName; //Its name (denominated usually by a lowercase char
	Item[] listOfItems = new Item[100]; //A list of the items that are currently in this bag
	int numItems; //The number of items in this bag
	int totalWeight; //The initial weight (immutable)
	
	//Constructor takes in a name and a weight
	public Bag(int weight, String bagName){
		this.bagName = bagName;
		this.numItems = 0; //Every Bag starts empty
		this.totalWeight = weight; 
	}
	
	// Adds an item to the bag
	public void addItem(Item item){
		this.listOfItems[this.numItems] = item; //Adds the item to the list of items in this bag
		this.weight += item.weight; //Adds the item's weight to the bag's current weight
		this.numItems++; //Increments the number of items in the Bag.
	}
}

