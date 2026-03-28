// Think of:
// Phone = Boss 👨‍💼
// State = Employee 👨‍🔧

// When you hire a new employee (new State):

// 👉 You must tell them which boss they work for

// Otherwise:

// ❌ They don’t know who to report to
// ❌ They can’t do anything

abstract class State {
    protected Phone phone;

    public State(Phone phone) {
        this.phone = phone;
    }

    public abstract String onHome();
    public abstract String onOffOn(); // power button
}

class OffState extends State {
    public OffState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        phone.setState(new LockedState(phone));
        return phone.turnOn();
    }

    @Override
    public String onOffOn() {
        phone.setState(new LockedState(phone));
        return phone.turnOn();
    }
}

class LockedState extends State {
    public LockedState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        phone.setState(new ReadyState(phone));
        return phone.unlock();
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }
}
class ReadyState extends State {
    public ReadyState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        return phone.home(); // no state change
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }
}
// Context = the main class that uses the pattern
class Phone {
    private State state;

    public Phone() {
        this.state = new OffState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public String lock() {
        return "Locking the phone and turning it off";
    }

    public String unlock() {
        return "Unlocking the phone to home";
    }

    public String home() {
        return "Going to home screen";
    }

    public String turnOn() {
        return "Turning on the phone";
    }
}

public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone();
    }
}