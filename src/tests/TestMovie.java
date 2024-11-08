package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashSet;

import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import location.Movie;

public class TestMovie {
  private Movie movie;

  @BeforeEach
  public void setUp() {
    Movie.reset();
    movie = new Movie();
  }

  @Test
  public void testConstructor() {
    assertEquals(1, Movie.getMovies().size());
  }

  @Test
  public void testReset() {
    Movie.reset();
    assertEquals(0, Movie.getMovies().size());
  }

  @Test
  public void testGetMovies() {
    assertEquals(1, Movie.getMovies().size());
  }

  @Test
  public void testGetMoviesPredicate() {
    new Movie("The Shawshank Redemption", 1994, null, null);
    assertEquals(1, Movie.getMovies(m -> m.getTitle().equals("The Shawshank Redemption")).size());
  }

  @Test
  public void testGetMoviesPredicateEmpty() {
    assertEquals(0, Movie.getMovies(m -> m.getTitle().equals("The Shawshank Redemption")).size());
  }
}
