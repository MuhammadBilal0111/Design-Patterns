import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

enum Event{
  NEW_ITEM,
  SALE,
  DISCOUNT
}
class Store {
  private final NotificationService notificationService;

  public Store(){
    this.notificationService = new NotificationService(); // Store has a NotificationService to manage subscribers and send notifications there is a composition relationship between Store and NotificationService
  }
  public void newProductArrived(){
    System.out.println("New product arrived in store");
    notificationService.notifySubscribers(Event.NEW_ITEM);
  }
  public void newProductOnSale(){
    System.out.println("A product is on sale in store");
    notificationService.notifySubscribers(Event.SALE);
  }
  public void newProductOnDiscount(){
    System.out.println("A product is on discount in store");
    notificationService.notifySubscribers(Event.DISCOUNT);
  }
  public NotificationService getNotificationService(){
    return notificationService;
  }
}
// Publisher (Subject)
class NotificationService{
  private final HashMap<Event, List<EventListener>> customers; // Customers subscribe to specific events, so we use a HashMap to store the list of subscribers for each event type. The key is the Event type and the value is a list of EventListener objects that are subscribed to that event.

  public NotificationService() {
    this.customers = new HashMap<>();
    Arrays.stream(Event.values()).forEach(event -> customers.put(event, new ArrayList<>()));
  }
  public void subscribe(Event eventType, EventListener eventListener){
    customers.get(eventType).add(eventListener); 
  }
  public void unsubscribe(Event eventType, EventListener eventListener){
    customers.get(eventType).remove(eventListener); 
  }
  public void notifySubscribers(Event eventType){
    customers.get(eventType).forEach(listener -> listener.update());
  }
}
// Observer
interface EventListener{
  void update();
}
// Concrete Observer
class EmailMsgListener implements EventListener{
  private final String email;

  public EmailMsgListener(String email){
    this.email = email;
  }
  public void update(){
    System.out.println("Email sent to " + email);
  }
}
// Concrete Observer
class MobileAppListener implements EventListener{
  private final String phoneNumber;

  public MobileAppListener(String phoneNumber){
    this.phoneNumber = phoneNumber;
  }
  public void update(){
    System.out.println("Mobile app notification sent to " + phoneNumber);
  }
}
// Client
public class Main {
  public static void main(String[] args) {
    Store store = new Store();
    EmailMsgListener customer1 = new EmailMsgListener("customer@example.com");
    MobileAppListener customer2 = new MobileAppListener("123-456-7890");
    store.getNotificationService().subscribe(Event.NEW_ITEM, customer1);
    store.getNotificationService().subscribe(Event.SALE, customer2);

    store.newProductArrived();
    store.newProductOnSale();
  }
}