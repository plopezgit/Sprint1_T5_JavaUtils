package n1Exe5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DirectoryAlphabeticList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private File dir;
	private ArrayList<String> directoryList;
	private final String FILE_NOT_FOUND_MSG = "File not found";

	public DirectoryAlphabeticList(File dir) {
		directoryList = new ArrayList<String>();
		this.dir = dir;
	}
	
	public ArrayList<String> getDirectoryList() {
		return directoryList;
	}

	public void setDirectoryList(ArrayList<String> directoryList) {
		this.directoryList = directoryList;
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
			System.err.println(FILE_NOT_FOUND_MSG);
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
			System.err.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	public void serializeDirectoryToFile () throws IOException {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("directoryBackup.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this);
			objectOutputStream.close();
		} catch (IOException e) {
			System.err.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	public void desSeriaizeDirectoryFromFileToObject () throws IOException, ClassNotFoundException {
		try {
			FileInputStream fileOutputStream = new FileInputStream("directoryBackup.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream);
			DirectoryAlphabeticList desSerializedDirectoryAlphaList = (DirectoryAlphabeticList) objectInputStream.readObject();
			objectInputStream.close();
			for (String s : desSerializedDirectoryAlphaList.getDirectoryList()) {
				System.out.println(s);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(FILE_NOT_FOUND_MSG);
		}
	} 
	
	/*
	 * Ahora el programa debe serializar un Objeto Java a un archivo 
	 * .ser y después debe desserializarlo.
	 */
}
