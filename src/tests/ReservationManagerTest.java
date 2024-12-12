package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import location.Artist;
import location.Movie;
import location.PersonalInformation;
import location.Reservation;
import location.ReservationManager;
import location.User;
import java.util.Set;

/**
 * Test class for ReservationManager to validate all methods and behaviors.
 */
public class ReservationManagerTest {
    private ReservationManager reservationManager;
    private User testUser;
    private Movie testMovie;
    private Reservation testReservation;

    /**
     * Set up test data before each test method.
     */
    @Before
    public void setUp() {
        reservationManager = new ReservationManager();
        PersonalInformation personalInfo = new PersonalInformation("John", "Doe", "adress", 30);
        testUser = new User("johndoe", "password", personalInfo);
        testMovie = new Movie("Inception", 2010, new Artist(), null);
        testReservation = new Reservation(testUser, testMovie, null, 1);
    }

    /**
     * Tests reset method to ensure reservations are cleared.
     */
    @Test
    public void testReset() {
        reservationManager.addReservation(testReservation);
        reservationManager.reset();
        assertTrue(reservationManager.getReservations().isEmpty());
    }

    /**
     * Tests getReservations method to retrieve all reservations.
     */
    @Test
    public void testGetReservations() {
        reservationManager.addReservation(testReservation);
        Set<Reservation> reservations = reservationManager.getReservations();
        assertEquals(1, reservations.size());
        assertTrue(reservations.contains(testReservation));
    }

    /**
     * Tests addReservation method to add a new reservation.
     */
    @Test
    public void testAddReservation() {
        reservationManager.addReservation(testReservation);
        assertTrue(reservationManager.getReservations().contains(testReservation));
    }

    /**
     * Tests removeReservation method to remove an existing reservation.
     */
    @Test
    public void testRemoveReservation() {
        reservationManager.addReservation(testReservation);
        reservationManager.removeReservation(testReservation);
        assertFalse(reservationManager.getReservations().contains(testReservation));
    }

    /**
     * Tests getReservations(User) method to retrieve reservations for a specific user.
     */
    @Test
    public void testGetReservationsByUser() {
        reservationManager.addReservation(testReservation);
        Set<Reservation> userReservations = reservationManager.getReservations(testUser);
        assertEquals(1, userReservations.size());
        assertTrue(userReservations.contains(testReservation));
    }

    /**
     * Tests getReservations(Movie) method to retrieve reservations for a specific movie.
     */
    @Test
    public void testGetReservationsByMovie() {
        reservationManager.addReservation(testReservation);
        Set<Reservation> movieReservations = reservationManager.getReservations(testMovie);
        assertEquals(1, movieReservations.size());
        assertTrue(movieReservations.contains(testReservation));
    }
}
