package n2Exe1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.Properties;

public class App {

	public static void main(String[] args) {	
		
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("file.properties"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		DirectoryAlphabeticList dir2 = new DirectoryAlphabeticList();
		Path path2 = Paths.get(properties.getProperty("directoryRead"));
		dir2.getFileTreeFrom(path2);
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(properties.getProperty("file"));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(dir2);
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(properties.getProperty("file"));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			DirectoryAlphabeticList dir3 = (DirectoryAlphabeticList) objectInputStream.readObject();
			objectInputStream.close();
			
			for (String p : dir3.getDirectory()) {
				System.out.println(p);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
