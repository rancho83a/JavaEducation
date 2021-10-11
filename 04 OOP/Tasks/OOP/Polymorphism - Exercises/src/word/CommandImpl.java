package word;

import java.util.*;

public class CommandImpl implements CommandInterface {

    private static Deque<String> cutRepo = new ArrayDeque<>();

    class ToUpperTransform implements TextTransform {
        @Override
        public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
            for (int i = startIndex; i < endIndex; i++) {
                text.setCharAt(i, Character.toUpperCase(text.charAt(i)));
            }
        }
    }

    class CutTransform implements TextTransform {

        @Override
        public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
            if (startIndex != endIndex) {
                String cut = text.substring(startIndex, endIndex);
                cutRepo.push(cut);
                text.replace(startIndex, endIndex, "");
            } else {
                if (!cutRepo.isEmpty()) {
                    cutRepo.pop();
                }
            }
        }
    }

        class PasteTransform implements TextTransform {

            @Override
            public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
                String cut="";
                if(!cutRepo.isEmpty()) {
                     cut = cutRepo.peek();
                }
                text.replace(startIndex, endIndex, cut);
            }
        }


        private Map<String, TextTransform> commandTransforms;
        private StringBuilder text;

        public CommandImpl(StringBuilder text) {
            this.commandTransforms = new HashMap<>();
            this.text = text;
        }

        @Override
        public void init() {
            this.commandTransforms.clear();
            for (Command p : this.initCommands()) {
                this.commandTransforms.putIfAbsent(p.getText(), p.getTextTransform());
            }
        }

        @Override
        public void handleInput(String input) {
            String[] tokens = input.split("\\s+");

            String commandName = tokens[0];
            int startInd = Integer.parseInt(tokens[1]);
            int endInd = Integer.parseInt(tokens[2]);

            this.commandTransforms.get(commandName).invokeOn(this.text, startInd, endInd);
        }

        protected List<Command> initCommands() {
            List<Command> commands = new ArrayList<>();
            commands.add(new Command("uppercase", new ToUpperTransform()));
            commands.add(new Command("cut", new CutTransform()));
            commands.add(new Command("paste", new PasteTransform()));


            return commands;
        }
    }
