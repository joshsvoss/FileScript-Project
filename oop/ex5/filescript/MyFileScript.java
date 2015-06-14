package oop.ex5.filescript;

public class MyFileScript {
	
	/** This main method drives the entire program, although
	 * most of the work is done inside CommandParser's parseCommands().
	 * @param args command line arguments.  
	 * Usage: java MyFileScript <src Directory> <command filepath>
	 * @throws FileScriptException
	 */
	public static void main(String[] args) throws FileScriptException { //TODO there should be any uncaught exception below no?
		
		try {
		
			CommandParser parser =  new CommandParser(args);
			parser.parseCommands();
		}
		// Catch any uncaught typeIIExceptions that occur in the program flow.
		catch(TypeIIException e2) {
			System.out.println("ERROR");
			return;
		}
		
		

	}

}
