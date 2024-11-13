package location;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Movie {
  /**
   * List of all the movies.
   */
  private static Set<Movie> movies = new HashSet<Movie>();

  /**
   * Resets the list of all the movies.
   */
  public static void reset() {
    movies = new HashSet<Movie>();
  }

  /**
   * Retrieves the list of all the movies.
   * 
   * @return the list of all the movies.
   */
  public static Set<Movie> getMovies() {
    return movies;
  }

  /**
   * Gets the list of all the movies matching
   * the specified predicate.
   * 
   * @param predicate the predicate to match
   * @return the list of all the movies.
   */
  public static Set<Movie> getMovies(Predicate<Movie> predicate) {
    Set<Movie> result = new HashSet<Movie>();

    for (Movie movie : movies)
      if (predicate.test(movie))
        result.add(movie);

    return result;
  }

  /**
   * The title of the movie.
   */
  private String title = "";

  /**
   * The year of the movie.
   */
  private int year;

  /**
   * The director of the movie.
   */
  private Director director;

  /**
   * The actors of the movie.
   */
  private Set<Actor> actors;

  /**
   * The minimum age required to watch the movie.
   */
  private int minimumAge;

  /**
   * Whether the movie is available for rent.
   */
  private boolean available;

  /**
   * The poster of the movie.
   */
  private String poster;

  /**
   * The genres of the movie.
   */
  private Set<Genre> genres = new HashSet<Genre>();

  /**
   * The reviews of the movie.
   */
  private Set<Review> reviews = new HashSet<>();

  /**
   * Constructs a new Movie.
   */
  public Movie() {
    movies.add(this);
  }

  /**
   * Constructs a new Movie with the specified title, year, director and actors.
   * 
   * @param title    the title of the movie
   * @param year     the year of the movie
   * @param director the director of the movie
   * @param actors   the actors of the movie
   */
  public Movie(String title, int year, Director director, Set<Actor> actors) {
    this.title = title;
    this.year = year;
    this.director = director;
    this.actors = actors;
    movies.add(this);
  }

  /**
   * Retrieves the title of the movie.
   * 
   * @return the title of the movie.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the movie.
   * 
   * @param title the title of the movie
   * @return the movie
   */
  public Movie setTitle(String title) {
    this.title = title;
    return this;
  }

  /**
   * Retrieves the year of the movie.
   * 
   * @return the year of the movie.
   */
  public int getYear() {
    return year;
  }

  /**
   * Set the release date of the movie
   * 
   * @param newYear The release date of the movie
   * @return the movie
   */
  public Movie setYear(int newYear) {
    this.year = newYear;
    return this;
  }

  /**
   * Retrieves the director of the movie.
   * 
   * @return the director of the movie.
   */
  public Director getDirector() {
    return director;
  }

  /**
   * Set the director of the movie
   * 
   * @param dir The director of the movie.
   * @return the movie
   */
  public Movie setDirector(Director dir) {
    this.director = dir;
    return this;
  }

  /**
   * Whether the movie is available for rent.
   * 
   * @return whether the movie is available for rent.
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * Set the availability of the movie
   * 
   * @param availability The availability of the movie
   * @return the movie
   */
  public Movie setAvailability(boolean availability) {
    this.available = availability;
    return this;
  }

  /**
   * Retrieves the actors of the movie.
   * 
   * @return the actors of the movie.
   */
  public Set<Actor> getActors() {
    return actors;
  }

  /**
   * Sets the actors of the movie.
   * 
   * @param actors the actors of the movie
   * @return the movie
   */
  public Movie setActors(Set<Actor> actors) {
    this.actors = actors;
    return this;
  }

  /**
   * Adds an actor to the movie.
   * 
   * @param actor the actor to add
   * @return the movie
   */
  public Movie addActor(Actor actor) {
    this.actors.add(actor);
    return this;
  }

  /**
   * Retrieves the minimum age required to watch the movie.
   * 
   * @return the minimum age required to watch the movie.
   */
  public int getMinimumAge() {
    return minimumAge;
  }

  /**
   * Sets the minimum age required to watch the movie.
   * 
   * @param minimumAge the minimum age required to watch the movie
   * @return the movie
   */
  public Movie setMinimumAge(int minimumAge) {
    this.minimumAge = minimumAge;
    return this;
  }

  /**
   * Retrieves the poster of the movie.
   * 
   * @return the poster of the movie.
   */
  public String getPoster() {
    return poster;
  }

  /**
   * Sets the poster of the movie.
   * 
   * @param poster the poster of the movie.
   * @return the movie.
   */
  public Movie setPoster(String poster) {
    this.poster = poster;
    return this;
  }

  /**
   * Retrieves the genres of the movie.
   * 
   * @return the genres of the movie.
   */
  public Set<Genre> getGenres() {
    return genres;
  }

  /**
   * Sets the genres of the movie.
   * 
   * @param genres the genres of the movie.
   * @return the movie.
   */
  public Movie setGenres(Set<Genre> genres) {
    this.genres = genres;
    return this;
  }

  /**
   * Adds a review to the movie.
   * 
   * @param review the review to add
   */
  public void addReview(Review review) {
    reviews.add(review);
  }

  /**
   * Retrieves the reviews of the movie.
   * 
   * @return the reviews of the movie.
   */
  public Set<Review> getReviews() {
    return reviews;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * 
   * @param obj the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument;
   *         {@code false} otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Movie other = (Movie) obj;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (year != other.year)
      return false;
    if (director == null) {
      if (other.director != null)
        return false;
    } else if (!director.equals(other.director))
      return false;
    return true;
  }
}
