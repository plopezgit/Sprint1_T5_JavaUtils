package n1Exe2.playingWithSimpleFileVisitorClass;

import java.nio.file.*;

public class App {

	public static void main(String[] args) {	
		/*DirectoryAlphabeticList dir = new DirectoryAlphabeticList();
		Path path = Paths.get("testDir");
		for (String p : dir.getDirectoryFrom(path)) {
			System.out.println(p);
		}*/
		
		DirectoryAlphabeticList dir2 = new DirectoryAlphabeticList();
		Path path2 = Paths.get("testDir");
		for (String p : dir2.getFileTreeFrom(path2)) {
			System.out.println(p);
		}
		
	}

}