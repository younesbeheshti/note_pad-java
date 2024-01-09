import java.util.Stack;

public class Function_Edit {

    GUI gui;
    Stack<String> undoStack;
    Stack<String> redoStack;
    public Function_Edit(GUI gui) {
        this.gui = gui;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }


    public void addToUndoStack(String text) {
        undoStack.push(text);
        if (undoStack.size() > 20) {
            undoStack.remove(0);
        }
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            String previousText = undoStack.pop();
            redoStack.push(gui.textArea.getText());
            gui.textArea.setText(previousText);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            String nextText = redoStack.pop();
            undoStack.push(gui.textArea.getText());
            gui.textArea.setText(nextText);
        }
    }

}