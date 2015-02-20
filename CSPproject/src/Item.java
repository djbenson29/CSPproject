/*
 * Daniel Benson djbenson@wpi.edu
 * Rafael Angelo rlangelo@wpi.edu
 * CS 4341 Project 4: CSP's
 * This class contains the Item object.
 * 
 */


public class Item {

	//Variables pertaining to the Item Object.
	int weight; //the weight of the object
	String itemName; //the name of the item (usually denominated by an UpperCase letter
	int totalWeight; //The initial weight of the item (immutable).
	
	//Constructor for the item class
	public Item(int weight, String itemName){
		this.weight = weight;
		this.itemName = itemName;
		this.totalWeight = weight;
	}
}
