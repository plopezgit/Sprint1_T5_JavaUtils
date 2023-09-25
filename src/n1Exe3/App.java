package n1Exe3;

import java.io.File;

public class App {

	public static void main(String[] args) {	
		
		DirectoryAlphabeticList directoryAlphaList = new DirectoryAlphabeticList(new File("testDir"));
		for (String s : directoryAlphaList.getOrderedFileTreeRecursively()) {
			directoryAlphaList.saveDirectoryBackupToFile(s);
		}
		
	}
	
}
