package com.se.codechallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {
    
    public String toJson(Object o) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new MessageEncodingException(e);
        }
    }    
}
