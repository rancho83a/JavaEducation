package utils;

import commands.Command;

import java.util.Scanner;

public class Main {
    public static class CommandBuilder {
        private String commandName;
        private Integer param;

        public CommandBuilder(String commandName, Integer param) {
            this.commandName = commandName;
            this.param = param;
        }

        public Command build() {
            Command command = SingletonContainer.getCommands().get(this.commandName);
            if (param != null) {
                command.setCommandValue(param);
            }
            return command;
        }

    }

    public static boolean gameOver = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        while (!gameOver) {
            String line = scan.nextLine();
            processInput(line);
        }
    }

    private static void processInput(String line) {
        String[] tokens = line.split("\\s+");
        Integer param = tokens.length ==2 ? Integer.parseInt(tokens[1]) : null;
        Command build = new CommandBuilder(tokens[0], param).build() ;
        System.out.println(build.execute());

    }
}
