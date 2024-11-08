package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import location.Movie;
import location.Director;
import location.Actor;

public class MovieTest {
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

  @Test
  public void testParameterizedConstructor() {
    Set<Actor> actors = new HashSet<>();
    Movie movie = new Movie("Test Movie", 2023, new Director(), actors);
    assertEquals("Test Movie", movie.getTitle());
    assertEquals(2023, movie.getYear());
    assertEquals(2, Movie.getMovies().size());
  }

  @Test
  public void testTitle() {
    assertEquals("", movie.getTitle());
    assertEquals(movie, movie.setTitle("New Title"));
    assertEquals("New Title", movie.getTitle());
  }

  @Test
  public void testYear() {
    assertEquals(0, movie.getYear());
    assertEquals(movie, movie.setYear(2023));
    assertEquals(2023, movie.getYear());
  }

  @Test
  public void testDirector() {
    assertEquals(null, movie.getDirector());
    Director director = new Director();
    assertEquals(movie, movie.setDirector(director));
    assertEquals(director, movie.getDirector());
  }

  @Test
  public void testAvailability() {
    assertEquals(false, movie.isAvailable());
    assertEquals(movie, movie.setAvailability(true));
    assertEquals(true, movie.isAvailable());
  }

  @Test
  public void testActors() {
    assertEquals(null, movie.getActors());
    Set<Actor> actors = new HashSet<>();
    assertEquals(movie, movie.setActors(actors));
    assertEquals(actors, movie.getActors());
  }

  @Test
  public void testAddActor() {
    Set<Actor> actors = new HashSet<>();
    movie.setActors(actors);
    Actor actor = new Actor();
    assertEquals(movie, movie.addActor(actor));
    assertTrue(movie.getActors().contains(actor));
  }

  @Test
  public void testMinimumAge() {
    assertEquals(0, movie.getMinimumAge());
    assertEquals(movie, movie.setMinimumAge(18));
    assertEquals(18, movie.getMinimumAge());
  }

  @Test
  public void testGetMoviesMultiple() {
    new Movie("Movie 2", 2023, null, null);
    new Movie("Movie 3", 2023, null, null);
    assertEquals(3, Movie.getMovies().size());
  }

  @Test
  public void testGetMoviesPredicateYear() {
    new Movie("Movie 2", 2023, null, null);
    new Movie("Movie 3", 2022, null, null);
    assertEquals(1, Movie.getMovies(m -> m.getYear() == 2022).size());
  }
}