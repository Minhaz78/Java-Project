/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loancalculator1;

/**
 *
 * @author User
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * The LoanCalculator class calculates and displays the monthly and total payments
 * for a loan amount entered by the user over a specified number of years, 
 * for interest rates ranging from 5.00% to 8.00% with an increment of 1/8 (0.125).
 * 
 * Author: Muhammad Rubel
 */
public class LoanCalculator1 {

    /**
     * The main method is the entry point of the program. It prompts the user to 
     * enter the loan amount and the loan period in years, and then it calculates 
     * and displays the monthly and total payments for interest rates ranging from 
     * 5.00% to 8.00%.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter the loan amount and number of years
        System.out.print("Loan Amount: ");
        double loanAmount = input.nextDouble();
        System.out.print("Number of Years: ");
        int numberOfYears = input.nextInt();

        // Print the header of the output table
        System.out.printf("%-20s%-20s%-20s\n", "Interest Rate", "Monthly Payment", "Total Payment");

        // Setup currency and percentage formatters
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        DecimalFormat percentageFormat = new DecimalFormat("0.000%");

        // Calculate and display the monthly and total payments for each interest rate
        for (double annualInterestRate = 5.0; annualInterestRate <= 8.0; annualInterestRate += 0.125) {
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = loanAmount * monthlyInterestRate / 
                    (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;

            // Print the results
            System.out.printf("%-20s%-20s%-20s\n",
                    percentageFormat.format(annualInterestRate / 100),
                    currencyFormat.format(monthlyPayment),
                    currencyFormat.format(totalPayment));
        }

        // Close the scanner
        input.close();
    }
}
