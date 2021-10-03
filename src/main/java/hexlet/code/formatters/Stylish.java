package hexlet.code.formatters;

import java.util.Map;

public class Stylish {

    public static String format(Map<String, Object[]> diff) {
        String formattedDiff = "{\n";
        for (String key : diff.keySet()) {
            Object[] diffValues = diff.get(key);
            if ("_!add".equals(diffValues[0])) {
                formattedDiff += "  + " + key + ": " + diffValues[1] + "\n";
            } else if ("_!delete".equals(diffValues[0])) {
                formattedDiff += "  - " + key + ": " + diffValues[1] + "\n";
            } else if ("_!same".equals(diffValues[0])) {
                formattedDiff += "    " + key + ": " + diffValues[1] + "\n";
            } else {
                formattedDiff += "  - " + key + ": " + diffValues[0] + "\n";
                formattedDiff += "  + " + key + ": " + diffValues[1] + "\n";
            }
        }
        return formattedDiff + "}";
    }
}
