package org.jason.stock;

import org.junit.jupiter.api.Test;

class JasonStockApplicationTests {

  private static double INVESTMENT_RATE = 0.30;
  private static double YEARLY_SAVING_INCREMENT = 0.1;

  @Test
  void testTotalIncome() {

    System.out.println("Investment Rate is " + (int) (INVESTMENT_RATE * 100) + "%");
    System.out.println(
        "Yearly Saving Increment From Salary is " + (int) (YEARLY_SAVING_INCREMENT * 100) + "%");

    for (int age = 22; age <= 45; age++) {
      int salarySaving = getCurrentYearSalarySaving(age);
      int currentInvestmentGain = getCurrentYearEndTotal(age - 1);
      int investmentGain = (int)(currentInvestmentGain * INVESTMENT_RATE);
      int currentTotal = getCurrentYearEndTotal(age);
      String output =
          String.format(
              "When Age is %s, salary saving is %s, investment gain is %s,  total asset is: %s ",
              age, salarySaving, investmentGain, currentTotal);
      System.out.println(output);
    }
  }

  public int getCurrentYearSalarySaving(int currentAge) {
    if (currentAge == 22) return 10_000;

    return (int)(getCurrentYearSalarySaving(currentAge - 1) * (1 + YEARLY_SAVING_INCREMENT));
  }

  public int getCurrentYearEndTotal(int currentAge) {

    if (currentAge < 22) return 0;

    if (currentAge == 22) return 10_000;

    return (int)(getCurrentYearEndTotal(currentAge - 1) * (1 + INVESTMENT_RATE)
        + getCurrentYearSalarySaving(currentAge));
  }
}
