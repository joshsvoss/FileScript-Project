package filters;


/** This filter matches all files in the source directory.  
 * @author Joshua Voss
 *
 */
public class AllFilter implements Filter {

	@Override
	public boolean doesPass(String filepath) {
		
		return true; 
	}

}
