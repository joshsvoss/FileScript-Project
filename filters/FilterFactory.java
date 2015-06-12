package filters;

public class FilterFactory {
	
	// Magic numbers
	private static final int FIRST_INDEX = 0;
	private static final int PARAM_ONE_INDEX = 1;
	private static final int PARAM_TWO_INDEX = 2;

	public static Filter buildFilter(String[] paramList) throws filescript.FileScriptException {
		
		// TODO ZEEV: to avoid repitition of expected number of params checking, should I 
		// TODO check them canonically based on the number of args they expect?
		
		//TODO Need to check for NOT param as well, before? 
		
		// Check for the parameter "NOT"
		boolean isFilterNegated = false;
		if (paramList[paramList.length - 1].equals("NOT")) { // TODO Does this need to be magic number?  And can we assume that NOT will be in that spot?
			// Then the filter needs to be negated.  Whatever filter it is:
			isFilterNegated = true;
		}
		
		Filter filterToReturn;
		switch(paramList[FIRST_INDEX]){
			case "greater_than":
				// Make sure parameter list is long enough
				if (paramList.length < 2) { //TODO is extra parameters also not acceptable?
					// TODO DUAA: EXTRA parameters (besides having one extra for "not" cause type II error in school solution and program just exits
					throw new InsufficientParamsException();
				}
				filterToReturn = new GreaterThanFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  // TODO This sorta recursive assignment works right?
				}
				return filterToReturn; //TODO what about the param that needs to be somehow passed to the filter, should it just be passed to the method?
				
			case "between":
				
				// Make sure param list is of correct length
				if (paramList.length < 3) { //TODO magic number here and above
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new BetweenFilter(paramList[PARAM_ONE_INDEX], paramList[PARAM_TWO_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				return filterToReturn;
				
				
				
				
				  // TODO need to get rid of break statements, since they're unreachable?
			
			case "smaller_than":
				
				if (paramList.length < 2) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new SmallerThanFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
				
			case "file":
				
				if (paramList.length < 1) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new FileFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
				
				
			case "contains": 
				
				if (paramList.length < 1) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new ContainsFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
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
