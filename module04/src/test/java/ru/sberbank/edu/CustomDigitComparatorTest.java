package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomDigitComparatorTest {
    Comparator comparator;
    @Test
    void compare() {
        comparator = new CustomDigitComparator();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            list.add(i);
        }
        list.sort(comparator);

Integer[] arrayForAssert=new Integer[]{2,4,1,3};
assertArrayEquals(arrayForAssert,list.toArray());

    }
}