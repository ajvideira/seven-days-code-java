package br.com.ajvideira.sdcj.model;

public record Movie(String title, String urlImage, String rating, String year) implements Content {

	@Override
	public String toString() {
		return "Movie [title=" + title + ", urlImage=" + urlImage + ", rating=" + rating + ", year=" + year + "]";
	}

}
