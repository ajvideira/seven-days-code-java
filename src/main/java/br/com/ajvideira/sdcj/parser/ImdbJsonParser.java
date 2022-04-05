package br.com.ajvideira.sdcj.parser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONObject;
import br.com.ajvideira.sdcj.model.Movie;

public class ImdbJsonParser implements JsonParser<Movie> {
  private String data;
  
  public ImdbJsonParser(String data) {
    this.data = data;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Movie> getContentList() throws JsonParserException {
    JSONObject responseJson = new JSONObject(data);
    if (!responseJson.optString("errorMessage").equals("")) {
      throw new JsonParserException("Error parsing json. Cause: " + responseJson.optString("errorMessage"));
    }
    
    List<Movie> contentList = responseJson.getJSONArray("items").toList().stream().map(item -> {
      Map<String, String> itemMap = (Map<String, String>) item;
      return new Movie(itemMap.get("title"), itemMap.get("image"), itemMap.get("imDbRating"),
          itemMap.get("year"));
    }).collect(Collectors.toList());

    return contentList;
  }
}
