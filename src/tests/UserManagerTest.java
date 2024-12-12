package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import location.*;

/**
 * Test class for UserManager to validate all methods and behaviors.
 */
public class UserManagerTest {
    private UserManager userManager;
    private PersonalInformation personalInfo;

    /**
     * Set up test data before each test method.
     */
    @Before
    public void setUp() {
        userManager = new UserManager();
        personalInfo = new PersonalInformation("John", "Doe", "adress", 30);
    }

    /**
     * Tests user registration with valid data.
     */
    @Test
    public void testRegisterValidUser() {
        int result = userManager.register("johndoe", "password", personalInfo);
        assertEquals(0, result);
    }

    /**
     * Tests user registration with duplicate username.
     */
    @Test
    public void testRegisterDuplicateUsername() {
        userManager.register("johndoe", "password", personalInfo);
        int result = userManager.register("johndoe", "anotherpassword", personalInfo);
        assertEquals(1, result);
    }

    /**
     * Tests user registration with empty username or password.
     */
    @Test
    public void testRegisterEmptyCredentials() {
        int result1 = userManager.register("", "", personalInfo);
        assertEquals(2, result1);

        int result2 = userManager.register(null, null, personalInfo);
        assertEquals(2, result2);
    }

    /**
     * Tests user registration with null personal information.
     */
    @Test
    public void testRegisterNullPersonalInfo() {
        int result = userManager.register("johndoe", "password", null);
        assertEquals(3, result);
    }

    /**
     * Tests user login with valid credentials.
     */
    @Test
    public void testLoginValidCredentials() {
        userManager.register("johndoe", "password", personalInfo);
        assertTrue(userManager.login("johndoe", "password"));
    }

    /**
     * Tests user login with invalid credentials.
     */
    @Test
    public void testLoginInvalidCredentials() {
        userManager.register("johndoe", "password", personalInfo);
        assertFalse(userManager.login("johndoe", "wrongpassword"));
        assertFalse(userManager.login("wronguser", "password"));
    }

    /**
     * Tests logout functionality.
     */
    @Test
    public void testLogout() {
        userManager.register("johndoe", "password", personalInfo);
        userManager.login("johndoe", "password");
        assertNotNull(userManager.getCurrentUser());
        try{
            userManager.logout();
        }
        catch(NotLoggedInException e){
            fail("NotLoggedInException should not be thrown.");
        }
        assertNull(userManager.getCurrentUser());
    }

    /**
     * Tests logout when no user is logged in.
     */
    @Test
    public void testLogoutWithNoCurrentUser() {
        assertThrows(NotLoggedInException.class, () -> userManager.logout());
    }

    /**
     * Tests reset users functionality.
     */
    @Test
    public void testResetUsers() {
        userManager.register("johndoe", "password", personalInfo);
        userManager.resetUsers();
        userManager.register("newuser", "newpassword", personalInfo);
        assertTrue(userManager.login("newuser", "newpassword"));
        assertFalse(userManager.login("johndoe", "password"));
    }
}
