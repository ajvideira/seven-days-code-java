package br.com.ajvideira.sdcj;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("API key not informed.");
			return;
		}

		String apiKey = args[0];

		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(IMDB_API_URL + "/Top250Movies/" + apiKey)).GET().build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			JSONObject responseJson = new JSONObject(response.body());

			if (!responseJson.optString("errorMessage").equals("")) {
				System.out.println(responseJson.optString("errorMessage"));
				return;
			}

			List<Movie> movies = responseJson.getJSONArray("items").toList().stream().map(item -> {
				Map<String, String> itemMap = (Map<String, String>) item;
				return new Movie(itemMap.get("title"), itemMap.get("image"), itemMap.get("imDbRating"),
						itemMap.get("year"));
			}).collect(Collectors.toList());

			OutputStream outputStream = new FileOutputStream("index.html");
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

			HtmlGenerator htmlGenerator = new HtmlGenerator(printWriter);
			htmlGenerator.generate(movies);

			printWriter.close();

		} catch (URISyntaxException e) {
			System.out.println("URL bad formatted.");
		} catch (IOException | InterruptedException e) {
			System.out.println("Unknown error sending request");
		}
	}

}
