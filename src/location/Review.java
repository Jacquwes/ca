package location;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Review {
  private double rating = 0;
  private String comment = null;
  private User user = null;
  private Movie movie = null;

  static private Set<Review> reviews = new HashSet<>();

  static public Set<Review> getReviews(Predicate<Review> p) {
    Set<Review> result = new HashSet<>();
    for (Review review : Review.reviews) {
      if (p.test(review))
        result.add(review);
    }

    return result;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public Review(User user, Movie movie, double rating, String comment)
      throws IllegalArgumentException {
    if (!getReviews(r -> {
      return r.user == user && r.movie == movie;
    }).isEmpty())
      throw new IllegalArgumentException("User already reviewed this movie");

    user.addReview(this);
    movie.addReview(this);

    this.user = user;
    this.movie = movie;
    this.rating = rating;
    this.comment = comment;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Review other = (Review) obj;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    if (movie == null) {
      if (other.movie != null)
        return false;
    } else if (!movie.equals(other.movie))
      return false;
    return true;
  }
}
