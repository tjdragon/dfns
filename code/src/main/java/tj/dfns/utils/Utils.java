package tj.dfns.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public final class Utils {
    private static final GsonBuilder gsonBuilder;
    private static Gson gson;

    static {
        gsonBuilder =
                new GsonBuilder()
                        .serializeSpecialFloatingPointValues()
                        .disableHtmlEscaping();
        gson = gsonBuilder.create();
    }

    public static String toJSON(final Object src, final Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    public static JsonObject asJsonObject(final String json) {
        return gson.fromJson(json, JsonObject.class);
    }

    public static String stringify(final String jsonData) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        final Map<String, Object> map = om.readValue(jsonData, HashMap.class);
        return om.writeValueAsString(map);
    }

    public static String toBase64URL(final byte[] data) {
        return Base64.getUrlEncoder().encodeToString(data);
    }
}
