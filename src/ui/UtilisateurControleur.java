package ui;

import location.Artist;
import location.Genre;
import location.Location;
import location.Movie;
import location.NotLoggedInException;
import location.PersonalInformation;
import location.Review;

import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * JavaFX Controller for the user window.
 *
 * @author Eric Cariou
 *
 */
public class UtilisateurControleur {

  @FXML
  private CheckBox checkFilmLouable;

  @FXML
  private TextField entreeAdresseUtilisateur;

  @FXML
  private TextField entreeAgeLimiteFilm;

  @FXML
  private TextField entreeAgeUtilisateur;

  @FXML
  private TextField entreeAnneeFilm;

  @FXML
  private TextField entreeAuteurEvaluation;

  @FXML
  private TextField entreeEvaluationMoyenne;

  @FXML
  private TextField entreeGenresFilm;

  @FXML
  private TextField entreeMotDePasseUtilisateur;

  @FXML
  private TextField entreeNationaliteArtiste;

  @FXML
  private TextField entreeNomArtiste;

  @FXML
  private TextField entreeNomPrenomRealisateurFilm;

  @FXML
  private TextField entreeNomUtilisateur;

  @FXML
  private TextField entreePrenomArtiste;

  @FXML
  private TextField entreePrenomUtilisateur;

  @FXML
  private TextField entreePseudoUtilisateur;

  @FXML
  private TextField entreeTitreFilm;

  @FXML
  private Label labelListeFilms;

  @FXML
  private Label labelListeArtistes;

  @FXML
  private ListView<String> listeArtistes;

  @FXML
  private ListView<String> listeEvaluations;

  @FXML
  private ListView<String> listeFilms;

  @FXML
  private ListView<String> listeFilmsEnLocation;

  @FXML
  private ListView<String> listeGenresFilm;

  @FXML
  private ChoiceBox<Integer> listeNoteEvaluation;

  @FXML
  private TextArea texteCommentaire;

  @FXML
  private StackPane paneAffiche;

  private Location location;

  public UtilisateurControleur(Location location) {
    this.location = location;
  }

  /**
   * Returns the movie selected in the movie list.
   *
   * @return the movie selected in the movie list.
   */
  private Movie getSelectedMovie() {
    // Returns the selected movie from the movie list
    String selected = listeFilms.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    return location.getMovie(selected);
  }

  /**
   * Returns the movie selected in the rented movies list.
   *
   * @return the movie selected in the rented movies list.
   */
  private Movie getSelectedRentedMovie() {
    // Returns the selected movie from the rented movies list
    String selected = listeFilmsEnLocation.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    return location.getMovie(selected);
  }

  /**
   * Returns the artist selected in the artists list.
   *
   * @return the artist selected in the artists list.
   */
  private Artist getSelectedActor() {
    // Returns the selected artist from the artists list
    String selected = listeArtistes.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    String[] parts = selected.split(" ");
    return location.getActor(parts[0], parts[1]);
  }

  /**
   * Returns the artist selected in the artists list.
   *
   * @return the artist selected in the artists list.
   */
  private Artist getSelectedDirector() {
    // Returns the selected artist from the artists list
    String selected = listeArtistes.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    String[] parts = selected.split(" ");
    return location.getDirector(parts[0], parts[1]);
  }

  /**
   * Returns the genre selected in the genres list.
   *
   * @return the genre selected in the genres list.
   */
  private Genre getSelectedGenre() {
    // Returns the selected genre from the genres list
    String selected = listeGenresFilm.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return null;
    }
    return Genre.valueOf(selected);
  }

  /**
   * Action of the "Movie->Show movie actors" button.
   *
   * @param event the button click event.
   */
  @FXML
  void actionBoutonAfficherActeursFilmSelectionne(ActionEvent event) {
    // Clear the artists list
    this.listeArtistes.getItems().clear();
    // Get the selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Add the actors of the selected movie to the artists list
    movie.getActors().forEach(actor -> this.listeArtistes.getItems().add(actor.toString()));

    // Display the artists list
    this.labelListeArtistes.setText("Actors of the movie " + movie.getTitle());
  }

  /**
   * Action of the "Artist->Show in list->Actors" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    // Clear the artists list
    this.listeArtistes.getItems().clear();
    // Get the actors
    location.allActors().forEach(actor -> this.listeArtistes.getItems().add(actor.toString()));

    // Display the artists list
    this.labelListeArtistes.setText("All actors");
  }

  /**
   * Action of the "Artist->Show in list->Directors" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    // Clear the artists list
    this.listeArtistes.getItems().clear();
    // Get the directors
    location.allDirectors().forEach(director -> this.listeArtistes.getItems().add(director.toString()));

    // Display the artists list
    this.labelListeArtistes.setText("All directors");
  }

  /**
   * Action of the "User->Show details of selected movie" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmLoue(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedRentedMovie();
    if (movie == null) {
      return;
    }

    // Update fields
    this.entreeTitreFilm.setText(movie.getTitle());
    this.entreeAnneeFilm.setText(String.valueOf(movie.getYear()));
    this.entreeAgeLimiteFilm.setText(String.valueOf(movie.getMinimumAge()));
    this.entreeNomPrenomRealisateurFilm.setText(movie.getDirector().toString());
    this.entreeGenresFilm
        .setText(String.join(", ", movie.getGenres().stream().map(Genre::toString).toArray(String[]::new)));
    this.checkFilmLouable.setSelected(movie.isAvailable());
  }

  /**
   * Action of the "Artist->Show movies of selected artist as->Director" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmRealisateurSelectionne(ActionEvent event) {
    // Clear the movies list
    this.listeFilms.getItems().clear();
    // Get the selected director
    Artist director = this.getSelectedDirector();
    if (director == null) {
      return;
    }

    // Add the movies of the selected director to the movies list
    director.getMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Display the movies list
    this.labelListeFilms.setText("Movies of director " + director.toString());
  }

  /**
   * Action of the "Artist->Show movies of selected artist as->Actor" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    // Clear the movies list
    this.listeFilms.getItems().clear();
    // Get the selected actor
    Artist actor = this.getSelectedActor();
    if (actor == null) {
      return;
    }

    // Add the movies of the selected actor to the movies list
    actor.getMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Display the movies list
    this.labelListeFilms.setText("Movies of actor " + actor.toString());
  }

  /**
   * Action of the "Movie->Show movie genres" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmsGenre(ActionEvent event) {
    // Clear the movies list
    this.listeFilms.getItems().clear();
    // Get the selected genre
    Genre genre = getSelectedGenre();

    // Add the movies of the selected genre to the movies list
    location.moviesByGenre(genre).forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Display the movies list
    this.labelListeFilms.setText("Movies of genre " + genre);
  }

  /**
   * Action of the "Artist->Show director movies" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    // Clear the movies list
    this.listeFilms.getItems().clear();
    // Get the selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }
    Artist director = movie.getDirector();
    if (director == null) {
      return;
    }

    // Add the movies of the selected director to the movies list
    director.getMovies().forEach(film -> this.listeFilms.getItems().add(film.getTitle()));

    // Display the movies list
    this.labelListeFilms.setText("Movies of director " + director.toString());
  }

  /**
   * Action of the "Review->Show my review" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherMonEvaluation(ActionEvent event) {
    // Clear fields
    this.listeNoteEvaluation.setValue(null);
    this.texteCommentaire.clear();
    this.entreeAuteurEvaluation.clear();

    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Get and display review of the user
    movie.getReviews().stream()
        .filter(review -> review.getUser().toString().equals(this.entreeAuteurEvaluation.getText()))
        .findFirst()
        .ifPresent(review -> {
          this.listeNoteEvaluation.setValue((int) review.getRating());
          this.texteCommentaire.setText(review.getComment());
          this.entreeAuteurEvaluation.setText(review.getUser().toString());
        });
  }

  /**
   * Action of the "Artist->Show in list->All artists" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    // Clear the artists list
    this.listeArtistes.getItems().clear();

    // Add artists to the artists list
    this.location.allArtists().forEach(artist -> this.listeArtistes.getItems().add(artist.toString()));

    // Display the artists list
    this.labelListeArtistes.setText("All artists");
  }

  /**
   * Action of the "Movie->Show all movies" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonAfficherTousFilms(ActionEvent event) {
    // Clear the movies list
    this.listeFilms.getItems().clear();
    // Get all movies
    location.allMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Display the movies list
    this.labelListeFilms.setText("All movies");
  }

  /**
   * Action of the "Artist->Search->An actor" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonChercherActeur(ActionEvent event) {
    // Clear the artists list
    this.listeArtistes.getItems().clear();
    // Get the actor
    Artist actor = location.getActor(this.entreeNomArtiste.getText(), this.entreePrenomArtiste.getText());
    if (actor == null) {
      return;
    }

    // Add the actor to the artists list
    this.listeArtistes.getItems().add(actor.toString());

    // Display the artists list
    this.labelListeArtistes.setText("Actor " + actor.toString());

    // Update artist fields
    this.entreeNationaliteArtiste.setText(actor.getNationality());
  }

  /**
   * Action of the "Movie->Search a movie" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    // Clear the movies list
    this.listeFilms.getItems().clear();
    // Get the movie
    Movie movie = location.getMovie(this.entreeTitreFilm.getText());
    if (movie == null) {
      return;
    }

    // Add the movie to the movies list
    this.listeFilms.getItems().add(movie.getTitle());

    // Display the movies list
    this.labelListeFilms.setText("Movie " + movie.getTitle());

    // Update movie fields
    this.entreeAnneeFilm.setText(String.valueOf(movie.getYear()));
    this.entreeAgeLimiteFilm.setText(String.valueOf(movie.getMinimumAge()));
    this.entreeNomPrenomRealisateurFilm.setText(movie.getDirector().toString());
    this.entreeGenresFilm
        .setText(String.join(", ", movie.getGenres().stream().map(Genre::toString).toArray(String[]::new)));
    this.checkFilmLouable.setSelected(movie.isAvailable());
  }

  /**
   * Action of the "Artist->Search->A director" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonChercherRealisateur(ActionEvent event) {
    // Clear the artists list
    this.listeArtistes.getItems().clear();
    // Get the director
    Artist director = location.getDirector(this.entreeNomArtiste.getText(), this.entreePrenomArtiste.getText());
    if (director == null) {
      return;
    }

    // Add the director to the artists list
    this.listeArtistes.getItems().add(director.toString());

    // Display the artists list
    this.labelListeArtistes.setText("Director " + director.toString());

    // Update artist fields
    this.entreeNationaliteArtiste.setText(director.getNationality());
  }

  /**
   * Action of the "User->Login" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonConnexion(ActionEvent event) {
    String username = this.entreePseudoUtilisateur.getText();
    String password = this.entreeMotDePasseUtilisateur.getText();

    if (!this.location.login(username, password)) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Incorrect username or password.").showAndWait();
      return;
    }

    // Populate the rented movies list
    this.listeFilmsEnLocation.getItems().clear();
    try {
      this.location.rentedMovies().forEach(movie -> this.listeFilmsEnLocation.getItems().add(movie.getTitle()));
    } catch (Exception e) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Error while retrieving rented movies.").showAndWait();
    }

    // Update user fields
    this.entreeNomUtilisateur.setText(this.location.getCurrentUser().getPersonalInformation().getLastName());
    this.entreePrenomUtilisateur.setText(this.location.getCurrentUser().getPersonalInformation().getFirstName());
    this.entreeAdresseUtilisateur.setText(this.location.getCurrentUser().getPersonalInformation().getAddress());
    this.entreeAgeUtilisateur.setText(String.valueOf(this.location.getCurrentUser().getPersonalInformation().getAge()));
  }

  /**
   * Action of the "Review->Create my review" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonCreerMonEvaluation(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Create review
    Review review = new Review();
    review.setRating(this.listeNoteEvaluation.getValue());
    review.setComment(this.texteCommentaire.getText());
    review.setUser(this.location.getCurrentUser());

    // Add review to movie
    movie.addReview(review);

    // Clear fields
    this.listeNoteEvaluation.setValue(null);
    this.texteCommentaire.clear();
    this.entreeAuteurEvaluation.clear();

    // Update average rating
    this.entreeEvaluationMoyenne
        .setText(String.valueOf(movie.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0)));

    // Update list of reviews
    this.listeEvaluations.getItems().clear();
    movie.getReviews().forEach(r -> this.listeEvaluations.getItems().add(r.toString()));
  }

  /**
   * Action of the "User->Logout" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {
    try {
      this.location.logout();
    } catch (NotLoggedInException e) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "You are not logged in.").showAndWait();
      return;
    }

    // Clear fields
    this.entreeNomUtilisateur.clear();
    this.entreePrenomUtilisateur.clear();
    this.entreeAdresseUtilisateur.clear();
    this.entreeAgeUtilisateur.clear();
    this.entreePseudoUtilisateur.clear();
    this.entreeMotDePasseUtilisateur.clear();
  }

  /**
   * Action of the "User->End rental of selected movie" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonFinLocation(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedRentedMovie();
    if (movie == null) {
      return;
    }

    // Close rental
    try {
      this.location.endMovieRental(movie);
    } catch (Exception e) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Error while closing the rental.").showAndWait();
      return;
    }

    // Update list of rented movies
    this.listeFilmsEnLocation.getItems().clear();
    try {
      this.location.rentedMovies().forEach(m -> this.listeFilmsEnLocation.getItems().add(m.getTitle()));
    } catch (Exception e) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Error while retrieving rented movies.").showAndWait();
    }
  }

  /**
   * Action of the "User->Register" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonInscription(ActionEvent event) {
    String username = this.entreePseudoUtilisateur.getText();
    String password = this.entreeMotDePasseUtilisateur.getText();
    String lastName = this.entreeNomUtilisateur.getText();
    String firstName = this.entreePrenomUtilisateur.getText();
    String address = this.entreeAdresseUtilisateur.getText();
    int age = Integer.parseInt(this.entreeAgeUtilisateur.getText());

    int result = this.location.register(username, password, new PersonalInformation(lastName, firstName, address, age));

    if (result == 0) {
      // Display success message
      new Alert(Alert.AlertType.INFORMATION, "User created successfully. You can now log in.")
          .showAndWait();
    } else if (result == 1) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Username already taken.").showAndWait();
    } else if (result == 2) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").showAndWait();
    } else if (result == 3) {
      new Alert(Alert.AlertType.ERROR, "Please fill in the fields correctly.").showAndWait();
    }
  }

  /**
   * Action of the "Movie->Rent selected movie" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonLouerFilmSelectionne(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Rent movie
    try {
      this.location.rentMovie(movie);
    } catch (Exception e) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Error while renting the movie.").showAndWait();
      return;
    }

    // Update list of rented movies
    this.listeFilmsEnLocation.getItems().clear();
    try {
      this.location.rentedMovies().forEach(m -> this.listeFilmsEnLocation.getItems().add(m.getTitle()));
    } catch (Exception e) {
      // Display error message
      new Alert(Alert.AlertType.ERROR, "Error while retrieving rented movies.").showAndWait();
    }
  }

  /**
   * Action of the "Modify my review" button.
   *
   * @param event
   */
  @FXML
  void actionBoutonModifierMonEvaluation(ActionEvent event) {
    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Get and update review of the user
    movie.getReviews().stream()
        .filter(review -> review.getUser().toString().equals(this.entreeAuteurEvaluation.getText()))
        .findFirst()
        .ifPresent(review -> {
          review.setRating(this.listeNoteEvaluation.getValue());
          review.setComment(this.texteCommentaire.getText());
        });

    // Clear fields
    this.listeNoteEvaluation.setValue(null);
    this.texteCommentaire.clear();
    this.entreeAuteurEvaluation.clear();

    // Update average rating
    this.entreeEvaluationMoyenne
        .setText(String.valueOf(movie.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0)));

    // Update list of reviews
    this.listeEvaluations.getItems().clear();
    movie.getReviews().forEach(r -> this.listeEvaluations.getItems().add(r.toString()));
  }

  /**
   * Action of selecting an artist.
   *
   * @param event
   */
  @FXML
  void actionSelectionArtiste(MouseEvent event) {
    // Clear the movies list
    this.listeFilms.getItems().clear();
    // Get the selected artist
    Artist artist = this.getSelectedActor();
    if (artist == null) {
      return;
    }

    // Add the movies of the selected artist to the movies list
    artist.getMovies().forEach(movie -> this.listeFilms.getItems().add(movie.getTitle()));

    // Display the movies list
    this.labelListeFilms.setText("Movies of artist " + artist.toString());
  }

  /**
   * Action of selecting a review.
   *
   * @param event
   */
  @FXML
  void actionSelectionEvaluation(MouseEvent event) {
    // Get the selected review
    String selected = this.listeEvaluations.getSelectionModel().getSelectedItem();
    if (selected == null) {
      return;
    }
    String[] parts = selected.split(" ");
    this.entreeAuteurEvaluation.setText(parts[0] + " " + parts[1]);

    // Get selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Get and display review of the user
    movie.getReviews().stream()
        .filter(review -> review.getUser().toString().equals(this.entreeAuteurEvaluation.getText()))
        .findFirst()
        .ifPresent(review -> {
          this.listeNoteEvaluation.setValue((int) review.getRating());
          this.texteCommentaire.setText(review.getComment());
        });
  }

  /**
   * Action of selecting a movie.
   *
   * @param event
   */
  @FXML
  void actionSelectionFilm(MouseEvent event) {
    // Clear the reviews list
    this.listeEvaluations.getItems().clear();
    // Get the selected movie
    Movie movie = this.getSelectedMovie();
    if (movie == null) {
      return;
    }

    // Add the reviews of the selected movie to the reviews list
    movie.getReviews().forEach(review -> this.listeEvaluations.getItems().add(review.toString()));
  }

  @FXML
  void initialize() {
    // Put here the initialization code for the window content
  }
}