package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {

    public static String format(Map<String, Object[]> diff) {
        try {
            return new ObjectMapper().writeValueAsString(diff);
        } catch (Exception E) {
            return "";
        }
    }
}
