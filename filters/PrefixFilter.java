package filters;

import java.io.File;

public class PrefixFilter implements Filter {

	private String prefixToMatch;

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
		return file.getName().startsWith(this.prefixToMatch); //TODO Does startsWith do what we want?  Or does the targil spec want the prefix specifically whats before the period and filetype
	}
	
}
