import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * a JUnit test class for the EmailAddress class
 */
public class EmailAddressTest {

  private EmailAddress email;

  @Before
  public void setUp() {
    email = new EmailAddress("test", "northeastern.edu");
  }

  @Test
  public void testUsername() {
    assertEquals("test", email.getUsername());
  }

  @Test
  public void testDomain() {
    assertEquals("northeastern.edu", email.getDomain());
  }

  @Test
  public void testgetEmailAddress() {
    assertEquals("test@northeastern.edu", email.getEmailAddress());
  }

}
