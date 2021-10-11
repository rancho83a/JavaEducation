package stackofStrings05;

public class Main {
    public static void main(String[] args) {
        StackOfStrings myStack = new StackOfStrings();

        myStack.push("1");
        myStack.push("2");
        myStack.push("3");

        System.out.println(myStack.isEmpty());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.isEmpty());
        System.out.println(myStack.pop());


    }
}
