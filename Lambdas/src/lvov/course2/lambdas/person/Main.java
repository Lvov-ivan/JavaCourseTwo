package lvov.course2.lambdas.person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Александр", 45), new Person("Сергей", 32),
                new Person("Екатерина", 25), new Person("Иван", 1),
                new Person("Александр", 67), new Person("Мария", 11),
                new Person("Пёрт", 57), new Person("Ирина", 20),
                new Person("Ирина", 20), new Person("Александр", 45),
                new Person("Пёрт", 38));

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.println("Список уникальных имён: " + uniqueNames);

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesString);
        System.out.println();

        List<Person> personsUnderEighteen = persons.stream()
                .filter(p -> p.getAge() < 18)
                .toList();
        OptionalDouble personsUnderEighteenAverageAge = OptionalDouble.of(personsUnderEighteen.stream()
                .collect(Collectors.averagingInt(Person::getAge)));

        if (personsUnderEighteenAverageAge.getAsDouble() >= 0) {
            System.out.println("Список людей младше 18 лет: " + personsUnderEighteen);
            System.out.println("Их средний возраст: " + personsUnderEighteenAverageAge.getAsDouble());
        } else {
            System.out.println("Нет людей младше 18 лет.");
        }

        System.out.println();

        Map<String, Double> valuesGroupedByNameMap = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Люди сгруппированные в Map, в котором ключи – имена, а значения – средний возраст:");
        System.out.println(valuesGroupedByNameMap);
        System.out.println();

        System.out.println("Список людей возрастом от 20 до 45 и отсортированный по возрасту от большего к меньшему:");
        persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .forEach(p -> System.out.printf("%s ", p.getName()));
    }
}