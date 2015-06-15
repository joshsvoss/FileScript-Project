package filters;

import java.io.File;

/** This filter will match files whose names contain the string passed to the constructor.
 * @author Joshua Voss
 *
 */
public class ContainsFilter implements Filter {
	
	private String stringToMatch;

	/** This constructore will throw an InvalidFilterParamException if the parameters are in
	 * anyway invalid.
	 * @param filename the name to be matched to the various files' names.
	 * @throws InvalidFilterParamException thrown if the string arg "filename" is null.
	 */
	public ContainsFilter(String filename) throws InvalidFilterParamException {
		if (filename == null) {
			throw new InvalidFilterParamException("String passed to constains filter is null");
		}
		
		// Otherwise,
		this.stringToMatch = filename;
	}
	
	@Override
	public boolean doesPass(String filepath) {
		
		File file = new File(filepath);
		return file.getName().contains(this.stringToMatch);
	}

}
