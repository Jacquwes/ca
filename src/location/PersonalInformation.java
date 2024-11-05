package location;

import java.util.Objects;

/**
 * Description des informations personnelles d'une personne : identité, âge et
 * address.
 *
 * @author Eric Cariou
 */
public final class PersonalInformation implements java.io.Serializable {
  
  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 4026408353251835506L;
  
  /**
   * Le lastName de la personne (ne peut pas être modifié).
   */
  private final String lastName;
  
  /**
   * Le prénom de la personne (ne peut pas être modifié).
   */
  private final String firstName;
  
  /**
   * L'âge de la personne (la valeur 0 correspond à un âge non défini).
   */
  private int age;
  
  /**
   * L'address de la personne (une chaine vide "" correspond à une address non
   * définie).
   */
  private String address;
  
  /**
   * Renvoie le lastName de la personne.
   *
   * @return le lastName de la personne
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * Renvoie le prénom de la personne.
   *
   * @return le prénom de la personne
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * Renvoie l'âge de la personne.
   *
   * @return l'âge de la personne
   */
  public int getAge() {
    return age;
  }
  
  /**
   * Modifie l'âge de la personne.
   *
   * @param age le nouvel âge (doit être supérieur à 0)
   */
  public void setAge(int age) {
    if (age > 0) {
      this.age = age;
    }
  }
  
  /**
   * Renvoie l'address de la personne.
   *
   * @return l'address de la personne
   */
  public String getAddress() {
    return address;
  }
  
  /**
   * Modifie l'address de la personne.
   *
   * @param address la nouvelle address (doit être différente de null)
   */
  public void setAddress(String address) {
    if (address != null) {
      this.address = address;
    }
  }
  
  /**
   * Crée une personne avec ses informations obligatoires.
   *
   * @param lastName le lastName de la personne
   * @param firstName le prénom de la personne
   */
  public PersonalInformation(String lastName, String firstName) {
    this(lastName, firstName, "", 0);
  }
  
  /**
   * Crée une personne avec toutes ses informations.
   *
   * @param lastName le lastName de la personne
   * @param firstName le prénom de la personne
   * @param address l'address de la personne
   * @param age l'âge de la personne
   */
  public PersonalInformation(String lastName, String firstName, String address,
      int age) {
    super();
    this.lastName = lastName;
    this.firstName = firstName;
    this.address = address;
    this.age = age;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(address, age, lastName, firstName);
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    PersonalInformation other = (PersonalInformation) obj;
    return Objects.equals(address, other.address) && age == other.age
        && Objects.equals(lastName, other.lastName)
        && Objects.equals(firstName, other.firstName);
  }
  
  @Override
  public String toString() {
    return firstName + " " + lastName + " d'age " + age + " ans, habite " + address;
  }
}
