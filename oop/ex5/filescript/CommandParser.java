package oop.ex5.filescript;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import orders.Order;
import orders.OrderFactory;
import filters.Filter;
import filters.FilterFactory;

public class CommandParser {
	
	private static final int CMD_FILE_INDEX = 1;
	private static final int SRC_DIR_INDEX = 0;
	// Magic numbers
	private static final String POUND_DELIMITER = "#";
	private static final String ABS_STRING = "abs";
	private static final String ALL_STRING = "all";
	
	
	// Data fields:
	String cmdFilepath;
	String srcDirPath;
	
	File cmdFile;
	File srcDir;
	
	public CommandParser(String[] args) throws TypeIIException {
		
		if (args.length != 2) { 
			TypeIIException e = new TypeIIException();
			throw e;
		}
		
		// Otherwise, we know we have enough args to assign them to our fields:
		
		
		this.cmdFilepath = args[CMD_FILE_INDEX];
		this.srcDirPath = args[SRC_DIR_INDEX];
	
		this.cmdFile = new File(cmdFilepath);
		this.srcDir = new File(srcDirPath); // File object can represent a directory too
	
		
		
		
		// Make sure the filepaths exist and can be used.
		validateFilepaths();
	
		
	}

	/** This helper method checks the filepaths to make sure they exist
	 * and can be used.  
	 * @throws TypeIIException 
	 * 
	 */
	private void validateFilepaths() throws TypeIIException {
		if (!cmdFile.isFile() || !srcDir.isDirectory()) { //TODO should we be ready to receive a file as the source directory?
			throw new TypeIIException();
		}
		
		// Make sure cmdFile can be read:
		if (!cmdFile.canRead()) {
			throw new TypeIIException();
		}
	}
	
	public void parseCommands() throws FileScriptException { //TODO should this return boolean?
		
		Scanner cmdScanner;
		
		try {
			cmdScanner = new Scanner(cmdFile); //TODO need to put entire parsing block inside try so the .close() can also be in the try?
			ArrayList<Section> sectionArray = new ArrayList<Section>(); //TODO where can I get the number of sections from?  Not sure I need an array, can run each section inside loop
			
			// But if we did succeed in finding the file, let's start to parse it.  
//			cmdScanner.useDelimiter(POUND_DELIMITER); //TODO DO I use this in the end?
			
			int lineNum = 0;
			while(cmdScanner.hasNextLine()) { // TODO change to hasNextLine?
				
				// Read the first line, which must be "FILTER"
				String firstSectionLine = cmdScanner.nextLine(); //TODO or should we just iterate until we find FILTER ?
				lineNum++;
				if (! firstSectionLine.equals("FILTER")) {  
					// If the first line isn't FILTER, we have incorrect command file syntax
					cmdScanner.close();
					throw new MissingSubSectionException("First line of section isn't 'FILTER'.");
				}
				
				// Otherwise though, we've just read over the FILTER line.
				// Now let's see what filter we have:
				if (!cmdScanner.hasNextLine()) {
					cmdScanner.close();
					throw new MissingSubSectionException("File ends after word 'FILTER'"); // TODO this shouldn't be allowed right?
				}
				// Otherwise, split the filter line by the "#" delimiter
				String filterLine = cmdScanner.nextLine();
				lineNum++;
				String[] paramList = filterLine.split(POUND_DELIMITER);
				Filter filter;
				try {
					filter = FilterFactory.buildFilter(paramList);
				}
				catch (TypeIException e) {
					e.printErrorMessage(lineNum);
					String[] defaultFilterList = {ALL_STRING};
					filter = FilterFactory.buildFilter(defaultFilterList);
					
				}
				// Let's get the order too, and put then in a Section
				if (cmdScanner.hasNextLine()) {
					String orderLine = cmdScanner.nextLine();
					lineNum++;
					if (!orderLine.equals("ORDER")) { // TODO ARE magic strings conisdered magic numbers?
						// If the  line isn't ORDER, we have incorrect command file syntax
						cmdScanner.close();
						throw new MissingSubSectionException("Line following Filter secion isn't 'ORDER'");
					}
				}
				else {
					cmdScanner.close();
					throw new MissingSubSectionException("File ends before Order line.");
				}
				
				
				//Otherwise, process the oder param line
				Order order;
				if (cmdScanner.hasNextLine()) {
					String orderParamLine = cmdScanner.nextLine();
					lineNum++;
					paramList = orderParamLine.split(POUND_DELIMITER);
					// Pass the parameters on to the factory:
					try {
						order = OrderFactory.buildOrder(paramList);
					}
					catch (TypeIException e) {
						e.printErrorMessage(lineNum);
						String[] defaultOrderList = {ABS_STRING}; //TODO repitition here and below in the else
						order = OrderFactory.buildOrder(defaultOrderList);
					}
					
				}
				else {
					// Then order is missing the param line
					// Then resort to the default order, all
					String[] defaultOrderList = {ABS_STRING}; 
					order = OrderFactory.buildOrder(defaultOrderList);
					
					
				}
				
				// Now that you have the filter and Order, let's make a section out of them 
				// And have the section print it's matching files in the correct order:
				sectionArray.add( new Section(filter, order, srcDirPath) ); //TODO should it pass the File srcDir instead of String filepath? Prob doesn't matter
				
				
				
			}
			
			// Now that we've initialized the sections and put them in an array, 
			// Let's print out any neccesary warnings, and then have the sections print out their output
			
			//TODO put warning printing here
			
			while( ! sectionArray.isEmpty()) {
				Section curSection = sectionArray.remove(0);
				curSection.printSectionResults();
			}
			
			// Make sure to close stream before method exits:
			cmdScanner.close();
		}
		catch (FileNotFoundException e) { // TODO the existence of the file is checked before.  Get rid of it above?
			// If we're here, that means the file didn't exist
			//TODO which exception is this?  My guess is type I
			throw new TypeIIException("The command file was not found"); //TODO should exception be more specific than just Type I?  
		}
		
		
	}

}
