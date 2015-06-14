package filters;

import java.io.File;

/** This class implements the HiddenFilter.
 * it will match all files who are hidden.
 * @author Joshua Voss
 *
 */
public class HiddenFilter implements Filter {
	
	private boolean yes;

	/** Constructor method for the HiddenFilter.
	 * @param yesOrNo must be either "YES" or "NO", otherwise the below exception will
	 * be thrown.  This decides whether to include or exclude hidden files.
	 * @throws InvalidFilterParamException thrown if yesOrNo is null or if it isn't either 
	 * "YES" or "NO".
	 */
	public HiddenFilter(String yesOrNo) throws InvalidFilterParamException {
		if (yesOrNo == null || ( !yesOrNo.equals("YES") && !yesOrNo.equals("NO") )) {
			throw new InvalidFilterParamException("Param passed to Hidden filter wasn't YES or NO, " 
			+ "or was null");
		}
		
		// Otherwise, 
		this.yes = yesOrNo.equals("YES");	
	}

	@Override
	public boolean doesPass(String filepath) { 
		File file = new File(filepath);
		
		if (this.yes) {
			return file.isHidden();
		}
		else {
			return !file.isHidden();
		}
	}

}
