package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import location.*;

/**
 * Test class for Review to validate all methods and behaviors.
 */
public class ReviewTest {
    private User testUser;
    private Movie testMovie;
    private Review testReview;

    /**
     * Set up test data before each test method.
     */
    @Before
    public void setUp() {
        PersonalInformation personalInfo = new PersonalInformation("John", "Doe", "adress", 30);
        testUser = new User("johndoe", "password", personalInfo);
        testMovie = new Movie("Inception", 2010, new Artist(), null);
        testReview = new Review(testUser, testMovie, 4.5, "Great movie!");
    }

    /**
     * Tests default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        Review emptyReview = new Review();
        assertNull(emptyReview.getUser());
        assertNull(emptyReview.getMovie());
        assertEquals(0.0, emptyReview.getRating(), 0.001);
        assertNull(emptyReview.getComment());
    }

    /**
     * Tests parameterized constructor and getter methods.
     */
    @Test
    public void testParameterizedConstructor() {
        assertEquals(testUser, testReview.getUser());
        assertEquals(testMovie, testReview.getMovie());
        assertEquals(4.5, testReview.getRating(), 0.001);
        assertEquals("Great movie!", testReview.getComment());
    }

    /**
     * Tests setter methods.
     */
    @Test
    public void testSetters() {
        Review review = new Review();
        review.setUser(testUser);
        review.setMovie(testMovie);
        review.setRating(3.5);
        review.setComment("Updated comment");

        assertEquals(testUser, review.getUser());
        assertEquals(testMovie, review.getMovie());
        assertEquals(3.5, review.getRating(), 0.001);
        assertEquals("Updated comment", review.getComment());
    }

    /**
     * Tests equals method with equal and non-equal reviews.
     */
    @Test
    public void testEquals() {
        Review sameReview = new Review(testUser, testMovie, 4.5, "Great movie!");
        Review differentReview = new Review(testUser, new Movie("Interstellar", 2014, new Artist(), null),3.5, "Okay movie");

        assertEquals(testReview, sameReview);
        assertNotEquals(testReview, differentReview);
        assertNotEquals(testReview, null);
    }
}
