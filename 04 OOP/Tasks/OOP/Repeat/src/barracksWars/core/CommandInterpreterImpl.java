package barracksWars.core;

import barracksWars.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private Repository repository;
    private UnitFactory factory;

    public CommandInterpreterImpl(Repository repository, UnitFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {

        Executable executable = null;

        try {
            Class<?> clazz = Class.forName(getCorrectCommandName(commandName));
            Constructor<?> ctor = clazz.getDeclaredConstructor();

            executable = (Executable) ctor.newInstance();

            setFields(executable, data);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return executable;
    }

    private void setFields(Executable executable, String[] data) throws IllegalAccessException {

        Field[] executableFields = executable.getClass().getDeclaredFields();
        Field[] local = this.getClass().getDeclaredFields();

        for (Field field : executableFields) {
            field.setAccessible(true);
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation != null) {
                if (field.getType() == (String[].class)) {
                    field.set(executable, data);
                } else {
                    for (Field localField : local) {
                        if (localField.getType()==field.getType()) {
                            field.set(executable, localField.get(this));
                        }
                    }
                }
            }
        }

    }

    private String getCorrectCommandName(String commandName) {
        return "barracksWars.core.commands." + Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1) + "Command";
    }

}
