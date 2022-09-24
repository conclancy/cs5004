/**
 * This class represents and employee.
 */
public class Employee {

  private String name;
  private double payRate;
  private double hoursWorked;

  /**
   * Constructor for the Employee class.
   *
   * @param name    is the name of the employee
   * @param payRate the hourly pay rate of the employee
   */
  public Employee(String name, double payRate) {
    this.name = name;
    this.payRate = payRate;
    this.hoursWorked = 0;
  }

  /**
   * Return the employee's name.
   *
   * @return the employee's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Return the current pay rate.
   *
   * @return pay rate in dollars
   */
  public double getPayRate() {
    return this.payRate;
  }

  /**
   * Return the hours worked for the current period.
   *
   * @return
   */
  public double getHoursWorked() {
    return this.hoursWorked;
  }

  /**
   * Add hours to the employees period hour count.
   *
   * @param hours to be added for the pay period
   */
  public void addHoursWorked(double hours) {
    this.hoursWorked += hours;
  }

  /**
   * Reset the number of hours worked for the employee to 0.
   */
  public void resetHoursWorked() {
    this.hoursWorked = 0;
  }

  /**
   * Create and return a paycheck object for this period.
   *
   * @return PayCheck for the employee.
   */
  public PayCheck getWeeklyCheck() {
    return new PayCheck(this.name, this.payRate, this.hoursWorked);
  }
}
