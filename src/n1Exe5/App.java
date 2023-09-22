package n1Exe5;

import java.nio.file.*;

public class App {

	public static void main(String[] args) {	
		
		DirectoryAlphabeticList dir2 = new DirectoryAlphabeticList();
		Path path2 = Paths.get("testDir");
		for (String p : dir2.getFileTreeFrom(path2)) {
			dir2.saveDirectoryBackup(p + "\n");;
		}
		
	}

}
