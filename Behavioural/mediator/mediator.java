// Lightweight ActionEvent model (using AWT)
import java.awt.event.ActionEvent;

// Mediator Interface
interface Mediator {
    void login();
}

// Component Interface
interface Component {
    void setMediator(Mediator mediator);
    String getName();
}

// Concrete Mediator
class Dialog implements Mediator {
    private TextBox usernameTextBox;
    private TextBox passwordTextBox;
    private LoginButton loginButton;

    public Dialog() {
        // Initialize components
        usernameTextBox = new TextBox();
        passwordTextBox = new TextBox();
        loginButton = new LoginButton();

        // Set mediator for all components
        usernameTextBox.setMediator(this);
        passwordTextBox.setMediator(this);
        loginButton.setMediator(this);
    }

    @Override
    public void login() {
        String username = usernameTextBox.getText();
        String password = passwordTextBox.getText();

        if (username == null || username.isEmpty() ||
            password == null || password.isEmpty()) {
            System.out.println("Username or Password cannot be empty!");
        } else {
            System.out.println("Logging in with username: " + username + " and password: " + password);
        }
    }

    // Helper methods to simulate UI interaction
    public void setUsername(String username) {
        usernameTextBox.setText(username);
    }

    public void setPassword(String password) {
        passwordTextBox.setText(password);
    }

    public void clickLogin() {
        loginButton.fireActionPerformed(
            new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "login")
        );
    }
}

// TextBox (Colleague)
class TextBox implements Component {
    private String text;
    private Mediator mediator;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "TextBox";
    }
}

// Base Button class
class JButton {
    private String label;

    public JButton(String label) {
        this.label = label;
    }
}

// Login Button (Concrete Component)
class LoginButton extends JButton implements Component {
    private Mediator mediator;

    public LoginButton() {
        super("Log In");
    }

    public void fireActionPerformed(ActionEvent actionEvent) {
        // Correct method name
        if (!"login".equals(actionEvent.getActionCommand())) {
            return;
        }

        // Safety check
        if (mediator != null) {
            mediator.login(); // Notify mediator
        }
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "LoginButton";
    }
}

// Main class to test
public class Main {
    public static void main(String[] args) {
        Dialog dialog = new Dialog();

        // Simulate user input
        dialog.setUsername("bilal123");
        dialog.setPassword("securePass");

        // Simulate button click
        dialog.clickLogin();
    }
}