package animals;

public  class ValidatorData {

    public static void validateName(String data){
        if(null==data || data.trim().isEmpty() ){
            throw  new IllegalArgumentException(" Invalid input!");
        }
    }

    public static void validateGender(String data){
        if(!"FemaleMale".contains(data)){
            throw  new IllegalArgumentException(" Invalid input!");
        }
    }

    public static void validateAge(int age){
        if(age<0){
            throw  new IllegalArgumentException(" Invalid input!");
        }
    }

}
