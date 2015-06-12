package filescript;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import orders.Order;
import orders.OrderFactory;
import filters.Filter;
import filters.FilterFactory;

public class CommandParser {
	
	// Magic numbers
	private static final String POUND_DELIMITER = "#";
	
	// Data fields:
	String cmdFilepath;
	String srcDirPath;
	
	File cmdFile;
	File srcDir;
	
	public CommandParser(String cmdFilepath, String srcDirPath) {
		// TODO should validation of passed in arguemnts be done here?  Or can parser assume that they're takin?
		
		this.cmdFilepath = cmdFilepath;
		this.srcDirPath = srcDirPath;
	
		this.cmdFile = new File(cmdFilepath);
		this.srcDir = new File(srcDirPath); // File object can represent a directory too
	
		
		
		
		// Make sure the filepaths exist and can be used.
		validateFilepaths();
	
		
	}

	/** This helper method checks the filepaths to make sure they exist
	 * and can be used.  
	 * 
	 */
	private void validateFilepaths() {
		if (!cmdFile.isFile() || !srcDir.isDirectory()) { //TODO should we be ready to receive a file as the source directory?
			//TODO what exception to happen here?
			System.err.println("ERROR: the filepaths provided don't point to a" 
					+ " file and directory.");
			System.exit(1);
		}
		
		// Make sure cmdFile can be read:
		if (!cmdFile.canRead()) {
			// TODO what exception to throw here
			System.err.println("ERROR: Command file can't be read.");
			System.exit(1);
		}
	}
	
	public void parseCommands() throws FileScriptException { //TODO should this return boolean?
		
		Scanner cmdScanner;
		
		try {
			cmdScanner = new Scanner(cmdFile); //TODO need to put entire parsing block inside try so the .close() can also be in the try?
			Section[] sectionArray = new Section[10]; //TODO where can I get the number of sections from?
			
			// But if we did succeed in finding the file, let's start to parse it.  
			cmdScanner.useDelimiter(POUND_DELIMITER); //TODO DO I use this in the end?
			
			int curSectionIndex = 0;
			while(cmdScanner.hasNextLine()) { // TODO change to hasNextLine?
				
				// Read the first line, which must be "FILTER"
				String firstSectionLine = cmdScanner.nextLine(); //TODO or should we just iterate until we find FILTER ?
				if (! firstSectionLine.equals("FILTER")) {  
					// If the first line isn't FILTER, we have incorrect command file syntax
					cmdScanner.close();
					throw new BadCommandSyntax("First line of section isn't 'FILTER'.");
				}
				
				// Otherwise though, we've just read over the FILTER line.
				// Now let's see what filter we have:
				if (!cmdScanner.hasNext()) {
					cmdScanner.close();
					throw new BadCommandSyntax("File ends after word 'FILTER'"); // TODO this shouldn't be allowed right?
				}
				// Otherwise, split the filter line by the "#" delimiter
				String filterLine = cmdScanner.nextLine();
				String[] paramList = filterLine.split(POUND_DELIMITER);
				Filter filter = FilterFactory.buildFilter(paramList);
				
				// Let's get the order too, and put then in a Section
				String orderLine = cmdScanner.nextLine();
				if (!orderLine.equals("ORDER")) { // TODO ARE magic strings conisdered magic numbers?
					// If the  line isn't ORDER, we have incorrect command file syntax
					cmdScanner.close();
					throw new BadCommandSyntax("Line following Filter secion ins't 'ORDER'");
				}
				//Otherwise, process the oder param line
				String orderParamLine = cmdScanner.nextLine();
				paramList = orderParamLine.split(POUND_DELIMITER);
				// Pass the parameters on to the factory:
				Order order = OrderFactory.buildOrder(paramList);
				
				// Now that you have the filter and Order, let's make a section out of them 
				// And have the section print it's matching files in the correct order:
				sectionArray[curSectionIndex] = new Section(filter, order, srcDirPath); //TODO should it pass the File srcDir instead of String filepath? Prob doesn't matter
				
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
