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
	int valueNumbers[], variableNumbers[], fittingNums[];
	String valueLetters[], variableLetters[], unaryInc[], unaryEx[];
	
	// Constructor
	public fillBags() {
		// Empty constructor to instantiate the class
	}
	
	// Method to take in the constraints in the input file
	void readConstraints(String fileName) throws FileNotFoundException, IOException{
		
		// Open the fileReader and read stuff in
		File inputFile = new File("./" + fileName);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		int counter = 0, i = 0;
		
		
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
				
			case 2: 
				// Values
				valueLetters[i] = ls.get(0);
				valueNumbers[i] = Integer.parseInt(ls.get(1));
				i++;
				
			case 3:	
				// Fitting Values
				fittingNums[i] = Integer.parseInt(ls.get(1));
				i++;
				
			case 4:
				// Unary Inclusive
				while(i < ls.size()){
					unaryInc[i] = ls.get(i);
					i++;
				}
				
			case 5: 
				// Unary Exclusive
				while(i < ls.size()){
					unaryEx[i] = ls.get(i);
					i++;
				}
				
			case 6: 
				// Binary Equals
				
				
				// Binary Not Equals
				
			}	// Binary Simultaneous 
		}	
	}
	
	

	public static void main(String [] arg) throws IOException {

		String fileName = arg[0];
		fillBags fb =  new fillBags();
		fb.readConstraints(fileName);
		

	}

	

}
