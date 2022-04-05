package br.com.ajvideira.sdcj.parser;

public class JsonParserException extends Exception {

  public JsonParserException(String message, Throwable cause) {
    super(message, cause);
  }

  public JsonParserException(String message) {
    super(message);
  }
}