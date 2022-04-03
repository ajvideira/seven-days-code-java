package br.com.ajvideira.sdcj;

public record Movie(String title, String urlImage, String rating, String year) {

	@Override
	public String toString() {
		return "Movie [title=" + title + ", urlImage=" + urlImage + ", rating=" + rating + ", year=" + year + "]";
	}

}
