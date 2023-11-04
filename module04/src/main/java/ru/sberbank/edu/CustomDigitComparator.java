package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
       if(o2%2==0){
           return 1;
       }
       if(o1%2==0){
           return -1;
       }
        return 0;
    }

}
