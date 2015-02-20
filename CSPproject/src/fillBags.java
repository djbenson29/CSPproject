/*
 * Daniel Benson djbenson@wpi.edu
 * Rafael Angelo rlangelo@wpi.edu
 * CS 4341 Project 4: CSP's
 * This class contains the main function. We take in an input file and create a log file while we attempt to solve
 * the Constraints Satisfaction Problem. After our program reaches conclusion it either displays the output at the 
 * standard output, or it displays no solution if none exists.
 * 
 */


import java.io.*;
import java.util.*;

public class fillBags {

	// Global Variables
	int valueNumbers[] = new int[100];
	int variableNumbers[] = new int[100];
	int fittingNums[] = new int[100];
	String valueLetters[] = new String[100];
	String variableLetters[] = new String[100];
	String unaryInc[][] = new String[100][100];
	String unaryEx[][] = new String[100][100];
	String binaryEq[][] = new String[100][100];
	String binaryNotEq[][] = new String[100][100];
	String mutualEx[][] = new String[100][100];
	int lowerLimit = 0;
	int higherLimit = 100;
	Bag listOfBags[] = new Bag[100];
	Item listOfItems[] = new Item[100];
	int MAX_LOOPS = 50;
	String filename;

	// Constructor
	public fillBags() {
		// Empty constructor to instantiate the class
	}

	// Method to take in the constraints in the input file
	void readConstraints(String fileName) throws FileNotFoundException, IOException{
		// Open the fileReader and read stuff in
		filename = fileName;
		File inputFile = new File("./" + fileName);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		int counter = 0, i = 0, j = 0;

		// If a line has a pound in it, it means we reached the next section
		for(String line; (line = br.readLine()) != null;){
			if (line.contains("#")){
				counter++;
				i = 0;
			}
			else{
				//Split the line by blank spaces
				List <String> ls = Arrays.asList(line.split(" "));
				switch(counter){
				case 1:
					// Add the variables names and weights to their respective arrays
					variableLetters[i] = ls.get(0);
					variableNumbers[i] = Integer.parseInt(ls.get(1));
					i++;
					break;

				case 2: 
					// Add the values names and weights to their respective array
					valueLetters[i] = ls.get(0);
					valueNumbers[i] = Integer.parseInt(ls.get(1));
					i++;
					break;

				case 3:	
					// Make the lower fitting value item 1 in array and higher item 2.
					fittingNums[0] = Integer.parseInt(ls.get(0));
					fittingNums[1] = Integer.parseInt(ls.get(1));
					lowerLimit = fittingNums[0];
					higherLimit = fittingNums[1];
					i++;
					break;

				case 4:
					// Add all, if any, of the unary inclusives in its 2-dimensional array
					while(j < ls.size()){
						unaryInc[i][j] = ls.get(j);
						j++;
					}
					i++;
					j = 0;
					break;

				case 5: 
					// Add all, if any, of the unary exclusives in their 2-dimensional array
					while(j < ls.size()){
						unaryEx[i][j] = ls.get(j);
						j++;
					}
					i++;
					j = 0;
					break;

				case 6: 
					// Add all, if any, of the binary equals in their 2-dimensional array
					while(j < ls.size()){
						binaryEq[i][j] = ls.get(j);
						j++;
					}
					i++;
					j=0;
					break;

				case 7:
					// Add all, if any, of the binary not equals in their 2-dimensional array
					while(j<ls.size()){
						binaryNotEq[i][j] = ls.get(j);
						j++;
					}
					i++;
					j=0;
					break;

				case 8:
					// Add all, if any, of the mutual exclusive in their 2-dimensional array
					while(j<(ls.size()/2)){
						mutualEx[i][j] = ls.get(j);
						j++;
					}
					i++;
					j=0;
					break;
				default:
					break;
				}	
			}
		}
		//Close the stream and send the value and variables name and weights to be instantiated
		initBagsAndItems(valueLetters, valueNumbers, variableLetters, variableNumbers);
		br.close();
	}

	//Instantiates items and bags based on the existing arrays.
	public void initBagsAndItems(String[] bagNames, int[] weights, String[] itemNames, int[] itemWeights) throws IOException{
		String currentName;
		int currentWeight = 100;
		//Each new bag is put in a global array of Bags
		for(int i=0;i<bagNames.length;i++){
			if(bagNames[i] != null){
				currentName = bagNames[i];
				currentWeight = weights[i];
				listOfBags[i] = new Bag(currentWeight, currentName); 
			}
			else{
				break;
			}
		}
		String currentItemName;
		int currentItemWeight = 100;
		//Each new item is put in a global array of Items
		for(int i=0;i<itemNames.length;i++){
			if (itemNames[i] != null) {
				currentItemName = itemNames[i];
				currentItemWeight = itemWeights[i];
				listOfItems[i] = new Item(currentItemWeight, currentItemName);
			}
			else{
				break;
			}
		}
		//Call the method to solve the CSP problem.
		distribute(listOfBags, listOfItems, 1);
		
	}
	
	//Method takes an item and returns a string. Checks to see if that item has 
	//an unary inclusive constraint. If so, returns the bag name it has to be in.
	public String unaryInclusive(Item item)
	{
		for (int k=0;k<unaryInc.length;k++){
			if (unaryInc[k][0] != null) {
				if (unaryInc[k][0].equals(item.itemName)){
						if (unaryInc[k][1] != null) {
							return unaryInc[k][1];
						}
						else {
							return null;
						}
				}
			}
			else {
				break;
			}
		}
		return null;
	}
	
	//Method takes an item and returns an array of strings. Checks to see if that item has 
	//an unary exclusive constraint. If so, returns the bag names for all of the bags it cannot be in.
	public String[] unaryExclusive(Item item){
		String[] excArrays = new String[100];
		for (int k=0;k<unaryEx.length;k++){
			if (unaryEx[k][0] != null){
				if (unaryEx[k][0].equals(item.itemName)){
					for (int j=1;j<unaryEx.length;j++){
						if (unaryEx[k][j] != null){
							for (int p=0;p<excArrays.length;p++){
								if (excArrays[p] == null){
									excArrays[p] = unaryEx[k][j];
									break;
								}
							}
						}
						else{
							break;
						}
					}	
				}
			}
			else{
				break;
			}
		}
		return excArrays;
	}
	
	//Method takes an item and returns a string. Checks to see if that item has 
	//an binary equal constraint. If so, returns the item name with which it has to be together.
	public String binaryEqual(Item item){
		for (int i=0;i<binaryEq.length;i++){
			if (binaryEq[i][0] != null){
				if (binaryEq[i][0].equals(item.itemName)){
					if (binaryEq[i][1] != null){
						return binaryEq[i][1];
					}
					else{
						return null;
					}
				}
			}
			else{
				return null;
			}
		}
		return null;
	}
	
	//Method takes an item and returns a string. Checks to see if that item has 
	//an binary not equal constraint. If so, returns the item name with which it cannot be together.
	public String binaryNotEqual(Item item){
		for (int i=0;i<binaryNotEq.length;i++){
			if (binaryNotEq[i][0] != null){
				if (binaryNotEq[i][0].equals(item.itemName)){
					if(binaryNotEq[i][1] != null){
						return binaryNotEq[i][1];
					}
					else{
						return null;
					}
				}
			}
			else{
				return null;
			}
		}
		return null; 
	}
	
	//Method takes an item and returns a string. Checks to see if that item has a mutual 
	// exclusive constraint. If so, returns the item name whose conditional constrain is related to.
	public String mutualExclusive(Item item){
		for (int i=0;i<mutualEx.length;i++){
			if (mutualEx[i][0] != null && mutualEx[i+1][0] != null){
				if (mutualEx[i][0].equals(item.itemName)){
					if (mutualEx[i][1] != null){
						return mutualEx[i+1][0];
					}
				}
				else{
					return null;
				}
			}
			else{
				return null;
			}
		}
		return null;
	}
	
	//Every time we want to backtrack we need to reset the bags to their initial condition.
	//This method resets all of the bags variables to the initial value. It also resets all of the items
	//original weights.
	public void resetBags() throws IOException
	{
		for (int i=0;i<listOfBags.length;i++)
		{
			if(listOfBags[i] != null){
				listOfBags[i].weight = listOfBags[i].totalWeight;
				listOfBags[i].numItems = 0;
				listOfBags[i].listOfItems = new Item[100];
			}
		}
		for (int j=0;j<listOfItems.length;j++){
			if (listOfItems[j] != null){
				listOfItems[j].weight = listOfItems[j].totalWeight;
			}
		}
	}
	
	//Method takes in the array of Bags, array of Items and a loop counter.
	//This method actually solves the Constraint Satisfaction Problem.
	public void distribute(Bag[] listOfBags, Item[] listOfItems, int loop) throws IOException{
		for (int j=0;j<listOfBags.length;j++) {
			for (int i=0;i<listOfItems.length;i++) {
				if (listOfItems[i] != null && listOfBags[j] != null){
					//Initially checks to see if the weight of the item is currently less than the weight of the Bag
					if (listOfItems[i].weight <= (listOfBags[j].totalWeight-listOfBags[j].weight) 
							&& listOfBags[j].numItems < higherLimit) {
						//Check unary constraints
						//Unary inclusive
						if (unaryInclusive(listOfItems[i]) != null)
						{
							if (listOfBags[j].bagName.equals(unaryInclusive(listOfItems[i])))
							{
								listOfBags[j].addItem(listOfItems[i]);
								listOfItems[i].weight = 100000;
							}
							listOfBags[j].addItem(listOfItems[i]);
							listOfItems[i].weight = 100000;
						}
						//Unary exclusive
						else if (unaryExclusive(listOfItems[i])[0] != null){
							String[] excArrays = new String[unaryExclusive(listOfItems[i]).length];
							excArrays = unaryExclusive(listOfItems[i]);
							for (int k=0;k<excArrays.length;k++){
								if (excArrays[k] != null){
									if (listOfBags[j].bagName.equals(excArrays[k])){

									}
									else{
										listOfBags[j].addItem(listOfItems[i]);
										listOfItems[i].weight = 100000;
									}
								}
							}
						}
						//Check binary constraints
						//binary equal
						else if (binaryEqual(listOfItems[i]) != null){
							String binaryPair = binaryEqual(listOfItems[i]);
							boolean found = false;
							for (int r=0;r<listOfBags.length;r++){
								if (listOfBags[r] != null) {
									for (int p=0;p<listOfBags[r].listOfItems.length;p++){
										if (listOfBags[r].listOfItems[p] != null){
											found = listOfBags[r].listOfItems[p].itemName.equals(binaryPair);
											if (found){break;}
										}
									}
								}
							}
							if (found){
								for (int b=0;b<listOfBags.length;b++){
									if (listOfBags[b] != null){
										for (int c=0;c<listOfBags[b].listOfItems.length;c++){
											if (listOfBags[b].listOfItems[c] != null){
												if(listOfBags[b].listOfItems[c].itemName.equals(binaryPair))
												{
													listOfBags[j].addItem(listOfItems[i]);
													listOfItems[i].weight = 100000;
													break;
												}
											}
										}
									}
								}
							}
							else{
								listOfBags[j].addItem(listOfItems[i]);
								listOfItems[i].weight = 100000;
							}
						}
						
						//binary not equal
						else if (binaryNotEqual(listOfItems[i]) != null){
							String notBinaryPair = binaryNotEqual(listOfItems[i]);
							boolean found = false;
							for (int r=0;r<listOfBags.length;r++){
								if (listOfBags[r] != null){
									for (int p=0;p<listOfBags[r].listOfItems.length;p++){
										if (listOfBags[r].listOfItems[p] != null){
											found = listOfBags[r].listOfItems[p].itemName.equals(notBinaryPair);
											if (found){ break;
											}
											
										}
									}
								}
							}
							if (found){
								for (int b=0;b<listOfBags.length;b++){
									if (listOfBags[b] != null){
										for (int c=0;c<listOfBags[b].listOfItems.length;c++){
											if (listOfBags[b].listOfItems[c] != null){
												if (listOfBags[b].weight >= listOfBags[b].listOfItems[c].weight){
													if(listOfBags[b].listOfItems[c].itemName.equals(notBinaryPair)){
													}
													else{
														listOfBags[b].addItem(listOfItems[i]);
														listOfItems[i].weight = 100000;
														break;

													}
												}
												else{
													if (listOfBags[b+1] != null){
														listOfBags[b+1].addItem(listOfItems[i]);
														listOfItems[i].weight = 100000;
														break;
													}
												}
											}
										}
									}
								}
							}
							else{
								//This is called in case there are no constraints
								listOfBags[j].addItem(listOfItems[i]);
								listOfItems[i].weight = 100000;
							}
						}
						
						else{
						listOfBags[j].addItem(listOfItems[i]);
						listOfItems[i].weight = 100000;
						}
					}
				}
				else {
					break;
				}
			}
		}
		//After each item is added to a Bag, its weight is set to 100000
		//This checks to see if any items have weights that are less than 100000
		//If yes, it means item is not in a bag and it found no solution.
		int counter = 0;
		for(int l=0;l<listOfItems.length;l++){
			if (listOfItems[l] != null){
				if (listOfItems[l].weight < 10000){
					System.out.println(listOfItems[l].itemName + " " + listOfItems[l].weight);
					counter++;
					break;
				}
			}
		}
		//If counter is not equal to 0, that means there are items out of Bags.
		//Reset everything. Shift arrays. Recall distribute.
		if (counter != 0){
			if (MAX_LOOPS == 0){
				System.out.println("IS IT HERE?");
				resetBags();
				listOfItems = shiftArray(listOfItems);
				loop++;
				distribute(listOfBags, listOfItems, loop);
			}
			System.out.println("NO SOLUTION!");
		}
		//If all variables are in counters. Call the output method to display the output.
		else{
			
			output(listOfBags, listOfItems);
		}
		
	}
	
	//This is to help in our backtracking. We take the current array of items and shift all of the items to the left
	//making the first item the last one, the second the first, etc.
	public Item[] shiftArray(Item[] listOfItems){
		Item[] temp = new Item[100];
		Item firstItem = listOfItems[0];
		for (int i=0;i<listOfItems.length-1;i++){
			if (listOfItems[i+1] != null){
			temp[i] = listOfItems[i+1];
			}
			else{
				break;
			}
		}
		for (int k=0;k<temp.length;k++){
			if (temp[k] == null){
				temp[k] = firstItem;
				break;
			}
		}
		
		return temp;
	}
	
	//Method takes in the final list of Bags and sets them up for the correct output format.
	public void output(Bag[] listOfBags, Item[] listOfItems) {
		for (int i=0;i<listOfBags.length;i++){
			if (listOfBags[i] != null){
				System.out.print(listOfBags[i].bagName + " ");
				for (int j=0;j<listOfBags[i].listOfItems.length;j++){
					if(listOfBags[i].listOfItems[j] != null){
						System.out.print(listOfBags[i].listOfItems[j].itemName + " ");
					}
					else{
						break;
					}
				}
				System.out.println("\nnumber of items: " + listOfBags[i].numItems);
				System.out.println("total weight: " + listOfBags[i].weight + "/" + listOfBags[i].totalWeight);
				System.out.println("wasted capacity: " + (listOfBags[i].totalWeight - listOfBags[i].weight));
				System.out.println(" ");
			}
		}
	}

	//Initializes the program. Starts by instantiating this class and calls the 
	//method to read the input file.
	public static void main(String [] arg) throws IOException {

		String fileName = arg[0];
		fillBags fb =  new fillBags();
		fb.readConstraints(fileName);
	}



}
