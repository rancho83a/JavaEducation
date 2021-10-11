package barracksWars.core.commands;

import barracksWars.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        // TODO: refactor for problem 4
        Executable executable = null;
        try {
            Class<?> clazz = Class.forName(getCorrectCommandName("barracksWars.core.commands.", commandName));
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
        Field[] localFields = this.getClass().getDeclaredFields();

        for (Field field : executableFields) {
            field.setAccessible(true);
            Inject annotation = field.getAnnotation(Inject.class);
            if(annotation!=null){
                if(field.getType()==String[].class){
                    field.set(executable, data);
                } else {

                    for (Field localField : localFields) {
                        if(localField.getType() == field.getType()){
                            field.set(executable, localField.get(this));
                        }
                    }

                }
            }
        }
    }

    private String getCorrectCommandName(String path, String commandName) {
        return path + (commandName.charAt(0) + "").toUpperCase() + commandName.substring(1) + "Command";
    }

}
