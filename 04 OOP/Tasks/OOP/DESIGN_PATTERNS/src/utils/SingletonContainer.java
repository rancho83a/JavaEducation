package utils;

import commands.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SingletonContainer {
    private static Map<String, Command> commandsByName;

    private SingletonContainer() {
    }

    public static void init() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        commandsByName = new HashMap<>();

        File file  =new File("C:\\Users\\User\\IdeaProjects\\OOP\\DESIGN_PATTERNS\\src\\commands");
        File[] files = file.listFiles();


        for (File f : files) {
            if(f.getName().equals("Command.java")){
                continue;
            }
            Class<?> clazz = Class.forName(("commands." + f.getName()).replaceAll(".java",""));
            Constructor<?> ctor = clazz.getDeclaredConstructor();
            Command command = (Command) ctor.newInstance();
            commandsByName.put(f.getName().replaceAll(".java", ""), command);
        }
    }

    public static Map<String, Command> getCommands(){
      if(commandsByName==null ){
          try {
              init();
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          } catch (NoSuchMethodException e) {
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          } catch (InvocationTargetException e) {
              e.printStackTrace();
          } catch (InstantiationException e) {
              e.printStackTrace();
          }
      }
        return commandsByName;
    }
}
