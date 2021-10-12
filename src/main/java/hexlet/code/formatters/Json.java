package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {

    public static String format(Map<String, Map<String, Object[]>> diff) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(diff);
    }
}
