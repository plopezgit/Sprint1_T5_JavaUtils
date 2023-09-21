package n1Exe1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

public class DirectoryAlphabeticList implements Comparator<Path> {
	
	private ArrayList<Path> directory;

	public DirectoryAlphabeticList() {
		directory = new ArrayList<Path>();
	}
	
	public ArrayList<Path> getDirectoryFrom (Path dir) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
			for (Path file: stream) {
		        directory.add(file.getFileName());
		    }
			
		} catch (IOException e) {
			System.err.println("File not found.");
		}
		
		directory.sort(Comparator.naturalOrder());
		
		return directory;
	}

	@Override
	public int compare(Path dir1, Path dir2) {
		Path dir1NameToCompare = dir1.getFileName();
		Path dir2NameToCompare = dir2.getFileName();
		return dir2NameToCompare.compareTo(dir1NameToCompare);
	}


	
	/*
	 * Crea una clase que liste alfabéticamente 
	 * el contenido de un directorio recibido por parámetro.
	 * 
	 * 
	 * 
	 */
}
