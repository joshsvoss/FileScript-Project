package filescript;

import java.io.File;
import java.util.Scanner;

public class CommandParser {
	
	// Data fields:
	String cmdFilepath;
	String srcDirPath;
	
	public CommandParser(String cmdFilepath, String srcDirPath) {
		// TODO should validation of passed in arguemnts be done here?  Or can parser assume that they're takin?
		
		// Make sure the the files exist and are the right type
		File cmdFile = new File(cmdFilepath);
		File srcDir = new File(srcDirPath); // File object can represent a directory too
		

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
		
		
		this.cmdFilepath = cmdFilepath;
		this.srcDirPath = srcDirPath;
		
	}
	
	public void parseCommands() { //TODO should this return boolean?
		
		Scanner cmdScanner = new Scanner(cmdFilepath);
		
	}

}
