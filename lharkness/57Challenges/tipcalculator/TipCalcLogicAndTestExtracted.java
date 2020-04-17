import java.util.Optional;
import java.util.Scanner;

/**
 * Represents the UI for a basic tip calculator.
 * Allows the user to enter a bill amount and a tip percentage
 * Displays the tip amount and the total.
 */
public class TipCalcLogicAndTestExtracted {

    private final Logic logic;
    private final Scanner scanner = new Scanner(System.in);

    public TipCalcLogicAndTestExtracted(final Logic logic) {
        this.logic = logic;
    }

    /**
     * Main program entry point.
     * @param args Program arguments.
     */
    public static void main(final String[] args) {
        final Logic logic = new Logic();
        final TipCalcLogicAndTestExtracted app = 
            new TipCalcLogicAndTestExtracted(logic);
        app.run();
    }

    /**
     * Runs the program
     */
    private void run() {
        // Prompt the user for the input
        final Optional<Double> tipAmount = promptUserFor("Tip Amount: ");
        if (!tipAmount.isPresent()) { System.exit(0);}
        final Optional<Double> billAmount = promptUserFor("Bill Amount: ");
        if (!billAmount.isPresent()) { System.exit(0);}

        // Determine the tip
        final double tip = logic.calculateTipFrom(tipAmount.get(), billAmount.get());

        // Show the user the output and terminate
        produceOutputFor(tip, billAmount.get());
        System.exit(0);
    }

    /**
     * Displays the output to the user
     * @param tipAmount the Amount tipped
     * @param billAmount the Amount of the bill
     * @param tip 
     */
    private void produceOutputFor(final double tipAmount, final double billAmount) {
        // Determine the amounts    
        final double billAmountRounded = logic.roundToNearestPenny(billAmount);
        final double tipAmountRounded = logic.roundToNearestPenny(tipAmount);
        final double total = billAmountRounded + tipAmountRounded;
        // Output
        System.out.println("Bill Amount: " + billAmountRounded);
        System.out.println("Tip Amount: " + tipAmountRounded);
        System.out.println("Total: " + total);
    }

    /**
     * Helper method to prompt user for amounts
     * @param prompt The prompt to display to the user
     * @return the Optional<Double> representing the user input.  Optional.empty() if the user
     * did not enter valid input.
     */
    private Optional<Double> promptUserFor(final String prompt) {
        System.out.print(prompt + " ");
        try {
            return Optional.of(Double.parseDouble(scanner.nextLine()));
        }
        catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }
}

/**
 * Represents the logic for calculating tips
 */
class Logic {

    /**
     * Rounds a given double value to the nearest penny.
     * @param amount The amount to round
     * @return the amount, rounded to the nearest penny
     */
    public double roundToNearestPenny(double amount) {
        double centAmount = amount * 100;
        if (centAmount > 0) {
            centAmount += 0.5;   
        }
        else {
            centAmount -= 0.5;
        }
        return ((int)centAmount) / 100.0d;
    }

    /**
     * Used to calculate a tip amount for a bill given percentage to tip
     * @param tipAmount the percentage to tip
     * @param billAmount the bill 
     * @return the amount of the tip.
     */
    public double calculateTipFrom(final double tipAmount, final double billAmount) {
        return billAmount * (tipAmount / 100.0);
    }
}

/**
 * Collection of tests for the logic
 */
class LogicTest {
    public static void main(String[] args) {

        Logic target = new Logic();

        // Test that roundToNearestPenny (rtnp) gives 0 up to .05
        double result = target.roundToNearestPenny(0);
        if (result != 0) {
            error("RTNP Failed for 0.  Expected 0");
        }
        // Test that rtnp gives 1 for .005 up
        result = target.roundToNearestPenny(.005);
        if (result != .01) {
            error("RTNP Failed for .005.  Expected .01");
        }
        // Test that rtnp works for larger numbers
        result = target.roundToNearestPenny(201.005);
        if (result != 201.01) {
            error("RTNP Failed for .005.  Expected 201.01");
        }
        // Test that rtnp works for negative numbers
        result = target.roundToNearestPenny(-.005);
        if (result != -.01) {
            error("RTNP Failed for .005.  Expected -0.01");
        }
        result = target.roundToNearestPenny(-.003);
        if (result != 0) {
            error("RTNP Failed for .005.  Expected 0");
        }
        // Test that calculateTipFrom works       
        result = target.calculateTipFrom(100.0, 10.0);
        if (result != 10.0) {
            error("CTF Failed for 100, 10.  Expected 10");
        }
    }

    /**
     * Helper method to output test errors
     * @param message the message to display
     */
    private static void error(String message) {
        System.out.println(message);
    }

}