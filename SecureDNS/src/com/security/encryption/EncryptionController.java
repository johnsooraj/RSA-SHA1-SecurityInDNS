package com.security.encryption;

public class EncryptionController {

	public EncryptionController() {

	}

	public String getURL(String userInput) {

		EncryptionValueHolder holder = new EncryptionValueHolder();

		SH1Encryption sh1Encryption = new SH1Encryption();
		holder.setSH1_CIPHER_TEXT(sh1Encryption.getCipherBySH1(userInput, userInput));
		holder.setSH1_ENCRYPTION_KEY(userInput);

		RSAEncryption rsaEncryption = new RSAEncryption();
		holder.setRSA_BIT_SIZE(rsaEncryption.m_iBitSize);
		holder.setRSA_PRIME_P(rsaEncryption.m_cBiPrimeP);
		holder.setRSA_PRIME_Q(rsaEncryption.m_cBiPrimeQ);
		holder.setRSA_PHI_N(rsaEncryption.m_cBiPhiN);
		holder.setRSA_PRIME_PRIVATEKEY(rsaEncryption.m_cBiPrivateKey);
		holder.setRSA_MOD_N(rsaEncryption.m_cBiModN);
		holder.setRSA_PUBLIC_KEY(rsaEncryption.m_cBiPublicKey);
		holder.setRSA_CIPHER_TEXT(rsaEncryption.encryptPlainStrToHex(holder.SH1_CIPHER_TEXT));
		System.out.println("---------------------------RSA ENCRYPTION-------------------------");
		System.out.println("RSA PUBLIC KEY  :" + holder.getRSA_PUBLIC_KEY());
		System.out.println("RSA PRIVATE KEY :" + holder.getRSA_PRIME_PRIVATEKEY());
		System.out.println("RSA MOD N  KEY  :" + holder.getRSA_MOD_N());
		System.out.println("RSA CIPHER TEXT :" + holder.getRSA_CIPHER_TEXT());
		System.out.println("\n" + "---------------------------SHA1 ENCRYPTION-------------------------");
		System.out.println("SHA 1 KEY 		   :" + holder.getSH1_ENCRYPTION_KEY());
		System.out.println("SHA 1 CHIPHER TEXT :" + holder.getSH1_CIPHER_TEXT());
		System.out.println("-------------------------------------------------------------------");
		DNSServerConnector connector = new DNSServerConnector(holder);
		return connector.getReponseURL();
	}
}
