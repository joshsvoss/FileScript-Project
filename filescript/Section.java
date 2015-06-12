package filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import filters.Filter;
import orders.Order;

public class Section {
	
	// Data fields:
	Filter filter;
	Order order;
	String srcDir;
	ArrayList<File> matchedFilesList; //TODO The comparator works with files, but you want your list containing filenames/paths not files?
	
	public Section(Filter filter, Order order, String srcDir) {
		this.filter = filter;
		this.order = order;
		this.srcDir = srcDir;
		
		this.matchedFilesList = new ArrayList<File>();
	}
	
	public void printSectionResults() { //TODO should this return boolean, or do we know for sure at this point, it no excpetion has been through that everything is kosher?
		storeMatchedFiles();
		orderMatchedFiles();
		printOrderedFiles();
		
	}


	private void storeMatchedFiles() {
		// TODO Auto-generated method stub
		
	}
	
	private void orderMatchedFiles() {
		Collections.sort(matchedFilesList, this.order);
	}
	
	private void printOrderedFiles() {
		// TODO Auto-generated method stub
		
	}

}
