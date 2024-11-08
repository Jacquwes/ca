package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;
import location.User;
import location.Movie;
import location.Reservation;
import location.PersonalInformation;

public class ReservationTest {

    private static final User USER = new User("johndoe", "password123", new PersonalInformation("John", "Doe"));
    private static final Movie MOVIE = new Movie("Inception", 2024, null, null);
    private static final Date DATE = new Date();
    private static final Integer DURATION = 7;



    @Test
    public void testReservationConstructor() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        assertNotNull(reservation);
        assertEquals(USER, reservation.getUser());
        assertEquals(MOVIE, reservation.getMovie());
        assertEquals(DATE, reservation.getDate());
        assertEquals(DURATION, reservation.getDuration());
    }

    @Test
    public void testGetUser() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        assertEquals(USER, reservation.getUser());
    }

    @Test
    public void testSetUser() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        User newUser = new User("janedoe", "password456", new PersonalInformation("Jane", "Doe"));
        reservation.setUser(newUser);
        assertEquals(newUser, reservation.getUser());
    }

    @Test
    public void testGetMovie() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        assertEquals(MOVIE, reservation.getMovie());
    }

    @Test
    public void testSetMovie() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        Movie newMovie = new Movie("Interstellar", 2024, null, null);
        reservation.setMovie(newMovie);
        assertEquals(newMovie, reservation.getMovie());
    }

    @Test
    public void testGetDate() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        assertEquals(DATE, reservation.getDate());
    }

    @Test
    public void testSetDate() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        Date newDate = new Date(DATE.getTime() + 86400000L); // +1 day
        reservation.setDate(newDate);
        assertEquals(newDate, reservation.getDate());
    }

    @Test
    public void testGetDuration() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        assertEquals(DURATION, reservation.getDuration());
    }

    @Test
    public void testSetDuration() {
        Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
        Integer newDuration = 14;
        reservation.setDuration(newDuration);
        assertEquals(newDuration, reservation.getDuration());
    }
}