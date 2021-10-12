package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        String file1 = Files.readString(Paths.get(filePath1));
        String file2 = Files.readString(Paths.get(filePath2));

        Map<String, Object> parsedFile1 = Parser.parse(file1, getFileExtension(filePath1));
        Map<String, Object> parsedFile2 = Parser.parse(file2, getFileExtension(filePath2));

        Set<String> keys =  new TreeSet<>(parsedFile1.keySet());
        keys.addAll(parsedFile2.keySet());

        if (keys.isEmpty()) {
            return "";
        }

        Map<String, Map<String, Object[]>> diff = new TreeMap<>();

        for (String key : keys) {
            if (!parsedFile1.containsKey(key)) {
                diff.put(key, Map.of("add", new Object[] {parsedFile2.get(key)}));
            } else if (!parsedFile2.containsKey(key)) {
                diff.put(key, Map.of("delete", new Object[] {parsedFile1.get(key)}));
            } else if (Objects.equals(parsedFile1.get(key), parsedFile2.get(key))) {
                diff.put(key, Map.of("same", new Object[] {parsedFile1.get(key)}));
            } else {
                diff.put(key, Map.of("changed", new Object[] {parsedFile1.get(key), parsedFile2.get(key)}));
            }
        }

        return Formatter.chooseFormat(format, diff);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static String getFileExtension(String pathToFile) {
        return pathToFile.substring(pathToFile.indexOf(".") + 1);
    }
}
