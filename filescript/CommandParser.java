package filescript;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
			cmdScanner = new Scanner(cmdFile);
		}
		catch (FileNotFoundException e) { // TODO the existence of the file is checked before.  Get rid of it above?
			// If we're here, that means the file didn't exist
			//TODO which exception is this?  My guess is type I
			throw new TypeIIException("The command file was not found"); //TODO should exception be more specific than just Type I?  
		}
		
		// But if we did succeed in finding the file, let's start to parse it.  
		cmdScanner.useDelimiter(POUND_DELIMITER); //TODO DO I use this in the end?
		
		while(cmdScanner.hasNextLine()) { // TODO change to hasNextLine?
			
			// Read the first line, which must be "FILTER"
			String firstSectionLine = cmdScanner.nextLine(); //TODO or should we just iterate until we find FILTER ?
			if (! firstSectionLine.equals("FILTER")) {  
				// If the first line isn't FILTER, we have incorrect command file syntax
				throw new BadCommandSyntax("First line of section isn't 'FILTER'.");
			}
			
			// Otherwise though, we've just read over the FILTER line.
			// Now let's see what filter we have:
			if (!cmdScanner.hasNext()) {
				throw new BadCommandSyntax("File ends after word 'FILTER'"); // TODO this shouldn't be allowed right?
			}
			// Otherwise, split the filter line by the "#" delimiter
			String filterLine = cmdScanner.nextLine();
			String[] paramList = filterLine.split(POUND_DELIMITER);
			FilterFactory.buildFilter(paramList);
			
			
		}
		
		// Make sure to close stream before method exits:
		cmdScanner.close();
	}

}
