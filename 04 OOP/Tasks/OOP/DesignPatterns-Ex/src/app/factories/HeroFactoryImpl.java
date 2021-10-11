package app.factories;

import app.entities.BaseHero;
import app.entities.Mage;
import app.entities.Rogue;

public class HeroFactoryImpl  implements HeroFactory {
    @Override
    public BaseHero createHero(String clazz, String name, int level) {
        switch(clazz){
            case "Rogue":
                return new Rogue(name,level);
            case "Mage":
                return new Mage(name,level);

            default:
                throw new IllegalArgumentException("Unknown class");
        }

    }
}
