## Problem Description
This is  a Java software that lets the user specify the loan amount and the number of years the loan will last. Next, at an increment of 1/8 (0.125), the computer will show the monthly and total payments for each interest rate, ranging from 5.00% to 8.00%.

# Major Steps for Solving the Problem:
## 1.	Setup Environment:
- Install Java Development Kit (JDK) 18.
- Install NetBeans 19 or the latest version.
## 2.	Create Java Project:
- Open NetBeans and create a new project for the loan calculator.
- Give the project a suitable name.
## 3.	Input Handling:
- Request that the user input the loan amount.
- Ask the user to specify the length of the loan in years.
## 4.	Calculation:
- Increase interest rates by 1/8 (0.125) as you loop through rates from 5.00% to 8.00%.
- For each interest rate, calculate the monthly payment using the formula:
  monthlyPayment=loanAmount×monthlyInterestRate1−(1+monthlyInterestRate)−12×numberOfYears\text{monthlyPayment} = \frac{\text{loanAmount} \times \text{monthlyInterestRate}}{1 - \left(1 + \text{monthlyInterestRate}\right)^{-12 \times \text{numberOfYears}}}monthlyPayment=1−(1+monthlyInterestRate)−12×numberOfYearsloanAmount×monthlyInterestRate
o	Calculate the total payment as: totalPayment=monthlyPayment×12×numberOfYears\text{totalPayment} = \text{monthlyPayment} \times 12 \times \text{numberOfYears}totalPayment=monthlyPayment×12×numberOfYears
## 5.	Output:
- Display the interest rate, monthly payment, and total payment in a formatted table.

