/**
 * This class calculates an employee's pay for the week.
 */
public class PayCheck {

  private String name;
  private double rate;
  private double hoursWorked;
  private double totalPay;

  /**
   * Constructor for the PayCheck class.
   *
   * @param name        the employee's name
   * @param rate        the rate of pay per hour during the pay period
   * @param hoursWorked the number of hours worked during the pay period
   */
  public PayCheck(String name, double rate, double hoursWorked) {
    this.name = name;
    this.rate = rate;
    this.hoursWorked = hoursWorked;
    this.totalPay = rate * hoursWorked;

    if (hoursWorked > 40) {

      double overTimeHours = this.hoursWorked - 40;
      double overTimeRate = this.rate * .5;

      this.totalPay = this.totalPay + (overTimeRate * overTimeHours);
    }
  }

  /**
   * Getter method for the totalPay field.  This is the amount of money the employee will get paid
   * as part of this paycheck.
   *
   * @return the employee's pay.
   */
  public double getTotalPay() {
    return this.totalPay;
  }

  /**
   * A formatted string of the employee's pay to two decimal points preceded by a dollar sign.
   *
   * @return a formatted string of the employee's total pay.
   */
  public String toString() {
    return String.format("$%.2f", this.totalPay);
  }
}
