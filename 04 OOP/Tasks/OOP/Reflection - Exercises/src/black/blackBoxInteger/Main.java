package black.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Scanner scan = new Scanner(System.in);

        Class<?> clazz = BlackBoxInt.class;

        Constructor<?> ctor = clazz.getDeclaredConstructor();
        ctor.setAccessible(true);
        Object obj = ctor.newInstance();
        assert obj instanceof BlackBoxInt;
        BlackBoxInt blackBoxInt = (BlackBoxInt) obj;

        Method[] methods = clazz.getDeclaredMethods();

        Map<String, Method> methodsByName = Arrays.stream(methods)
                .peek(m->m.setAccessible(true))
                .collect(Collectors.toMap(Method::getName, m -> m));

        Field field = clazz.getDeclaredField("innerValue");
        field.setAccessible(true);

        String input;
        while (!"END".equals(input = scan.nextLine())) {
            String methodName = input.split("_")[0];
            int value = Integer.parseInt(input.split("_")[1]);
            methodsByName.get(methodName).invoke(blackBoxInt,value);
            System.out.println(field.get(blackBoxInt));
        }

    }
}
