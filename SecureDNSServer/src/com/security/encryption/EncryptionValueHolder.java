package com.security.encryption;

import java.io.Serializable;
import java.math.BigInteger;

public class EncryptionValueHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	private int RSA_BIT_SIZE;
	private BigInteger RSA_PRIME_P, RSA_PRIME_Q, RSA_PHI_N, RSA_PRIME_PRIVATEKEY;
	private BigInteger RSA_MOD_N, RSA_PUBLIC_KEY;
	String RSA_CIPHER_TEXT;
	String SH1_CIPHER_TEXT;
	String SH1_ENCRYPTION_KEY;

	public EncryptionValueHolder() {

	}

	public int getRSA_BIT_SIZE() {
		return RSA_BIT_SIZE;
	}

	public void setRSA_BIT_SIZE(int rSA_BIT_SIZE) {
		RSA_BIT_SIZE = rSA_BIT_SIZE;
	}

	public BigInteger getRSA_PRIME_P() {
		return RSA_PRIME_P;
	}

	public void setRSA_PRIME_P(BigInteger rSA_PRIME_P) {
		RSA_PRIME_P = rSA_PRIME_P;
	}

	public BigInteger getRSA_PRIME_Q() {
		return RSA_PRIME_Q;
	}

	public void setRSA_PRIME_Q(BigInteger rSA_PRIME_Q) {
		RSA_PRIME_Q = rSA_PRIME_Q;
	}

	public BigInteger getRSA_PHI_N() {
		return RSA_PHI_N;
	}

	public void setRSA_PHI_N(BigInteger rSA_PHI_N) {
		RSA_PHI_N = rSA_PHI_N;
	}

	public BigInteger getRSA_PRIME_PRIVATEKEY() {
		return RSA_PRIME_PRIVATEKEY;
	}

	public void setRSA_PRIME_PRIVATEKEY(BigInteger rSA_PRIME_PRIVATEKEY) {
		RSA_PRIME_PRIVATEKEY = rSA_PRIME_PRIVATEKEY;
	}

	public BigInteger getRSA_MOD_N() {
		return RSA_MOD_N;
	}

	public void setRSA_MOD_N(BigInteger rSA_MOD_N) {
		RSA_MOD_N = rSA_MOD_N;
	}

	public BigInteger getRSA_PUBLIC_KEY() {
		return RSA_PUBLIC_KEY;
	}

	public void setRSA_PUBLIC_KEY(BigInteger rSA_PUBLIC_KEY) {
		RSA_PUBLIC_KEY = rSA_PUBLIC_KEY;
	}

	public String getRSA_CIPHER_TEXT() {
		return RSA_CIPHER_TEXT;
	}

	public void setRSA_CIPHER_TEXT(String rSA_CIPHER_TEXT) {
		RSA_CIPHER_TEXT = rSA_CIPHER_TEXT;
	}

	public String getSH1_CIPHER_TEXT() {
		return SH1_CIPHER_TEXT;
	}

	public void setSH1_CIPHER_TEXT(String sH1_CIPHER_TEXT) {
		SH1_CIPHER_TEXT = sH1_CIPHER_TEXT;
	}

	public String getSH1_ENCRYPTION_KEY() {
		return SH1_ENCRYPTION_KEY;
	}

	public void setSH1_ENCRYPTION_KEY(String sH1_ENCRYPTION_KEY) {
		SH1_ENCRYPTION_KEY = sH1_ENCRYPTION_KEY;
	}

	@Override
	public String toString() {
		return "EncryptionValueHolder [RSA_BIT_SIZE=" + RSA_BIT_SIZE + ", RSA_PRIME_P=" + RSA_PRIME_P + ", RSA_PRIME_Q="
				+ RSA_PRIME_Q + ", RSA_PHI_N=" + RSA_PHI_N + ", RSA_PRIME_PRIVATEKEY=" + RSA_PRIME_PRIVATEKEY
				+ ", RSA_MOD_N=" + RSA_MOD_N + ", RSA_PUBLIC_KEY=" + RSA_PUBLIC_KEY + ", RSA_CIPHER_TEXT="
				+ RSA_CIPHER_TEXT + ", SH1_CIPHER_TEXT=" + SH1_CIPHER_TEXT + ", SH1_ENCRYPTION_KEY="
				+ SH1_ENCRYPTION_KEY + "]";
	}
}
