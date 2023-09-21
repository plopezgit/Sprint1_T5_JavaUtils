package n1Exe1;

import java.nio.file.*;

public class App {

	public static void main(String[] args) {	
		DirectoryAlphabeticList dir = new DirectoryAlphabeticList();
		Path path = Paths.get("testDir");
		for (Path p : dir.getDirectoryFrom(path)) {
			System.out.println(p);
		}
	}

}
