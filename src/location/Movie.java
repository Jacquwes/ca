package location;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

// TO BE COMPLETED

public class Movie {
  /**
   * List of all the movies.
   */
  private static Set<Movie> movies = new HashSet<Movie>();

  /**
   * The title of the movie.
   */
  private String title;

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
   * Retrieves the year of the movie.
   * 
   * @return the year of the movie.
   */
  public int getYear() {
    return year;
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
   * Retrieves the actors of the movie.
   * 
   * @return the actors of the movie.
   */
  public Set<Actor> getActors() {
    return actors;
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
}
