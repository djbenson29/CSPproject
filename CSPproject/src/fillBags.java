/*
 * Daniel Benson djbenson@wpi.edu
 * Rafael Angelo rlangelo@wpi.edu
 * CS 4341 Project 4: CSP's
 * This class contains the main function. We take in an input file and create a log file while we attempt to solve
 * the Constraints Satisfaction Problem. After our program reaches conclusion we create an output file with our solutions.
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

	// Constructor
	public fillBags() {
		// Empty constructor to instantiate the class
	}

	// Method to take in the constraints in the input file
	void readConstraints(String fileName) throws FileNotFoundException, IOException{
		System.out.println("It is here!");
		// Open the fileReader and read stuff in
		File inputFile = new File("./" + fileName);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		int counter = 0, i = 0, j = 0;


		for(String line; (line = br.readLine()) != null;){
			//line = br.readLine();
			if (line.contains("#")){
				counter++;
				i = 0;
				//System.out.println("Incrementing counter! " + counter);
			}
			else{
				List <String> ls = Arrays.asList(line.split(" "));
				//System.out.println(ls.get(0) + ls.get(1));
				switch(counter){
				case 1:
					// Variables
					variableLetters[i] = ls.get(0);
					variableNumbers[i] = Integer.parseInt(ls.get(1));
					i++;
					break;

				case 2: 
					// Values
					valueLetters[i] = ls.get(0);
					valueNumbers[i] = Integer.parseInt(ls.get(1));
					i++;
					break;

				case 3:	
					// Fitting Values
					fittingNums[0] = Integer.parseInt(ls.get(0));
					fittingNums[1] = Integer.parseInt(ls.get(1));
					lowerLimit = fittingNums[0];
					higherLimit = fittingNums[1];
					i++;
					break;

				case 4:
					// Unary Inclusive
					while(j < ls.size()){
						unaryInc[i][j] = ls.get(j);
						j++;
					}
					i++;
					j = 0;
					break;

				case 5: 
					// Unary Exclusive
					while(j < ls.size()){
						unaryEx[i][j] = ls.get(j);
						j++;
					}
					i++;
					j = 0;
					break;

				case 6: 
					// Binary Equals
					while(j < ls.size()){
						binaryEq[i][j] = ls.get(j);
						j++;
					}
					i++;
					j=0;
					break;

				case 7:
					// Binary Not Equals
					while(j<ls.size()){
						binaryNotEq[i][j] = ls.get(j);
						j++;
					}
					i++;
					j=0;
					break;

				case 8:
					// Binary Mutual Exclusive 
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
/*
		System.out.println("Values");
		for (int k=0; k<valueLetters.length;k++){
			if (valueLetters[k] != null || valueNumbers[k] != 0){
				System.out.print(valueLetters[k] + " " + valueNumbers[k] + "\n");
			}
		}
		System.out.println("Variables");
		for (int k=0; k<variableLetters.length;k++){
			if (variableLetters[k] != null || variableNumbers[k] != 0){
				System.out.print(variableLetters[k] + " " + variableNumbers[k] + "\n");
			}
		}
		
		System.out.println("Fitting Numbers");
		for (int k=0; k<fittingNums.length;k++){
			if (fittingNums[k] != 0){
				System.out.println(fittingNums[k] + " ");
			}
		}
		
		System.out.print("\n");


		System.out.println("Unary Inclusive");
		for (int k=0; k<unaryInc.length;k++){
			for (int p=0; p<unaryInc.length;p++){
				if (unaryInc[k][p] != null){
					System.out.print(unaryInc[k][p] + " ");
				}
			}
			//System.out.print("\n");
		}
		
		System.out.print("\n");


		System.out.println("Unary Exclusive");
		for (int k=0; k<unaryEx.length;k++){
			for (int p=0; p<unaryEx.length;p++){
				if (unaryEx[k][p] != null){
					System.out.print(unaryEx[k][p] + " ");
				}
			}
			//System.out.print("\n");
		}
		
		System.out.print("\n");


		System.out.println("Binary Equal");
		for (int k=0; k<binaryEq.length;k++){
			for (int p=0; p<binaryEq.length;p++){
				if (binaryEq[k][p] != null){
					System.out.print(binaryEq[k][p] + " ");
				}
			}
			//System.out.print("\n");
		}
		
		System.out.print("\n");


		System.out.println("Binary Not Equal");
		for (int k=0; k<binaryNotEq.length;k++){
			for (int p=0;p<binaryNotEq.length;p++){
				if (binaryNotEq[k][p] != null){
					System.out.print(binaryNotEq[k][p] + " ");
				}
			}
			//System.out.print("\n");
		}
		
		System.out.print("\n");

		System.out.println("Mutual Exclusive");
		for (int k=0; k<mutualEx.length;k++){
			for (int p=0;p<mutualEx.length;p++){
				if (mutualEx[k][p] != null){
					System.out.print(mutualEx[k][p] + " ");
				}
			}
			//System.out.print("\n");
		}
		
		System.out.print("\n");
*/
		fillBags fb = new fillBags();
		fb.initBags(valueLetters, valueNumbers);
		br.close();
	}

	public void initBags(String[] bagNames, int[] weights){
		String currentName;
		int currentWeight = 100;
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
		for(int i=0;i<listOfBags.length;i++){
			if(listOfBags[i] != null){
				System.out.println(listOfBags[i].bagName + " " + listOfBags[i].weight);
			}
			else
			{
				break;
			}
		}
		
	}

	public static void main(String [] arg) throws IOException {

		String fileName = arg[0];
		fillBags fb =  new fillBags();
		fb.readConstraints(fileName);
	}



}
