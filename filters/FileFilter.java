package filters;

import java.io.File;

/** The File Filter, matches all files who have the same name name (excluding path).
 * @author Joshua Voss
 *
 */
public class FileFilter implements Filter {
	
	private String stringToMatch;

	/** FileFilter constructor.  
	 * @param filename the name to be matched to an identically-named file.
	 * @throws InvalidFilterParamException thrown if the string filename is null.
	 */
	public FileFilter(String filename) throws InvalidFilterParamException {
		
		if (filename == null) {
			throw new InvalidFilterParamException("String passed to file filter is null");
		}
		
		// Otherwise,
		this.stringToMatch = filename;
		
	}

	@Override
	public boolean doesPass(String filepath) {
		File file = new File(filepath);
		return file.getName().equals(this.stringToMatch);
	}

}
