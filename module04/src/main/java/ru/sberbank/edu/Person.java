package ru.sberbank.edu;

import java.util.Objects;

public class Person implements Comparable<Person> {

    String name;
    String city;
    int age;

    public String getName() {
        return name;
    }


    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person other) {
        if (!this.city.equals(other.city)) {
            return this.city.compareTo(other.city);
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name.toLowerCase(), person.name.toLowerCase()) && Objects.equals(city.toLowerCase(), person.city.toLowerCase()) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name.toLowerCase(), city.toLowerCase(), age);
    }
}
