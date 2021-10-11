package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;


public class RetireCommand extends Command {
    @Inject
    private String[] data;
    @Inject
    private Repository repository;

    public RetireCommand() {

    }
        @Override
        public String execute() {
            String type = this.data[1];
            try {
                this.repository.removeUnit(type);
                return type + " retired!";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
    }
