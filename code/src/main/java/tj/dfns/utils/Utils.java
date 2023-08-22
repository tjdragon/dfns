package tj.dfns.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
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
        registerNullTypeAdapter(gsonBuilder);
        gson = gsonBuilder.create();
    }

    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }

    private static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    private static <T> void registerNullTypeAdapter(final GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
    }

    public static String toJSON(final Object src, final Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    public static <T> T fromJSON(final String json, final Class<T> clazz) {
        return gson.fromJson(json, clazz);
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

    public static byte[] fromBase64URL(final String data) {
        return Base64.getUrlDecoder().decode(data);
    }

    public static void json2java(final String jsonPath, final String cn, final String pkgName, final String outputFolder) throws Exception {
        final URL source = new URL("file://" + jsonPath);
        json2java(source, cn, pkgName, outputFolder);
    }

    public static void json2java(final URL source, final String cn, final String pkgName, final String outputFolder) throws Exception {
        final JCodeModel codeModel = new JCodeModel();

        final GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }

            @Override
            public AnnotationStyle getAnnotationStyle() {
                return AnnotationStyle.GSON;
            }
        };

        final SchemaMapper mapper = new SchemaMapper(
                new RuleFactory(config, new GsonAnnotator(config), new SchemaStore()),
                new SchemaGenerator());
        mapper.generate(codeModel, cn, pkgName, source);

        final File outputFolderDir = new java.io.File(outputFolder);
        assert outputFolderDir.isDirectory();
        codeModel.build(outputFolderDir);
    }

    public static void main(String[] args) throws Exception {
        json2java(
                "/Users/tj/PERSO/DEV/dfns/data/create-assignment.json",
                "CreateAssignmentRequest",
                "tj.dfns.gen.model.assignment.create",
                "/Users/tj/PERSO/DEV/dfns/code/src/main/java");
    }
}
