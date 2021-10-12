package hexlet.code.formatters;

import java.util.Map;

public class Stylish {

    public static String format(Map<String, Map<String, Object[]>> diff) {
        String formattedDiff = "{\n";
        for (String key : diff.keySet()) {
            Map<String, Object[]> diffValue = diff.get(key);
            if (diffValue.containsKey("add")) {
                formattedDiff += "  + " + key + ": " + diffValue.get("add")[0] + "\n";
            } else if (diffValue.containsKey("delete")) {
                formattedDiff += "  - " + key + ": " + diffValue.get("delete")[0] + "\n";
            } else if (diffValue.containsKey("same")) {
                formattedDiff += "    " + key + ": " + diffValue.get("same")[0] + "\n";
            } else {
                formattedDiff += "  - " + key + ": " + diffValue.get("changed")[0] + "\n";
                formattedDiff += "  + " + key + ": " + diffValue.get("changed")[1] + "\n";
            }
        }
        return formattedDiff + "}";
    }
}
