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

}
