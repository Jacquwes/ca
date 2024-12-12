package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Manages all the movies.
 */
public class MovieManager implements Serializable {
  /**
   * List of all the movies.
   */
  private Set<Movie> movies = new HashSet<Movie>();

  /**
   * Resets the list of all the movies.
   */
  public void reset() {
    movies = new HashSet<Movie>();
  }

  /**
   * Retrieves the list of all the movies.
   *
   * @return the list of all the movies.
   */
  public Set<Movie> getMovies() {
    return movies;
  }

  /**
   * Gets the list of all the movies matching
   * the specified predicate.
   *
   * @param predicate the predicate to match.
   * @return the list of all the movies.
   */
  public Set<Movie> getMovies(Predicate<Movie> predicate) {
    Set<Movie> result = new HashSet<Movie>();

    for (Movie movie : movies) {
      if (predicate.test(movie)) {
        result.add(movie);
      }
    }

    return result;
  }

  /**
   * Adds a movie to the list of all the movies.
   *
   * @param movie the movie to add. 
   * @return true if the movie was added, false otherwise.
   */
  public boolean addMovie(Movie movie) {
    return movies.add(movie);
  }

  /**
   * Deletes the specified movie.
   *
   * @param movie the movie to delete.
   * @return true if the movie was deleted, false otherwise.
   */
  public boolean deleteMovie(Movie movie) {
    return movies.remove(movie);
  }
}
