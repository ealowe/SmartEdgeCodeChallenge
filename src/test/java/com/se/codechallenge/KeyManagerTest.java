package com.se.codechallenge;
import java.security.KeyPair;

import org.junit.Assert;
import org.junit.Test;

public class KeyManagerTest {
    private static final String RSA_ALGORITHM = "RSA";
    
    KeyManager keyManager = new KeyManager(RSA_ALGORITHM);
    
    @Test 
    public void shouldCreateKeyPair() {
        try {
            KeyPair keyPair = keyManager.getKeyPair();
            Assert.assertNotNull("expected non-null key pair", keyPair);
            Assert.assertNotNull("expected non-null private key", keyPair.getPrivate());
            Assert.assertNotNull("expected non-null public key", keyPair.getPublic());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
