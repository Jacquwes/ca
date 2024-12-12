package location;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the administration of the location.
 */
public class LocationAdmin implements AdministrationInterface, Serializable {

  /**
   * Represents the artist manager.
   */
  private ArtistManager artistManager;

  /**
   * Represents the movie manager.
   */
  private MovieManager movieManager;

  /**
   * Represents the reservation manager.
   */
  private ReservationManager reservationManager;

  /**
   * Represents the review manager.
   */
  private ReviewManager reviewManager;

  /**
   * Represents the user manager.
   */
  private UserManager userManager;

  /**
   * Constructs a new LocationAdmin.
   *
   * @param artistManager      the artist manager
   * @param movieManager       the movie manager
   * @param reservationManager the reservation manager
   * @param reviewManager      the review manager
   * @param userManager        the user manager
   */
  public LocationAdmin(
      ArtistManager artistManager,
      MovieManager movieManager,
      ReservationManager reservationManager,
      ReviewManager reviewManager,
      UserManager userManager) {
    this.artistManager = artistManager;
    this.movieManager = movieManager;
    this.reservationManager = reservationManager;
    this.reviewManager = reviewManager;
    this.userManager = userManager;
  }

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
      this.artistManager.addActor(actor);
      movie.addActor(actor);
    }
    return true;
  }

  /**
   * Adds a director to a movie.
   *
   * @param movie    the movie
   * @param director the director
   *
   * @return true if the director was added to the movie, false otherwise
   */
  public boolean addDirector(Movie movie, Artist director) {
    this.artistManager.addDirector(director);
    movie.setDirector(director);
    return true;
  }

  /**
   * Adds an artist to the artist manager.
   *
   * @param artist the artist to add
   *
   * @return true if the artist was added, false otherwise
   */
  public boolean addArtist(Artist artist) {
    return this.artistManager.addArtist(artist);

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
    return artistManager.getActors();
  }

  @Override
  public Set<Artist> getAllDirectors() {
    return artistManager.getDirectors();
  }

  /**
   * Returns the set of all artists.
   *
   * @return the set of all artists
   */
  public Set<Artist> getAllArtists() {
    return artistManager.getArtists();
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
    return artistManager.getArtists(a -> a.getLastName().equals(lastName)
        && a.getFirstName().equals(firstName))
        .stream()
        .findFirst().orElse(null);
  }

  @Override
  public Movie getMovie(String title) {
    return movieManager.getMovies(movie -> movie.getTitle().equals(title)).stream()
        .findFirst().orElse(null);
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
