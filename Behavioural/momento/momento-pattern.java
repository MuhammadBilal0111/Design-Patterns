import java.util.LinkedList;
import java.util.Deque;

class TextArea {
    private String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Memento takeSnapshot() {
        return new Memento(this.text);
    }

    public void restore(Memento memento) {
        if (memento != null) {
            this.text = memento.getSavedText();
        }
    }

    public static class Memento {
        private final String text;

        public Memento(String textToSave) {
            this.text = textToSave;
        }

        public String getSavedText() {
            return text;
        }
    }
}

class Editor {
    private Deque<TextArea.Memento> stateHistory;
    private TextArea textArea;

    public Editor() {
        this.stateHistory = new LinkedList<>();
        this.textArea = new TextArea();

        stateHistory.addLast(textArea.takeSnapshot());
    }

    public void write(String text) {
        textArea.setText(text);
        stateHistory.addLast(textArea.takeSnapshot());
    }

    public void undo() {
        if (stateHistory.size() <= 1) {
            System.out.println("Nothing to undo!");
            return;
        }

        stateHistory.removeLast();
        textArea.restore(stateHistory.peekLast());
    }

    public String getText() {
        return textArea.getText();
    }
}

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();

        editor.write("First version of the text.");
        editor.write("Second version of the text.");
        editor.write("Third version of the text.");

        System.out.println("Current text: " + editor.getText());

        editor.undo();
        System.out.println("After undo: " + editor.getText());

        editor.undo();
        System.out.println("After second undo: " + editor.getText());

    }
}