package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;
import static onlineShop.common.constants.OutputMessages.COMPUTER_PERIPHERALS_TO_STRING;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return Collections.unmodifiableList(this.components);
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return Collections.unmodifiableList(this.peripherals);
    }

    @Override
    public void addComponent(Component component) {
        //if(this.getComponents().contains(component)
        if(this.getComponents().stream().anyMatch(c->c.getClass().getSimpleName().equals(component.getClass().getSimpleName()))){
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT
                    ,component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = this.getComponents().stream()
                .filter(c-> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);
        //TODO: poprobovat cherez oeElse(throw
        if(this.getComponents().isEmpty() || component==null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(), this.getId()));
        }
        this.components.remove(component);
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if(this.getPeripherals().contains(peripheral)){
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = this.getPeripherals().stream()
                .filter(c-> c.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);

        if(this.peripherals.isEmpty() || peripheral==null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance()+
                this.getComponents().stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00);
    }

    @Override
    public double getPrice() {
        double componentsPrice = this.getComponents().stream().mapToDouble(Product::getPrice).sum();
        double peripheralsPrice = this.getPeripherals().stream().mapToDouble(Product::getPrice).sum();
        return super.getPrice()+componentsPrice+peripheralsPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
                .append(System.lineSeparator());
        sb.append(" "+String.format(COMPUTER_COMPONENTS_TO_STRING,this.getComponents().size()))
                .append(System.lineSeparator());

        this.getComponents().forEach(c-> sb.append("  ").append(c.toString()).append(System.lineSeparator()));

        sb.append(" "+String.format(COMPUTER_PERIPHERALS_TO_STRING,
                this.getPeripherals().size(),
                this.getPeripherals().stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00)
                ))
                .append(System.lineSeparator());

        this.getPeripherals().forEach(p-> sb.append("  ").append(p.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
