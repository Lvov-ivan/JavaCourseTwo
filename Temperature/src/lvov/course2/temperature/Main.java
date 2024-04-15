package lvov.course2.temperature;

import lvov.course2.temperature.controller.Controller;
import lvov.course2.temperature.model.Converter;
import lvov.course2.temperature.model.TemperatureConverter;
import lvov.course2.temperature.view.DesktopView;
import lvov.course2.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new DesktopView();
        Controller controller = new Controller(converter, view);
        view.setController(controller);
        view.start();
    }
}