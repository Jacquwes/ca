package tests;

import org.junit.Before;
import location.User;
import location.Movie;
import location.Artist;
import location.Reservation;
import location.PersonalInformation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.Test;
import java.util.Date;

/**
 * Test class for Reservation entity.
 */
public class ReservationTest {
    private User user;
    private Movie movie;
    private Date reservationDate;
    private Reservation reservation;

    /**
     * Set up test fixtures before each test method.
     */
    @Before
    public void setUp() {
        PersonalInformation info = new PersonalInformation("John", "Doe", "adress", 30);
        user = new User("john.doe", "password", info);
        
        Artist director = new Artist("Spielberg", "Steven", "American");
        movie = new Movie("Saving Private Ryan", 1998, director, null);
        
        reservationDate = new Date();
        
        reservation = new Reservation(user, movie, reservationDate, 7);
    }

    /**
     * Test reservation constructor and getters.
     */
    @Test
    public void testReservationConstructorAndGetters() {
        assertEquals(user, reservation.getUser());
        assertEquals(movie, reservation.getMovie());
        assertEquals(reservationDate, reservation.getDate());
        assertEquals(Integer.valueOf(7), reservation.getDuration());
    }

    /**
     * Test setter methods.
     */
    @Test
    public void testSetters() {
        PersonalInformation newInfo = new PersonalInformation("Jane", "Smith", "adress", 30);
        User newUser = new User("jane.smith", "password", newInfo);
        reservation.setUser(newUser);
        assertEquals(newUser, reservation.getUser());

        Movie newMovie = new Movie("Jurassic Park", 1993, null, null);
        reservation.setMovie(newMovie);
        assertEquals(newMovie, reservation.getMovie());

        Date newDate = new Date(System.currentTimeMillis() + 86400000);
        reservation.setDate(newDate);
        assertEquals(newDate, reservation.getDate());

        reservation.setDuration(14);
        assertEquals(Integer.valueOf(14), reservation.getDuration());
    }

    /**
     * Test equals method.
     */
    @Test
    public void testEquals() {
        Reservation sameReservation = new Reservation(user, movie, reservationDate, 7);
        Reservation differentReservation = new Reservation(
            new User("jane.smith@example.com", "Jane", new PersonalInformation("Jane", "Smith", "adress", 30)),
            new Movie("Jurassic Park", 1993, null, null), 
            new Date(), 
            14
        );

        assertEquals(reservation, sameReservation);
        assertNotEquals(reservation, differentReservation);
    }
}