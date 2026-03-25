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

class SimpleBurgerFactory {
  public Burger createBurger(String type) {
    if (type.equalsIgnoreCase("beef")) {
        return new BeefBurger(1, "Cheese", true);
    } else if (type.equalsIgnoreCase("veggie")) {
        return new VeggieBurger(2, "Lettuce", true);
    }
    return null;
  }
}

class Restaurant {
  // It is inherited as-is by child classes
  // Child classes do not need to implement it
  // It is usually not overridden

  public Burger orderBurger(String type) {
    SimpleBurgerFactory factory = new SimpleBurgerFactory();
    Burger burger = factory.createBurger(type);

    if (burger != null) {
        burger.prepare();
    }

    return burger;
  }
}

public class Main {
  public static void main(String[] args) {
    Restaurant restaurant = new Restaurant();

    restaurant.orderBurger("beef");
    restaurant.orderBurger("veggie");
  }
}

/* 
  The parent defines the process (orderBurger), and the child decides what to create (createBurger).
*/