package tests;

import static org.junit.Assert.*;

import java.util.Set;
import location.Artist; 
import location.Movie;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Artist class functionality.
 * Tests the creation, modification, and retrieval of Artist objects.
 */
public class ArtistTest {
  /**
   * Test instance of Artist class.
   */
  private Artist artist;
  
  /**
   * Second test instance of Artist class.
   */
  private Artist artist2;
  
  /**
   * Sets up the test environment before each test.
   * Clears all existing artists and creates two test Artist instances.
   */
  @Before
  public void setUp() {
    Artist.getArtists().clear();
    artist = new Artist("Doe", "John");
    artist2 = new Artist("Smith", "Jane");
  }
  
  /**
   * Tests the parameterized constructor of Artist class.
   * Verifies correct initialization of lastName, firstName and addition to artists collection.
   */
  @Test
  public void testConstructor() {
    assertEquals("Doe", artist.getLastName());
    assertEquals("John", artist.getFirstName());
    assertTrue(Artist.getArtists().contains(artist));
  }
  
  /**
   * Tests the default constructor of Artist class.
   * Verifies that firstName and lastName are null and artist is added to collection.
   */
  @Test
  public void testDefaultConstructor() {
    Artist emptyArtist = new Artist();
    assertNull(emptyArtist.getLastName());
    assertNull(emptyArtist.getFirstName());
    assertTrue(Artist.getArtists().contains(emptyArtist));
  }
  
  /**
   * Tests the setter methods of Artist class.
   * Verifies the fluent interface pattern and correct value assignment.
   */
  @Test
  public void testSetters() {
    artist.setLastName("Brown").setFirstName("Bob");
    assertEquals("Brown", artist.getLastName());
    assertEquals("Bob", artist.getFirstName());
  }
  
  /**
   * Tests the movie-related operations of Artist class.
   * Verifies adding movies and retrieving movie collections.
   */
  @Test
  public void testMovies() {
    Movie movie = new Movie();
    assertTrue(artist.getMovies().isEmpty());
    artist.addMovie(movie);
    assertTrue(artist.getMovies().contains(movie));
    assertEquals(1, artist.getMovies().size());
  }
  
  /**
   * Tests the static getArtists method.
   * Verifies that all created artists are properly stored in the collection.
   */
  @Test
  public void testGetArtists() {
    assertEquals(2, Artist.getArtists().size());
    assertTrue(Artist.getArtists().contains(artist));
    assertTrue(Artist.getArtists().contains(artist2));
  }
  
  /**
   * Tests the filtered getArtists method with predicate.
   * Verifies filtering functionality based on lastName.
   */
  @Test
  public void testGetArtistsWithPredicate() {
    Set<Artist> filtered = Artist.getArtists(a -> a.getLastName().equals("Doe"));
    assertEquals(1, filtered.size());
    assertTrue(filtered.contains(artist));
  }
}