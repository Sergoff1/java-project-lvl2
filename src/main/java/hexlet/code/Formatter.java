package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String chooseFormat(String format, Map<String, Object[]> diff) {
        return  "plain".equals(format) ? Plain.format(diff) : Stylish.format(diff);
    }
}
