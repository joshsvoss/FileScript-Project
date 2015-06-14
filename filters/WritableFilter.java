package filters;

import java.io.File;

/** The WritableFilter matches files whose writable permission is turned on.
 * @author Joshua Voss
 *
 */
public class WritableFilter implements Filter {

	private boolean yes;

	/** Constructor method.
	 * @param yesOrNo Must be either "YES" or "NO" or below exception is thrown.
	 * this argument decides whether mathced files are returned, or excluded and unmatched 
	 * are returned.  
	 * @throws InvalidFilterParamException thrown if yesOrNo is null or not "YES" or "NO".
	 */
	public WritableFilter(String yesOrNo) throws InvalidFilterParamException {
		if (yesOrNo == null || ( !yesOrNo.equals("YES") && !yesOrNo.equals("NO") )) {
			throw new InvalidFilterParamException("Param passed to Writable filter wasn't YES or NO, " 
			+ "or was null");
		}
		
		// Otherwise, 
		this.yes = yesOrNo.equals("YES");	
	}

	@Override
	public boolean doesPass(String filepath) { 
		File file = new File(filepath);
		
		if (this.yes) {
			return file.canWrite();
		}
		else {
			return !file.canWrite();
		}
	}

}
