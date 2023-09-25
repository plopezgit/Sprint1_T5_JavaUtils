package n1Exe1;

import java.nio.file.*;

public class App {

	public static void main(String[] args) {	
		Path path = Paths.get("testDir");
		DirectoryAlphabeticList dir = new DirectoryAlphabeticList(path);
		for (Path p : dir.getDirectoryFrom()) {
			System.out.println(p);
		}
	}

}
