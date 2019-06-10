package apis;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.PrivateKey;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;

public final class Commons {

    private static String encodedPrivateKey = "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgBVVr5i4W9/Sos5g/HDIOrze9w4" +
            "3BbK2C1Z73LJwZhVuhRANCAATL4Uqo1hBE/KwDRb8jfcCeY2XcBNUw7DdHYHTCsv0grVKXepwPHlY4ET9SlN3Y" +
            "zbcDcGXX13AOLi2+gji3S6lT";

    public static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static Gson GSON = new Gson();

    private static Base64.Decoder BASE64_DECODER = Base64.getDecoder();
    private static Base64.Encoder BASE64_ENCODER = Base64.getEncoder();

    public static String getTimestamp() {
        return LocalDateTime.now(Clock.systemUTC()).format(TIMESTAMP_FORMATTER);
    }

    public static String signPayload(Map<String, String> payloadAsMap) {
        final String payload = GSON.toJson(payloadAsMap);

        final PrivateKey privateKey = ECCrypto.privateKeyFromBytes(BASE64_DECODER.decode(encodedPrivateKey));
        final String bankSignature = BASE64_ENCODER.encodeToString(ECCrypto.sign(payload, privateKey));

        return BASE64_ENCODER.encodeToString(GSON.toJson(new AuthHeader("02", bankSignature)).getBytes());
    }

    private Commons() {

    }

    @Data
    @AllArgsConstructor
    private static class AuthHeader {
        private String bankCode;
        private String bankSignature;
    }

}
