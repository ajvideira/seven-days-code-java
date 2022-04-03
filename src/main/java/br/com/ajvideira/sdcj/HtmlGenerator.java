package br.com.ajvideira.sdcj;

import java.io.IOException;
import java.io.Writer;
import java.lang.StringBuilder;
import java.util.List;

public class HtmlGenerator {
  private Writer writer;

  public HtmlGenerator(Writer writer) {
    this.writer = writer;
  }

  public void generate(List<Movie> movies ) throws IOException {
    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<!DOCTYPE html>\n");
    htmlBuilder.append("<html lang=\"en\">\n");
    htmlBuilder.append(getHead());
    htmlBuilder.append(getCardsFormatted(movies));
    htmlBuilder.append("</html>");

    writer.write(htmlBuilder.toString());
  }

  private String getHead() {
    return 
      """
        <head>
          <meta charset=\"UTF-8\" />
          <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />
          <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />
          <title>7 Days of Code - Java</title>
          <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" />
          <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin />
          <link
            href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\"
            rel=\"stylesheet\"
          />
          <style>
            * {
              box-sizing: border-box;
              margin: 0;
              padding: 0;
            }
      
            body {
              font-family: 'Roboto', sans-serif;
            }
      
            main {
              display: flex;
              align-items: center;
              justify-content: center;
              flex-wrap: wrap;
            }
      
            .card {
              background-color: rgb(119, 113, 113);
              margin-right: 20px;
              margin-top: 20px;
              color: #ffffff;
              border-radius: 8px;
            }
      
            .card-header {
              border-bottom: 1px solid #000000;
              padding: 15px;
              font-weight: bold;
              font-size: 20px;
            }
      
            .card-content {
              display: flex;
              justify-content: center;
              align-items: center;
              padding-top: 15px;
              padding-left: 15px;
              padding-right: 15px;
            }
      
            .card-content img {
              width: 200px;
            }
      
            .card-footer {
              padding: 15px;
            }
          </style>
        </head>  
      """;
  }

  private String getCardsFormatted(List<Movie> movies) {
    StringBuilder cardsBuilder = new StringBuilder();
    cardsBuilder.append("\t<body>\n");
    cardsBuilder.append("\t\t<main>\n");
    for (Movie movie : movies) {
      cardsBuilder.append(getCardFormatted(movie));
    }
    cardsBuilder.append("\t\t</main>\n");
    cardsBuilder.append("\t</body>\n");
    return cardsBuilder.toString();
  }

  private String getCardFormatted(Movie movie) {
    String title = movie.title().length() > 22 ? 
      movie.title().substring(0, 18).concat("...") : movie.title();

    return 
      """
            <div class=\"card\">
              <div class=\"card-header\"><span>%s</span></div>
              <div class=\"card-content\">
                <img
                  src=\"%s\"
                  alt=\"%s\"
                />
              </div>
              <div class=\"card-footer\"><span>Nota: %s - Ano: %s</span></div>
            </div>    
      """.formatted(title, movie.urlImage(), movie.title() + " poster", movie.rating(), movie.year());
  }
}
