package location;

import java.util.HashSet;
import java.util.Set;

public class ReservationManager {
  private Set<Reservation> reservations = new HashSet<Reservation>();

  public void reset() {
    reservations = new HashSet<Reservation>();
  }

  public Set<Reservation> getReservations() {
    return reservations;
  }

  public void addReservation(Reservation reservation) {
    reservations.add(reservation);
  }

  public  void removeReservation(Reservation reservation) {
    reservations.remove(reservation);
  }

  public Set<Reservation> getReservations(User user) {
    Set<Reservation> result = new HashSet<Reservation>();

    for (Reservation reservation : reservations) {
      if (reservation.getUser() == user) {
        result.add(reservation);
      }
    }

    return result;
  }

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
