
abstract class Burger {
  protected Integer productId;
  protected String addOns;

  public Burger(Integer productId, String addOns) {
      this.productId = productId;
      this.addOns = addOns;
  }

  abstract void prepare();
}

class BeefBurger extends Burger {
  private Boolean angus;

  public BeefBurger(Integer productId, String addOns, Boolean angus) {
      super(productId, addOns);
      this.angus = angus;
  }

  @Override
  public void prepare() {
      System.out.println("Preparing Beef Burger with productId: " + productId +
              ", AddOns: " + addOns + ", angus: " + angus);
  }
}

class VeggieBurger extends Burger {
  private Boolean vegan;

  public VeggieBurger(Integer productId, String addOns, Boolean vegan) {
      super(productId, addOns);
      this.vegan = vegan;
  }

  @Override
  public void prepare() {
      System.out.println("Preparing Veggie Burger with productId: " + productId +
              ", AddOns: " + addOns + ", vegan: " + vegan);
  }
}


abstract class Restaurant {
  public Burger orderBurger() {
    Burger burger = createBurger();
    burger.prepare();
    return burger;
  }
  public abstract Burger createBurger();
  // Create Burger is the factory method will be implemented by the abstract class

}
class BeefBurgerRestaurant extends Restaurant {
  @Override
  public Burger createBurger() {
    return new BeefBurger(1, "Cheese", true);
  }
}
class VeggieBurgerRestaurant extends Restaurant {
  @Override
  public Burger createBurger() {
    return new VeggieBurger(2, "Lettuce", true);
  }
}

public class Main {
  public static void main(String[] args) {
    Restaurant beefRestaurant = new BeefBurgerRestaurant();
    beefRestaurant.orderBurger();

    Restaurant veggieRestaurant = new VeggieBurgerRestaurant();
    veggieRestaurant.orderBurger();
  }
}