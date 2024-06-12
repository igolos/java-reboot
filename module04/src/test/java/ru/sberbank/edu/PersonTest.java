package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person;
    Person person2;
    Person person3;

    @Test
    void compareTo() {
        Person person=new Person("Anton","Moscow",29);
        Person person2=new Person("Bogdan","Abakan",68);
        Person person3=new Person("Mihail","Abakan",30);
        List<Person> list=new ArrayList<>();
        list.add(person);
        list.add(person2);
        list.add(person3);
        list.sort(Person::compareTo);

        //Проверяем по имени, что сортировка правильно работает
        assertEquals("Bogdan", list.get(0).getName());
        assertEquals("Mihail", list.get(1).getName());
        assertEquals("Anton", list.get(2).getName());

    }

    @Test
    void testEquals() {
        person=new Person("anton","moscow",22);
        person2=new Person("Anton","Moscow",22);
        assertTrue(person.equals(person2));

        person3=new Person("Bogdan","Moscow",22);
        assertFalse(person.equals(person3));
    }

    @Test
    void testHashCode() {
       person=new Person("anton","moscow",22);
       person2=new Person("Anton","Moscow",22);
       assertEquals(person.hashCode(),person2.hashCode());

       person3=new Person("Bogdan","Moscow",22);
       assertNotEquals(person.hashCode(),person3.hashCode());
    }
}