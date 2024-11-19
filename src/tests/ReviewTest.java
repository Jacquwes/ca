package tests;

import location.Movie;
import location.PersonalInformation;
import location.Review;
import location.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Review class functionality.
 */
public class ReviewTest {
  /**
   * Test instance of Movie class.
   */
  Movie movie;

  /**
   * Test instance of Review class.
   */
  Review review;
  
  /**
   * Test instance of User class.
   */
  User user;

  /**
   * Sets up the test environment before each test.
   */
  @BeforeEach
  void setUp() {
    movie = new Movie("Interstellar", 2014, null, null);
    review = new Review();
    user = new User("login",
        "password",
        new PersonalInformation("last", "first"));
  }

  /**
   * Tests the equals method of Review class.
   */
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

  /**
   * Tests the comment method of Review class.
   */
  @Test
  void testComment() {
    review.setComment("This is a comment");
    Assertions.assertEquals("This is a comment", review.getComment());
  }

  /**
   * Tests the movie of Review class.
   */
  @Test
  void testMovie() {
    review.setMovie(movie);
    Assertions.assertEquals(movie, review.getMovie());
  }

  /**
   * Tests the rating of Review class.
   */
  @Test
  void testRating() {
    review.setRating(5);
    Assertions.assertEquals(5, review.getRating());
  }

  /**
   * Tests the user of Review class.
   */
  @Test
  void testUser() {
    review.setUser(user);
    Assertions.assertEquals(user, review.getUser());
  }
}
