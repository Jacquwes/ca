package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import location.*;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Test class for ReviewManager to validate all methods and behaviors.
 */
public class ReviewManagerTest {
    private ReviewManager reviewManager;
    private User testUser;
    private Movie testMovie;
    private Review testReview;

    /**
     * Set up test data before each test method.
     */
    @Before
    public void setUp() {
        reviewManager = new ReviewManager();
        PersonalInformation personalInfo = new PersonalInformation("John", "Doe", "adress", 30);
        testUser = new User("johndoe", "password", personalInfo);
        testMovie = new Movie("Inception", 2010, new Artist(), null);
        testReview = new Review(testUser, testMovie, 4.5, "Great movie!");
        reviewManager.add(testReview);
    }

    /**
     * Tests add method with user, movie, rating, and comment parameters.
     */
    @Test
    public void testAddReviewWithParameters() {
        Set<Review> reviews = reviewManager.getReviews(r -> r.getUser() == testUser && r.getMovie() == testMovie);
        assertFalse(reviews.isEmpty());
    }

    /**
     * Tests add method with Review object.
     */
    @Test
    public void testAddReviewObject() {
        reviewManager.add(testReview);
        Set<Review> reviews = reviewManager.getReviews(r -> r.getUser() == testUser && r.getMovie() == testMovie);
        assertFalse(reviews.isEmpty());
    }

    /**
     * Tests that adding a duplicate review throws an exception.
     */
    @Test
    public void testAddDuplicateReview() {
        assertThrows(IllegalArgumentException.class, () -> reviewManager.add(testUser, testMovie, 4.0, "Another comment"));
    }

    /**
     * Tests getReviews method with a custom predicate.
     */
    @Test
    public void testGetReviewsWithPredicate() {
        reviewManager.add(testReview);
        
        Predicate<Review> predicate = r -> r.getRating() > 4.0;
        Set<Review> filteredReviews = reviewManager.getReviews(predicate);
        
        assertFalse(filteredReviews.isEmpty());
        assertTrue(filteredReviews.contains(testReview));
    }
}
