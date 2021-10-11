package task03_Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        nameValidator.validate(name);
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    private void setMoney(double money) {
        numericValidator.validate(money);
        this.money = money;
    }

    public void buyProduct(Product product){
        if (product.getCost() > this.money) {
            throw new IllegalArgumentException(String.format("%s can`t afford %s", this.name, product.getName()));
        }
            this.products.add(product);
            this.money-=product.getCost();
    }

    @Override
    public String toString() {
        String output = this.products.isEmpty() ? "Nothing bought" :
                this.products.stream().map(Product::getName).collect(Collectors.joining(", "));
        return this.name + " - " + output;
    }
}
