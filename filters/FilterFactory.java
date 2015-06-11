package filters;

public class FilterFactory {
	
	// Magic numbers
	private static final int FIRST_INDEX = 0;
	private static final int PARAM_ONE = 1;

	public static Filter buildFilter(String[] paramList) throws filescript.FileScriptException {
		
		switch(paramList[FIRST_INDEX]){
			case "greater_than":
				// Make sure parameter list is long enough
				if (paramList.length < 2) { //TODO is extra parameters also not acceptable?
					// TODO what exception gets thrown for incorrect number of filter parameters?
				}
					
				return new GreaterThanFilter(paramList[PARAM_ONE]); //TODO what about the param that needs to be somehow passed to the filter, should it just be passed to the method?
				
			case "between":
				return new BetweenFilter();
				  // TODO need to get rid of break statements, since they're unreachable?
			case "smaller_than":
				return new SmallerThanFilter();
				
			case "file":
				return new FileFilter();
				
			case "contains": 
				return new ContainsFilter();
				
			case "prefix":
				return new PrefixFilter();
				
			case "suffix":
				return new SuffixFilter();
				
			case "writable":
				return new WritableFilter();
				
			case "executable":
				return new ExecutableFilter();
				
			case "hidden": 
				return new HiddenFilter();
				
			case "all":
				return new AllFilter();
				
			// Otherwise, we've got a string that doesn't match what we expect
			default:
				//TODO what exception is supposed to occur here?
				throw new BadFilterNameException();
				
				
				
		}
		
	}

}
