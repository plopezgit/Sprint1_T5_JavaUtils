package n1Exe2.playingWithSimpleFileVisitorClass;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;

public class DirectoryAlphabeticList implements Comparator<Path> {
	
	private ArrayList<String> directory;

	public DirectoryAlphabeticList() {
		directory = new ArrayList<String>();
	}
	
	public ArrayList<String> getDirectoryFrom (Path dir) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
			for (Path file: stream) {
		        directory.add(file.getFileName().toString());
		    }
			
		} catch (IOException e) {
			System.err.println("File not found.");
		}
		
		directory.sort(Comparator.naturalOrder());
		
		return directory;
	}
	
	public ArrayList<String> getFileTreeFrom (Path dir) {
		try {
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>(){
				  @Override
				  public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {			
				       directory.add("(D) " + file.getFileName() + " | Modified: " + attrs.lastModifiedTime());
				    return FileVisitResult.CONTINUE;
				  }  
				  @Override
				  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				    if(!attrs.isDirectory()){
				       directory.add("(F) " + file.getFileName() + " | Modified: " + attrs.lastModifiedTime());
				    }
				    return FileVisitResult.CONTINUE;
				  }});
		} catch (IOException e) {
			e.printStackTrace();
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
	 * Añade a la clase del ejercicio anterior, 
	 * la funcionalidad de listar un árbol de directorios 
	 * con el contenido de todos sus niveles (recursivamente) 
	 * de forma que se impriman en pantalla en orden alfabético 
	 * dentro de cada nivel, indicando además si es un directorio
	 * (D) o un archivo (F), y su última fecha de modificación.
	 * 
	 * package com.arquitecturajava;

import java.io.File;
import java.io.IOException;

public class Principal07 {

  public static void main(String[] args) throws IOException {

    File fichero = new File("carpeta1");

    mostrarCarpeta(fichero);
  }

  // una funcion recursiva que se llame a si misma
  

  }

}
	 * 
	 * 
	 * 
	 */
}