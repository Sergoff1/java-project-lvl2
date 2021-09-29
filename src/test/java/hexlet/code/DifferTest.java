package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

class DifferTest {

    @Test
    void testGenerate() throws Exception {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        String file1 = Files.readString(Paths.get(filePath1));
        String file2 = Files.readString(Paths.get(filePath2));

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map1 = objectMapper.readValue(file1, new TypeReference<>() { });
        Map<String, Object> map2 = objectMapper.readValue(file2, new TypeReference<>() { });

        Assertions.assertEquals(expected, Differ.generate(map1, map2));

        expected = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";

        Assertions.assertEquals(expected, Differ.generate(map1, Map.of()));

        Assertions.assertEquals("{\n}", Differ.generate(Map.of(), Map.of()));
    }
}
