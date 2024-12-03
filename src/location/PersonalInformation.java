package location;

import java.util.Objects;

/**
 * Description of a person's personal information: identity, age, and address.
 *
 * @author Florent Delalande
 * @author Paul Hariel
 */
public final class PersonalInformation implements java.io.Serializable {

  /**
   * Serialization identifier.
   */
  private static final long serialVersionUID = 4026408353251835506L;

  /**
   * The last name of the person (cannot be modified).
   */
  private final String lastName;

  /**
   * The first name of the person (cannot be modified).
   */
  private final String firstName;

  /**
   * The age of the person (the value 0 corresponds to an undefined age).
   */
  private int age;

  /**
   * The address of the person (an empty string "" corresponds to an undefined
   * address).
   */
  private String address;

  /**
   * Returns the last name of the person.
   *
   * @return the last name of the person
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the first name of the person.
   *
   * @return the first name of the person
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the age of the person.
   *
   * @return the age of the person
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the person.
   *
   * @param age the new age (must be greater than 0)
   */
  public void setAge(int age) {
    if (age > 0) {
      this.age = age;
    }
  }

  /**
   * Returns the address of the person.
   *
   * @return the address of the person
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the address of the person.
   *
   * @param address the new address (must be different from null)
   */
  public void setAddress(String address) {
    if (address != null) {
      this.address = address;
    }
  }

  /**
   * Creates a person with their mandatory information.
   *
   * @param lastName  the last name of the person
   * @param firstName the first name of the person
   */
  public PersonalInformation(String lastName, String firstName) {
    this(lastName, firstName, "", 0);
  }

  /**
   * Creates a person with all their information.
   *
   * @param lastName  the last name of the person
   * @param firstName the first name of the person
   * @param address   the address of the person
   * @param age       the age of the person
   */
  public PersonalInformation(String lastName, String firstName, String address,
      int age) {
    super();
    this.lastName = lastName;
    this.firstName = firstName;
    this.setAge(age);
    this.setAddress(address);
  }

  /**
   * Compare two personal information objects.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    PersonalInformation other = (PersonalInformation) obj;
    return Objects.equals(address, other.address) && age == other.age
        && Objects.equals(lastName, other.lastName)
        && Objects.equals(firstName, other.firstName);
  }

  /**
   * Returns a string representation of the person's information.
   */
  @Override
  public String toString() {
    return firstName + " " + lastName + " aged " + age + " years, lives at " + address;
  }
}
