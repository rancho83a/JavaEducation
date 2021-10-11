package harvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Class clazz = RichSoilLand.class;

        Map<String, Integer> tableModifier = Map.of(
                "protected", 4,
                "public", 1,
                "private", 2
        );
        String input;
        while (!(input = reader.readLine()).equals("HARVEST")) {
            Field[] declaredFields = clazz.getDeclaredFields();
            if (input.equals("all")) {
                Arrays.stream(declaredFields).forEach(field ->
                        System.out.println(String.format("%s %s %s",
                               // field.toString().substring(0, field.toString().indexOf(" ")),
                                Modifier.toString(field.getModifiers()),
                                field.getType().getSimpleName(), field.getName())));
                continue;
            }
            for (Field field : declaredFields) {
                if (tableModifier.get(input) == field.getModifiers()) {
                    System.out.println(String.format("%s %s %s",input,field.getType().getSimpleName(),field.getName()));
                }
            }
        }
    }
}
