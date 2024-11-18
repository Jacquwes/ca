package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import location.PersonalInformation;
import location.User;
import location.Reservation;
import location.RentingException;
import location.Movie;
import java.util.Date;

public class UserTest {

  private static final String USERNAME_JOHNDOE = "johndoe";
  private static final String PASSWORD = "password123";
  private static final String LASTNAME_JOHN = "John";
  private static final String FIRSTNAME_DOE = "Doe";
  private User user;
  private PersonalInformation info;
  private Movie movie;

  @BeforeEach
  public void setUp() {
    info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    user.resetReservations();
    User.resetUsers();
    movie = new Movie("Interstellar", 2024, null, null);
  }

  @Test
  public void testRegisterSuccess() {
    assertNotNull(user);
    assertEquals(USERNAME_JOHNDOE, user.getLogin());
    assertEquals(info, user.getPersonalInformation());
  }

  @Test
  public void testRegisterUsernameAlreadyExists() {
    PersonalInformation info1 = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User.register(USERNAME_JOHNDOE, PASSWORD, info1);
    PersonalInformation info2 = new PersonalInformation("Jane", FIRSTNAME_DOE);
    user = User.register(USERNAME_JOHNDOE, "password456", info2);
    assertNull(user);
  }

  @Test
  public void testRegisterEmptyUsername() {
    user = User.register("", PASSWORD, info);
    assertNull(user);
  }

  @Test
  public void testRegisterEmptyPassword() {
    user = User.register(USERNAME_JOHNDOE, "", info);
    assertNull(user);
  }

  @Test
  public void testRegisterNullUsername() {
    user = User.register(null, PASSWORD, info);
    assertNull(user);
  }

  @Test
  public void testRegisterNullPassword() {
    user = User.register(USERNAME_JOHNDOE, null, info);
    assertNull(user);
  }

  @Test
  public void testRegisterNullPersonalInformation() {
    user = User.register(USERNAME_JOHNDOE, PASSWORD, null);
    assertNull(user);
  }

  @Test
  public void testGetLogin() {
    assertNotNull(user);
    assertEquals(USERNAME_JOHNDOE, user.getLogin());
  }

  @Test
  public void testSetLogin() {
    assertNotNull(user);
    user.setLogin("newlogin");
    assertEquals("newlogin", user.getLogin());
  }

  @Test
  public void testGetPassword() {
    assertNotNull(user);
    assertEquals(PASSWORD, user.getPassword());
  }

  @Test
  public void testSetPassword() {
    assertNotNull(user);
    user.setPassword("newpassword");
    assertEquals("newpassword", user.getPassword());
  }

  @Test
  public void testGetPersonalInformation() {
    assertNotNull(user);
    assertEquals(info, user.getPersonalInformation());
  }

  @Test
  public void testSetPersonalInformation() {
    assertNotNull(user);
    PersonalInformation newInfo = new PersonalInformation("Jane", FIRSTNAME_DOE);
    user.setPersonalInformation(newInfo);
    assertEquals(newInfo, user.getPersonalInformation());
  }

  @Test
  public void testAddReservationSuccess() {
    assertNotNull(user);
    Reservation reservation = new Reservation(user, movie, new Date(), 3);
    assertTrue(user.getReservations().contains(reservation));
  }

  @Test
  public void testAddReservationNull() {
    assertNotNull(user);
    assertThrows(RentingException.class, () -> user.addReservation(null));
  }

  @Test
  public void testAddReservationAlreadyExists() {
    assertNotNull(user);
    Reservation reservation = new Reservation(user, movie, new Date(), 3);
    assertThrows(RentingException.class, () -> user.addReservation(reservation));
  }

  @Test
  public void testAddReservationLimitExceeded() {
    assertNotNull(user);
    Movie movie1 = new Movie("Interstellar", 2024, null, null);
    new Reservation(user, movie1, new Date(), 3);
    Movie movie2 = new Movie("Inception", 2020, null, null);
    new Reservation(user, movie2, new Date(), 3);
    Movie movie3 = new Movie("Tenet", 2021, null, null);
    new Reservation(user, movie3, new Date(), 3);
    Movie movie4 = new Movie("Dunkirk", 2017, null, null);
    assertThrows(RentingException.class, () -> new Reservation(user, movie4, new Date(), 3));
  }

  @Test
  public void testCancelReservationSuccess() {
    assertNotNull(user);
    Reservation reservation = new Reservation(user, movie, new Date(), 3);
    user.cancelReservation(reservation);
    assertFalse(user.getReservations().contains(reservation));
  }
}
