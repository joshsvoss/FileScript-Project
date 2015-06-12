package filters;

public class NegFilter implements Filter{
	
	Filter containedFilter;
	
	public NegFilter(Filter negatedFilter) { // TODO Need any other arguments here?
		this.containedFilter = negatedFilter;
	}
	
	public boolean doesPass(String filepath) {
		return ! containedFilter.doesPass(filepath);
	}

}
