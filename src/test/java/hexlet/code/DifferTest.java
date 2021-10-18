package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

class DifferTest {

    @Test
    void testGenerateStylish() throws Exception {
        String expected = Files.readString(Paths.get("src", "test", "resources", "expected", "stylish"));

        File file1 = getFileByName("file1.json");
        File file2 = getFileByName("file2.json");

        Assertions.assertEquals(expected, Differ.generate(file1.getPath(), file2.getPath(), "stylish"));

        file1 = getFileByName("file1.yml");
        file2 = getFileByName("file2.yml");

        Assertions.assertEquals(expected, Differ.generate(file1.getPath(), file2.getPath()));
    }

    @Test
    void testGeneratePlain() throws Exception {
        String expected = Files.readString(Paths.get("src", "test", "resources", "expected", "plain"));

        File file1 = getFileByName("file1.json");
        File file2 = getFileByName("file2.json");

        Assertions.assertEquals(expected, Differ.generate(file1.getPath(), file2.getPath(), "plain"));

        file1 = getFileByName("file1.yml");
        file2 = getFileByName("file2.yml");

        Assertions.assertEquals(expected, Differ.generate(file1.getPath(), file2.getPath(), "plain"));
    }

    @Test
    void testGenerateJson() throws Exception {
        String expected = Files.readString(Paths.get("src", "test", "resources", "expected", "json"));

        File file1 = getFileByName("file1.json");
        File file2 = getFileByName("file2.json");

        Assertions.assertEquals(expected, Differ.generate(file1.getPath(), file2.getPath(), "json"));

        file1 = getFileByName("file1.yml");
        file2 = getFileByName("file2.yml");

        Assertions.assertEquals(expected, Differ.generate(file1.getPath(), file2.getPath(), "json"));
    }

    private File getFileByName(final String name) {
        return new File(getClass().getClassLoader().getResource(name).getFile());
    }
}
