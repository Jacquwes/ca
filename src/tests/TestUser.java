package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import location.PersonalInformation;
import location.User;

public class TestUser {

  private User user;
  private PersonalInformation info;

  @BeforeEach
  public void setUp() {
    user = new User();
    info = new PersonalInformation("John", "Doe");
    User.users = new HashSet<>();
  }

  @Test
  public void testRegisterSuccess() {
    int result = user.register("john_doe", "password123", info);
    assertEquals(0, result);
  }

  @Test
  public void testRegisterUsernameAlreadyUsed() {
    user.register("john_doe", "password123", info);
    User anotherUser = new User();
    int result = anotherUser.register("john_doe", "password456", new PersonalInformation("Jane", "Doe"));
    assertEquals(1, result);
  }

  @Test
  public void testRegisterEmptyUsernameOrPassword() {
    int result1 = user.register("", "password123", info);
    assertEquals(2, result1);

    int result2 = user.register("john_doe", "", info);
    assertEquals(2, result2);

    int result3 = user.register(null, "password123", info);
    assertEquals(2, result3);

    int result4 = user.register("john_doe", null, info);
    assertEquals(2, result4);
  }

  @Test
  public void testRegisterNullPersonalInformation() {
    int result = user.register("john_doe", "password123", null);
    assertEquals(3, result);
  }
}