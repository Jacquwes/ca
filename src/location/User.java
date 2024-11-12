package location;

import java.util.Set;
import java.util.HashSet;

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
   * The password of the user.
   * This field is used to store the user's password.
   */
  private String password;

  /**
   * The personal information of the user.
   */
  private PersonalInformation personalInformation;

  /**
   * The set of reservations made by the user.
   */
  private Set<Reservation> reservations;

  /**
   * The set of registered users.
   */
  private static Set<User> users = new HashSet<>();

  private static Set<Review> reviews = new HashSet<>();

  /**
   * Constructs a new User with the specified login and personal information.
   *
   * @param login               the login identifier for the user
   * @param personalInformation the personal information of the user
   */
  public User(String login, String password, PersonalInformation personalInformation) {
    this.login = login;
    this.password = password;
    this.personalInformation = personalInformation;
    this.reservations = new HashSet<>();
    User.users.add(this);
  }

  /**
   * Retrieves the personal information of the user.
   *
   * @return the personal information of the user.
   */
  public PersonalInformation getPersonalInformation() {
    return this.personalInformation;
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
    return this.login;
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
   * Sets the password for the user.
   *
   * @param password the new password to be set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Retrieves the password of the user.
   *
   * @return the password of the user.
   */
  public String getPassword() {
    return this.password;
  }

  /*
   * Retrieves the set of reservations made by the user.
   * 
   * @return the set of reservations made by the user.
   */
  public Set<Reservation> getReservations() {
    return this.reservations;
  }

  /**
   * Display the reservations made by the user. 
   */

  /**
   * Adds a review to the user.
   * 
   * @param review the review to be added
   */
  public void addReview(Review review) {
    reviews.add(review);
  }

  /**
   * Retrieves the reviews of the user.
   * 
   * @return the reviews of the user
   */
  public Set<Review> getReviews() {
    return reviews;
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
  public static User register(String username, String password, PersonalInformation info) {
    if (info == null) {
      return null;
    }

    if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
      return null;
    }

    for (User user : users) {
      if (user.login.equals(username)) {
        return null;
      }
    }

    User user = new User(username, password, info);
    users.add(user);
    return user;
  }
}
