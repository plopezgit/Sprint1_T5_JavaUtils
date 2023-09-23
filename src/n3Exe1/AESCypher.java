package n3Exe1;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESCypher {
	
	private SecretKeySpec createKey (String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		byte[] keyEncryption = key.getBytes("UTF-8");
		
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		
		keyEncryption = sha.digest(keyEncryption);
		keyEncryption = Arrays.copyOf(keyEncryption, 16);
		
		SecretKeySpec secretKey = new SecretKeySpec(keyEncryption,"AES");
		
		return secretKey;
	}
	
	public String encrypt (String data, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, 
													InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, 
														BadPaddingException {
		SecretKeySpec secretKey = this.createKey(key);
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] dataToEncrypt = data.getBytes("UTF-8");
		byte[] encryptedBytes = cipher.doFinal(dataToEncrypt);
		String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
		
		
		return encrypted;
	}
	
	public String desencrypt (String encryptedData, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, 
															InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, 
																BadPaddingException {
		SecretKeySpec secretKey = this.createKey(key);
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
		byte[] dataDesencrypted = cipher.doFinal(encryptedBytes);
		String data = new String(dataDesencrypted);
		
		return data;
	}
	
	
}