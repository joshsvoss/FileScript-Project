package filters;

public class FilterFactory {
	
	public static Filter buildFilter(String filterName) {
		
		switch(filterName){
			case "greater_than":
				return new GreaterThanFilter(); //TODO what about the param that needs to be somehow passed to the filter, should it just be passed to the method?
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
				break;
				
				
				
		}
		
	}

}
