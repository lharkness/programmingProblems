import java.util.Optional;
import java.util.Scanner;

public class BasicTipCalculator {

    private final Scanner scanner = new Scanner(System.in);
    public static void main(final String[] args) {
        final BasicTipCalculator app = new BasicTipCalculator();
        app.run();
    }

    private void run() {
        final Optional<Double> tipAmount = promptUserFor("Tip Amount: ");
        if (!tipAmount.isPresent()) { System.exit(0);}
        final Optional<Double> billAmount = promptUserFor("Bill Amount: ");
        if (!billAmount.isPresent()) { System.exit(0);}

        final double tip = calculateTipFrom(tipAmount.get(), billAmount.get());

        produceOutputFor(tipAmount.get(), billAmount.get(), tip);
    }

    private void produceOutputFor(double tipAmount, double billAmount, double tip) {
            double billAmountRounded = roundToNearestPenny(billAmount);
            double tipAmountRounded = roundToNearestPenny(tipAmount);
            double total = billAmountRounded + tipAmountRounded;
            System.out.println("Bill Amount: " + billAmountRounded);
            System.out.println("Tip Amount: " + tipAmountRounded);
            System.out.println("Total: " + total);
    }

    private double roundToNearestPenny(double billAmount) {
        double centAmount = billAmount * 100;
        if (centAmount > 0) {
            centAmount += 0.5;   
        }
        else {
            centAmount -= 0.5;
        }
        return ((int)centAmount) / 100.0d;
    }

    private double calculateTipFrom(final double tipAmount, final double billAmount) {
        return billAmount * (tipAmount / 100.0);
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