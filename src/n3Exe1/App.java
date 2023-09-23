package n3Exe1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class App {

	public static void main(String[] args) {

		Properties properties = new Properties();
		AESCypher encrypter = new AESCypher();

		try {
			properties.load(new FileReader("file.properties"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		DirectoryAlphabeticList dir2 = new DirectoryAlphabeticList();
		Path path2 = Paths.get(properties.getProperty("directoryRead"));

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(properties.getProperty("file"));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			String encryptedDirAlphaList = encrypter.encrypt(dir2.getFileTreeFrom(path2).toString(), properties.getProperty("encryptionKey"));

			objectOutputStream.writeObject(encryptedDirAlphaList);
			objectOutputStream.close();

		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fileInputStream = new FileInputStream(properties.getProperty("file"));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			String desencryptedDirAlphaList = encrypter.desencrypt((String) objectInputStream.readObject(),
					properties.getProperty("encryptionKey"));

			System.out.println(desencryptedDirAlphaList);
			objectInputStream.close();

		} catch (IOException | ClassNotFoundException | InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

	}

}
