package location;

import java.util.Set;

/**
 * The User class represents a user.
 * 
 * @author
 */
public class User {

  /**
   * The login of the user.
   */
  private String login;

  /**
   * The personal information of the user.
   */
  private PersonalInformation personalInformation;

  /**
   * The set of registered users.
   */
  private static Set<User> users;

  /**
   * Constructor for the User class.
   */
  public User() {
    this.login = "";
    this.personalInformation = new PersonalInformation("", "");
  }

  /**
   * Retrieves the personal information of the user.
   *
   * @return the personal information of the user.
   */
  public PersonalInformation getPersonalInformation() {
    return personalInformation;
  }

  /**
   * Sets the personal information of the user.
   *
   * @param personalInformation the personal information of the user.
   */
  public void setPersonalInformation(PersonalInformation personalInformation) {
    this.personalInformation = personalInformation;
  }

  /**
   * Retrieves the login of the user.
   *
   * @return the login of the user.
   */
  public String getLogin() {
    return login;
  }

  /**
   * Sets the login of the user.
   *
   * @param login the login of the user.
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * User registration. The chosen username must not already exist among the
   * registered users.
   *
   * @param username the (unique) username of the user
   * @param password the user's password (must not be empty or <code>null</code>)
   * @param info     personal information about the user
   * @return a code specifying the result of the registration: 0 if the
   *         registration was successful, 1 if the username was already used, 2 if
   *         the username or password was empty, 3 if the personal information was
   *         not properly specified
   */
  public int register(String username, String password, PersonalInformation info) {
    if (info == null) {
      return 3;
    }

    if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
      return 2;
    }

    for (User user : users) {
      if (user.login.equals(username)) {
        return 1;
      }
    }

    this.setLogin(username);
    this.setPersonalInformation(info);

    users.add(this);

    return 0;
  }
}
