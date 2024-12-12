package ui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import location.ArtistManager;
import location.Location;
import location.LocationAdmin;
import location.MovieManager;
import location.ReservationManager;
import location.ReviewManager;
import location.UserManager;

/**
 * Classe qui lance l'interface graphique de l'application.
 *
 * @author Eric Cariou
 */
public final class MainInterface extends Application {

  /**
   * Affiche la fenêtre de l'utilisateur pour louer des films.
   */
  public void startFenetreEtudiants(Location location) {
    try {
      URL url = getClass().getResource("utilisateur.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      fxmlLoader.setControllerFactory(c -> new UtilisateurControleur(location));
      VBox root = (VBox) fxmlLoader.load();

      Scene scene = new Scene(root, 1200, 650);

      Stage stage = new Stage();
      stage.setResizable(true);
      stage.setTitle("Location de films");

      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      System.err.println("Erreur au chargement de la fenêtre utilisateur : " + e);
    }
  }

  /**
   * Affiche la fenêtre d'administration des films.
   *
   * @param primaryStage le paramètre passé par JavaFX pour la fenêtre
   *                     principale
   */
  public void startFenetreFormation(Stage primaryStage, LocationAdmin locationAdmin) {
    try {
      URL url = getClass().getResource("administration.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      fxmlLoader.setControllerFactory(c -> new AdministrationControleur(locationAdmin));
      VBox root = (VBox) fxmlLoader.load();

      Scene scene = new Scene(root, 615, 725);

      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Administration des films");
      primaryStage.show();

    } catch (IOException e) {
      System.err.println("Erreur au chargement de la fenêtre administration : " + e);
    }
  }

  @Override
  public void start(Stage primaryStage) {

    ArtistManager artistManager = new ArtistManager();
    MovieManager movieManager = new MovieManager();
    ReservationManager reservationManager = new ReservationManager();
    ReviewManager reviewManager = new ReviewManager();
    UserManager userManager = new UserManager();

    Location location = new Location(
        artistManager,
        movieManager,
        reservationManager,
        reviewManager,
        userManager);
    LocationAdmin locationAdmin = new LocationAdmin(
        artistManager,
        movieManager,
        reservationManager,
        reviewManager,
        userManager);

    // Lancement des 2 fenêtres de l'application
    this.startFenetreFormation(primaryStage, locationAdmin);
    this.startFenetreEtudiants(location);

    // Rajouter ici du code si besoin ou avant le lancement des fenêtres
  }

  /**
   * Méthode principale de l'application.
   *
   * @param args les arguments passés en ligne de commande
   */
  public static void main(String[] args) {
    launch(args);
  }
}
