package n1Exe5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DirectoryAlphabeticList implements Comparator<Path> {
	


	private ArrayList<String> directory;

	public DirectoryAlphabeticList() {
		directory = new ArrayList<String>();
	}
	
	public ArrayList<String> getFileTreeFrom (Path dir) {
		try {
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>(){
				  @Override
				  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				    if(!attrs.isDirectory()){
				       directory.add("(D) " + file.getParent() + "\n(F) " + file.getFileName() + " | Modified: " + attrs.lastModifiedTime());
				    }
				    return FileVisitResult.CONTINUE;
				  }
				  });
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		directory.sort(Comparator.naturalOrder());

		return directory;
	}
	
	public void saveDirectoryBackup(String path) {

		try {
			FileWriter output = new FileWriter("directoryBackup.txt", true);
			BufferedWriter buffer = new BufferedWriter(output);
			buffer.write(path);
			buffer.close();
		} catch (IOException event) {
			System.out.println("File not found.");
		}
	}

	@Override
	public int compare(Path dir1, Path dir2) {
		Path dir1NameToCompare = dir1.getFileName();
		Path dir2NameToCompare = dir2.getFileName();
		return dir2NameToCompare.compareTo(dir1NameToCompare);
	}

	
	/*
	 * Añade a la clase del ejercicio anterior, 
	 * la funcionalidad de listar un árbol de directorios 
	 * con el contenido de todos sus niveles (recursivamente) 
	 * de forma que se impriman en pantalla en orden alfabético 
	 * dentro de cada nivel, indicando además si es un directorio
	 * (D) o un archivo (F), y su última fecha de modificación.
	 */
}
