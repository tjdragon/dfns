package tj.dfns.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
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
        gson = gsonBuilder.create();
    }

    public static String toJSON(final Object src, final Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    public static <T> T fromJSON(final String json, final Class<T> clazz) {
        return gson.fromJson(json, clazz);
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
                "/Users/tj/PERSO/DEV/dfns/data/create-policy.json",
                "NewPolicy",
                "tj.dfns.gen.model.policies.create",
                "/Users/tj/PERSO/DEV/dfns/code/src/main/java");
    }
}
