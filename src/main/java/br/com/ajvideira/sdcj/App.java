package br.com.ajvideira.sdcj;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class App {
	public static final String IMDB_API_URL = "https://imdb-api.com/en/API";

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("API key not informed.");
			return;
		}

		String apiKey = args[0];

		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(IMDB_API_URL + "/Top250Movies/" + apiKey)).GET()
					.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			JSONObject responseJson = new JSONObject(response.body());

			if (!responseJson.optString("errorMessage").equals("")) {
				System.out.println(responseJson.optString("errorMessage"));
				return;
			}
			
			List<Object> itemsList = responseJson.getJSONArray("items").toList();
			
			List<String> ids = extractAttributeList(itemsList, "id");
			List<String> ranks = extractAttributeList(itemsList, "rank");
			List<String> titles = extractAttributeList(itemsList, "title");
			List<String> fullTitles = extractAttributeList(itemsList, "fullTitle");
			List<String> years = extractAttributeList(itemsList, "year");
			List<String> images = extractAttributeList(itemsList, "image");
			List<String> crews = extractAttributeList(itemsList, "crew");
			List<String> imdbRatings = extractAttributeList(itemsList, "imDbRating");
			List<String> imdbRatingCounts = extractAttributeList(itemsList, "imDbRatingCount");
			
			ids.forEach(id -> System.out.print(id+", "));
			System.out.println("");
			ranks.forEach(rank -> System.out.print(rank+"; "));
			System.out.println("");
			titles.forEach(title -> System.out.print(title+"; "));
			System.out.println("");
			fullTitles.forEach(fullTitle -> System.out.print(fullTitle+"; "));
			System.out.println("");
			years.forEach(year -> System.out.print(year+"; "));
			System.out.println("");
			images.forEach(image -> System.out.print(image.substring(0,30)+"; "));
			System.out.println("");
			crews.forEach(crew -> System.out.print(crew+"; "));
			System.out.println("");
			imdbRatings.forEach(imdbRating -> System.out.print(imdbRating+", "));
			System.out.println("");
			imdbRatingCounts.forEach(imdbRatingCount -> System.out.print(imdbRatingCount+", "));
			
		} catch (URISyntaxException e) {
			System.out.println("URL bad formatted.");
		} catch (IOException | InterruptedException e) {
			System.out.println("Unknown error sending request");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> extractAttributeList(List<Object> itemsList, String attribute) {
		return itemsList.stream().map(item -> ((Map<String, String>)item).get(attribute)).collect(Collectors.toList());
	}
}
