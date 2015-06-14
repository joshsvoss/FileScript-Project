package oop.ex5.filescript;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import orders.Order;
import orders.OrderFactory;
import filters.Filter;
import filters.FilterFactory;

/** This class parses through the command file, and constructs the matching filters
 * and orders through their respective factories.  
 * @author Joshua Voss
 *
 */
public class CommandParser {

	// Magic numbers
	private static final int CMD_FILE_INDEX = 1;
	private static final int SRC_DIR_INDEX = 0;
	private static final String POUND_DELIMITER = "#";
	private static final String ABS_STRING = "abs";
	private static final String ALL_STRING = "all";
	
	
	// Data fields:
	String cmdFilepath;
	String srcDirPath;
	
	File cmdFile;
	File srcDir;
	
	/** Constructor method
	 * @param args the command line args passed from the main().
	 * @throws TypeIIException Thrown if the wrong number of args are passed in.  
	 */
	public CommandParser(String[] args) throws TypeIIException {
		
		if (args.length != 2) { 
			TypeIIException e = new TypeIIException();
			throw e;
		}
		
		// Otherwise, we know we have enough args to assign them to our fields:
		this.srcDirPath = args[SRC_DIR_INDEX];
		this.cmdFilepath = args[CMD_FILE_INDEX];
	
		this.srcDir = new File(srcDirPath); // File object can represent a directory too
		this.cmdFile = new File(cmdFilepath);
	
		
		// Make sure the filepaths exist and can be used.
		validateFilepaths();
	
		
	}

	/** This helper method checks the filepaths to make sure they exist
	 * and can be used.  
	 * @throws TypeIIException if the command file can't be read or isn't a file or if 
	 * srcDir isn't a directory.
	 * 
	 */
	private void validateFilepaths() throws TypeIIException {
		if (!cmdFile.isFile() || !srcDir.isDirectory()) { 
			throw new TypeIIException();
		}
		
		// Make sure cmdFile can be read:
		if (!cmdFile.canRead()) {
			throw new TypeIIException();
		}
	}
	
	/** This method parses through the commands of the command file
	 * and creates the necessary Sections, filled with a filter and order, and 
	 * runs their printing method calls.
	 * @throws FileScriptException if there are errors parsign the commands.  
	 */
	public ArrayList<Section> parseCommands() throws FileScriptException { 
		
		Scanner cmdScanner;
		
		try {
			cmdScanner = new Scanner(cmdFile); 
			ArrayList<Section> sectionArray = new ArrayList<Section>(); //TODO delete this if not used?
			
			// But if we did succeed in finding the file, let's start to parse it.  
			int lineNum = 0;
			boolean alreadySawFilter = false;
			int filterWarningLine = 0;
			int orderWarningLine = 0;
			while(cmdScanner.hasNextLine()) { 
				
				if (alreadySawFilter == false) {
					// Read the first line, which must be "FILTER"
					String firstSectionLine = cmdScanner.nextLine(); //TODO or should we just iterate until we find FILTER ?
					lineNum++;
					if (!firstSectionLine.equals("FILTER")) {
						// If the first line isn't FILTER, we have incorrect command file syntax
						cmdScanner.close();
						throw new MissingSubSectionException(
								"First line of section isn't 'FILTER'.");
					}

				}
				else {
					lineNum++;
				}
				alreadySawFilter = false;
				// Otherwise though, we've just read over the FILTER line.
				// Now let's see what filter we have:
				if (!cmdScanner.hasNextLine()) {
					cmdScanner.close();
					throw new MissingSubSectionException("File ends after word 'FILTER'"); // TODO this shouldn't be allowed right? New Exception called EarlyEndOfFileException?
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
					// If you have a problem constructing the filter with the given params,
					// Make a default one:
//					e.printErrorMessage(lineNum); //TODO delete?
					filterWarningLine = lineNum;
					String[] defaultFilterList = {ALL_STRING};
					filter = FilterFactory.buildFilter(defaultFilterList);
					
				}
				// Let's get the order too, and put then in a Section
				if (cmdScanner.hasNextLine()) {
					String orderLine = cmdScanner.nextLine();
					lineNum++;
					if (!orderLine.equals("ORDER")) { // TODO ARE magic strings considered magic numbers?
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
				if (cmdScanner.hasNextLine()  ) {
					String orderParamLine = cmdScanner.nextLine();
					
					if(orderParamLine.equals("FILTER")) {
						// Then order is missing the param line
						// Then resort to the default order, all
						String[] defaultOrderList = {ABS_STRING}; 
						order = OrderFactory.buildOrder(defaultOrderList);
						alreadySawFilter = true;
					}
					else {
						lineNum++;
						paramList = orderParamLine.split(POUND_DELIMITER);
						// Pass the parameters on to the factory:
						try {
							order = OrderFactory.buildOrder(paramList);
						}
						catch (TypeIException e) {
//							e.printErrorMessage(lineNum); //TODO delete?
							orderWarningLine = lineNum;
							String[] defaultOrderList = {ABS_STRING}; //TODO repitition here and below in the else
							order = OrderFactory.buildOrder(defaultOrderList);
						}
					
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
				
				Section curSection = new Section(filter, order, srcDirPath, filterWarningLine, 
						orderWarningLine);
//				curSection.printSectionResults(); //TODO Delete?
				//TODO DELTE what's below?
				sectionArray.add( curSection ); //TODO should it pass the File srcDir instead of String filepath? Prob doesn't matter
				
				
				// Reset the warning line numbers to 0:
				filterWarningLine = 0;
				orderWarningLine = 0;
			}
			
			// Now that we've initialized the sections and put them in an array, 
			// have the sections print out their output
			//TODO BELOW CAN NOW BE DELETED?
//			while( ! sectionArray.isEmpty()) {
//				Section curSection = sectionArray.remove(0);
//				curSection.printSectionResults();
//			}
//			
			// Make sure to close stream before method exits:
			cmdScanner.close();

			return sectionArray;
		}
		catch (FileNotFoundException e) { 
			// If we're here, that means the file didn't exist
			throw new TypeIIException("The command file was not found"); //TODO should exception be more specific than just Type I?  
		}
		
		
	}

}
