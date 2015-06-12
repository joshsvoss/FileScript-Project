package filters;

import java.io.File;

public class WritableFilter implements Filter {

	private boolean yes;

	public WritableFilter(String yesOrNo) throws InvalidFilterParamException {
		if (yesOrNo == null || ( !yesOrNo.equals("YES") && !yesOrNo.equals("NO") )) {
			throw new InvalidFilterParamException("Param passed to Writable filter wasn't YES or NO, " 
			+ "or was null");
		}
		
		// Otherwise, 
		this.yes = yesOrNo.equals("YES");	
	}

	@Override
	public boolean doesPass(String filepath) { //TODO should this method expect a file instead of a filename?
		File file = new File(filepath);
		
		if (this.yes) {
			return file.canWrite();
		}
		else {
			return !file.canWrite();
		}
	}

}
