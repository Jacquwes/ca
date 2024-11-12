package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import location.PersonalInformation;
import location.User;

public class UserTest {

  private static final String USERNAME_JOHNDOE = "johndoe";
  private static final String PASSWORD = "password123";
  private static final String LASTNAME_JOHN = "John";
  private static final String FIRSTNAME_DOE = "Doe";

  @Test
  public void testRegisterSuccess() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    assertNotNull(user);
    assertEquals(USERNAME_JOHNDOE, user.getLogin());
    assertEquals(info, user.getPersonalInformation());
  }

  @Test
  public void testRegisterUsernameAlreadyExists() {
    PersonalInformation info1 = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User.register(USERNAME_JOHNDOE, PASSWORD, info1);
    PersonalInformation info2 = new PersonalInformation("Jane", FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, "password456", info2);
    assertNull(user);
  }

  @Test
  public void testRegisterEmptyUsername() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register("", PASSWORD, info);
    assertNull(user);
  }

  @Test
  public void testRegisterEmptyPassword() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, "", info);
    assertNull(user);
  }

  @Test
  public void testRegisterNullUsername() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(null, PASSWORD, info);
    assertNull(user);
  }

  @Test
  public void testRegisterNullPassword() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, null, info);
    assertNull(user);
  }

  @Test
  public void testRegisterNullPersonalInformation() {
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, null);
    assertNull(user);
  }

  @Test
  public void testGetLogin() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    assertNotNull(user);
    assertEquals(USERNAME_JOHNDOE, user.getLogin());
  }

  @Test
  public void testSetLogin() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    assertNotNull(user);
    user.setLogin("newlogin");
    assertEquals("newlogin", user.getLogin());
  }

  @Test
  public void testGetPassword() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    assertNotNull(user);
    assertEquals(PASSWORD, user.getPassword());
  }

  @Test
  public void testSetPassword() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    assertNotNull(user);
    user.setPassword("newpassword");
    assertEquals("newpassword", user.getPassword());
  }

  @Test
  public void testGetPersonalInformation() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
    assertNotNull(user);
    assertEquals(info, user.getPersonalInformation());
  }

  @Test
  public void testSetPersonalInformation() {
    PersonalInformation info = new PersonalInformation(LASTNAME_JOHN, FIRSTNAME_DOE);
    User user = User.register(USERNAME_JOHNDOE, PASSWORD, info);
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
    Movie movie2 = new Movie("Inception", 2020, null, null);
    Movie movie3 = new Movie("Tenet", 2021, null, null);
    Movie movie4 = new Movie("Dunkirk", 2017, null, null);
    assertThrows(RentingException.class, () -> user.addReservation(new Reservation(user, movie4, new Date(), 3)));
  }

  @Test
  public void testCancelReservationSuccess() {
    assertNotNull(user);
    Reservation reservation = new Reservation(user, movie, new Date(), 3);
    user.cancelReservation(reservation);
    assertFalse(user.getReservations().contains(reservation));
  }
}
