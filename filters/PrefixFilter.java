package filters;

import java.io.File;

/** This filter matches files who start with the string passed to the constructor.
 * @author Joshua Voss
 *
 */
public class PrefixFilter implements Filter {

	private String prefixToMatch;

	/** Constructor method.
	 * @param filename The string to be matched to files that start with it.
	 * @throws InvalidFilterParamException thrown if the filename is null.
	 */
	public PrefixFilter(String filename) throws InvalidFilterParamException {
		//TODO should null just be matched to a blank filename, or should it be an exception?
		if (filename == null) {
			throw new InvalidFilterParamException("String passed to prefix filter is null");
		}
		
		// Otherwise,
		this.prefixToMatch = filename;
	}
	
	@Override
	public boolean doesPass(String filepath) {
		//TODO is the string passed in a an absolute filepath or just relative?  Probably absolute I imagine
		File file = new File(filepath);
		return file.getName().startsWith(this.prefixToMatch); 
	}
	
}
