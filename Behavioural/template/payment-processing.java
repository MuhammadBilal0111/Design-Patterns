abstract class PaymentProcessor {
  public final void processPayment() {
    validateDetails();
    authenticate();
    transferAmount();
    sendReceipt();
  }

  abstract void validateDetails();
  abstract void authenticate();
  abstract void transferAmount();

  void sendReceipt() {
    System.out.println("Receipt sent");
  }
}

class CreditCardProcessor extends PaymentProcessor {
  @Override
  void validateDetails() {
    System.out.println("Validating credit card details...");
  }
  @Override
  void authenticate() {
    System.out.println("Authenticating credit card...");
  }
  @Override
  void transferAmount() {
    System.out.println("Transferring amount via credit card...");
  }
}

class PayPalProcessor extends PaymentProcessor {
  @Override
  void validateDetails() {
    System.out.println("Validating PayPal account...");
  }
  @Override
  void authenticate() {
    System.out.println("Authenticating PayPal account...");
  }
  @Override
  void transferAmount() {
    System.out.println("Transferring amount via PayPal...");
  }
}

class GooglePayProcessor extends PaymentProcessor {
  @Override
  void validateDetails() {
    System.out.println("Validating Google Pay details...");
  }
  @Override
  void authenticate() {
    System.out.println("Authenticating Google Pay...");
  }
  @Override
  void transferAmount() {
    System.out.println("Transferring amount via Google Pay...");
  }
}

public class Main {
  public static void main(String[] args) {
    PaymentProcessor creditCardProcessor = new CreditCardProcessor();
    creditCardProcessor.processPayment();

    System.out.println();

    PaymentProcessor paypalProcessor = new PayPalProcessor();
    paypalProcessor.processPayment();

    System.out.println();

    PaymentProcessor googlePayProcessor = new GooglePayProcessor();
    googlePayProcessor.processPayment();
  }
}