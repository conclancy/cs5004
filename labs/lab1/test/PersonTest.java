import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {

  private Person john;
  private EmailAddress johnEmail;

  @Before
  public void setUp() {
    johnEmail = new EmailAddress("jdoe", "test.com");
    john = new Person("john", "doe", 1989, "6175551234", johnEmail);
  }

  @Test
  public void testFirst() {
    assertEquals("john", john.getFirstName());

  }

  @Test
  public void testSecond() {
    assertEquals("doe", john.getLastName());
  }

  @Test
  public void testYearOfBirth() {
    assertEquals(1989, john.getYearOfBirth());
  }

  @Test
  public void testFullName() {
    assertEquals("john doe", john.getFullName());
  }

  @Test
  public void testPhoneNumber() {
    assertEquals("6175551234", john.getPhoneNumber());
  }

  @Test
  public void testEmailAddress() {
    assertEquals("jdoe@test.com", john.getEmailAddress());
  }

}