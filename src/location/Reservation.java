package location;

import java.util.Date;

/**
 * Represents a reservation made by a user for a movie.
 */
public class Reservation {

    /**
     * The user who made the reservation.
     */
    private User user;

    /**
     * The movie that is reserved.
     */
    private Movie movie;

    /**
     * The date of the reservation.
     */
    private Date date;

    /**
     * The duration of the reservation in days.
     */
    private Integer duration;

    /**
     * Constructs a new Reservation with the specified user, movie, date, and duration.
     *
     * @param user     the user who made the reservation
     * @param movie    the movie that is reserved
     * @param date     the date of the reservation
     * @param duration the duration of the reservation in days
     */
    public Reservation(User user, Movie movie, Date date, Integer duration) {
        this.user = user;
        this.movie = movie;
        this.date = date;
        this.duration = duration;
    }

    /**
     * Returns the user who made the reservation.
     *
     * @return the user who made the reservation
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Returns the movie that is reserved.
     *
     * @return the movie that is reserved
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Returns the date of the reservation.
     *
     * @return the date of the reservation
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns the duration of the reservation in days.
     *
     * @return the duration of the reservation in days
     */
    public Integer getDuration() {
        return this.duration;
    }

    /**
     * Sets the user who made the reservation.
     *
     * @param user the user who made the reservation
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the movie that is reserved.
     *
     * @param movie the movie that is reserved
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Sets the date of the reservation.
     *
     * @param date the date of the reservation
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets the duration of the reservation in days.
     *
     * @param duration the duration of the reservation in days
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}