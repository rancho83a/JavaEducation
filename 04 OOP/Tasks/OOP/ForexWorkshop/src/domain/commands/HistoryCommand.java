package domain.commands;

import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

import java.util.List;

public class HistoryCommand implements Command<HistoryCommand.Input>{

    private final ConversionHistoryRepository repo;
    private Logger logger;

    public HistoryCommand(ConversionHistoryRepository repo, Logger logger){

        this.repo = repo;
        this.logger = logger;
    }

    public static class Input extends EmptyInput{
        private final int numbersToShow;

        public Input(int commandsToShow) {
            if(commandsToShow<1){
                throw new IllegalArgumentException("Must be positive Integer");
            }
            this.numbersToShow = commandsToShow;
        }

        public int getNumbersToShow() {
            return numbersToShow;
        }
    }
    @Override
    public void execute(Input input) {
        List<String> lastNConversions = repo.getLast(input.getNumbersToShow());
         lastNConversions.forEach(cmd->logger .logLine(cmd));
        input.getNumbersToShow();
//show last input.getCommandsToShow
    }
}
