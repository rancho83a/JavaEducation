package barracksWars;

import barracksWars.core.CommandInterpreterImpl;
import barracksWars.interfaces.CommandInterpreter;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Runnable;
import barracksWars.interfaces.UnitFactory;
import barracksWars.core.Engine;
import barracksWars.core.factories.UnitFactoryImpl;
import barracksWars.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        UnitFactory factory = new UnitFactoryImpl();
        Repository repository = new UnitRepository();

        Runnable engine = new Engine(new CommandInterpreterImpl(repository, factory));
        engine.run();
    }
}
