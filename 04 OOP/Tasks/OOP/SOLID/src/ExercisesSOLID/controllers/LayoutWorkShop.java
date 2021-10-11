package ExercisesSOLID.controllers;

import ExercisesSOLID.controllers.SimpleLayout;
import ExercisesSOLID.controllers.XmlLayout;
import ExercisesSOLID.interfaces.Layout;
import ExercisesSOLID.interfaces.LayoutFactory;

public class LayoutWorkShop implements LayoutFactory {


    @Override
    public Layout produce(String type) {
        Layout layout = null;
        switch (type) {
            case "SimpleLayout":
                layout = new SimpleLayout();
                break;
            case "XmlLayout":
                layout = new XmlLayout();
            case "FileAppender":
                break;
            default:
                throw new IllegalStateException("not valid type for Layout " + type);
        }
        return layout;
    }
}
