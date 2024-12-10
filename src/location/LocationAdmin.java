package location;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LocationAdmin implements AdministrationInterface {
  private ArtistManager artistManager = new ArtistManager();
  private MovieManager movieManager = new MovieManager();
  private ReservationManager reservationManager = new ReservationManager();

  @Override
  public Artist createArtist(String lastName, String firstName, String nationality) {
    return new Artist(lastName, firstName, nationality);
  }

  @Override
  public boolean deleteArtist(Artist artist) {
    return artistManager.deleteArtist(artist);
  }

  @Override
  public Movie createMovie(String title, Artist director, int year, int ageLimit) {
    Movie movie = new Movie(title, year, director, new HashSet<>());
    movie.setMinimumAge(ageLimit);
    this.movieManager.addMovie(movie);
    return movie;
  }

  @Override
  public boolean addActors(Movie movie, Artist... actors) {
    for (Artist actor : actors) {
      movie.addActor(actor);
    }
    return true;
  }

  @Override
  public boolean addGenres(Movie movie, Genre... genres) {
    for (Genre genre : genres) {
      movie.addGenre(genre);
    }
    return true;
  }

  @Override
  public boolean addPoster(Movie movie, String file) throws IOException {
    movie.setPoster(file);
    return true;
  }

  @Override
  public boolean deleteMovie(Movie movie) {
    return movieManager.deleteMovie(movie);
  }

  @Override
  public Set<Movie> getAllMovies() {
    return movieManager.getMovies();
  }

  @Override
  public Set<Artist> getAllActors() {
    return artistManager.getArtists();
  }

  @Override
  public Set<Artist> getAllDirectors() {
    return artistManager.getDirectors();
  }

  @Override
  public Set<Movie> getMoviesByDirector(Artist director) {
    return movieManager.getMovies(movie -> movie.getDirector().equals(director));
  }

  @Override
  public Set<Movie> getMoviesByActor(Artist actor) {
    return movieManager.getMovies(movie -> movie.getActors().contains(actor));
  }

  @Override
  public Artist getArtist(String lastName, String firstName) {
    return artistManager.getArtists(a -> a.getLastName().equals(lastName) && a.getFirstName().equals(firstName))
        .stream()
        .findFirst().orElse(null);
  }

  @Override
  public Movie getMovie(String title) {
    return movieManager.getMovies(movie -> movie.getTitle().equals(title)).stream().findFirst().orElse(null);
  }

  @Override
  public boolean openRental(Movie movie) {
    Movie m = movieManager.getMovies(movie::equals).stream().findFirst().orElse(null);
    if (m == null) {
      return false;
    }
    m.setAvailability(true);
    return true;
  }

  @Override
  public boolean closeRental(Movie movie) {
    Movie m = movieManager.getMovies(movie::equals).stream().findFirst().orElse(null);
    if (m == null) {
      return false;
    }
    m.setAvailability(false);
    return true;
  }
}
