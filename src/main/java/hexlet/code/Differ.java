package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(Map<String, Object> parsedFile1, Map<String, Object> parsedFile2) {
        Set<String> keys =  new TreeSet<>(parsedFile1.keySet());
        keys.addAll(parsedFile2.keySet());

        if (keys.isEmpty()) {
            return "";
        }

        String diff = "{\n";
        for (String key : keys) {
            if (parsedFile1.get(key) == null) {
                diff += "  + " + key + ": " + parsedFile2.get(key) + "\n";
            } else if (parsedFile2.get(key) == null) {
                diff += "  - " + key + ": " + parsedFile1.get(key) + "\n";
            } else if (parsedFile1.get(key).equals(parsedFile2.get(key))) {
                diff += "    " + key + ": " + parsedFile1.get(key) + "\n";
            } else {
                diff += "  - " + key + ": " + parsedFile1.get(key) + "\n";
                diff += "  + " + key + ": " + parsedFile2.get(key) + "\n";
            }
        }
        return diff + "}";
    }
}
