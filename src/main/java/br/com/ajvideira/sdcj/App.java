package br.com.ajvideira.sdcj;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class App {
	
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("API key not informed.");
			return;
		}

		String apiKey = args[0];

		try (PrintWriter printWriter = new PrintWriter("index.html", Charset.forName("UTF-8"))) {
			ImdbClient imdbClient = new ImdbClient(apiKey);

			ImdbParserJson imdbParserJson = new ImdbParserJson(imdbClient.getResponseBody()); 

			HtmlGenerator htmlGenerator = new HtmlGenerator(printWriter);
			htmlGenerator.generate(imdbParserJson.getMovies());	

		} catch (ImdbClientException e) {
			System.out.println(e.getMessage());
		} catch (ImdbParserJsonException e) {
			System.out.println(e.getMessage());
		} catch (HtmlGeneratorException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error creating writer.");
		}
	}

}
