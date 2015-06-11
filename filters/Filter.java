package filters;

public interface Filter { //TODO should This be an abstract class instead, to demand having a "boolean not" field?  But Tirgul says interface
	
	public boolean doesPass(String filepath);
}

// TODO should rest of real filters go in this file, or doesn't classname need to match filename?
