package collectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    List<String> items;
    int maxSize;

    protected Collection() {
        this.maxSize = 100;
        this.items = new ArrayList<>();
    }

    public int getSize(){
        return items.size();
    }
    public List<String> getItems(){
        return this.items;
    }
}
