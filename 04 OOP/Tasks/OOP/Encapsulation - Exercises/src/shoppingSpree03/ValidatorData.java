package shoppingSpree03;

public class ValidatorData {

    public static void nameValidator(String name){
        if(name==null  || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }

    public static void  statValidator(int stat, String type){
        if(stat<0 || stat>100){
            throw new IllegalArgumentException(type+" should be between 0 and 100.");
        }
    }
}
