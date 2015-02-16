/*
 * Daniel Benson djbenson@wpi.edu
 * Rafael Angelo rlangelo@wpi.edu
 * CS 4341 Project 4: CSP's
 * This class contains the main function. We take in an input file and create a log file while we attempt to solve
 * the Constraints Satisfaction Problem. After our program reaches conclusion we create an output file with our solutions.
 * 
 */


import java.io.*;

public class fillBags {
	
	// Global Variables
	int valueNumbers[], variableNumbers[], fittingNums[];
	char valueLetters[], variableLetters[];
	
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
			// Variables
			if(counter == 1){
				variableLetters[i] = line.charAt(0);
				variableNumbers[i] = Integer.parseInt(line.substring(2, 100));
				i++;
			}
			
			// Values
			else if (counter == 2){
				valueLetters[i] = line.charAt(0);
				valueNumbers[i] = Integer.parseInt(line.substring(2, 100));
				i++;
			}
			
			// Fitting Values
			else if(counter == 3){
				fittingNums[i] = Integer.parseInt(line.substring(2, 100));
				i++;
			}
		}
		
	}
	
	

	public static void main(String [] arg) throws IOException {

		String fileName = arg[0];
		fillBags fb =  new fillBags();
		fb.readConstraints(fileName);
		

	}

	

}
