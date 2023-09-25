package n1Exe5;

import java.io.File;
import java.io.IOException;

public class App {

	public static void main(String[] args) {	
		
		DirectoryAlphabeticList directoryAlphaList = new DirectoryAlphabeticList(new File("testDir"));
		for (String s : directoryAlphaList.getOrderedFileTreeRecursively()) {
			directoryAlphaList.saveDirectoryBackupToFile(s);
		}
		
		try {
			directoryAlphaList.readDirectoryFromBackup();
			directoryAlphaList.serializeDirectoryToFile();
			directoryAlphaList.desSeriaizeDirectoryFromFileToObject();
		} catch (IOException | ClassNotFoundException e) {
			e.getMessage();
		}
		
	}

}
