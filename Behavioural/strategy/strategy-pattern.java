interface PaymentStrategy{
  void collectPaymentDetails();
  boolean validatePaymentDetails();
  void pay(int amount);
}
class PaymentByCreditCard implements PaymentStrategy{
  private Card card;
  @Override
  public void collectPaymentDetails(){
    card = new Card("1234 5678 9012 3456", "John Doe", "12/25", "123");
  }
  @Override
  public boolean validatePaymentDetails(){
    return false;
  }
  @Override
  public void pay(int amount){
    System.out.println("Payment of " + amount + " made using Credit Card");
    card.setAmount(card.getAmount() - amount);
  }
}
class Card{
  private String number;
  private String name;
  private String expiryDate;
  private String cvv;
  private int amount;

  public Card(String number, String name, String expiryDate, String cvv){
    this.number = number;
    this.name = name;
    this.expiryDate = expiryDate;
    this.cvv = cvv;
    this.amount = 1000;
  }
  public void processPayment(int amount){
    System.out.println("Processing payment of " + amount + " for card " + number);
  }
  public int getAmount(){
    return amount;
  }
  public void setAmount(int amount){
    this.amount = amount;
  }
}

class PaymentByPaypal implements PaymentStrategy{
  private String email;
  private String password;

  @Override
  public void collectPaymentDetails(){
    this.email = "...";
    this.password = "...";
  }
  @Override
  public boolean validatePaymentDetails(){
    return false;
  }
  @Override
  public void pay(int amount){
    System.out.println("Payment of " + amount + " made using PayPal");
  }
}
class PaymentService{
  private int cost;
  private boolean includeDeliveryCharges;
  private PaymentStrategy strategy;

  public PaymentService(){
  }
  public PaymentService(int cost, boolean includeDeliveryCharges, PaymentStrategy strategy){
    this.cost = cost;
    this.includeDeliveryCharges = includeDeliveryCharges;
    this.strategy = strategy;
  }
  
  public void processOrder(){
    strategy.collectPaymentDetails();
    if(strategy.validatePaymentDetails()){
      strategy.pay(getTotal());
    }
  }
  private int getTotal(){
    return includeDeliveryCharges ? cost + 50 : cost;
  }
}
class Main {
  public static void main(String[] args) {
    PaymentService paymentService = new PaymentService(100, true, new PaymentByCreditCard());
    paymentService.processOrder();
  }
}