package solid_Ex.interfaces;

public interface InputParser {
   static String[] parse(String input){
       if(input.contains("|")){
           return input.split("\\|");
       }
       return input.split("\\s+");
   };
}
