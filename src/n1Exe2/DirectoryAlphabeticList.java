package n1Exe2;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DirectoryAlphabeticList {

	private File dir;
	private ArrayList<String> directoryList;

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
	
	/*
	 * Añade a la clase del ejercicio anterior, la funcionalidad de listar un árbol
	 * de directorios con el contenido de todos sus niveles (recursivamente) de
	 * forma que se impriman en pantalla en orden alfabético dentro de cada nivel, 
	 * indicando además si es un directorio (D) o un archivo (F), y su última fecha
	 * de modificación.
	 */
}