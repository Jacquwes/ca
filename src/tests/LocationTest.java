package tests;

import location.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Test class for the Location class.
 */
public class LocationTest {
    private Location location;
    private UserManager userManager;
    private ArtistManager artistManager;
    private MovieManager movieManager;
    private ReservationManager reservationManager;
    private ReviewManager reviewManager;
    
    private User testUser;
    private Artist director;
    private Movie testMovie;

    /**
     * Set up method to initialize test objects before each test.
     */
    @Before
    public void setUp() {
        userManager = new UserManager();
        artistManager = new ArtistManager();
        movieManager = new MovieManager();
        reservationManager = new ReservationManager();
        reviewManager = new ReviewManager();

        location = new Location(
            artistManager, 
            movieManager, 
            reservationManager, 
            reviewManager, 
            userManager
        );

        // Register and login a test user
        PersonalInformation info = new PersonalInformation("Doe", "John", "American", 30);
        userManager.register("testuser", "password", info);
        userManager.login("testuser", "password");

        // Create a test movie
        director = new Artist("Nolan", "Christopher", "British");
        testMovie = new Movie("Inception", 2010, director, new HashSet<>());
        movieManager.addMovie(testMovie);
    }

    /**
     * Test user registration.
     */
    @Test
    public void testRegister() {
        PersonalInformation newInfo = new PersonalInformation( "Smith","Jane", "American", 25);
        int result = location.register("newuser", "newpassword", newInfo);
        assertTrue(result >= 0);
    }

    /**
     * Test user login.
     */
    @Test
    public void testLogin() {
        assertTrue(location.login("testuser", "password"));
    }

    /**
     * Test current user retrieval.
     */
    @Test
    public void testGetCurrentUser() {
        assertNotNull(location.getCurrentUser());
        assertEquals("John", location.getCurrentUser().getPersonalInformation().getFirstName());
    }

    /**
     * Test movie rental.
     */
    @Test
    public void testRentMovie() throws Exception {
        location.rentMovie(testMovie);
        assertTrue(location.rentedMovies().contains(testMovie));
    }

    /**
     * Test movie rental availability.
     */
    @Test
    public void testIsRentable() throws Exception {
        assertTrue(location.isRentable(testMovie));
    }

    /**
     * Test adding a review.
     */
    @Test
    public void testAddReview() throws Exception {
        Review review = new Review(location.getCurrentUser(), testMovie, 5, "Great movie!");
        location.addReview(testMovie, review);
        
        Set<Review> movieReviews = location.reviewsForMovie(testMovie);
        assertTrue(movieReviews.contains(review));
    }

    /**
     * Test retrieving all movies.
     */
    @Test
    public void testAllMovies() {
        Set<Movie> allMovies = location.allMovies();
        assertTrue(allMovies.contains(testMovie));
    }

    /**
     * Test retrieving movies by genre.
     */
    @Test
    public void testMoviesByGenre() {
        testMovie.addGenre(Genre.ScienceFiction);
        Set<Movie> genreMovies = location.moviesByGenre(Genre.ScienceFiction);
        assertTrue(genreMovies.contains(testMovie));
    }
}
