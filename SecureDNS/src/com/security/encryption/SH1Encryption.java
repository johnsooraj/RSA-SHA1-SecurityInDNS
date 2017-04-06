package com.security.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import bwmorg.bouncycastle.util.encoders.Base64;

public class SH1Encryption {

	private static byte[] sharedvector = { 0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11 };

	public SH1Encryption() {

	}

	/// Encryption
	public String getCipherBySH1(String RawText,String sh1Key) {
		String EncText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;
		String key = sh1Key;
		byte[] toEncryptArray = null;

		try {

			toEncryptArray = RawText.getBytes("UTF-8");
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key.getBytes("UTF-8"));

			if (temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for (int i = temporaryKey.length; i < 24; i++) {
					keyArray[i] = temporaryKey[index];
				}
			}

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
			byte[] encrypted = c.doFinal(toEncryptArray);
			// byte[] encodedBytes = Base64.getEncoder().encode(c.getBytes());
			EncText = new String(Base64.encode(encrypted));

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException NoEx) {
			JOptionPane.showMessageDialog(null, NoEx);
		}

		return EncText;
	}
}
