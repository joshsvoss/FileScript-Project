package orders;

import java.io.File;

/** This order sorts by absolute name, using getAbsolutePath()
 * @author Joshua Voss
 *
 */
public class AbsoluteOrder implements Order {

	@Override
	public int compare(File f1, File f2) {
		return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
	}

}
