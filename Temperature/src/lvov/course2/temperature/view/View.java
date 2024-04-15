package lvov.course2.temperature.view;

import lvov.course2.temperature.controller.Controller;

public interface View {
    void start();

    void setController(Controller controller);

    void showResult(double temperature);
}
