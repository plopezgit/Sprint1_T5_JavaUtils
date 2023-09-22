package n1Exe4;

import java.io.IOException;
import java.nio.file.*;

public class App {

	public static void main(String[] args) {	
		
		DirectoryAlphabeticList dir2 = new DirectoryAlphabeticList();
		Path path2 = Paths.get("testDir");
		for (String p : dir2.getFileTreeFrom(path2)) {
			dir2.saveDirectoryBackupToFile(p + "\n");
		}
		try {
			dir2.readDirectoryFromBackup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
