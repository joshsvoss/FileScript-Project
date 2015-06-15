package oop.ex5.filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import filters.Filter;
import orders.Order;

/** This class implements the Section object, which contains a filter and an order,
 * and prints out the names of files that match the filter, in the order of the contained order.  
 * @author Joshua Voss
 *
 */
public class Section {
	
	// Data fields:
	private Filter filter;
	private Order order;
	private String srcDir;
	private ArrayList<File> matchedFilesList; 
	// Fields for solving warning/error/files ordering
	private int filterWarningLine;
	private int orderWarningLine;
	
	/** Full-arg constructor.  
	 * @param filter the filter to be applied.
	 * @param order the order of the printing of the files.
	 * @param srcDir the path to the source directory that is to be explored.  
	 */
	public Section(Filter filter, Order order, String srcDir, int filterWarningLine, int orderWarningLine) {
		this.filter = filter;
		this.order = order;
		this.srcDir = srcDir;
		//Ordering variables:
		this.filterWarningLine = filterWarningLine;
		this.orderWarningLine = orderWarningLine;
		
		this.matchedFilesList = new ArrayList<File>();
	}
	
	/** This method prints the sorted filenames.
	 * 
	 */
	public void printSectionResults() { 
		storeMatchedFiles();
		orderMatchedFiles();
		printOrderedFiles();
		
	}


	/** This method stores the matched files in a collection, 
	 * where they will soon be sorted.
	 * 
	 */
	private void storeMatchedFiles() {
		// For each file in the source directory, if it passes the filter, 
		// add it to the list.
		File srcDirFile = new File(this.srcDir);
		File[] fileAndDirArray = srcDirFile.listFiles();
		ArrayList<File> fileArray = new ArrayList<File>();
		for (File fileOrDir: fileAndDirArray) {
			if (fileOrDir.isFile()) {
				fileArray.add(fileOrDir);
			}
				
		}
		
		for (File curFile: fileArray) {
			if (this.filter.doesPass(curFile.getAbsolutePath())) {
				// Then add it to our data structure:
				matchedFilesList.add(curFile);
			}
		}
		
		
	}
	
	/** This method sorts the files stored in the collection.  
	 * 
	 */
	private void orderMatchedFiles() {
		Collections.sort(matchedFilesList, this.order);
	}
	
	/** This method iterates through the files in order and prints their names to stdout.
	 * 
	 */
	private void printOrderedFiles() {
		// First print warnings if any exist (if line number is greater than 0)
		// And then print the files:
		if (this.filterWarningLine > 0) {
			System.err.println("Warning in line " + this.filterWarningLine);
		}
		if (this.orderWarningLine > 0) {
			System.err.println("Warning in line " + this.orderWarningLine);
		}
		
		// Iterate through ordered list and print results
		ListIterator<File> iterator = matchedFilesList.listIterator();
		while(iterator.hasNext()) {
			String filename = iterator.next().getName();
			System.out.println(filename);
		}
		
	}

}
