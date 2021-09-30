package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        Map<String, Object> parsedFile1 = Parser.parse(filePath1);
        Map<String, Object> parsedFile2 = Parser.parse(filePath2);

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, parsedFile2));

        filePath1 = "src/test/resources/file1.yml";
        filePath2 = "src/test/resources/file2.yml";

        parsedFile1 = Parser.parse(filePath1);
        parsedFile2 = Parser.parse(filePath2);

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, parsedFile2));

        expected = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, Map.of()));

        Assertions.assertEquals("", Differ.generate(Map.of(), Map.of()));
    }
}
