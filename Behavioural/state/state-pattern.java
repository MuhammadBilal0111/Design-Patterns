class Phone{
  public void turnOn(){
    System.out.println("Turning on the phone");
  }
  public void lock(){
    System.out.println("Locking the phone");
  }
  public void unlock(){
    System.out.println("Unlocking the phone");
  }
}
class State {
  protected Phone phone;
  public State(Phone phone){
    this.phone = phone;
  }
  public abstract void onHome();
  public abstract void onPowerOffOn();
}
class LockedState extends State{
  public LockedState(Phone phone){
    super(phone);
  }
  @Override
  public void onHome(){
    System.out.println("Phone is locked. Cannot go to home screen.");
  }
  @Override
  public void onPowerOffOn(){
    System.out.println("Turning on the phone");
    phone.unlock();
  }
}
class Main {
  public static void main(String[] args) {
    Phone phone = new Phone();
  }
}