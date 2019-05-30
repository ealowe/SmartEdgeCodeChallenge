package com.se.codechallenge;

import java.io.EOFException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class KeyManager {
    private static final String KEY_STORE_FILENAME = "CodeChallengeKeyStore.jks";
    private static final String KEY_STORE_PASSWORD = "__!KeyStorePassword!__";
    private static final String PRIVATE_KEY_ENTRY_ALIAS = "MyPrivateKeyEntry";
    private static final String PRIVATE_KEY_ENTRY_PASSWORD = "__!PrivateKeyEntryPassword!__";
    private static final String PUBLIC_KEY_ENTRY_ALIAS = "MyPublicKeyEntry";
    private static final String PUBLIC_KEY_ENTRY_PASSWORD = "__!PublicKeyEntryPassword!__";
    
    private String keyGenerationAlgorithm;

    public String getKeyGenerationAlgorithm() {
        return keyGenerationAlgorithm;
    }

    public KeyManager(String keyGenerationAlgorithm) {
        this.keyGenerationAlgorithm = keyGenerationAlgorithm;
    }
    
    public KeyPair getKeyPair() throws KeyManagerException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] keyStorePassword = KEY_STORE_PASSWORD.toCharArray(); 
            Path path = Paths.get(KEY_STORE_FILENAME);
            KeyPair keyPair = null;
            if (Files.exists(path)) {
                try {
                    keyStore.load(Files.newInputStream(path), keyStorePassword);
                    PublicKey publicKey = getPublicKeyFromStore(keyStore);
                    PrivateKey privateKey = getPrivateKeyFromStore(keyStore);
                    keyPair = new KeyPair(publicKey, privateKey);
                } catch (EOFException e) {
                    System.out.println("EOF while opening key store, generating new key pair");
                }
            }
            if (keyPair == null) {
                String algorithm = getKeyGenerationAlgorithm();
                keyPair = KeyPairGenerator.getInstance(algorithm).generateKeyPair();
                keyStore.load(null, keyStorePassword);
                saveKeyPairToStore(keyStore, keyPair);
                //keyStore.store(Files.newOutputStream(path), keyStorePassword);
            }
            return keyPair;
        } catch (KeyManagerException e) {
            throw e;
        } catch (Exception e) {
            throw new KeyManagerException(e);
        }
    }
    
    private void saveKeyPairToStore(KeyStore keyStore, KeyPair keyPair) {
        try {
            // haven't figured out how to generate a certificate yet....
        } catch (Exception e) {
            throw new KeyManagerException("failed to add key pair to key store", e);
        }
    }
    
    private PrivateKey getPrivateKeyFromStore(KeyStore keyStore) {
        Entry entry = getKeyEntryFromStore(keyStore, PRIVATE_KEY_ENTRY_ALIAS, PRIVATE_KEY_ENTRY_PASSWORD);
        return PrivateKeyEntry.class.cast(entry).getPrivateKey();
    }

    private PublicKey getPublicKeyFromStore(KeyStore keyStore) {
        Entry entry = getKeyEntryFromStore(keyStore, PUBLIC_KEY_ENTRY_ALIAS, PUBLIC_KEY_ENTRY_PASSWORD);
        if (entry == null) {
            throw new KeyManagerException("public key entry not found in key store");
        }
        Certificate certificate = TrustedCertificateEntry.class.cast(entry).getTrustedCertificate();
        if (certificate == null) {
            throw new KeyManagerException("trusted certificate not found in public key entry");
        }
        return certificate.getPublicKey();
    }

    private KeyStore.Entry getKeyEntryFromStore(KeyStore keyStore, String keyAlias, String keyPassword) {
        try {
            return keyStore.getEntry(keyAlias, new KeyStore.PasswordProtection(keyPassword.toCharArray()));
        } catch (Exception e) {
            throw new KeyManagerException(e);
        }
    }
}
