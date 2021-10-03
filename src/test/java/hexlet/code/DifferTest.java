package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DifferTest {

    @Test
    void testGenerateStylish() throws Exception {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        Map<String, Object> parsedFile1 = Parser.parse(filePath1);
        Map<String, Object> parsedFile2 = Parser.parse(filePath2);

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, parsedFile2, "stylish"));

        filePath1 = "src/test/resources/file1.yml";
        filePath2 = "src/test/resources/file2.yml";

        parsedFile1 = Parser.parse(filePath1);
        parsedFile2 = Parser.parse(filePath2);

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, parsedFile2, "stylish"));

        expected = """
                {
                  - chars1: [a, b, c]
                  - chars2: [d, e, f]
                  - checked: false
                  - default: null
                  - id: 45
                  - key1: value1
                  - numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  - numbers3: [3, 4, 5]
                  - setting1: Some value
                  - setting2: 200
                  - setting3: true
                }""";

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, Map.of(), "stylish"));

        Assertions.assertEquals("", Differ.generate(Map.of(), Map.of(), "stylish"));
    }

    @Test
    void testGeneratePlain() throws Exception {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        Map<String, Object> parsedFile1 = Parser.parse(filePath1);
        Map<String, Object> parsedFile2 = Parser.parse(filePath2);

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, parsedFile2, "plain"));

        filePath1 = "src/test/resources/file1.yml";
        filePath2 = "src/test/resources/file2.yml";

        parsedFile1 = Parser.parse(filePath1);
        parsedFile2 = Parser.parse(filePath2);

        Assertions.assertEquals(expected, Differ.generate(parsedFile1, parsedFile2, "plain"));

        Assertions.assertEquals("", Differ.generate(Map.of(), Map.of(), "plain"));
    }
}
