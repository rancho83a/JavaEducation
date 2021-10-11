package shoppingSpree03TempRename;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;


    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setMoney(double money) {
//        if (money < 0) {
//            throw new IllegalArgumentException("Money cannot be negative");
//        }
        Validator.validateMoney(money);
        this.money = money;
    }

    private void setName(String name) {
//        if (name==null || name.trim().isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be empty");
//        }
        Validator.validateNAme(name);
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {
        if (this.money < product.getCost()) {
            throw new IllegalStateException(this.getName() + " can't afford " + product.getName());
        }
        this.products.add(product);
        this.setMoney(this.money - product.getCost());
    }

    @Override
    public String toString() {
        //String printList = this.products.toString().replaceAll("[]\\[]", "");
        String printList = this.products.stream().map(e-> e.getName()).collect(Collectors.joining(", "));
        if (this.products.isEmpty()) {
            printList = "Nothing bought";
        }
        return this.getName() + " - " + printList;
    }
}
