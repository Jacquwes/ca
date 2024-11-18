import java.io.IOException;

/**
 * Test class for application functionality.
 *
 * @author Florent Delalande
* @author Paul Hariel
 *
 */
public class MainLocation {

  /**
   * If main() executes, it means the project is functional.
   *
   * @param args not used here.
   */
  public static void main(String[] args) {
    System.out.println("\nPress Enter to terminate the program ...");
    try {
      System.in.read();
    } catch (IOException e) {
      System.err.println("You managed to break the keyboard: " + e);
    }
    System.out.println("... terminated");
  }
}
