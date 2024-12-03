package location;

import java.util.HashSet;
import java.util.Set;

/**
 * The User class represents a user.
 *
 * @author Florent Delalande
 * @author Paul Hariel
 *
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
   * The set of reviews made by the user.
   */
  private Set<Review> reviews = new HashSet<>();

  /**
   * Constructs a new User with the specified login and personal information.
   *
   * @param login               the login identifier for the user
   * @param password            the password of the user
   * @param personalInformation the personal information of the user
   */
  public User(String login, String password, PersonalInformation personalInformation) {
    this.login = login;
    this.password = password;
    this.personalInformation = personalInformation;
    this.reservations = new HashSet<>();
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

  /**
   * Retrieves the set of reservations made by the user.
   *
   * @return the set of reservations made by the user.
   */
  public Set<Reservation> getReservations() {
    return this.reservations;
  }

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
   * Adds a reservation to the user's list of reservations.
   *
   * @param reservation the reservation to be added
   * @throws RentingException if the reservation is null
   * @throws RentingException if the reservation already exists in the list
   * @throws RentingException if the user already has 3 reservations
   */
  public void addReservation(Reservation reservation) {
    if (reservation == null) {
      throw new RentingException("The reservation is null");
    }

    if (reservations.contains(reservation)) {
      throw new RentingException("The reservation already exists");
    }

    if (reservations.size() >= 3) {
      throw new RentingException("The user has already 3 reservations");
    }

    reservations.add(reservation);
  }

  /**
   * Cancels the given reservation by removing it from the user's list of
   * reservations
   * and dissociating the user from the reservation.
   *
   * @param reservation the reservation to be canceled
   */
  public void cancelReservation(Reservation reservation) {
    reservations.remove(reservation);
    reservation.setUser(null);
  }

  /**
   * Resets the user's reservations by initializing a new empty HashSet.
   * This method clears all existing reservations.
   */
  public void resetReservations() {
    reservations = new HashSet<>();
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param obj the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument;
   *         {@code false} otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    User other = (User) obj;
    if (login == null) {
      if (other.login != null) {
        return false;
      }
    } else if (!login.equals(other.login)) {
      return false;
    }
    return true;
  }
}
