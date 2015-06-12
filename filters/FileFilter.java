package filters;

import java.io.File;

public class FileFilter implements Filter {
	
	private String stringToMatch;

	public FileFilter(String filename) throws InvalidFilterParamException {
		//TODO should null just be matched to a blank filename, or should it be an exception?
		if (filename == null) {
			throw new InvalidFilterParamException("String passed to file filter is null");
		}
		
		// Otherwise,
		this.stringToMatch = filename;
		
	}

	@Override
	public boolean doesPass(String filepath) {
		//TODO is the string passed in a an absolute filepath or just relative?  Probably absolute I imagine
		File file = new File(filepath);
		return file.getName().equals(this.stringToMatch);
	}

}
