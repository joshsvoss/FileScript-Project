package filescript;

import java.util.Scanner;

public class CommandParser {
	
	// Data fields:
	String cmdFilepath;
	String srcDirPath;
	
	public CommandParser(String cmdFilepath, String srcDirPath) {
		// TODO should validation of passed in arguemnts be done here?  Or can parser assume that they're takin?
		
		this.cmdFilepath = cmdFilepath;
		this.srcDirPath = srcDirPath;
		
	}
	
	public void parseCommands() { //TODO should this return boolean?
		
		Scanner cmdScanner = new Scanner(cmdFilepath);
		
	}

}
