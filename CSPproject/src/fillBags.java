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
	
	// Constructor
	public fillBags() {
		// Empty constructor to instantiate the class
	}
	
	// Method to take in the constraints in the input file
	void readConstraints(String fileName) throws FileNotFoundException, IOException{
		
		// Open the fileReader and read stuff in
		File inputFile = new File("./" + fileName);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		int counter = 0, i = 0, j = 0;
		
		
		String line = null;
		while((line = br.readLine()) != null){
			if (line.contains("#")){
				counter++;
				i = 0;
				continue;
			}
			
			List <String> ls = Arrays.asList(line.split(" "));

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
			}	
		}
		System.out.println("Values");
		for (int k=0; k<valueLetters.length;k++){
			System.out.println(valueLetters[i] + " " + valueNumbers[i] + "\n");
		}
		System.out.println("Variables");
		for (int k=0; k<variableLetters.length;k++){
			System.out.println(variableLetters[i] + " " + variableNumbers[i] + "\n");
		}
		System.out.println("Fitting Numbers");
		for (int k=0; k<fittingNums.length;k++){
			System.out.println(fittingNums[i] + " ");
		}
		System.out.println("Unary Inclusive");
		for (int k=0; k<unaryInc.length;k++){
			for (int p=0; p<unaryInc.length;p++){
				System.out.println(unaryInc[k][p] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("Unary Exclusive");
		for (int k=0; k<unaryEx.length;k++){
			for (int p=0; p<unaryEx.length;p++){
				System.out.println(unaryEx[k][p] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("Binary Equal");
		for (int k=0; k<binaryEq.length;k++){
			for (int p=0; p<binaryEq.length;p++){
				System.out.println(binaryEq[k][p] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("Binary Not Equal");
		for (int k=0; k<binaryNotEq.length;k++){
			for (int p=0;p<binaryNotEq.length;p++){
				System.out.println(binaryNotEq[k][p] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("Mutual Exclusive");
		for (int k=0; k<mutualEx.length;k++){
			for (int p=0;p<mutualEx.length;p++){
				System.out.println(mutualEx[k][p] + " ");
			}
			System.out.println("\n");
		}
		
		
		
	}
	
	

	public static void main(String [] arg) throws IOException {

		String fileName = arg[0];
		fillBags fb =  new fillBags();
		fb.readConstraints(fileName);
		

	}

	

}
