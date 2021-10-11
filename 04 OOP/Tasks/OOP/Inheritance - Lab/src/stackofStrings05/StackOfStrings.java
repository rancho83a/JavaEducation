package stackofStrings05;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StackOfStrings {
    private ArrayList<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item) {
        this.data.add(0, item);
    }

    public String pop() {
        ensureNonEmpty();
        return this.data.remove(0);
    }

    public String peek() {
        ensureNonEmpty();
        return this.data.get(0);
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    private void ensureNonEmpty() {
        if (this.data.isEmpty()) {
            throw new NoSuchElementException("No such element");
        }
    }
}
