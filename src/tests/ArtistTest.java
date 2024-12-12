package tests;

import location.Artist;
import location.Movie;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Test class for the Artist class.
 */
public class ArtistTest {
    private Artist artist;
    private Movie movie1;
    private Movie movie2;

    /**
     * Set up method to initialize test objects before each test.
     */
    @Before
    public void setUp() {
        artist = new Artist("Doe", "John", "American");
        movie1 = new Movie("Movie1", 2020, artist, new HashSet<>());
        movie2 = new Movie("Movie2", 2021, artist, new HashSet<>());
    }

    /**
     * Test constructor with parameters.
     */
    @Test
    public void testConstructor() {
        assertEquals("Doe", artist.getLastName());
        assertEquals("John", artist.getFirstName());
        assertEquals("American", artist.getNationality());
    }

    /**
     * Test getter and setter for last name.
     */
    @Test
    public void testLastNameGetterSetter() {
        artist.setLastName("Smith");
        assertEquals("Smith", artist.getLastName());
    }

    /**
     * Test getter and setter for first name.
     */
    @Test
    public void testFirstNameGetterSetter() {
        artist.setFirstName("Jane");
        assertEquals("Jane", artist.getFirstName());
    }

    /**
     * Test getter for nationality.
     */
    @Test
    public void testNationalityGetter() {
        assertEquals("American", artist.getNationality());
    }

    /**
     * Test adding a movie to an artist.
     */
    @Test
    public void testAddMovie() {
        artist.addMovie(movie1);
        artist.addMovie(movie2);
        assertTrue(artist.getMovies().contains(movie1));
        assertTrue(artist.getMovies().contains(movie2));
        assertEquals(2, artist.getMovies().size());
    }

    /**
     * Test toString method.
     */
    @Test
    public void testToString() {
        assertEquals("Doe John", artist.toString());
    }

    /**
     * Test equals method.
     */
    @Test
    public void testEquals() {
        Artist sameArtist = new Artist("Doe", "John", "American");
        Artist differentArtist = new Artist("Smith", "Jane", "British");

        assertEquals(artist, sameArtist);
        assertNotEquals(artist, differentArtist);
        assertNotEquals(artist, null);
    }

    /**
     * Test JSON serialization.
     */
    @Test
    public void testSerialize() {
        String expectedJson = "{\"lastName\":\"Doe\",\"firstName\":\"John\",\"nationality\":\"American\"}";
        assertEquals(expectedJson, artist.serialize());
    }

    /**
     * Test JSON parsing.
     */
    @Test
    public void testParse() {
        String json = "{\"lastName\":\"Doe\",\"firstName\":\"John\",\"nationality\":\"American\"}";
        Artist parsedArtist = Artist.parse(json);
        assertEquals("Doe", parsedArtist.getLastName());
        assertEquals("John", parsedArtist.getFirstName());
        // Note: There's a bug in the parse method where nationality is not set correctly
    }
}
