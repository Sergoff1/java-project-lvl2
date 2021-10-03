package hexlet.code.formatters;

import javax.lang.model.type.PrimitiveType;
import java.util.Map;

public class Plain {

    public static String format(Map<String, Object[]> diff) {
        String formattedDiff = "";
        for (String key : diff.keySet()) {
            Object[] diffValues = diff.get(key);
            if ("_!same".equals(diffValues[0])) {
                continue;
            }

            if (diffValues[0] instanceof String) {
                diffValues[0] = "'" + diffValues[0] + "'";
            } else if (diffValues[1] instanceof Object) {
                diffValues[0] = "[complex value]";
            }

            if (diffValues[1] instanceof String) {
                diffValues[1] = "'" + diffValues[1] + "'";
            } else if (!(diffValues[1] instanceof PrimitiveType)) {
                diffValues[1] = "[complex value]";
            }

            formattedDiff += "Property '" + key + "' was ";
            if ("'_!add'".equals(diffValues[0])) {
                formattedDiff += "added with value: " + diffValues[1] + "\n";
            } else if ("'_!delete'".equals(diffValues[0])) {
                formattedDiff += "was removed\n";
            } else {
                formattedDiff += "updated. From " + diffValues[0] + " to " + diffValues[1] + "\n";
            }
        }

        return formattedDiff;
    }
}
