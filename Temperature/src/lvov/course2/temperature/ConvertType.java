package lvov.course2.temperature;

public enum ConvertType {
    CELSIUS_TO_KELVIN("Цельсия > Кельвина"),
    CELSIUS_TO_FAHRENHEIT("Цельсия > Фаренгейты"),
    KELVIN_TO_CELSIUS("Кельвина > Цельсия"),
    KELVIN_TO_FAHRENHEIT("Кельвина > Фаренгейты"),
    FAHRENHEIT_TO_CELSIUS("Фаренгейта > Цельсия"),
    FAHRENHEIT_TO_KELVIN("Фаренгейта > Кельвина");


    final private String title;

    ConvertType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
