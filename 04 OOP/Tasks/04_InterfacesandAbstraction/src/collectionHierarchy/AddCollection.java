package collectionHierarchy;


public class AddCollection extends Collection  implements Addable{



public AddCollection(){
    super();
}

    @Override
    public int add(String item) {
        super.getItems().add(item);
        return super.getSize()-1;
    }
}
