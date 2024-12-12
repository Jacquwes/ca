package tests;

import location.Artist;
import location.ArtistManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Test class for the ArtistManager class.
 */
public class ArtistManagerTest {
    private ArtistManager artistManager;
    private Artist actor1;
    private Artist director1;

    /**
     * Set up method to initialize test objects before each test.
     */
    @Before
    public void setUp() {
        artistManager = new ArtistManager();
        actor1 = new Artist("Doe", "John", "American");
        director1 = new Artist("Smith", "Jane", "British");
    }

    /**
     * Test adding an artist.
     */
    @Test
    public void testAddArtist() {
        assertTrue(artistManager.addArtist(actor1));
        assertTrue(artistManager.getArtists().contains(actor1));
    }

    /**
     * Test adding an actor.
     */
    @Test
    public void testAddActor() {
        artistManager.addActor(actor1);
        assertTrue(artistManager.getActors().contains(actor1));
        assertTrue(artistManager.getArtists().contains(actor1));
    }

    /**
     * Test adding a director.
     */
    @Test
    public void testAddDirector() {
        artistManager.addDirector(director1);
        assertTrue(artistManager.getDirectors().contains(director1));
        assertTrue(artistManager.getArtists().contains(director1));
    }

    /**
     * Test deleting an artist.
     */
    @Test
    public void testDeleteArtist() {
        artistManager.addActor(actor1);
        assertTrue(artistManager.deleteArtist(actor1));
        assertFalse(artistManager.getActors().contains(actor1));
        assertFalse(artistManager.getArtists().contains(actor1));
    }

    /**
     * Test getting artists with a predicate.
     */
    @Test
    public void testGetArtistsWithPredicate() {
        artistManager.addActor(actor1);
        artistManager.addDirector(director1);

        Set<Artist> filteredArtists = artistManager.getArtists(artist -> 
            artist.getNationality().equals("American"));

        assertEquals(1, filteredArtists.size());
        assertTrue(filteredArtists.contains(actor1));
    }

    /**
     * Test serialization and parsing.
     */
    @Test
    public void testSerializeAndParse() {
        artistManager.addActor(actor1);
        artistManager.addDirector(director1);

        String serialized = artistManager.serialize();
        
        ArtistManager newManager = new ArtistManager();
        newManager.parse(serialized);

        assertEquals(2, newManager.getArtists().size());
        assertEquals(1, newManager.getActors().size());
        assertEquals(1, newManager.getDirectors().size());
    }
}
