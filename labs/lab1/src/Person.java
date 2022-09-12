/**
 * This class represents a person The person has a first name, last name, and year of birth.
 */
public class Person {

  private String firstName;
  private String lastName;
  private int yearOfBirth;
  private String phoneNumber;
  private EmailAddress email;

  /**
   * Constructs a Person object and initializes it to the given first name, last name and year of
   * birth.
   *
   * @param firstName   the first name of this person
   * @param lastName    the last name of this person
   * @param yearOfBirth the year of birth of this person
   */

  public Person(String firstName, String lastName, int yearOfBirth, String phoneNumber,
      EmailAddress email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  /**
   * Get the first name of this person.
   *
   * @return the first name of this person
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Return the last name of this person.
   *
   * @return the last name of this person
   */

  public String getLastName() {
    return this.lastName;
  }

  /**
   * Return the year of birth of this person.
   *
   * @return the year of birth of this person
   */
  public int getYearOfBirth() {
    return this.yearOfBirth;
  }

  /**
   * Return the first and last name of the person as a single string.
   *
   * @return the full name of the person
   */
  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  /**
   * The phone number of the person as a string.
   *
   * @return the person's phone number
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getEmailAddress() {
    return this.email.getEmailAddress();
  }
}
