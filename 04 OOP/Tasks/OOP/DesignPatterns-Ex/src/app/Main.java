package app;

import app.entities.BaseHero;
import app.entities.Item;
import app.factories.HeroFactoryImpl;
import app.skills.Invoker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        HeroFactoryImpl factory = new HeroFactoryImpl();
        BaseHero rouge = factory.createHero("Rogue", "Pesho", 100);

        Item dager = Item.builder()
                .name("Fel dagger")
                .attack(2222)
                .cost(111.11)
                .crete();

        dager =Item.builder(dager)
                .attack(11111)
                .cost(1111)
                .crete();

        Invoker invoker = new Invoker();
        invoker.castSpell("rouge");
        invoker.castSpell("mage");
        invoker.castSpell("warlock");
    }
}
