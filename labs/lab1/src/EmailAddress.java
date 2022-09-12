/**
 * This class represents an email address, which has a username and a domain.
 */
public class EmailAddress {
  private String username;
  private String domain;

  /**
   * Constructs an EmailAddress object and initializes it to the given username
   * and domain.
   *
   * @param username  the username of the account
   * @param domain    the site domain for the account
   */
  public EmailAddress(String username, String domain) {
    this.username = username;
    this.domain = domain;
  }

  /**
   * Return the username of the account.
   * @return the username of the account
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Return the domain of the account.
   * @return the domain of the account
   */
  public String getDomain() {
    return this.domain;
  }

  /**
   * Return the formatted email address as a single string.
   * @return the formatted email address.
   */
  public String getEmailAddress() {
    return this.username + "@" + this.domain;
  }

}
