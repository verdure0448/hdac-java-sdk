package com.hdactech.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;

import com.hdactech.util.Util;

public class AESCryprto {
	private static String AesAlgorithm = "AES";
	
	public AESCryprto() {}

	public String encrypt(String plainText, byte[] password) {
		byte[] hashPwd = null;
		try {
			hashPwd = Util.ripemd128(Util.ripemd128(password));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(AesAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecretKeySpec secretKeySpec = new SecretKeySpec(hashPwd, AesAlgorithm);
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		byte[] cipherText = null;
		try {
			cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return new Base64().encodeAsString(cipherText);
	}

	public String decrypt(String cipherText, byte[] password) {
		byte[] hashPwd = null;
		try {
			hashPwd = Util.ripemd128((Util.ripemd128(password)));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] decodedCipherText = new Base64().decode(cipherText);
		
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(AesAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecretKeySpec secretKeySpec = new SecretKeySpec(hashPwd, AesAlgorithm);
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] plainText = null;
		try {
			plainText = cipher.doFinal(decodedCipherText);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String(plainText);
	}
}