package br.com.ajvideira.sdcj;

public record Movie(String titulo, String urlImage, String rating, String year) {

	@Override
	public String toString() {
		return "Movie [titulo=" + titulo + ", urlImage=" + urlImage + ", rating=" + rating + ", year=" + year + "]";
	}

}
