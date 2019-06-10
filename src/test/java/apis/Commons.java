package apis;

import com.google.gson.Gson;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public final class Commons {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static Gson GSON = new Gson();

    public static String getTimestamp() {
        return LocalDateTime.now(Clock.systemUTC()).format(TIMESTAMP_FORMATTER);
    }

    public static String signPayload(Map<String, String> json) {
        return GSON.toJson(json);
    }

    private Commons() {

    }

}
