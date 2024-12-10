package location;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class MovieManager {
  /**
   * List of all the movies.
   */
  private static Set<Movie> movies = new HashSet<Movie>();

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

  public boolean addMovie(Movie movie) {
    return movies.add(movie);
  }

  /**
   * Gets the list of all the movies matching
   * the specified predicate.
   *
   * @param predicate the predicate to match
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

  public boolean deleteMovie(Movie movie) {
    return movies.remove(movie);
  }
}
