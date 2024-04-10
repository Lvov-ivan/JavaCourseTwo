package lvov.course2.lambdas.person.main;

import lvov.course2.lambdas.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Александр", 45));
        persons.add(new Person("Сергей", 32));
        persons.add(new Person("Екатерина", 25));
        persons.add(new Person("Иван", 1));
        persons.add(new Person("Александр", 67));
        persons.add(new Person("Мария", 11));
        persons.add(new Person("Пёрт", 57));
        persons.add(new Person("Ирина", 20));
        persons.add(new Person("Александр", 45));
        persons.add(new Person("Пёрт", 38));

        var uniqueNames = persons.stream().map(Person::getName).distinct().toList();
        System.out.println("Список уникальных имён: " + uniqueNames);

        String uniqueNamesToString = persons.stream().map(Person::getName).distinct().collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesToString);

        var underEighteen = persons.stream().filter(x -> x.getAge() < 18).toList();
        double averageAgeUnderEighteen = underEighteen.stream().collect(Collectors.averagingDouble(Person::getAge));

        System.out.println("Список людей младше 18 лет " + underEighteen);
        System.out.println("Их средний возраст " + averageAgeUnderEighteen);

        var personsMap = persons.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println(personsMap);

        var filteredPersons = persons.stream().filter(x -> x.getAge() >= 20 && x.getAge() <= 45).toList();
        var sortedPersons = filteredPersons.stream().sorted((person1, person2) -> person2.getName().length() - person1.getName().length()).toList();
        System.out.println("Список людей возрастом от 20 до 45 и отсортированный по длине имени от большего к меньшему: ");
        System.out.println(sortedPersons);
    }
}