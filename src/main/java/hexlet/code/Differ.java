package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
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
