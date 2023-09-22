package n1Exe4;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

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
	
	public void saveDirectoryBackupToFile (String path) {

		try {
			FileWriter output = new FileWriter("directoryBackup.txt", true);
			BufferedWriter buffer = new BufferedWriter(output);
			buffer.write(path);
			buffer.close();
		} catch (IOException event) {
			System.out.println("File not found.");
		}
	}
	
	public void readDirectoryFromBackup () throws IOException {
		BufferedReader buffer = null;
		try {
			FileReader input = new FileReader 
					("directoryBackup.txt");
			buffer = new BufferedReader(input);
			String line = "";
			while (line != null) {
				line = buffer.readLine();
				System.out.print(line + "\n");
			}
		} catch (IOException event) {
			System.out.println("File not found.");
			
		}finally {
			buffer.close();
		}
	}

	@Override
	public int compare(Path dir1, Path dir2) {
		Path dir1NameToCompare = dir1.getFileName();
		Path dir2NameToCompare = dir2.getFileName();
		return dir2NameToCompare.compareTo(dir1NameToCompare);
	}

	/*
	 * Añade la funcionalidad de leer cualquier archivo TXT y muestra su contenido por consola.
	 */
}
