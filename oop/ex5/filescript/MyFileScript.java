package oop.ex5.filescript;

public class MyFileScript {
	
	public static void main(String[] args) throws FileScriptException {
		
		// First validate the filepaths before you pass them on to the parser
		//TODO How do I know the parser shouldn't validate the filepaths?  How do I know who should be responsible for what?
		
		try {
			if (args.length != 2) { 
				TypeIIException e = new TypeIIException();
				throw e;
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
		}
		catch(TypeIIException e2) {
			e2.printErrorMessage();
		}
		catch (TypeIException e1) {
			e1.printErrorMessage(2); //TODO where can I get the line number from, lower down in the stack?
		}
		
		// TODO delete debug
		System.out.println("Main done");
	}

}
