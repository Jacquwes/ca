package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages all the reservations.
 */
public class ReservationManager implements Serializable {

  /**
   * The set of reservations.
   */
  private Set<Reservation> reservations = new HashSet<Reservation>();

  /**
   * Constructs a new ReservationManager.
   */
  public void reset() {
    reservations = new HashSet<Reservation>();
  }

  /**
   * Retrieves all the reservations.
   *
   * @return all the reservations.
   */
  public Set<Reservation> getReservations() {
    return reservations;
  }

  /**
   * Adds a reservation to the set of reservations.
   *
   * @param reservation the reservation to add.
   */
  public void addReservation(Reservation reservation) {
    reservations.add(reservation);
  }

  /**
   * Removes a reservation from the set of reservations.
   *
   * @param reservation the reservation to remove.
   */
  public  void removeReservation(Reservation reservation) {
    reservations.remove(reservation);
  }

  /**
   * Retrieves all the reservations that satisfy the predicate.
   *
   * @param p the predicate to satisfy.
   * @return all the reservations that satisfy the predicate.
   */
  public Set<Reservation> getReservations(User user) {
    Set<Reservation> result = new HashSet<Reservation>();

    for (Reservation reservation : reservations) {
      if (reservation.getUser() == user) {
        result.add(reservation);
      }
    }

    return result;
  }

  /**
   * Retrieves all the reservations that satisfy the predicate.
   *
   * @param p the predicate to satisfy.
   * @return all the reservations that satisfy the predicate.
   */
  public Set<Reservation> getReservations(Movie movie) {
    Set<Reservation> result = new HashSet<Reservation>();

    for (Reservation reservation : reservations) {
      if (reservation.getMovie() == movie) {
        result.add(reservation);
      }
    }

    return result;
  }
}
