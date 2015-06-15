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
		if (filename == null) {
			throw new InvalidFilterParamException("String passed to prefix filter is null");
		}
		
		// Otherwise,
		this.prefixToMatch = filename;
	}
	
	@Override
	public boolean doesPass(String filepath) {
		File file = new File(filepath);
		return file.getName().startsWith(this.prefixToMatch); 
	}
	
}
