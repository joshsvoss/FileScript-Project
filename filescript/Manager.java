package filescript;

public class Manager {
	
	public static void main(String[] args) throws FileScriptException {
		
		// First validate the filepaths before you pass them on to the parser
		//TODO How do I know the parser shouldn't validate the filepaths?  How do I know who should be responsible for what?
		
		if (args.length < 2) { //TODO is this to be assumed?  Throw exception instead?
			System.err.println("ERROR: not enough cmd ln arguments, please provide 2.");
		}
		
		try {
		
			CommandParser parser =  new CommandParser(args[0], args[1]);
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
