package tj.dfns.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public final class RESTInvoker {
    public static String DEFAULT_ENDPOINT = "https://api.dfns.ninja";

    public static String get(final String endPoint, final Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder().GET();
        for (Map.Entry<String, String> kv : headers.entrySet()) {
            builder = builder.header(kv.getKey(), kv.getValue());
        }
        final HttpRequest httpRequest = builder.uri(URI.create(endPoint)).build();
        System.out.println("headers: " + httpRequest.headers());
        System.out.println("uri: " + httpRequest.uri());
        System.out.println("httpRequest: " + httpRequest);

        final HttpClient httpClient = HttpClient
                    .newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .followRedirects(HttpClient.Redirect.NORMAL).build();

        final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String post(final String endPoint, final Map<String, String> headers, final String body) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(body));
        for (Map.Entry<String, String> kv : headers.entrySet()) {
            builder = builder.header(kv.getKey(), kv.getValue());
        }
        final HttpRequest httpRequest = builder.uri(URI.create(endPoint)).build();
        System.out.println("headers: " + httpRequest.headers());
        System.out.println("uri: " + httpRequest.uri());
        System.out.println("httpRequest: " + httpRequest);

        final HttpClient httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL).build();

        final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response Status Code: " + response.statusCode());
        return response.body();
    }
}
