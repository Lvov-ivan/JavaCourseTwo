package lvov.course2.temperature.model;

public interface Converter {
    double convertCelsiusToKelvin(double celsiusTemperature);

    double convertCelsiusToFahrenheit(double celsiusTemperature);

    double convertKelvinToCelsius(double kelvinTemperature);

    double convertKelvinToFahrenheit(double kelvinTemperature);

    double convertFahrenheitToCelsius(double fahrenheitTemperature);

    double convertFahrenheitToKelvin(double fahrenheitTemperature);
}
