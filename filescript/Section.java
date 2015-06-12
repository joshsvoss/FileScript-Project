package filescript;

import filters.Filter;
import orders.Order;

public class Section {
	
	// Data fields:
	Filter filter;
	Order order;
	
	public Section(Filter filter, Order order) {
		this.filter = filter;
		this.order = order;
	}
	
	public void printSectionResults() { //TODO should this return boolean, or do we know for sure at this point, it no excpetion has been through that everything is kosher?
		
		
	}

}
