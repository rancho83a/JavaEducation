import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {
    private List<Product> products;

    public Instock() {
        this.products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.contains(product);
    }


    @Override
    public void add(Product product) {
        this.products.add(product);
    }

    @Override
    public void changeQuantity(String label, int quantity) {
        Product foundProduct = searchByLabel(label);
        foundProduct.setQuantity(quantity);
    }


    @Override
    public Product find(int index) {

        return this.products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return searchByLabel(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (countOutOfRange(count)) {
            return new ArrayList<>();
        }

        return this.products.stream().sorted(Comparator.comparing(Product::getLabel))
                .limit(count).collect(Collectors.toList());
    }

    private boolean countOutOfRange(int count) {
        return count < 0 || count >= this.products.size();
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {

        return this.products.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.products.stream().filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > this.products.size()) {
            throw new IllegalArgumentException();
        }

        return this.products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count).collect(Collectors.toList());

    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {

        return this.products.stream().filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {

        return this.products.iterator();
    }

    private Product searchByLabel(String label) {
        return this.products.stream().filter(p -> p.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with label " + label + " not found"));
    }
}
