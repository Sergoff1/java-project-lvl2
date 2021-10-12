package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String data, String dataFormat) throws Exception {
        ObjectMapper objectMapper = getObjectMapperFor(dataFormat);
        return objectMapper.readValue(data, new TypeReference<HashMap<String, Object>>() { });
    }

    private static ObjectMapper getObjectMapperFor(String format) {
        return switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            default -> new ObjectMapper(new YAMLFactory());
        };
    }
}
