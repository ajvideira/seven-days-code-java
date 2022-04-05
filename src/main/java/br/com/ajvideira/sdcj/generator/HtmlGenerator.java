package br.com.ajvideira.sdcj.generator;

import java.util.List;
import br.com.ajvideira.sdcj.model.Content;

public interface HtmlGenerator<T extends Content> {
  void generate(List<T> contentList ) throws HtmlGeneratorException;
}
