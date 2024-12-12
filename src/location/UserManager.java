package location;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

/**
 * Manages all the users.
 */
public class UserManager implements Serializable {
  /**
   * The set of registered users.
   */
  private Set<User> users;

  /**
   * The currently logged in user.
   */
  private User currentUser;

  /**
   * Retrieves the currently logged in user.
   *
   * @return the currently logged in user
   */
  public User getCurrentUser() {
    return currentUser;
  }

  /**
   * Constructs a new UserManager.
   */
  public UserManager() {
    users = new HashSet<>();
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
      if (user.getLogin().equals(username)) {
        return 1;
      }
    }

    User user = new User(username, password, info);
    users.add(user);
    return 0;
  }

  public boolean login(String username, String password) {
    for (User user : users) {
      if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
        currentUser = user;
        return true;
      }
    }
    return false;
  }

  public void logout() throws NotLoggedInException {
    if (currentUser == null) {
      throw new NotLoggedInException();
    }
    currentUser = null;
  }

  /**
   * Resets the collection of users to an empty HashSet.
   * This method clears all existing users and initializes a new empty set.
   */
  public void resetUsers() {
    users = new HashSet<>();
  }
}
