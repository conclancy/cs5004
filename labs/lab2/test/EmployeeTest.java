import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

  private Employee connor;
  private Employee sal;
  private Employee monika;

  /**
   * Set up the initial employee objects.
   */
  @Before
  public void setUp() {

    // Instantiate Employee Objects
    connor = new Employee("Connor", 12.34);
    sal = new Employee("Sal", 49);
    monika = new Employee("Monika", 0);
  }

  /**
   * Test to make sure the names are set up correctly.
   */
  @Test
  public void testGetName() {
    assertEquals("Connor", connor.getName());
    assertEquals("Sal", sal.getName());
    assertEquals("Monika", monika.getName());
  }

  /**
   * Test to make sure the pay rate is set up correctly.
   */
  @Test
  public void testGetPayRate() {
    assertEquals(12.34, connor.getPayRate(), .01);
    assertEquals(49, sal.getPayRate(), .01);
    assertEquals(0, monika.getPayRate(), .01);
  }

  /**
   * Test to make sure the add hours functionality works.
   */
  @Test
  public void testAddHoursWorked() {

    // Test Connor
    connor.addHoursWorked(40);
    assertEquals(40, connor.getHoursWorked(), .01);

    // Test adding increments existing values
    connor.addHoursWorked(40);
    assertEquals(80, connor.getHoursWorked(), .01);

    // Test Sal
    sal.addHoursWorked(54.12);
    assertEquals(54.12, sal.getHoursWorked(), .01);

    // Test Monika
    monika.addHoursWorked(17);
    assertEquals(17, monika.getHoursWorked(), .01);

    // Test adding a negative decrements existing value
    monika.addHoursWorked(-7.5);
    assertEquals(9.5, monika.getHoursWorked(), .01);
  }

  /**
   * Test that a paycheck object is created when getWeeklyPaycheck is called.
   */
  @Test
  public void testGetWeeklyPaycheck() {

    // Test Connor
    connor.addHoursWorked(40);
    assertEquals(493.6, connor.getWeeklyCheck().getTotalPay(), .01);

    // Test Sal
    sal.addHoursWorked(54.12);
    assertEquals(2997.82, sal.getWeeklyCheck().getTotalPay(), .01);

    // Test Monika
    monika.addHoursWorked(17);
    assertEquals(0, monika.getWeeklyCheck().getTotalPay(), .01);
  }

  /**
   * Test to make sure the resetHoursWorked function resets the value to 0.
   */
  @Test
  public void testResetHoursWorked() {
    // Test Connor
    connor.addHoursWorked(40);
    assertEquals(40, connor.getHoursWorked(), .01);
    connor.resetHoursWorked();
    assertEquals(0, connor.getHoursWorked(), .01);

    // Test Sal
    sal.addHoursWorked(54.12);
    assertEquals(54.12, sal.getHoursWorked(), .01);
    sal.resetHoursWorked();
    assertEquals(0, sal.getHoursWorked(), .01);

    // Test Monika
    monika.addHoursWorked(17);
    assertEquals(17, monika.getHoursWorked(), .01);
    monika.resetHoursWorked();
    assertEquals(0, monika.getHoursWorked(), .01);
  }
}
