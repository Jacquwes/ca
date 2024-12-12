package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import location.Movie;
import location.Reservation;
import location.Review;
import location.User;

import java.util.Set;

/**
 * Test class for User to validate all methods and behaviors.
 */
public class UserTest {
    private User testUser;
    private Movie testMovie;
    private Reservation testReservation;
    private Review testReview;

    /**
     * Set up test data before each test method.
     */
    @Before
    public void setUp() {
        PersonalInformation personalInfo = new PersonalInformation("John", "Doe", "adress", 30);
        testUser = new User("johndoe", "password", personalInfo);
        testMovie = new Movie("Inception", 2010, new Artist(), null);
        testReservation = new Reservation(testUser, testMovie, null, 2);
        testReview = new Review(testUser, testMovie, 4.5, "Great movie!");
    }

    /**
     * Tests constructor and getter methods.
     */
    @Test
    public void testConstructorAndGetters() {
        assertEquals("johndoe", testUser.getLogin());
        assertEquals("password", testUser.getPassword());
        assertNotNull(testUser.getPersonalInformation());
    }

    /**
     * Tests setter methods.
     */
    @Test
    public void testSetters() {
        PersonalInformation newInfo = new PersonalInformation("Jane", "Smith", "new address", 25);
        testUser.setLogin("newlogin");
        testUser.setPassword("newpassword");
        testUser.setPersonalInformation(newInfo);

        assertEquals("newlogin", testUser.getLogin());
        assertEquals("newpassword", testUser.getPassword());
        assertEquals(newInfo, testUser.getPersonalInformation());
    }

    /**
     * Tests adding and retrieving reservations.
     */
    @Test
    public void testReservations() {
        Set<Reservation> reservations = testUser.getReservations();
        assertTrue(reservations.contains(testReservation));
        assertEquals(1, reservations.size());
    }

    /**
     * Tests cancel reservation.
     */
    @Test
    public void testCancelReservation() {
        testUser.cancelReservation(testReservation);
        assertFalse(testUser.getReservations().contains(testReservation));
        assertNull(testReservation.getUser());
    }

    /**
     * Tests adding too many reservations.
     */
    @Test
    public void testAddTooManyReservations() {
        testUser.resetReservations();
        Reservation reservation1 = new Reservation(testUser, new Movie("Inception", 2010, new Artist(), null), null, 2);
        Reservation reservation2 = new Reservation(testUser, new Movie("Interstellar", 2014, new Artist(), null), null, 2);
        Reservation reservation3 = new Reservation(testUser, new Movie("Dunkirk", 2017, new Artist(), null), null, 2);
        assertThrows(RentingException.class, () -> new Reservation(testUser, new Movie("Tenet", 2020, new Artist(), null), null, 2));
    }

    /**
     * Tests adding a review to the user.
     */
    @Test
    public void testAddAndGetReviews() {
        testUser.addReview(testReview);
        Set<Review> reviews = testUser.getReviews();
        
        assertTrue(reviews.contains(testReview));
        assertEquals(1, reviews.size());
    }

    /**
     * Tests adding and removing multiple reviews.
     */
    @Test
    public void testMultipleReviews() {
        Review review1 = new Review(testUser, 
            new Movie("Movie 1", 2020, new Artist(), null), 
            4.0, "Good movie");
        Review review2 = new Review(testUser, 
            new Movie("Movie 2", 2021, new Artist(), null), 
            4.5, "Great movie");
        
        testUser.addReview(review1);
        testUser.addReview(review2);
        
        Set<Review> reviews = testUser.getReviews();
        assertEquals(2, reviews.size());
        assertTrue(reviews.contains(review1));
        assertTrue(reviews.contains(review2));
    }

    /**
     * Tests that reservations are unique.
     */
    @Test
    public void testUniqueReservations() {
        testUser.resetReservations();
        Reservation reservation1 = new Reservation(testUser, 
            new Movie("Inception", 2010, new Artist(), null), null, 2);
        Reservation reservation2 = new Reservation(testUser,
            new Movie("Interstellar", 2014, new Artist(), null), null, 2);
        
        
        Set<Reservation> reservations = testUser.getReservations();
        assertEquals(2, reservations.size());
        assertTrue(reservations.contains(reservation1));
        assertTrue(reservations.contains(reservation2));
    }
}