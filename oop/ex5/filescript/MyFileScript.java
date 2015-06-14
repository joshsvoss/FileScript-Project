package oop.ex5.filescript;

public class MyFileScript {
	
	public static void main(String[] args) throws FileScriptException {
		
		try {
		
			CommandParser parser =  new CommandParser(args);
			parser.parseCommands();
		}
		// Catch any uncaught typeIIExceptions that occur in the program flow.
		catch(TypeIIException e2) {
			System.out.println("ERROR");
			System.exit(1);
		}
		

	}

}
