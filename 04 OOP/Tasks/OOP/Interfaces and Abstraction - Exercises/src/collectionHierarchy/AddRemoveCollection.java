package collectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable {

    AddRemoveCollection(){
        super();
    }


    @Override
    public String remove() {
        return super.getItems().remove(super.getItems().size()-1);
    }

    @Override
    public int add(String str) {
        super.getItems().add(0,str);
        return 0;
    }
}
