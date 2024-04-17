package lvov.course2.lambdas.person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Александр", 45), new Person("Сергей", 32),
                new Person("Екатерина", 25), new Person("Иван", 1),
                new Person("Александр", 67), new Person("Мария", 11),
                new Person("Пёрт", 57), new Person("Ирина", 20),
                new Person("Ирина", 20), new Person("Александр", 45),
                new Person("Пёрт", 38));

        List<String> uniqueNames = persons.stream().map(Person::getName).distinct().toList();
        System.out.println("Список уникальных имён: " + uniqueNames);

        String uniqueNamesString = uniqueNames.stream().collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesString);
        System.out.println();

        List<Person> underEighteenYears = persons.stream().filter(x -> x.getAge() < 18).toList();
        double averageAgeUnderEighteenYeas = underEighteenYears.stream().collect(Collectors.averagingInt(Person::getAge));

        if (averageAgeUnderEighteenYeas > 0) {
            System.out.println("Список людей младше 18 лет: " + underEighteenYears);
            System.out.println("Их средний возраст: " + averageAgeUnderEighteenYeas);
            System.out.println();
        } else {
            System.out.println("Нет людей младше 18 лет.");
            System.out.println();
        }

        Map<String, Double> personsGroupedByName = persons.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println("Люди сгруппированные в Map, в котором ключи – имена, а значения – средний возраст:");
        System.out.println(personsGroupedByName);
        System.out.println();

        List<Person> personsSortedByAge = persons.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((person1, person2) -> person2.getAge() - person1.getAge())
                .toList();
        System.out.println("Список людей возрастом от 20 до 45 и отсортированный по длине имени от большего к меньшему:");
        System.out.println(personsSortedByAge);
    }
}
