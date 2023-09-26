package n1Exe4;

import java.io.File;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		
		DirectoryAlphabeticList directoryAlphaList = new DirectoryAlphabeticList(new File("testDir"));
		for (String s : directoryAlphaList.getOrderedFileTreeRecursively()) {
			directoryAlphaList.saveDirectoryBackupToFile(s);
		}
		
		directoryAlphaList.readDirectoryFromBackup();
		
	}

}
