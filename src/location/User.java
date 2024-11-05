package location;

/**
 * The User class represents a user with personal information.
 * It implements the InterUtilisateur interface.
 * 
 * <p>This class provides methods to access the user's personal information.</p>
 * 
 * @author 
 */
public class User implements InterUtilisateur {

  /**
   * The personal information of the user.
   */
  private PersonalInformation personalInformation;

  /**
   * Constructs a new User with the specified personal information.
   *
   * @param lastName the last name of the user
   * @param firstName the first name of the user
   * @param age the age of the user
   * @param address the address of the user
   */
  User(String lastName, String firstName, int age, String address) {
    this.personalInformation = new PersonalInformation(lastName, firstName, address, age);
  }

  /**
   * Retrieves the personal information of the user.
   *
   * @return the personal information of the user.
   */
  public PersonalInformation getPersonalInformation() {
    return personalInformation;
  }
}


