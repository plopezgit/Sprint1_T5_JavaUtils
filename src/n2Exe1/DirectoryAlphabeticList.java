package n2Exe1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.Properties;

public class DirectoryAlphabeticList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Properties properties;
	private File dir;
	private ArrayList<String> directoryList;
	private final String FILE_NOT_FOUND_MSG = "File not found";

	public DirectoryAlphabeticList(File dir) {
		properties = new Properties();
		directoryList = new ArrayList<String>();
		this.dir = dir;
		loadDirectoryAlphabeticListProperties();
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
			FileWriter output = new FileWriter(properties.getProperty("fileTxtPath"), true);
			BufferedWriter buffer = new BufferedWriter(output);
			buffer.write(path + "\n");
			buffer.close();
		} catch (IOException event) {
			System.err.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	public void readDirectoryFromBackup () {
		try {
			FileReader input = new FileReader 
					(properties.getProperty("fileTxtPath"));
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
	
	public void serializeDirectoryToFile () {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(properties.getProperty("fileSerPath"));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this);
			objectOutputStream.close();
		} catch (IOException e) {
			System.err.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	public void desSeriaizeDirectoryFromFileToObject () {
		try {
			FileInputStream fileOutputStream = new FileInputStream(properties.getProperty("fileSerPath"));
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
	
	private void loadDirectoryAlphabeticListProperties () {
		try {
			this.properties.load(new FileReader("file.properties"));
		} catch (IOException e) {
			System.err.println(FILE_NOT_FOUND_MSG);
		}
	}

	
	/*
	 * Ejecuta el ejercicio 3 del nivel anterior parametrizando 
	 * todos los métodos en un archivo de configuración.
	 * Puedes utilizar un archivo Java Properties, 
	 * o bien la librería Apache Commons Configuration si lo prefieres.
	 * Del ejercicio anterior, parametriza lo siguiente:
	 * Directorio a leer.
	 * Nombre y directorio del archivo TXT resultante.
	 */
}
