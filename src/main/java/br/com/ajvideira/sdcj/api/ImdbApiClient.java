package br.com.ajvideira.sdcj.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiClient implements ApiClient {
  
  public static final String IMDB_API_URL = "https://imdb-api.com/en/API";

  private String apiKey;

  public ImdbApiClient(String apiKey) {
    this.apiKey = apiKey;
  }

  @Override
  public String getResponseBody() throws ApiClientException {
    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(IMDB_API_URL + "/Top250Movies/" + this.apiKey)).GET().build();
  
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
  
      return response.body();
    } catch (URISyntaxException e) {
      throw new ApiClientException("Error creating API URI.", e);
    } catch (IOException | InterruptedException e) {
      throw new ApiClientException("Error sending requesto to API.", e);
    }
  }

}
