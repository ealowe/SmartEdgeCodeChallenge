package com.se.codechallenge;

import org.junit.Assert;
import org.junit.Test;

public class DtoBuilderTest {
    @Test
    public void shouldBuildDto() {
        DtoBuilder builder = new DtoBuilder();
        SignatureDto dto = builder.buildDto("manny", "moe", "jack");
        Assert.assertEquals("manny", dto.getMessage());
        Assert.assertEquals("moe", dto.getSignature());
        Assert.assertTrue(dto.getPublicKey().startsWith("-----BEGIN PUBLIC KEY-----"));
        Assert.assertTrue(dto.getPublicKey().contains("jack"));
        Assert.assertTrue(dto.getPublicKey().endsWith("-----END PUBLIC KEY-----\n"));
    }
}
