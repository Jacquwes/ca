package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import location.Movie;
import location.PersonalInformation;
import location.Reservation;
import location.User;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Reservation class functionality.
 */
public class ReservationTest {
  /**
   * Test instance of User.
   */
  private static final User USER = new User("johndoe",
      "password123",
      new PersonalInformation("John", "Doe"));

  /**
   * Test instance of Movie.
   */
  private static final Movie MOVIE = new Movie("Inception", 2024, null, null);

  /**
   * Test instance of Reservation.
   */
  private static final Date DATE = new Date();

  /**
   * Test instance of Reservation.
   */
  private static final Integer DURATION = 7;

  /**
   * Tests the parameterized constructor of Reservation class.
   */
  @Test
  public void testReservationConstructor() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    assertNotNull(reservation);
    assertEquals(USER, reservation.getUser());
    assertEquals(MOVIE, reservation.getMovie());
    assertEquals(DATE, reservation.getDate());
    assertEquals(DURATION, reservation.getDuration());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testGetUser() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    assertEquals(USER, reservation.getUser());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testSetUser() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    User newUser = new User("janedoe", "password456", new PersonalInformation("Jane", "Doe"));
    reservation.setUser(newUser);
    assertEquals(newUser, reservation.getUser());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testGetMovie() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    assertEquals(MOVIE, reservation.getMovie());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testSetMovie() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    Movie newMovie = new Movie("Interstellar", 2024, null, null);
    reservation.setMovie(newMovie);
    assertEquals(newMovie, reservation.getMovie());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testGetDate() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    assertEquals(DATE, reservation.getDate());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testSetDate() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    Date newDate = new Date(DATE.getTime() + 86400000L); // +1 day
    reservation.setDate(newDate);
    assertEquals(newDate, reservation.getDate());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testGetDuration() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    assertEquals(DURATION, reservation.getDuration());
  }

  /**
   * Tests the setter methods of Reservation class.
   */
  @Test
  public void testSetDuration() {
    Reservation reservation = new Reservation(USER, MOVIE, DATE, DURATION);
    Integer newDuration = 14;
    reservation.setDuration(newDuration);
    assertEquals(newDuration, reservation.getDuration());
  }
}