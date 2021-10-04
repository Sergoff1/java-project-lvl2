package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String chooseFormat(String format, Map<String, Object[]> diff) {
        return switch (format) {
            case "plain" -> Plain.format(diff);
            case "json" -> Json.format(diff);
            default -> Stylish.format(diff);
        };
    }
}
