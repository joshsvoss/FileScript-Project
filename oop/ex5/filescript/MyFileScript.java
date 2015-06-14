package oop.ex5.filescript;

public class MyFileScript {
	
	public static void main(String[] args) throws FileScriptException {
		
		// First validate the filepaths before you pass them on to the parser
		//TODO How do I know the parser shouldn't validate the filepaths?  How do I know who should be responsible for what?
		
	
		
		
		try {
		
			CommandParser parser =  new CommandParser(args);
			parser.parseCommands();
		}
		// Catch any uncaught typeIIExceptions that occur in the program flow.
		catch(TypeIIException e2) {
			System.out.println("ERROR");
			System.exit(1);
		}
		
		
		// TODO delete debug
		System.out.println("Main done");
	}

}
