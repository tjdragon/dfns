package tj.dfns.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import tj.dfns.utils.Utils;

import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class Nonce {
    public final String uuid;
    public final String date;

    public Nonce() {
        this.uuid = UUID.randomUUID().toString();
        this.date = ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT).toString();
    }

    public String jsonURLencoded() throws JsonProcessingException {
        final String json = Utils.toJSON(this, getClass());
        final String stringifiedJson = Utils.stringify(json);
        final String encoded = Utils.toBase64URL(stringifiedJson.getBytes(StandardCharsets.UTF_8));
        return encoded;
    }

    @Override
    public String toString() {
        return "Nonce{" +
                "uuid='" + uuid + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
