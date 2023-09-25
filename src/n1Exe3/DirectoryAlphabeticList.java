package n1Exe3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DirectoryAlphabeticList {
	
	private File dir;
	private ArrayList<String> directoryList;
	private final String FILE_NOT_FOUND_MSG = "File not found";

	public DirectoryAlphabeticList(File dir) {
		directoryList = new ArrayList<String>();
		this.dir = dir;
	}

	private ArrayList<String> goThroughDirectoryTree(File dir, String[] directory) {
		Collections.sort(Arrays.asList(directory));
		for (String item : directory) {
			File file = new File(dir.getAbsolutePath(), item);
			if (file.isDirectory()) {
				directoryList.add("(D) " + file.getName() + " | Modified: " + simpleDateFormat(file.lastModified()));
				goThroughDirectoryTree(file, file.list());
			} else {
				directoryList.add("(F) " + file.getName() + " | Modified: " + simpleDateFormat(file.lastModified()));
			}
		}
		
		return directoryList;
	}
	
	public ArrayList<String> getOrderedFileTreeRecursively() {
		String[] directory = this.dir.list();
		return goThroughDirectoryTree(dir, directory);
	}
	
	private String simpleDateFormat (long date) {
		DateFormat dateFormat =  new SimpleDateFormat("dd-MM-yyyy hh-MM-ss");
		String lastModifiedDateFormatted = dateFormat.format(date);
		return lastModifiedDateFormatted;
	}
	
	public void saveDirectoryBackupToFile(String path) {

		try {
			FileWriter output = new FileWriter("directoryBackup.txt", true);
			BufferedWriter buffer = new BufferedWriter(output);
			buffer.write(path + "\n");
			buffer.close();
		} catch (IOException event) {
			System.out.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	/*
	 * Modifica el ejercicio anterior.
	 * Ahora, en lugar de mostrar el resultado por la pantalla, 
	 * guarda el resultado en un archivo TXT.
	 */
}
