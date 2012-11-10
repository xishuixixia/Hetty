package com.hetty.core.ssl;

import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyPair;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Vector;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;

import com.hetty.conf.HettyConfig;
import com.hetty.util.FileUtil;

public class PEMKeyManager extends X509ExtendedKeyManager {

	static PEMKeyManager instance = new PEMKeyManager();
	PrivateKey key;
	X509Certificate[] chain;

	private PEMKeyManager() {
		
		PEMReader reader = null;
		try {
		    final HettyConfig hettyConfig =HettyConfig.getInstance();
		    reader = new PEMReader(new FileReader(FileUtil.getFile(hettyConfig.getCertificateKeyFile())), new PasswordFinder() {
				public char[] getPassword() {
					return hettyConfig.getCertificatePassword().toCharArray();
				}
			});
			key = ((KeyPair) reader.readObject()).getPrivate();

			reader = new PEMReader(new FileReader(FileUtil.getFile(hettyConfig.getCertificateFile())));

			X509Certificate cert;
			Vector<X509Certificate> chainVector = new Vector<X509Certificate>();

			while ((cert = (X509Certificate) reader.readObject()) != null) {
				chainVector.add(cert);
			}
			chain = (X509Certificate[]) chainVector.toArray(new X509Certificate[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String chooseEngineServerAlias(String s, Principal[] principals, SSLEngine sslEngine) {
		return "";
	}

	public String[] getClientAliases(String s, Principal[] principals) {
		return new String[] { "" };
	}

	public String chooseClientAlias(String[] strings, Principal[] principals, Socket socket) {
		return "";
	}

	public String[] getServerAliases(String s, Principal[] principals) {
		return new String[] { "" };
	}

	public String chooseServerAlias(String s, Principal[] principals, Socket socket) {
		return "";
	}

	public X509Certificate[] getCertificateChain(String s) {
		return chain;
	}

	public PrivateKey getPrivateKey(String s) {
		return key;
	}
}
