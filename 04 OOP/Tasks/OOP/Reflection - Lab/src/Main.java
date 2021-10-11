import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Class clazzType = Reflection.class;


        Field[] fields = Arrays.stream(clazzType.getDeclaredFields())
                .sorted(Comparator.comparing(Field::getName)).toArray(Field[]::new);

        Arrays.stream(fields)
                .filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .forEach(field -> System.out.println(field.getName() + " must be private!"));


        Method[] declaredMethods = clazzType.getDeclaredMethods();

        Method[] gettersAr = Arrays.stream(declaredMethods).filter(m -> m.getName().startsWith("get"))
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);
        Arrays.stream(gettersAr).filter(m -> !Modifier.isPublic(m.getModifiers()))
                .forEach(m -> System.out.println(m.getName() + " have to be public!"));

        List<Method> setters = new ArrayList<>();
        for (int i = 0; i < declaredMethods.length; i++) {
            if (declaredMethods[i].getName().contains("set")) {
                setters.add(declaredMethods[i]);
            }
        }
        setters.stream().filter(s-> !Modifier.isPrivate(s.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.println(m.getName() + " have to be private!"));
    }
}
