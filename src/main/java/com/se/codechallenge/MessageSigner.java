package com.se.codechallenge;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class MessageSigner {
    private String algorithm;
    
    public MessageSigner(String algorithm) {
        this.algorithm = algorithm;
    }
    
    public String createSignature(String message, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(message.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            throw new MessageEncodingException("failed to encode message", e);
        }
    }
}
