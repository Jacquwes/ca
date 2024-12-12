package location;

import java.util.Set;

/**
 * Movie rental services from the user's point of view.
 *
 * @author Florent Delalande
 * @author Paul Hariel
 */
public interface UserInterface {

  /**
   * User registration. The chosen username must not already exist among the
   * registered users.
   *
   * @param username the (unique) username of the user.
   * @param password the user's password (must not be empty or <code>null</code>).
   * @param info     personal information about the user.
   * @return a code specifying the result of the registration: 0 if the
   *         registration was successful, 1 if the username was already used, 2 if
   *         the username or password was empty, 3 if the personal information was 
   *         not properly specified.
   */
  int register(String username, String password, PersonalInformation info);

  /**
   * User login. Once logged in, the user can access rental services and leave
   * comments on the movies they have rented.
   *
   * @param username the user's username.
   * @param password the user's password.
   * @return <code>true</code> if the login was successful, <code>false</code>
   *         in case of invalid username/password combination.
   */
  boolean login(String username, String password);

  /**
   * Logs out the currently logged-in user.
   *
   * @throws NotLoggedInException if no user is logged in.
   */
  void logout() throws NotLoggedInException;

  /**
   * The logged-in user rents a movie. They can rent it if they have less than
   * 3 movies currently rented and if they are old enough to watch the movie.
   *
   * @param movie the movie to rent.
   * @throws NotLoggedInException if no user is logged in.
   * @throws RentingException     in case of rental refusal, the exception will
   *                              contain a message specifying the problem
   *                              (already 3 movies rented,
   *                              insufficient age, or other).
   */
  void rentMovie(Movie movie) throws NotLoggedInException, RentingException;

  /**
   * Ends the rental of a movie.
   *
   * @param movie the movie whose rental is ending.
   * @throws NotLoggedInException if no user is logged in.
   * @throws RentingException     in case of a problem, especially if the user did
   *                              not have this movie rented, the exception will
   *                              contain a message specifying
   *                              the problem.
   */
  void endMovieRental(Movie movie) throws NotLoggedInException, RentingException;

  /**
   * Information on whether a movie is available for rental.
   *
   * @param movie the movie to check for rental availability.
   * @return <code>true</code> if the movie is available for rental,
   *         <code>false</code> otherwise.
   * @throws NotLoggedInException if no user is logged in.
   */
  boolean isRentable(Movie movie) throws NotLoggedInException;

  /**
   * Returns the set of movies currently rented by the logged-in user.
   *
   * @return the movies rented by the logged-in user or <code>null</code>
   *         if no movies are currently rented.
   * @throws NotLoggedInException if no user is logged in.
   */
  Set<Movie> rentedMovies() throws NotLoggedInException;

  /**
   * Adds a review to a movie from the logged-in user. The user must have
   * rented the movie to comment on it (whether the movie is currently rented
   * or was rented and returned previously). The user must not have already
   * submitted a review for this movie.
   *
   * @param movie  the movie to review.
   * @param review the review of the movie.
   * @throws NotLoggedInException if no user is logged in.
   * @throws RentingException     in case of an error adding the review, the
   *                              exception will contain a message specifying the
   *                              problem.
   */
  void addReview(Movie movie, Review review) throws NotLoggedInException, RentingException;

  /**
   * Modifies the review that the logged-in user had already submitted for a
   * movie. Can only be done if the user had already reviewed the movie.
   *
   * @param movie  the movie whose review is being modified.
   * @param review the new review that replaces the previous one.
   * @throws NotLoggedInException if no user is logged in.
   * @throws RentingException     in case of an error modifying the review,
   *                              the exception will contain a message specifying
   *                              the problem.
   */
  void modifyReview(Movie movie, Review review) throws NotLoggedInException, RentingException;

  /**
   * Returns the set of all movies.
   *
   * @return the set of all movies or <code>null</code> if no movies exist.
   */
  Set<Movie> allMovies();

  /**
   * Returns the set of all actors.
   *
   * @return the set of all actors or <code>null</code> if no actors exist.
   */
  Set<Artist> allActors();

  /**
   * Returns the set of all directors.
   *
   * @return the set of all directors or <code>null</code> if no directors exist.
   */
  Set<Artist> allDirectors();

  /**
   * Searches for an actor by their last name and first name.
   *
   * @param lastName  the actor's last name.
   * @param firstName the actor's first name.
   * @return the actor if found or <code>null</code> otherwise.
   */
  Artist getActor(String lastName, String firstName);

  /**
   * Searches for a director by their last name and first name.
   *
   * @param lastName  the director's last name.
   * @param firstName the director's first name.
   * @return the director if found or <code>null</code> otherwise.
   */
  Artist getDirector(String lastName, String firstName);

  /**
   * Searches for a movie by its title.
   *
   * @param title the movie's title.
   * @return the movie if found or <code>null</code> otherwise.
   */
  Movie getMovie(String title);

  /**
   * Returns the set of movies by a certain director.
   *
   * @param director the director.
   * @return the set of movies by the director or <code>null</code> if no movies
   *         were found or if the parameter was invalid.
   */
  Set<Movie> moviesByDirector(Artist director);

  /**
   * Returns the set of movies by a certain director.
   *
   * @param lastName  the director's last name.
   * @param firstName the director's first name.
   * @return the set of movies by the director or <code>null</code> if no
   *         movies were found or if the parameters were invalid.
   */
  Set<Movie> moviesByDirector(String lastName, String firstName);

  /**
   * Returns the set of movies by a certain actor.
   *
   * @param actor the actor.
   * @return the set of movies by the actor or <code>null</code> if no movies
   *         were found or if the parameter was invalid.
   */
  Set<Movie> moviesByActor(Artist actor);

  /**
   * Returns the set of movies by a certain actor.
   *
   * @param lastName  the actor's last name.
   * @param firstName the actor's first name.
   * @return the set of movies by the actor or <code>null</code> if no
   *         movies were found or if the parameters were invalid.
   */
  Set<Movie> moviesByActor(String lastName, String firstName);

  /**
   * Returns the set of movies of a certain genre.
   *
   * @param genre the movie's genre.
   * @return the set of movies of the genre or <code>null</code> if no
   *         movies were found.
   */
  Set<Movie> moviesByGenre(Genre genre);

  /**
   * Returns the set of movies of a certain genre.
   *
   * @param genre the movie's genre (must correspond to an element of
   *              the {@link location.Genre Genre} enumeration).
   * @return the set of movies of the genre or <code>null</code> if no
   *         movies were found or if the genre was invalid.
   * @see location.Genre
   */
  Set<Movie> moviesByGenre(String genre);

  /**
   * Returns the set of reviews for a movie.
   *
   * @param movie the movie whose reviews are wanted.
   * @return all the reviews of a movie or <code>null</code> if no reviews
   *         exist for the movie or if the movie was invalid (e.g.,
   *         <code>null</code> value).
   */
  Set<Review> reviewsForMovie(Movie movie);

  /**
   * Returns the set of reviews for a movie.
   *
   * @param title the title of the movie whose reviews are wanted.
   * @return all the reviews of a movie or <code>null</code> if no reviews exist
   *         for the movie or if the movie title was unknown or invalid (e.g.,
   *         <code>null</code> value).
   */
  Set<Review> reviewsForMovie(String title);

  /**
   * Returns the average rating of a movie (the average of all the ratings of
   * the reviews for the movie).
   *
   * @param movie the movie whose average rating is being retrieved.
   * @return the average rating of the movie or -1 if the movie has no reviews
   *         or -2 in case of an invalid movie (non-existent or <code>null</code>
   *         value).
   */
  double averageRating(Movie movie);

  /**
   * Returns the average rating of a movie (the average of all the ratings
   * of the reviews for the movie).
   *
   * @param title the title of the movie whose average rating is being retrieved.
   * @return the average rating of the movie or -1 if the movie has no reviews
   *         or -2 in case of an invalid movie title (no movie with that title or
   *         <code>null</code> value).
   */
  double averageRating(String title);
}
