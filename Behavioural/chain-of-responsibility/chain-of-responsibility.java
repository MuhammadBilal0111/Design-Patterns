import java.util.HashMap;
import java.util.Map;

public abstract class Handler {
  private Handler next; // reference to the next handler in the chain
  public Handler setNextHandler(Handler next) {
    this.next = next;
    return next;
  }
  protected boolean handleNext(String username, String password) {
    if (next == null) {
      return true; // end of chain, all checks passed
    }
    return next.handle(username, password);
  }
  public abstract boolean handle(String username, String password);
}
class UserExistHandler extends Handler {
  private Database database;

  public UserExistHandler(Database database) {
    this.database = database;
  }
  @Override
  public boolean handle(String username, String password) {
    if (!database.isValidUser(username)) {
      System.out.println("User does not exist.");
      return false;
    }
    return handleNext(username, password); // pass to next handler
  }
}
class ValidPasswordHandler extends Handler {
  private Database database;

  public ValidPasswordHandler(Database database) {
    this.database = database;
  }

  @Override
  public boolean handle(String username, String password) {
    if (!database.isValidPassword(username, password)) {
      System.out.println("Invalid password.");
      return false;
    }
    return handleNext(username, password);
  }
}
class RoleCheckHandler extends Handler {
  @Override
  public boolean handle(String username, String password) {
    if("admin_user".equals(username)) {
      System.out.println("User does not have the required role.");
      return false;
    }
    System.out.println("Role check passed.");
    return handleNext(username, password);
  }
}

class Database {
  private final Map<String, String> data;

  public Database() {
    this.data = new HashMap<>();
    data.put("user1", "password1");
    data.put("user2", "password2");
    data.put("admin_user", "admin_password");
  }
  public boolean isValidUser(String username) {
    return data.containsKey(username);
  }
  public boolean isValidPassword(String username, String password) {
    return data.get(username).equals(password);
  }
}
class AuthenticationService {
  private Handler handler;

  public AuthenticationService(Handler handler) {
    this.handler = handler;
  }

  public boolean login(String username, String password) {
    if(handler.handle(username, password)) {
      System.out.println("Login successful!");
      return true;
    }
    return false;
  }
}
public class Main{
  public static void main(String[] args) {
    Database database = new Database();

    Handler userExistHandler = new UserExistHandler(database);
    Handler validPasswordHandler = new ValidPasswordHandler(database);
    Handler roleCheckHandler = new RoleCheckHandler();

    userExistHandler.setNextHandler(validPasswordHandler).setNextHandler(roleCheckHandler);

    AuthenticationService authService = new AuthenticationService(userExistHandler);

    authService.login("user1", "password1"); // Login successful!
    authService.login("user1", "wrong_password"); // Invalid password.
    authService.login("nonexistent_user", "password"); // User does not exist.
    authService.login("admin_user", "admin_password"); // User does not have the required role.
  }
}