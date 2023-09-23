package n3Exe1;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
}
