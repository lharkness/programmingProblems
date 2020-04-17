import java.util.Optional;
import java.util.Scanner;

public class TipCalcLogicExtracted {

    Logic logic;
    private final Scanner scanner = new Scanner(System.in);

    public TipCalcLogicExtracted(Logic logic) {
        this.logic = logic;
    }
    public static void main(final String[] args) {
        Logic logic = new Logic();
        final TipCalcLogicExtracted app = new TipCalcLogicExtracted(logic);
        app.run();
    }

    private void run() {
        final Optional<Double> tipAmount = promptUserFor("Tip Amount: ");
        if (!tipAmount.isPresent()) { System.exit(0);}
        final Optional<Double> billAmount = promptUserFor("Bill Amount: ");
        if (!billAmount.isPresent()) { System.exit(0);}

        final double tip = logic.calculateTipFrom(tipAmount.get(), billAmount.get());

        produceOutputFor(tipAmount.get(), billAmount.get(), tip);
    }

    private void produceOutputFor(double tipAmount, double billAmount, double tip) {
            double billAmountRounded = logic.roundToNearestPenny(billAmount);
            double tipAmountRounded = logic.roundToNearestPenny(tipAmount);
            double total = billAmountRounded + tipAmountRounded;
            System.out.println("Bill Amount: " + billAmountRounded);
            System.out.println("Tip Amount: " + tipAmountRounded);
            System.out.println("Total: " + total);
    }

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

class Logic {

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

    public double roundToNearestPenny(double billAmount) {
        double centAmount = billAmount * 100;
        if (centAmount > 0) {
            centAmount += 0.5;   
        }
        else {
            centAmount -= 0.5;
        }
        return ((int)centAmount) / 100.0d;
    }

    public double calculateTipFrom(final double tipAmount, final double billAmount) {
        return billAmount * (tipAmount / 100.0);
    }

    private static void error(String message) {
        System.out.println(message);
    }
}