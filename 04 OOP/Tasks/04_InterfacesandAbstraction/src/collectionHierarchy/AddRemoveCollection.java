package collectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable{
    public AddRemoveCollection(){
        super();
    }

    @Override
    public String remove() {
        return super.getItems().remove(super.getItems().size()-1);
    }

    @Override
    public int add(String item) {
        super.getItems().add(0,item);
        return 0;
    }
}
