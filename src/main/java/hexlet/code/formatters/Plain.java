package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Map;

public class Plain {

    public static String format(Map<String, Map<String, Object[]>> diff) {
        String formattedDiff = "";
        for (String key : diff.keySet()) {
            Map<String, Object[]> diffValue = diff.get(key);
            if (diffValue.containsKey("same")) {
                continue;
            }

            formattedDiff += "Property '" + key + "' was ";
            if (diffValue.containsKey("add")) {
                formattedDiff += "added with value: " + filterValue(diffValue.get("add")[0]) + "\n";
            } else if (diffValue.containsKey("delete")) {
                formattedDiff += "removed\n";
            } else {
                formattedDiff += "updated. From " + filterValue(diffValue.get("changed")[0])
                        + " to " + filterValue(diffValue.get("changed")[1]) + "\n";
            }
        }

        return formattedDiff.substring(0, formattedDiff.length() - 1);
    }

    private static boolean isComplex(Object obj) {
        return obj != null
                && (obj.getClass().isArray()
                || Map.class.isAssignableFrom(obj.getClass())
                || ArrayList.class.isAssignableFrom(obj.getClass()));
    }

    private static Object filterValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (isComplex(value)) {
            return "[complex value]";
        }
        return value;
    }
}
