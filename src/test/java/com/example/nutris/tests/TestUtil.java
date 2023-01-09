package com.example.nutris.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.util.Pair;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

public class TestUtil {
    public static LinkedMultiValueMap<String, String> toQueryParams(List<Pair<String, String>> vars) {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        for (Pair<String, String> var : vars) {
            params.add(var.getFirst(), var.getSecond());
        }
        return params;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
