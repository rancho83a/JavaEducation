package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Collection<Computer> computers;//TODO:Change for MAP
    private Collection<Component> components;
    private Collection<Peripheral> peripherals;


    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (this.computers.stream().anyMatch(c -> c.getId() == id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        Computer computer;
        if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        this.computers.add(computer);
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model,
                                double price, double overallPerformance, String connectionType) {

        checkComputerWithIdExists(computerId);
        if (this.peripherals.stream().anyMatch(p -> p.getId() == id)) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }
        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().get();
        computer.addPeripheral(peripheral);
        this.peripherals.add(peripheral);

        return String.format(ADDED_PERIPHERAL,peripheralType,id,computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        checkComputerWithIdExists(computerId);
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().get();
        Peripheral peripheral = computer.removePeripheral(peripheralType);
        this.peripherals.remove(peripheral);
        return String.format(REMOVED_PERIPHERAL,peripheralType,peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model,
                               double price, double overallPerformance, int generation) {
        checkComputerWithIdExists(computerId);
        if (this.components.stream().anyMatch(c -> c.getId() == id)) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }
        Component component;
        switch (componentType) {

            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().get();
        computer.addComponent(component);
        this.components.add(component);

        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    private void checkComputerWithIdExists(int computerId) {
        if (this.computers.stream().noneMatch(c -> c.getId() == computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        checkComputerWithIdExists(computerId);
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().get();
        Component component = computer.removeComponent(componentType);
        this.components.remove(component);
        return String.format(REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        checkComputerWithIdExists(id);
        Computer computer = this.computers.stream().filter(c -> c.getId() == id).findFirst().get();
        this.computers.remove(computer);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer bestComputer = this.computers.stream()
                .sorted(Comparator.comparing(Product::getOverallPerformance).reversed())
                .filter(c -> c.getPrice() <= budget)
                .findFirst()
                .orElse(null);
        if(bestComputer==null){
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER,budget));
        }
        this.computers.remove(bestComputer);
        return bestComputer.toString();
    }

    @Override
    public String getComputerData(int id) {
        checkComputerWithIdExists(id);
        Computer computer = this.computers.stream().filter(c -> c.getId() == id).findFirst().get();
        return computer.toString();
    }
}
