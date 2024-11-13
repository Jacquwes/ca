package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import location.Movie;
import location.PersonalInformation;
import location.Review;
import location.User;

public class ReviewTest {
  Movie movie;
  Review review;
  User user;

  @BeforeEach
  void setUp() {
    movie = new Movie("Interstellar", 2014, null, null);
    review = new Review();
    user = new User("login",
        "password",
        new PersonalInformation("last", "first"));
  }

  @Test
  void testEquals() {
    Review review2 = new Review();
    review.setUser(user);
    review2.setUser(user);
    review.setMovie(movie);
    review2.setMovie(movie);
    Assertions.assertEquals(review, review2);

    review.setUser(null);
    Assertions.assertNotEquals(review, review2);
  }

  @Test
  void testComment() {
    review.setComment("This is a comment");
    Assertions.assertEquals("This is a comment", review.getComment());
  }

  @Test
  void testMovie() {
    review.setMovie(movie);
    Assertions.assertEquals(movie, review.getMovie());
  }

  @Test
  void testRating() {
    review.setRating(5);
    Assertions.assertEquals(5, review.getRating());
  }

  @Test
  void testUser() {
    review.setUser(user);
    Assertions.assertEquals(user, review.getUser());
  }
}
