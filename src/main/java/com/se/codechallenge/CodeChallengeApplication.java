package com.se.codechallenge;

import java.security.KeyPair;
import java.util.Base64;

public class CodeChallengeApplication {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new UsageException("no message provided");
            }
            String message = args[0];
            String keyGenerationAlgorithm = "RSA";
            KeyManager keyManager = new KeyManager(keyGenerationAlgorithm);
            KeyPair keyPair = keyManager.getKeyPair();
            String signingAlgorithm = "SHA256withRSA";
            MessageSigner signer = new MessageSigner(signingAlgorithm);
            String signature = signer.createSignature(message, keyPair.getPrivate());
            String encodedKey = Base64.getEncoder().encodeToString(keyPair.getPublic().toString().getBytes());
            DtoBuilder dtoBuilder = new DtoBuilder();
            SignatureDto dto = dtoBuilder.buildDto(message, signature, encodedKey);
            String json = new JsonSerializer().toJson(dto);
            System.out.println(json);
        } catch (UsageException e) {
            System.err.printf("error: %s%n", e.getMessage());
            System.err.println("usage: CodeChallengeApplication message-text");
        } catch (Exception e) {
            System.err.printf("error: %s%n", e);
            System.err.println("application failed to start");
            e.printStackTrace(System.err);
        }
    }    
}
