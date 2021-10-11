package app.factories;

import app.entities.BaseHero;

public interface HeroFactory {
    BaseHero createHero(String clazz, String name, int level);
}
