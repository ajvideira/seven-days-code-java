package br.com.ajvideira.sdcj.parser;

import java.util.List;
import br.com.ajvideira.sdcj.model.Content;

public interface JsonParser<T extends Content> {
  List<T> getContentList() throws JsonParserException;
}
