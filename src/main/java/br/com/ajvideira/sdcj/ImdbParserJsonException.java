package br.com.ajvideira.sdcj;

public class ImdbParserJsonException extends Exception {

  public ImdbParserJsonException(String message, Throwable cause) {
    super(message, cause);
  }

  public ImdbParserJsonException(String message) {
    super(message);
  }
}