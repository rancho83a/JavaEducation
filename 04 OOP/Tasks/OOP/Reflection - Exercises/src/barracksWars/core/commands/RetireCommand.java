package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;

public class RetireCommand extends Command {
    @Inject
    private String[] data;
    @Inject
    private Repository repository;

    public RetireCommand(){
    }

    @Override
    public String execute(){

        try {
            repository.removeUnit(data[1]);
            return data[1] + " retired!";
        } catch (IllegalArgumentException iae) {
            return iae.getMessage();
        }
    }
}
