package task03_Shop;

public class nameValidator {
    private nameValidator(){}

    public static void validate(String name){
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ConstantMessages.INVALID_NAME_EXCEPTION_MESSAGE);
        }
    }
}
