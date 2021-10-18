package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String chooseFormat(String format, Map<String, Map<String, Object[]>> diff)
            throws Exception {

        return switch (format) {
            case "plain" -> Plain.format(diff);
            case "json" -> Json.format(diff);
            case "stylish" -> Stylish.format(diff);
            default -> throw new Exception("Unknown output format");
        };
    }
}
