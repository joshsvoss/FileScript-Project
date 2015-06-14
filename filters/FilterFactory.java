package filters;

/** This class constructs the appropraite filter based on the 
 * String array passed in.
 * @author Joshua Voss
 *
 */
public class FilterFactory {
	
	// Magic numbers
	private static final int ONE_PARAM = 1;
	private static final int TWO_PARAMS = 2;
	private static final int BETWEEN_NUM_PARAMS = 3;
	private static final int FIRST_INDEX = 0;
	private static final int PARAM_ONE_INDEX = 1;
	private static final int PARAM_TWO_INDEX = 2;
	private static final String NOT_STRING = "NOT";

	/** The Filter Factory constructor.
	 * @param paramList this contains the name of the filter, and any parameters for it.
	 * @return the filter that was created.  
	 * @throws oop.ex5.filescript.TypeIException either at this level of the stack 
	 * for having insufficient number of parameters, or re-throwing an exception 
	 * thrown by the constructors for the parameters being invalid.  
	 */
	public static Filter buildFilter(String[] paramList) throws oop.ex5.filescript.TypeIException {
		
		// TODO ZEEV: to avoid repitition of expected number of params checking, should I 
		// TODO check them canonically based on the number of args they expect?
		
		
		// Check for the parameter "NOT"
		boolean isFilterNegated = false;
		if (paramList[paramList.length - ONE_PARAM].equals(NOT_STRING)) { // TODO Does this need to be magic number?  And can we assume that NOT will be in that spot?
			// Then the filter needs to be negated.  Whatever filter it is:
			isFilterNegated = true;
		}
		
		Filter filterToReturn;
		switch(paramList[FIRST_INDEX]){
			case "greater_than": //TODO turn into magic strings!
				// Make sure parameter list is long enough
				if (paramList.length < TWO_PARAMS) { //TODO is extra parameters also not acceptable?
					// DUAA: EXTRA parameters (besides having one extra for "not" cause type 
					// II error in school solution and program just exits
					throw new InsufficientParamsException();
				}
				filterToReturn = new GreaterThanFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				return filterToReturn; 
				
			case "between":
				
				// Make sure param list is of correct length
				if (paramList.length < BETWEEN_NUM_PARAMS) { 
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
				
				if (paramList.length < TWO_PARAMS) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new SmallerThanFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
				
			case "file":
				
				if (paramList.length < ONE_PARAM) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new FileFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
				
				
			case "contains": 
				
				if (paramList.length < ONE_PARAM) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new ContainsFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
			case "prefix":
				
				if (paramList.length < ONE_PARAM) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new PrefixFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
			case "suffix":
				
				if (paramList.length < ONE_PARAM) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new SuffixFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
				
			case "writable":
				if (paramList.length < ONE_PARAM) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new WritableFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
			case "executable":
				if (paramList.length < ONE_PARAM) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new ExecutableFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
			case "hidden": 
				if (paramList.length < ONE_PARAM) {
					throw new InsufficientParamsException();
				}
				
				filterToReturn = new HiddenFilter(paramList[PARAM_ONE_INDEX]);
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
			case "all":
				filterToReturn = new AllFilter();
				
				// If filter is to be negated, then put it inside the decorator NegFilter
				if (isFilterNegated) {
					filterToReturn = new NegFilter(filterToReturn);  
				}
				
				return filterToReturn;
				
			// Otherwise, we've got a string that doesn't match what we expect
			default:
				//TODO what exception is supposed to occur here?
				throw new BadFilterNameException();
				
				
				
		}
		
	}

}
