package solid_Ex.controllers;

import solid_Ex.interfaces.Layout;
import solid_Ex.interfaces.LayoutFactory;

public class LayoutWorkshop implements LayoutFactory {
    @Override
    public Layout produce(String type) {

        return switch (type){
            case "SimpleLayout" -> new SimpleLayout();
            case "XmlLayout" -> new XmlLayout();

            default -> throw new IllegalArgumentException("Invalid type of layout " + type);
        };
    }
}
