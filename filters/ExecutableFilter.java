package filters;

import java.io.File;

/** This filter matches files whose exectuable permission is true.
 * @author Joshua Voss
 *
 */
public class ExecutableFilter implements Filter{

	private boolean yes;

	/** Filter constructor.
	 * @param yesOrNo decides whether executable or non-executable files are matched.
	 * Must be either "YES" or "NO".
	 * @throws InvalidFilterParamException thrown if yesOrNo is null, or a string
	 * besides "YES" or "NO"
	 */
	public ExecutableFilter(String yesOrNo) throws InvalidFilterParamException {
		if (yesOrNo == null || ( !yesOrNo.equals("YES") && !yesOrNo.equals("NO") )) {
			throw new InvalidFilterParamException("Param passed to Executable filter wasn't YES or NO, " 
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
