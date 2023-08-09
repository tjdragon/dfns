package tj.dfns.security;

import tj.dfns.utils.Utils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class Nonce {
    public final String uuid;
    public final String time;

    public Nonce() {
        this.uuid = UUID.randomUUID().toString();
        this.time = ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT).toString();
    }

    public String asJSON() {
        return Utils.toJSON(this, getClass());
    }

    @Override
    public String toString() {
        return "Nonce{" +
                "uuid='" + uuid + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
