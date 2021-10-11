package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Scanner scan = new Scanner(System.in);

        Class clazz = BlackBoxInt.class;
        Constructor ctor = clazz.getDeclaredConstructor();
        ctor.setAccessible(true);
        Object obj = ctor.newInstance();
        assert obj instanceof BlackBoxInt;
        BlackBoxInt blackBoxInt = (BlackBoxInt) obj;


        Field field = clazz.getDeclaredField("innerValue");
        field.setAccessible(true);


        String input;
        while (!"END".equals(input = scan.nextLine())) {
            String methodName = input.split("_")[0];
            int value = Integer.parseInt(input.split("_")[1]);

            int currentValue = (int) field.get(blackBoxInt);
            switch (methodName) {
                case "add":
                    currentValue+=value;
                    break;
                case "subtract":
                    currentValue-=value;
                    break;
                case "multiply":
                    currentValue*=value;
                    break;
                case "divide":
                    currentValue/=value;
                    break;
                case "rightShift":
                    currentValue>>=value;
                    break;
                case "leftShift":
                    currentValue<<=value;
                    break;
            }
            field.set(blackBoxInt, currentValue);
            System.out.println(field.get(blackBoxInt));
        }

    }
}
