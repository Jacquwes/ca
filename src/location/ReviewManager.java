package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Manages all the reviews.
 */
public class ReviewManager implements Serializable {
  /**
   * Represents all the reviews.
  */
  private Set<Review> reviews;

  /**
   * Constructs a new ReviewManager.
   */
  public ReviewManager() {
    reviews = new HashSet<>();
  }

  /**
   * Retrieves all the reviews that satisfy the predicate.
   *
   * @param p the predicate to satisfy.
   * @return all the reviews that satisfy the predicate.
   */
  public Set<Review> getReviews(Predicate<Review> p) {
    Set<Review> result = new HashSet<>();
    for (Review review : reviews) {
      if (p.test(review)) {
        result.add(review);
      }
    }
    return result;
  }

  /**
   * Adds a review to the set of reviews.
   *
   * @param review the review to add.
   */
  public void add(User user, Movie movie, double rating, String comment)
      throws IllegalArgumentException {
    if (!getReviews(r -> r.getUser() == user && r.getMovie() == movie).isEmpty()) {
      throw new IllegalArgumentException("User already reviewed this movie");
    }

    Review review = new Review(user, movie, rating, comment);

    user.addReview(review);
    movie.addReview(review);
  }

  /**
   * Adds a review to the set of reviews.
   *
   * @param review the review to add.
   */
  public void add(Review review) throws IllegalArgumentException {
    if (!getReviews(r -> r.getUser() == review.getUser() && r.getMovie() == review.getMovie()).isEmpty()) {
      throw new IllegalArgumentException("User already reviewed this movie");
    }

    reviews.add(review);
  }
}
