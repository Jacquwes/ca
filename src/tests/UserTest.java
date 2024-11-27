package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import location.PersonalInformation;
import location.User;
import location.RentingException;
import location.Reservation;
import location.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the User class functionality.
 */
public class UserTest {
  /**
   * Username of the test user.
   */
  private static final String USERNAME_JOHNDOE = "johndoe";

  /**
   * Password of the test user.
   */
  private static final String PASSWORD = "password123";

  /**
   * Last name of the test user.
   */
  private static final String LASTNAME_JOHN = "John";

  /**
   * First name of the test user.
   */
  private static final String FIRSTNAME_DOE = "Doe";

  /**
   * Test instance of User.
   */
  private User user;

  /**
   * Test instance of PersonalInformation.
   */
  private PersonalInformation info;

  /**
   * Test instance of Movie.
   */
  private Movie movie;

  /**
   * Sets up the test environment before each test.
   */
  @BeforeEach
  public void setUp() {
    info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    user.resetReservations();
    User.resetUsers();
    movie = new Movie("Interstellar", 2024, null, null);
  }

  /**
   * Tests the register method of User class.
   */
  @Test
  public void testRegisterSuccess() {
    assertNotNull(user);
    assertEquals(USERNAME_JOHNDOE, user.getLogin());
    assertEquals(info, user.getPersonalInformation());
  }

  /**
   * Tests the register method of User class with a null username.
   */
  @Test
  public void testRegisterUsernameAlreadyExists() {
    PersonalInformation info1 = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User.register(USERNAME_JOHNDOE, PASSWORD, info1);
    PersonalInformation info2 = new PersonalInformation("Jane", FIRSTNAME_DOE);
    user = User.register(USERNAME_JOHNDOE, "password456", info2);
    assertNull(user);
  }

  /**
   * Tests the register method of User class with a null username.
   */
  @Test
  public void testRegisterEmptyUsername() {
    user = User.register("", PASSWORD, info);
    assertNull(user);
  }

  /**
   * Tests the register method of User class with a null password.
   */
  @Test
  public void testRegisterEmptyPassword() {
    user = User.register(USERNAME_JOHNDOE, "", info);
      assertNull(user);
  }

  /**
   * Tests the register method of User class with a null personal information.
   */
  @Test
  public void testRegisterNullUsername() {
    user = User.register(null, PASSWORD, info);
    assertNull(user);
  }

  /**
   * Tests the register method of User class with a null password.
   */
  @Test
  public void testRegisterNullPassword() {
    user = User.register(USERNAME_JOHNDOE, null, info);
    assertNull(user);
  }

  /**
   * Tests the register method of User class with a null personal information.
   */
  @Test
  public void testRegisterNullPersonalInformation() {
    user = User.register(USERNAME_JOHNDOE, PASSWORD, null);
    assertNull(user);
  }

  /**
   * Tests the login method of User class.
   */
  @Test
  public void testGetLogin() {
    assertNotNull(user);
    assertEquals(USERNAME_JOHNDOE, user.getLogin());
  }

  /**
   * Tests the login method of User class.
   */
  @Test
  public void testSetLogin() {
    assertNotNull(user);
    user.setLogin("newlogin");
    assertEquals("newlogin", user.getLogin());
  }

  /**
   * Tests the password methods of User class.
   */
  @Test
  public void testGetPassword() {
    assertNotNull(user);
    assertEquals(PASSWORD, user.getPassword());
  }

  /**
   * Tests the password methods of User class.
   */
  @Test
  public void testSetPassword() {
    assertNotNull(user);
    user.setPassword("newpassword");
    assertEquals("newpassword", user.getPassword());
  }

  /**
   * Tests the personal information methods of User class.
   */
  @Test
  public void testGetPersonalInformation() {
    assertNotNull(user);
    assertEquals(info, user.getPersonalInformation());
  }

  /**
   * Tests the personal information methods of User class.
   */
  @Test
  public void testSetPersonalInformation() {
    assertNotNull(user);
    PersonalInformation newInfo = new PersonalInformation("Jane", FIRSTNAME_DOE);
    user.setPersonalInformation(newInfo);
    assertEquals(newInfo, user.getPersonalInformation());
  }

  /**
   * Tests the addReservation method of User class.
   */
  @Test
  public void testAddReservationSuccess() {
    assertNotNull(user);
    Reservation reservation = new Reservation(user, movie, new Date(), 3);
    assertTrue(user.getReservations().contains(reservation));
  }

  /**
   * Tests the addReservation method of User class with a null reservation.
   */
  @Test
  public void testAddReservationNull() {
    assertNotNull(user);
    assertThrows(RentingException.class, () -> user.addReservation(null));
  }

  /**
   * Tests the addReservation method of User class with an already existing.
   */
  @Test
  public void testAddReservationAlreadyExists() {
    assertNotNull(user);
    Reservation reservation = new Reservation(user, movie, new Date(), 3);
    assertThrows(RentingException.class, () -> user.addReservation(reservation));
  }

  /**
   * Tests the addReservation method of User class with a reservation limit.
   * exceeded.
   */
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

  /**
   * Tests the cancelReservation method of User class with a null
   */
  @Test
  public void testCancelReservationSuccess() {
    assertNotNull(user);
    Reservation reservation = new Reservation(user, movie, new Date(), 3);
    user.cancelReservation(reservation);
    assertFalse(user.getReservations().contains(reservation));
  }
}
