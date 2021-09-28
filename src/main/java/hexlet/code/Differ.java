package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        String file1 = Files.readString(Paths.get(filePath1));
        String file2 = Files.readString(Paths.get(filePath2));

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map1 = objectMapper.readValue(file1, new TypeReference<>() { });
        Map<String, Object> map2 = objectMapper.readValue(file2, new TypeReference<>() { });

        Set<String> keys =  new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        String diff = "{\n";
        for (String key : keys) {
            if (map1.get(key) == null) {
                diff += "  + " + key + ": " + map2.get(key) + "\n";
            } else if (map2.get(key) == null) {
                diff += "  - " + key + ": " + map1.get(key) + "\n";
            } else if (map1.get(key).equals(map2.get(key))) {
                diff += "    " + key + ": " + map1.get(key) + "\n";
            } else {
                diff += "  - " + key + ": " + map1.get(key) + "\n";
                diff += "  + " + key + ": " + map2.get(key) + "\n";
            }
        }
        return diff + "}";
    }
}
