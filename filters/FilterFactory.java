package filters;

public class FilterFactory {
	
	// Magic numbers
	private static final int FIRST_INDEX = 0;
	private static final int PARAM_ONE = 1;

	public static Filter buildFilter(String[] paramList) throws FileScriptException {
		
		switch(paramList[FIRST_INDEX]){
			case "greater_than":
				// Make sure parameter list is long enough
				if (paramList.length < 2) { //TODO is extra parameters also not acceptable?
					// TODO what exception gets thrown for incorrect number of filter parameters?
				}
					
				return new GreaterThanFilter(paramList[PARAM_ONE]); //TODO what about the param that needs to be somehow passed to the filter, should it just be passed to the method?
				break;
			case "between":
				return new BetweenFilter();
				break;  // TODO need to get rid of break statements, since they're unreachable?
			case "smaller_than":
				return new SmallerThanFilter();
				break;
			case "file":
				return new FileFilter();
				break;
			case "contains": 
				return new ContainsFilter();
				break;
			case "prefix":
				return new PrefixFilter();
				break;
			case "suffix":
				return new SuffixFilter();
				break;
			case "writable":
				return new WritableFilter();
				break;
			case "executable":
				return new ExecutableFilter();
				break;
			case "hidden": 
				return new HiddenFilter();
				break;
			case "all":
				return new AllFilter();
				break;
			// Otherwise, we've got a string that doesn't match what we expect
			default:
				//TODO what exception is supposed to occur here?
				throw new BadFilterNameException();
				
				
				
		}
		
	}

}
