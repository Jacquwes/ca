package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import location.PersonalInformation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the class {@link location.PersonalInformation
 * PersonalInformation}.
 *
 * @author Florent Delalande
 * @author Paul Hariel
 * @see location.PersonalInformation
 */
class TestPersonalInformation {

  /**
   * Basic information: first name and last name.
   */
  private PersonalInformation basicInfo;
  /**
   * Complete information: first name, last name, address, and age.
   */
  private PersonalInformation completeInfo;

  /**
   * Instantiates basic and complete information for the tests.
   *
   * @throws Exception cannot be thrown here
   */
  @BeforeEach
  void setUp() throws Exception {
    basicInfo = new PersonalInformation("Skywalker", "Luke");
    completeInfo = new PersonalInformation("Skywalker", "Luke", "Planet Tatooine", 20);
  }

  /**
   * Does nothing after the tests: modify if needed.
   *
   * @throws Exception cannot be thrown here
   */
  @AfterEach
  void tearDown() throws Exception {
  }

  /**
   * Verifies that an age of 25 years can be set.
   */
  @Test
  void testAge25Basic() {
    basicInfo.setAge(25);
    assertEquals(basicInfo.getAge(), 25);
  }

  /**
   * Verifies that a negative age cannot be set on basic information.
   */
  @Test
  void testNegativeAgeBasic() {
    basicInfo.setAge(-20);
    assertTrue(basicInfo.getAge() != -20);
  }

  /**
   * Verifies that a negative age cannot be set on complete information: the age
   * remains the same as before.
   */
  @Test
  void testNegativeAgeComplete() {
    int age = completeInfo.getAge();
    completeInfo.setAge(-20);
    assertEquals(completeInfo.getAge(), age);
  }

  /**
   * Verifies that an address is not null when personal information is created.
   */
  @Test
  void testAddressNotNull() {
    assertTrue(basicInfo.getAddress() != null);
    assertTrue(completeInfo.getAddress() != null);
  }

  /**
   * Verifies that a null address cannot be set on existing information.
   */
  @Test
  void testSetterAddressNull() {
    completeInfo.setAddress(null);
    assertTrue(completeInfo.getAddress() != null);
  }

  /**
   * Verifies that the constructor parameters are correctly handled.
   */
  @Test
  void testConstructor() {
    PersonalInformation info = new PersonalInformation("Vader", "Darth", null, -30);
    assertEquals(info.getLastName(), "Vader");
    assertEquals(info.getFirstName(), "Darth");
    assertTrue(info.getAddress() != null);
    assertTrue(info.getAge() >= 0);
  }

}
