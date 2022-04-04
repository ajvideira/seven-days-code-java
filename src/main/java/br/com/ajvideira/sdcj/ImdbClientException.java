package br.com.ajvideira.sdcj;

public class ImdbClientException extends Exception {
  public ImdbClientException(String message) {
    super(message);
  }

  public ImdbClientException(String message, Throwable cause) {
    super(message, cause);
  }
}
