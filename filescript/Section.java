package filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

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
		// For each file in the source directory, if it passes the filter, 
		// add it to the list.
		File srcDirFile = new File(this.srcDir);
		File[] fileArray = srcDirFile.listFiles();
		
		for (File curFile: fileArray) {
			if (this.filter.doesPass(curFile.getAbsolutePath())) { //TODO seems silly to take the filepath of the File and pass that to something that will turn the filepath back into a File object
				// Then add it to our data structure:
				matchedFilesList.add(curFile);
			}
		}
		
		
	}
	
	private void orderMatchedFiles() {
		Collections.sort(matchedFilesList, this.order);
	}
	
	private void printOrderedFiles() {
		// Iterate through ordered list and print results
		ListIterator<File> iterator = matchedFilesList.listIterator();
		while(iterator.hasNext()) {
			String filename = iterator.next().getName();
			System.out.println(filename);
		}
		
	}

}
