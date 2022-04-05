package br.com.ajvideira.sdcj;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import br.com.ajvideira.sdcj.api.ApiClientException;
import br.com.ajvideira.sdcj.api.ImdbApiClient;
import br.com.ajvideira.sdcj.generator.HtmlGenerator;
import br.com.ajvideira.sdcj.generator.HtmlGeneratorException;
import br.com.ajvideira.sdcj.generator.ImdbHtmlGenerator;
import br.com.ajvideira.sdcj.model.Movie;
import br.com.ajvideira.sdcj.parser.JsonParser;
import br.com.ajvideira.sdcj.parser.ImdbJsonParser;
import br.com.ajvideira.sdcj.parser.JsonParserException;

public class App {
	
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("API key not informed.");
			return;
		}

		String apiKey = args[0];

		try (PrintWriter printWriter = new PrintWriter("index.html", Charset.forName("UTF-8"))) {
			ImdbApiClient imdbClient = new ImdbApiClient(apiKey);

			JsonParser<Movie> imdbParserJson = new ImdbJsonParser(imdbClient.getResponseBody()); 

			HtmlGenerator<Movie> htmlGenerator = new ImdbHtmlGenerator(printWriter);
			htmlGenerator.generate(imdbParserJson.getContentList());	
		} catch (ApiClientException e) {
			System.out.println(e.getMessage());
		} catch (JsonParserException e) {
			System.out.println(e.getMessage());
		} catch (HtmlGeneratorException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error creating writer.");
		}
	}

}
