package tj.dfns.security;

import tj.dfns.utils.Utils;

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

    public String asJSON() {
        return Utils.toJSON(this, getClass());
    }

    @Override
    public String toString() {
        return "Nonce{" +
                "uuid='" + uuid + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
