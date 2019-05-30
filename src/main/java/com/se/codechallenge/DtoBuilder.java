package com.se.codechallenge;

public class DtoBuilder {
    private static final String PUBLIC_KEY_PREFIX = "-----BEGIN PUBLIC KEY-----\n";
    private static final String PUBLIC_KEY_SUFFIX = "\n-----END PUBLIC KEY-----\n";

    public SignatureDto buildDto(String message, String signature, String encodedPublicKey) {
        SignatureDto dto = new SignatureDto();
        dto.setMessage(message);
        dto.setSignature(signature);
        dto.setPublicKey(PUBLIC_KEY_PREFIX + encodedPublicKey + PUBLIC_KEY_SUFFIX);
        return dto;
    }
}
