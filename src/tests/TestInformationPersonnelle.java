package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import location.PersonalInformation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link location.PersonalInformation
 * PersonalInformation}.
 *
 * @author Eric Cariou
 * @see location.PersonalInformation
 */
class TestInformationPersonnelle {
  
  /**
   * Une information basique : prénom et lastName.
   */
  private PersonalInformation infoBasique;
  /**
   * Une information complète : prénom, lastName, address et âge.
   */
  private PersonalInformation infoComplete;
  
  /**
   * Instancie une information basique et une complète pour les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    infoBasique = new PersonalInformation("Skywalker", "Luke");
    infoComplete =
        new PersonalInformation("Skywalker", "Luke", "Planète Tatooine", 20);
  }
  
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Vérifie que l'on peut positionner un êge de 25 ans.
   */
  @Test
  void testAge25Basique() {
    infoBasique.setAge(25);
    assertEquals(infoBasique.getAge(), 25);
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner un âge négatif sur une information
   * basique.
   */
  @Test
  void testAgeNegatifBasique() {
    infoBasique.setAge(-20);
    assertTrue(infoBasique.getAge() != -20);
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner un age négatif sur une information
   * complàte : l'âge reste le même qu'avant.
   */
  @Test
  void testAgeNegatifComplet() {
    int age = infoComplete.getAge();
    infoComplete.setAge(-20);
    assertEquals(infoComplete.getAge(), age);
  }
  
  
  /**
   * Vérifie qu'une address n'est pas null quand on crée une information
   * personnelle.
   */
  @Test
  void testAdresseNonNull() {
    assertTrue(infoBasique.getAddress() != null);
    assertTrue(infoComplete.getAddress() != null);
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner une address null sur une information
   * existante.
   */
  @Test
  void testSetterAdresseNull() {
    infoComplete.setAddress(null);
    assertTrue(infoComplete.getAddress() != null);
  }
  
  /**
   * Vérifie que les paramètres des constructeurs sont correctement gérés.
   */
  @Test
  void testConstructeur() {
    PersonalInformation inf =
        new PersonalInformation("Vador", "Dark", null, -30);
    assertEquals(inf.getLastName(), "Vador");
    assertEquals(inf.getFirstName(), "Dark");
    assertTrue(inf.getAddress() != null);
    assertTrue(inf.getAge() >= 0);
  }
  
}
