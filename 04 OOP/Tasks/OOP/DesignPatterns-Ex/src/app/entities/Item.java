package app.entities;

public class Item {
    private String name;
    private int health;
    private int attack;
    private int defence;
    private double cost;

    private Item() {
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public double getCost() {
        return cost;
    }

    public static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public static ItemBuilder builder(Item item){
        ItemBuilder builder = Item.builder();
        builder.item.name=item.getName();
        builder.item.health=item.getHealth();
        builder.item.attack=item.getAttack();
        builder.item.cost=item.getCost();
        builder.item.defence=item.getDefence();
        return builder;
    }

    public static class ItemBuilder {
        private Item item = new Item();

        private ItemBuilder() {
        }

        public Item crete() {
            return this.item;
        }

        public ItemBuilder name(String name) {
            this.item.name = name;
            return this;
        }

        public ItemBuilder health(int health) {
            this.item.health = health;
            return this;
        }

        public ItemBuilder attack(int attack) {
            this.item.attack = attack;
            return this;
        }

        public ItemBuilder defence(int defence) {
            this.item.defence = defence;
            return this;
        }

        public ItemBuilder cost(double cost) {
            this.item.cost = cost;
            return this;
        }

    }

}
