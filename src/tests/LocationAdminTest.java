package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import location.Artist;
import location.ArtistManager;
import location.Genre;
import location.LocationAdmin;
import location.Movie;
import location.ReservationManager;
import location.ReviewManager;
import location.UserManager;
import location.MovieManager;
import java.io.IOException;
import java.util.Set;

/**
 * Test class for LocationAdmin entity.
 */
public class LocationAdminTest {
    private LocationAdmin locationAdmin;
    private ArtistManager artistManager;
    private MovieManager movieManager;
    private ReservationManager reservationManager;
    private ReviewManager reviewManager;
    private UserManager userManager;

    /**
     * Set up test fixtures before each test method.
     */
    @Before
    public void setUp() {
        artistManager = new ArtistManager();
        movieManager = new MovieManager();
        reservationManager = new ReservationManager();
        reviewManager = new ReviewManager();
        userManager = new UserManager();

        locationAdmin = new LocationAdmin(
            artistManager, 
            movieManager, 
            reservationManager, 
            reviewManager, 
            userManager
        );
    }

    /**
     * Test creating an artist.
     */
    @Test
    public void testCreateArtist() {
        Artist artist = locationAdmin.createArtist("Hanks", "Tom", "American");
        
        assertEquals("Hanks", artist.getLastName());
        assertEquals("Tom", artist.getFirstName());
        assertEquals("American", artist.getNationality());
    }

    /**
     * Test deleting an artist.
     */
    @Test
    public void testDeleteArtist() {
        Artist artist = locationAdmin.createArtist("Hanks", "Tom", "American");
        locationAdmin.addArtist(artist);
        
        assertTrue(locationAdmin.deleteArtist(artist));
    }

    /**
     * Test creating a movie.
     */
    @Test
    public void testCreateMovie() {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        
        assertEquals("Saving Private Ryan", movie.getTitle());
        assertEquals(director, movie.getDirector());
        assertEquals(1998, movie.getYear());
        assertEquals(13, movie.getMinimumAge());
    }

    /**
     * Test adding actors to a movie.
     */
    @Test
    public void testAddActors() {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        
        Artist actor1 = locationAdmin.createArtist("Hanks", "Tom", "American");
        Artist actor2 = locationAdmin.createArtist("Damon", "Matt", "American");
        
        assertTrue(locationAdmin.addActors(movie, actor1, actor2));
        assertTrue(movie.getActors().contains(actor1));
        assertTrue(movie.getActors().contains(actor2));
    }

    /**
     * Test adding genres to a movie.
     */
    @Test
    public void testAddGenres() {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        
        assertTrue(locationAdmin.addGenres(movie, Genre.War, Genre.Action));
        assertTrue(movie.getGenres().contains(Genre.War));
        assertTrue(movie.getGenres().contains(Genre.Action));
    }

    /**
     * Test adding a poster to a movie.
     */
    @Test
    public void testAddPoster() throws IOException {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        
        assertTrue(locationAdmin.addPoster(movie, "path/to/poster.jpg"));
        assertEquals("path/to/poster.jpg", movie.getPoster());
    }

    /**
     * Test delete movie.
     */
    @Test
    public void testDeleteMovie() {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        
        assertTrue(locationAdmin.deleteMovie(movie));
    }

    /**
     * Test getting all movies.
     */
    @Test
    public void testGetAllMovies() {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie1 = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        Movie movie2 = locationAdmin.createMovie("Jurassic Park", director, 1993, 13);
        
        Set<Movie> movies = locationAdmin.getAllMovies();
        assertEquals(2, movies.size());
        assertTrue(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
    }

    /**
     * Test getting movies by director.
     */
    @Test
    public void testGetMoviesByDirector() {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie1 = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        Movie movie2 = locationAdmin.createMovie("Jurassic Park", director, 1993, 13);
        
        Set<Movie> movies = locationAdmin.getMoviesByDirector(director);
        assertEquals(2, movies.size());
        assertTrue(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
    }

    /**
     * Test opening and closing movie rental.
     */
    @Test
    public void testRentalAvailability() {
        Artist director = locationAdmin.createArtist("Spielberg", "Steven", "American");
        Movie movie = locationAdmin.createMovie("Saving Private Ryan", director, 1998, 13);
        
        assertTrue(locationAdmin.openRental(movie));
        assertTrue(movie.isAvailable());
        
        assertTrue(locationAdmin.closeRental(movie));
        assertFalse(movie.isAvailable());
    }
}
