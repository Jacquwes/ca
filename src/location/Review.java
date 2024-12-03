package location;

/**
 * Represents a review made by a user for a movie.
 */
public class Review {
  /**
   * The rating of the review.
   */
  private double rating = 0;

  /**
   * The comment of the review.
   */
  private String comment = null;

  /**
   * The user who made the review.
   */
  private User user = null;

  /**
   * The movie that is reviewed.
   */
  private Movie movie = null;

  /**
   * Constructs a new Review.
   */
  public Review() {
  }
  
  /**
   * Constructs a new Review with the specified rating, comment, user, and movie.
   * @param user the user who made the review
   * @param movie the movie that is reviewed
   * @param rating the rating of the review
   * @param comment the comment of the review
   */
  public Review(User user, Movie movie, double rating, String comment) {
    this.rating = rating;
    this.comment = comment;
    this.user = user;
    this.movie = movie;
  }

  /**
   * Retrieves the rating of the review.
   *
   * @return the rating of the review.
   */
  public double getRating() {
    return rating;
  }

  /**
   * Sets the rating of the review.
   *
   * @param rating the rating of the review.
   */
  public void setRating(double rating) {
    this.rating = rating;
  }

  /**
   * Retrieves the comment of the review.
   *
   * @return the comment of the review.
   */
  public String getComment() {
    return comment;
  }

  /**
   * Sets the comment of the review.
   *
   * @param comment the comment of the review.
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Retrieves the user who made the review.
   *
   * @return the user who made the review.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the user who made the review.
   *
   * @param user the user who made the review.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Retrieves the movie that is reviewed.
   *
   * @return the movie that is reviewed.
   */
  public Movie getMovie() {
    return movie;
  }

  /**
   * Sets the movie that is reviewed.
   *
   * @param movie the movie that is reviewed.
   */
  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  /**
   * Compares this review to the specified object.
   *
   * @param obj the object to compare
   * @return true if the object is equal to this review, false otherwise
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
    Review other = (Review) obj;
    if (user == null) {
      if (other.user != null) {
        return false;
      }
    } else if (!user.equals(other.user)) {
      return false;
    }
    if (movie == null) {
      if (other.movie != null) {
        return false;
      }
    } else if (!movie.equals(other.movie)) {
      return false;
    }
    return true;
  }
}
