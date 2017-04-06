package com.security.encryption;

import java.math.BigInteger;

public class RSADecryption {

	public int m_iBitSize = 512;
	public BigInteger m_cBiPrimeP, m_cBiPrimeQ, m_cBiPhiN, m_cBiPrivateKey;
	public BigInteger m_cBiModN, m_cBiPublicKey;
	String cipherText;

	public RSADecryption(EncryptionValueHolder holder) {
		this.m_iBitSize = holder.getRSA_BIT_SIZE();
		this.m_cBiPhiN = holder.getRSA_PHI_N();
		this.m_cBiModN = holder.getRSA_MOD_N();
		this.m_cBiPublicKey = holder.getRSA_PUBLIC_KEY();
		this.cipherText = holder.getRSA_CIPHER_TEXT();
	}

	public String decryptHexCipherToPlainMsg() {
		System.out.println();
		System.out.println("RSA CIPHER TEXT	: " + cipherText);
		System.out.println("RSA MOD N Value	: " + m_cBiModN);
		System.out.println("RSA PUBLIC KEY 	: " + m_cBiPublicKey);
		System.out.println();
		return convertHexToString(decryptMessage(cipherText, m_cBiModN, m_cBiPublicKey));
	}

	public String decryptHexStr(String sHexStr, BigInteger cBiModN, BigInteger cBiKey) {
		return decryptMessage(sHexStr, cBiModN, cBiKey);
	}

	private String decryptMessage(String sHexString, BigInteger N, BigInteger d) {
		System.out.println(":::::decrypt Function Started::::::");
		if (sHexString.length() == 0 || sHexString == null)
			return null;
		int iMaxCharLenInOneStr = m_iBitSize / 2;
		if (iMaxCharLenInOneStr < sHexString.length()) {
			String sRetOutStr = "";
			int iBeginIndex = 0;
			int iEndIndex = iMaxCharLenInOneStr;
			while (iBeginIndex < sHexString.length()) {
				if (iEndIndex < sHexString.length()) {
					sRetOutStr += (new BigInteger(sHexString.substring(iBeginIndex, iEndIndex), 16)).modPow(d, N)
							.toString(16);
					iBeginIndex = iEndIndex;
					iEndIndex += iMaxCharLenInOneStr;
				} else {
					sRetOutStr += (new BigInteger(sHexString.substring(iBeginIndex), 16)).modPow(d, N).toString(16);
					break;
				}
			}
			return sRetOutStr;
		} else
			return (new BigInteger(sHexString, 16)).modPow(d, N).toString(16);
	}

	public String convertHexToString(String hex) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {
			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);
			temp.append(decimal);
		}
		System.out.println("Decimal : " + temp.toString());
		return sb.toString();
	}
}
