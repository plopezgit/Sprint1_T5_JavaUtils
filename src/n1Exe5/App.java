package n1Exe5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;

public class App {

	public static void main(String[] args) {	
		
		DirectoryAlphabeticList dir2 = new DirectoryAlphabeticList();
		Path path2 = Paths.get("testDir");
		dir2.getFileTreeFrom(path2);
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("directoryBackup.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(dir2);
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream fileOutputStream = new FileInputStream("directoryBackup.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream);
			DirectoryAlphabeticList dir3 = (DirectoryAlphabeticList) objectInputStream.readObject();
			objectInputStream.close();
			
			for (String p : dir3.getDirectory()) {
				System.out.println(p);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
