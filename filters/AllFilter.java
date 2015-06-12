package filters;

public class AllFilter implements Filter {

	@Override
	public boolean doesPass(String filepath) {
		
		return true; //TODO what if filepath doesn't exist?  I can assume I won't be sending in filepaths that dont' exist I don't think
	}

}
