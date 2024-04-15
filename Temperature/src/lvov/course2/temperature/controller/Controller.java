package lvov.course2.temperature.controller;

import lvov.course2.temperature.ConvertType;
import lvov.course2.temperature.model.Converter;
import lvov.course2.temperature.view.View;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    public void convertTemperature(ConvertType convertType, double temperature) {
        if (ConvertType.CELSIUS_TO_KELVIN == convertType) {
            view.showResult(converter.convertCelsiusToKelvin(temperature));
            return;
        }

        if (ConvertType.CELSIUS_TO_FAHRENHEIT == convertType) {
            view.showResult(converter.convertCelsiusToFahrenheit(temperature));
            return;
        }

        if (ConvertType.KELVIN_TO_CELSIUS == convertType) {
            view.showResult(converter.convertKelvinToCelsius(temperature));
            return;
        }

        if (ConvertType.KELVIN_TO_FAHRENHEIT == convertType) {
            view.showResult(converter.convertKelvinToFahrenheit(temperature));
            return;
        }

        if (ConvertType.FAHRENHEIT_TO_CELSIUS == convertType) {
            view.showResult(converter.convertFahrenheitToCelsius(temperature));
            return;
        }

        if (ConvertType.FAHRENHEIT_TO_KELVIN == convertType) {
            view.showResult(converter.convertFahrenheitToKelvin(temperature));
        }
    }
}
