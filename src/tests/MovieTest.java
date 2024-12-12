package tests;

import location.Artist;
import location.Genre;
import location.User;
import location.Movie;
import location.PersonalInformation;
import location.Review;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.HashSet;
import java.util.Set;

/**
 * Test class for the Movie class.
 */
public class MovieTest {
    private Movie movie;
    private User user;
    private User user2;
    private Artist director;
    private Artist actor1;
    private Artist actor2;
    private Review review1;
    private Review review2;

    /**
     * Set up method to initialize test objects before each test.
     */
    @Before
    public void setUp() {
        director = new Artist("Nolan", "Christopher", "British");
        actor1 = new Artist("Bale", "Christian", "American");
        actor2 = new Artist("Hathaway", "Anne", "American");
        
        movie = new Movie("Inception", 2010, director, new HashSet<>());

        PersonalInformation info = new PersonalInformation("Doe", "John", "American", 30);
        user = new User("testuser", "password", info);

        PersonalInformation info2 = new PersonalInformation("Doe", "Jane", "American", 25);
        user2 = new User("testuser2", "password", info2);
        
        review1 = new Review(user, movie, 5, "Great movie");
        review2 = new Review(user2, movie, 4, "Good movie");
    }

    /**
     * Test constructor with parameters.
     */
    @Test
    public void testConstructor() {
        assertEquals("Inception", movie.getTitle());
        assertEquals(2010, movie.getYear());
        assertEquals(director, movie.getDirector());
    }

    /**
     * Test title getter and setter.
     */
    @Test
    public void testTitleGetterSetter() {
        movie.setTitle("Interstellar");
        assertEquals("Interstellar", movie.getTitle());
    }

    /**
     * Test year getter and setter.
     */
    @Test
    public void testYearGetterSetter() {
        movie.setYear(2014);
        assertEquals(2014, movie.getYear());
    }

    /**
     * Test director getter and setter.
     */
    @Test
    public void testDirectorGetterSetter() {
        Artist newDirector = new Artist("Spielberg", "Steven", "American");
        movie.setDirector(newDirector);
        assertEquals(newDirector, movie.getDirector());
    }

    /**
     * Test adding actors.
     */
    @Test
    public void testAddActor() {
        movie.addActor(actor1);
        movie.addActor(actor2);
        
        assertTrue(movie.getActors().contains(actor1));
        assertTrue(movie.getActors().contains(actor2));
        assertEquals(2, movie.getActors().size());
    }

    /**
     * Test minimum age getter and setter.
     */
    @Test
    public void testMinimumAgeGetterSetter() {
        movie.setMinimumAge(13);
        assertEquals(13, movie.getMinimumAge());
    }

    /**
     * Test availability getter and setter.
     */
    @Test
    public void testAvailabilityGetterSetter() {
        movie.setAvailability(false);
        assertFalse(movie.isAvailable());
    }

    /**
     * Test poster getter and setter.
     */
    @Test
    public void testPosterGetterSetter() {
        movie.setPoster("poster.jpg");
        assertEquals("poster.jpg", movie.getPoster());
    }

    /**
     * Test adding genres.
     */
    @Test
    public void testAddGenre() {
        movie.addGenre(Genre.Action);
        movie.addGenre(Genre.ScienceFiction);
        
        assertTrue(movie.getGenres().contains(Genre.Action));
        assertTrue(movie.getGenres().contains(Genre.ScienceFiction));
        assertEquals(2, movie.getGenres().size());
    }

    /**
     * Test adding reviews.
     */
    @Test
    public void testAddReview() {
        movie.addReview(review1);
        movie.addReview(review2);
        
        assertTrue(movie.getReviews().contains(review1));
        assertTrue(movie.getReviews().contains(review2));
        assertEquals(2, movie.getReviews().size());
    }

    /**
     * Test equals method.
     */
    @Test
    public void testEquals() {
        Movie sameMovie = new Movie("Inception", 2010, director, new HashSet<>());
        Movie differentMovie = new Movie("Interstellar", 2014, director, new HashSet<>());

        assertEquals(movie, sameMovie);
        assertNotEquals(movie, differentMovie);
    }

    /**
     * Test toString method.
     */
    @Test
    public void testToString() {
        assertEquals("Inception (2010)", movie.toString());
    }
}
