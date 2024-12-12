package tests;

import location.Artist;
import location.Movie;
import location.MovieManager;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Test class for MovieManager entity.
 */
public class MovieManagerTest {
    private MovieManager movieManager;
    private Movie movie1;
    private Movie movie2;

    /**
     * Set up test fixtures before each test method.
     */
    @Before
    public void setUp() {
        movieManager = new MovieManager();
        
        Artist director1 = new Artist("Spielberg", "Steven", "American");
        Artist director2 = new Artist("Nolan", "Christopher", "British");
        
        movie1 = new Movie("Saving Private Ryan", 1998, director1, null);
        movie2 = new Movie("Inception", 2010, director2, null);
    }

    /**
     * Test resetting the movie list.
     */
    @Test
    public void testReset() {
        movieManager.addMovie(movie1);
        movieManager.addMovie(movie2);
        
        movieManager.reset();
        
        assertTrue(movieManager.getMovies().isEmpty());
    }

    /**
     * Test adding a movie.
     */
    @Test
    public void testAddMovie() {
        assertTrue(movieManager.addMovie(movie1));
        assertTrue(movieManager.getMovies().contains(movie1));
    }

    /**
     * Test getting all movies.
     */
    @Test
    public void testGetMovies() {
        movieManager.addMovie(movie1);
        movieManager.addMovie(movie2);
        
        Set<Movie> movies = movieManager.getMovies();
        assertEquals(2, movies.size());
        assertTrue(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
    }

    /**
     * Test getting movies by predicate.
     */
    @Test
    public void testGetMoviesByPredicate() {
        movieManager.addMovie(movie1);
        movieManager.addMovie(movie2);
        
        Predicate<Movie> predicate = movie -> movie.getYear() > 2000;
        Set<Movie> filteredMovies = movieManager.getMovies(predicate);
        
        assertEquals(1, filteredMovies.size());
        assertTrue(filteredMovies.contains(movie2));
        assertFalse(filteredMovies.contains(movie1));
    }

    /**
     * Test deleting a movie.
     */
    @Test
    public void testDeleteMovie() {
        movieManager.addMovie(movie1);
        
        assertTrue(movieManager.deleteMovie(movie1));
        assertFalse(movieManager.getMovies().contains(movie1));
    }
}
