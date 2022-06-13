package com.decathlon.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@RequiredArgsConstructor
public class TestUtils {

    private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T mapJsonToObject(Class<T> type, final String fileName) throws IOException {
        return fileName == null ? null : objectMapper.readValue(new ClassPathResource(fileName).getFile(), type);
    }
}
