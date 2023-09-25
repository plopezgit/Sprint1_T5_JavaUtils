package n1Exe4;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
	
	public void saveDirectoryBackupToFile (String path) {

		try {
			FileWriter output = new FileWriter("directoryBackup.txt", true);
			BufferedWriter buffer = new BufferedWriter(output);
			buffer.write(path + "\n");
			buffer.close();
		} catch (IOException event) {
			System.out.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	public void readDirectoryFromBackup () throws IOException {
		try {
			FileReader input = new FileReader 
					("directoryBackup.txt");
			BufferedReader buffer = new BufferedReader(input);
			String line = "";
			while (line != null) {
				line = buffer.readLine();
				System.out.print(line + "\n");
			}
			buffer.close();
		} catch (IOException event) {
			System.out.println(FILE_NOT_FOUND_MSG);
			
		}
		
	}

	/*
	 * Añade la funcionalidad de leer cualquier archivo TXT y muestra su contenido por consola.
	 */
}
