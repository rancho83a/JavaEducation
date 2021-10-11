package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;


public class ReportCommand extends Command {
    @Inject
    private Repository repository;

    public ReportCommand() {
    }

    @Override
    public String execute() {
        return repository.getStatistics();
    }
}
