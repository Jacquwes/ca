package location;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class Location implements UserInterface {
  /**
   * The manager of all the artists.
   */
  private ArtistManager artistManager;

  /**
   * The manager of all the movies.
   */
  private MovieManager movieManager;

  /**
   * The manager of all the reservations.
   */
  private ReservationManager reservationManager;

  /**
   * The manager of all the reviews.
   */
  private ReviewManager reviewManager;

  /**
   * The manager of all the users.
   */
  private UserManager userManager;

  /**
   * Constructs a new Location.
   */
  public Location() {
    this.artistManager = new ArtistManager();
    this.movieManager = new MovieManager();
    this.reservationManager = new ReservationManager();
    this.reviewManager = new ReviewManager();
    this.userManager = new UserManager();
  }

  @Override
  public int register(String username, String password, PersonalInformation info) {
    return userManager.register(username, password, info);
  }

  @Override
  public boolean login(String username, String password) {
    return userManager.login(username, password);
  }

  @Override
  public void logout() throws NotLoggedInException {
    userManager.logout();
  }

  @Override
  public void rentMovie(Movie movie) throws NotLoggedInException, RentingException {
    reservationManager.addReservation(
        new Reservation(userManager.getCurrentUser(),
            movie,
            new Date(),
            3));
  }

  @Override
  public void endMovieRental(Movie movie) throws NotLoggedInException, RentingException {
    reservationManager.removeReservation(
        reservationManager.getReservations(userManager.getCurrentUser())
            .stream()
            .filter(r -> r.getMovie().equals(movie))
            .findFirst()
            .orElse(null));
  }

  @Override
  public boolean isRentable(Movie movie) throws NotLoggedInException {
    return movie.isAvailable();
  }

  @Override
  public Set<Movie> rentedMovies() throws NotLoggedInException {
    return reservationManager.getReservations(userManager.getCurrentUser())
        .stream()
        .map(Reservation::getMovie)
        .collect(Collectors.toSet());
  }

  @Override
  public void addReview(Movie movie, Review review) throws NotLoggedInException, RentingException {
    reviewManager.add(review);
    movie.addReview(review);
  }

  @Override
  public void modifyReview(Movie movie, Review review) throws NotLoggedInException, RentingException {
    movie.getReviews().remove(review);
    reviewManager.add(review);
  }

  @Override
  public Set<Movie> allMovies() {
    return movieManager.getMovies();
  }

  @Override
  public Set<Artist> allActors() {
    // if artist extends actor or director
    return artistManager.getArtists(a -> a instanceof Actor);
  }

  @Override
  public Set<Artist> allDirectors() {
    return artistManager.getArtists();
  }

  @Override
  public Artist getActor(String lastName, String firstName) {
    return artistManager.getArtists(a -> a.getLastName().equals(lastName)
        && a.getFirstName().equals(firstName))
        .stream().findFirst().orElse(null);
  }

  @Override
  public Artist getDirector(String lastName, String firstName) {
    return artistManager.getArtists(a -> a.getLastName().equals(lastName)
        && a.getFirstName().equals(firstName))
        .stream().findFirst().orElse(null);
  }

  @Override
  public Movie getMovie(String title) {
    return movieManager.getMovies(m -> m.getTitle().equals(title))
        .stream().findFirst().orElse(null);
  }

  @Override
  public Set<Movie> moviesByDirector(Artist director) {
    return movieManager.getMovies(m -> m.getDirector().equals(director));
  }

  @Override
  public Set<Movie> moviesByDirector(String lastName, String firstName) {
    return movieManager.getMovies(m -> m.getDirector().getLastName().equals(lastName)
        && m.getDirector().getFirstName().equals(firstName));
  }

  @Override
  public Set<Movie> moviesByActor(Artist actor) {
    return movieManager.getMovies(m -> m.getActors().contains(actor));
  }

  @Override
  public Set<Movie> moviesByActor(String lastName, String firstName) {
    return movieManager.getMovies(m -> m.getActors().stream().anyMatch(a -> a.getLastName().equals(lastName)
        && a.getFirstName().equals(firstName)));
  }

  @Override
  public Set<Movie> moviesByGenre(Genre genre) {
    return movieManager.getMovies(m -> m.getGenres().contains(genre));
  }

  @Override
  public Set<Movie> moviesByGenre(String genre) {
    return movieManager.getMovies(m -> m.getGenres().stream().anyMatch(g -> g.toString().equals(genre)));
  }

  @Override
  public Set<Review> reviewsForMovie(Movie movie) {
    return reviewManager.getReviews(r -> r.getMovie().equals(movie));
  }

  @Override
  public Set<Review> reviewsForMovie(String title) {
    return reviewManager.getReviews(r -> r.getMovie().getTitle().equals(title));
  }

  @Override
  public double averageRating(Movie movie) {
    return reviewManager.getReviews(r -> r.getMovie().equals(movie)).stream()
        .mapToDouble(Review::getRating).average().orElse(0);
  }

  @Override
  public double averageRating(String title) {
    return reviewManager.getReviews(r -> r.getMovie().getTitle().equals(title)).stream()
        .mapToDouble(Review::getRating).average().orElse(0);
  }
}
