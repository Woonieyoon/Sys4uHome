package ky.sys4u.file.filetree;

public interface Convertable<S,R> {
	
	public R convert(S source);

}
