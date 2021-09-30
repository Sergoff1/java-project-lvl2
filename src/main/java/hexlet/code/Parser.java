package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws Exception {
        String file = Files.readString(Paths.get(filePath));

        String fileExtension = filePath.substring(filePath.indexOf(".") + 1).toLowerCase();

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        if (fileExtension.equals("json")) {
            objectMapper = new ObjectMapper();
        }

        return objectMapper.readValue(file, new TypeReference<>() { });
    }
}
