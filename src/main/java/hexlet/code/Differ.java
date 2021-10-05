package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> parsedFile1 = Parser.parse(filePath1);
        Map<String, Object> parsedFile2 = Parser.parse(filePath2);

        Set<String> keys =  new TreeSet<>(parsedFile1.keySet());
        keys.addAll(parsedFile2.keySet());

        if (keys.isEmpty()) {
            return "";
        }

        Map<String, Object[]> diff = new TreeMap<>();

        for (String key : keys) {
            if (!parsedFile1.containsKey(key)) {
                diff.put(key, new Object[] {"_!add", parsedFile2.get(key)});
            } else if (!parsedFile2.containsKey(key)) {
                diff.put(key, new Object[] {"_!delete", parsedFile1.get(key)});
            } else if (Objects.equals(parsedFile1.get(key), parsedFile2.get(key))) {
                diff.put(key, new Object[] {"_!same", parsedFile1.get(key)});
            } else {
                diff.put(key, new Object[] {parsedFile1.get(key), parsedFile2.get(key)});
            }
        }

        return Formatter.chooseFormat(format, diff);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> parsedFile1 = Parser.parse(filePath1);
        Map<String, Object> parsedFile2 = Parser.parse(filePath2);

        Set<String> keys =  new TreeSet<>(parsedFile1.keySet());
        keys.addAll(parsedFile2.keySet());

        if (keys.isEmpty()) {
            return "";
        }

        Map<String, Object[]> diff = new TreeMap<>();

        for (String key : keys) {
            if (!parsedFile1.containsKey(key)) {
                diff.put(key, new Object[] {"_!add", parsedFile2.get(key)});
            } else if (!parsedFile2.containsKey(key)) {
                diff.put(key, new Object[] {"_!delete", parsedFile1.get(key)});
            } else if (Objects.equals(parsedFile1.get(key), parsedFile2.get(key))) {
                diff.put(key, new Object[] {"_!same", parsedFile1.get(key)});
            } else {
                diff.put(key, new Object[] {parsedFile1.get(key), parsedFile2.get(key)});
            }
        }

        return Formatter.chooseFormat("stylish", diff);
    }
}
