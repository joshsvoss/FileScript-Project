package filters;

/** This class reverses the output of any filter passed to its constructor.
 * @author Joshua Voss
 *
 */
public class NegFilter implements Filter{
	
	Filter containedFilter;
	
	/** Constructor method.  
	 * @param negatedFilter filter who's doesPass output is to be reversed.  
	 */
	public NegFilter(Filter negatedFilter) { 
		this.containedFilter = negatedFilter;
	}
	
	@Override
	public boolean doesPass(String filepath) {
		return ! containedFilter.doesPass(filepath);
	}

}
