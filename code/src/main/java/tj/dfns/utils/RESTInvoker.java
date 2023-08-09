package tj.dfns.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public final class RESTInvoker {
    public static String DEFAULT_ENDPOINT = "https://api.dfns.ninja";
    public static String DEFAULT_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhdXRoLmRmbnMubmluamEiLCJhdWQiOiJkZm5zOmF1dGg6dXNlciIsInN1YiI6Im9yLTM1dnIyLWVhM3VqLTlraXE2NXJkNDkxaWR2Z3MiLCJqdGkiOiJ1ai02YWFmdi0xZTdsMy04OXVhcHZtMWc4Y2M5djNqIiwic2NvcGUiOiIiLCJwZXJtaXNzaW9ucyI6W10sImh0dHBzOi8vY3VzdG9tL3VzZXJuYW1lIjoiIiwiaHR0cHM6Ly9jdXN0b20vYXBwX21ldGFkYXRhIjp7InVzZXJJZCI6InRvLTFidTNmLTJjOWw4LTltYXFrN2kycGE4bjQzNmkiLCJvcmdJZCI6Im9yLTM1dnIyLWVhM3VqLTlraXE2NXJkNDkxaWR2Z3MiLCJ0b2tlbktpbmQiOiJTZXJ2aWNlQWNjb3VudCJ9LCJpYXQiOjE2OTE1ODIyNTQsImV4cCI6MTc1NDY1NDI1NH0.Zh2n2RIsvx-S4uqHkSZEWywVKfI759blFt0UHseoQ52tkUcRevPlUjI--Qq9t0l8CXFyoS6B72jrLvbfIDnpS39TRIZ9tQlghlTQKjNaCAjn8KWAoYt6IybS76cmDy37WlZ7EeRSCvcpWuhE1SBZlKJiseUCN1s6YmXemfDJEe3MvHrvmWB13sEua3PWw-JhrSVwCDALmcYDnjVcgV307pFUqIxrvnzgbolh6HwOors9q68bvQUpA8jScV4VjKjAG4h4Yp7rTTGDpJZWeV8a6FjIESmesJqoABc9dO-2hTpNEHGTDGCm5Kxt4TMP3RzqYOSOcLZd9r-wlHM9G5oaJA";

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
        return response.body();
    }
}
