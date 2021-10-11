package task4_Pizza;

public class Validator {
    public static void weightValidate(double weight, int upLimit, String param){
        if(weight<1 || weight>upLimit){
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..%d].", param,upLimit));
        }
    }
    public  static  void toppingTypeValidate(String toppingType) {
        try {
            ToppingEnum.valueOf(toppingType.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
    }

    public static void flourTypeValidate(String flourType) {
        try{
            DoughEnum.valueOf(flourType.toUpperCase());
        } catch(IllegalArgumentException ex){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public static void toppingCountValidate(int toppingsCount) {
        if(toppingsCount<0 || toppingsCount>10){
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
    }

    public static void nameValidate(String name) {
        if(name==null || name.trim().isEmpty() || name.trim().length()>15){
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
    }
}
