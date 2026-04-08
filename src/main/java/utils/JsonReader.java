package utils;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    private static Map<String, Map<String, String>> data;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(
                new File("resources/testData/loginData.json"),
                Map.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

    public static String getData(String userType, String key) {
        return data.get(userType).get(key);
    }
    
    
}