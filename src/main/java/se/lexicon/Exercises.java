package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	TODO: Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);

        List<Person> eriks = storage.findMany(person -> person.getFirstName().equals("Erik"));

        for(Person erik : eriks){
            System.out.println(erik.getFirstName() + " " + erik.getLastName());
        }
        System.out.println("----------------------");
    }

    /*
        2.	TODO: Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {


        List<Person> females = storage.findMany(person -> person.getGender().equals(Gender.FEMALE));

        for(Person female : females){
            System.out.println(female.getFirstName() + " " + female.getLastName());
        }
        System.out.println("----------------------");
    }

    /*
        3.	TODO: Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);

        List<Person> personsBornAfterY2K = storage.findMany(person -> person.getBirthDate().isAfter(LocalDate.parse("1999-12-31")));

        for(Person zoomer : personsBornAfterY2K){
            System.out.println(zoomer.getFirstName() + " " + zoomer.getLastName() + " " + zoomer.getBirthDate());
        }
        System.out.println("----------------------");
    }

    /*
        4.	TODO: Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);

        Person personWithSpecifiedId = storage.findOne(person -> person.getId() == 123);

        System.out.println(personWithSpecifiedId);
        System.out.println("----------------------");
    }

    /*
        5.	TODO: Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);

        String result = storage.findOneAndMapToString(
                person -> person.getId() == 456,
                person -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate());

        System.out.println(result);
        System.out.println("----------------------");
    }

    /*
        6.	TODO: Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);

        List<String> result = storage.findManyAndMapEachToString(
                person -> person.getFirstName().startsWith("E"),
                person -> person.getFirstName() + " " + person.getLastName());

        for(String s : result){
            System.out.println(s);
        }
        System.out.println("----------------------");
    }

    /*
        7.	TODO: Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);

        List<String> result = storage.findManyAndMapEachToString(
                person -> person.getBirthDate().isAfter(LocalDate.now().minusYears(9)),
                person -> person.getFirstName() + " " + person.getLastName() + " " + (LocalDate.now().getYear() - person.getBirthDate().getYear()) + " years"
        );

        for(String s : result){
            System.out.println(s);
        }
        System.out.println("----------------------");
    }

    /*
        8.	TODO: Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);

        storage.findAndDo(person -> person.getFirstName().equals("Ulf"),
                person -> System.out.println(person.getFirstName() + " " + person.getLastName()));

        System.out.println("----------------------");
    }

    /*
        9.	TODO: Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);

        storage.findAndDo(
                person -> person.getLastName().toLowerCase().contains(person.getFirstName().toLowerCase()),
                person -> System.out.println(person.getFirstName() + " " + person.getLastName())
        );

        System.out.println("----------------------");
    }

    /*
        10.	TODO: Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        storage.findAndDo(
                person -> {
                    String firstName = person.getFirstName();
                    String reversedFirstName = new StringBuilder(firstName).reverse().toString();
                    return firstName.equalsIgnoreCase(reversedFirstName);
                },
                person -> System.out.println(person.getFirstName() + " " + person.getLastName())
        );

        System.out.println("----------------------");
    }

    /*
        11.	TODO: Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        List<Person> sortedResult = storage.findAndSort(
                person -> person.getFirstName().startsWith("A"),
                Comparator.comparing(Person::getBirthDate));

        for(Person sortedP : sortedResult){
            System.out.println(sortedP.getFirstName() + " " + sortedP.getBirthDate());
        }
        System.out.println("----------------------");
    }

    /*
        12.	TODO: Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        List<Person> boomers = storage.findAndSort(
                person -> person.getBirthDate().isBefore(LocalDate.parse("1950-01-01")),
                Comparator.comparing(Person::getBirthDate).reversed()
        );

        for(Person sortedP : boomers){
            System.out.println(sortedP.getFirstName() + " " + sortedP.getBirthDate());
        }

        System.out.println("----------------------");
    }

    /*
        13.	TODO: Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        List<Person> multiSorted = storage.findAndSort(
                Comparator.comparing(Person::getLastName)
                          .thenComparing(Person::getFirstName)
                          .thenComparing(Person::getBirthDate));

        for(Person p : multiSorted){
            System.out.println(p.getLastName() + " " + p.getFirstName() + " " +p.getBirthDate());
        }

        System.out.println("----------------------");
    }

}