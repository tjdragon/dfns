package tj.dfns.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

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
}
