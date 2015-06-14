package filters;

import java.io.File;

/** The SuffixFilter matches files whose names end with the suffix passed to the constructor.
 * @author Joshua Voss
 *
 */
public class SuffixFilter implements Filter {

	private String suffixToMatch;

	/** Constructor method.
	 * @param suffix to be matche to the end of matching files.
	 * @throws InvalidFilterParamException thrown if suffix is null.
	 */
	public SuffixFilter(String suffix) throws InvalidFilterParamException {
		//TODO should null just be matched to a blank filename, or should it be an exception?
		if (suffix == null) {
			throw new InvalidFilterParamException("String passed to suffix filter is null");
		}
		
		// Otherwise,
		this.suffixToMatch = suffix;
	}
	
	@Override
	public boolean doesPass(String filepath) {
		File file = new File(filepath);
		return file.getName().endsWith(this.suffixToMatch);
	}

}
