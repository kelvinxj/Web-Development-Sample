package com.kelvin.kelvinTestProjectMaven;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class CertificateTest {

	@Test
	public void test() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		char[] password = "Lihui2020".toCharArray();
		FileInputStream fis = new FileInputStream("C:\\Users\\JiaXie\\CrashPlan\\.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fis, password);
		
		KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("mykey"
				, new KeyStore.PasswordProtection(password));
		PrivateKey myPrivateKey = pkEntry.getPrivateKey();
		
		//get public key from keystore file
		//PublicKey publicKey = ks.getCertificate("mykey").getPublicKey();
		PublicKey publicKey = pkEntry.getCertificate().getPublicKey();
		
		String before = "asdf";
        byte[] plainText = before.getBytes("UTF-8");
        
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream fis1 = new FileInputStream("C:\\Users\\JiaXie\\CrashPlan\\mycert.cert");
		publicKey = cf.generateCertificate(fis1).getPublicKey();
        
        //encrypt by public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(plainText);
        
        //decrypt by private key
        cipher.init(Cipher.DECRYPT_MODE, myPrivateKey);
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println(new String(newPlainText, "UTF-8"));
        
		fis.close();
	}

}
