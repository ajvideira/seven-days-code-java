package br.com.ajvideira.sdcj;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class ImdbParserJson {
  private String data;
  
  public ImdbParserJson(String data) {
    this.data = data;
  }

  @SuppressWarnings("unchecked")
  public List<Movie> getMovies() throws ImdbParserJsonException {
    JSONObject responseJson = new JSONObject(data);
    if (!responseJson.optString("errorMessage").equals("")) {
      throw new ImdbParserJsonException("Error parsing json. Cause: " + responseJson.optString("errorMessage"));
    }
    
    List<Movie> movies = responseJson.getJSONArray("items").toList().stream().map(item -> {
      Map<String, String> itemMap = (Map<String, String>) item;
      return new Movie(itemMap.get("title"), itemMap.get("image"), itemMap.get("imDbRating"),
          itemMap.get("year"));
    }).collect(Collectors.toList());

    return movies;
  }
}
