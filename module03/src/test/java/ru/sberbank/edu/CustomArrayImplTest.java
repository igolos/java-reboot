package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayImplTest {


    CustomArrayImpl<Integer> intArray = new CustomArrayImpl<>();


    @Test
    public void checkSizeMethods() {
        assertEquals(0, intArray.size());
        assertTrue(intArray.add(12));
        assertEquals(1, intArray.size());
    }
    @Test
    public void checkEmptyMethod(){
        assertTrue(intArray.isEmpty());
        assertTrue(intArray.add(12));
        assertFalse(intArray.isEmpty());
    }
    @Test
    public void checkAddAllMethodFromArray(){
        Integer[] birthday=new Integer[]{1998,2014};
        assertTrue(intArray.addAll(birthday));
    }
    @Test
    public void checkAddAllMethodFromAnotherCollection(){
        intArray.add(12);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(3);
        assertTrue(intArray.addAll(arrayList));
    }
    @Test
    public void checkAddAllMethodFromWithIndexAndGet(){
        intArray.add(12);
        Integer[] birthday=new Integer[]{1998,2014};
        assertTrue(intArray.addAll(1,birthday));
        assertEquals(12, intArray.get(0));
        assertEquals(2014, intArray.get(2));
    }
    @Test
    public void checkSetAndGet() {
        intArray.add(12);
        intArray.add(1998);
       intArray.set(0,0);
        assertEquals(0, intArray.get(0));

    }
    @Test
    public void checkRemove(){
        intArray.add(12);
        intArray.add(1998);
        intArray.remove(0);
        assertEquals(1998, intArray.get(0));
    }
    @Test
    public void checkRemoveElement(){
        Integer a=1;
        intArray.add(a);
        intArray.add(1998);
        intArray.remove(a);
        assertEquals(1998, intArray.get(0));
    }
    @Test
    public void checkContains(){
        intArray.add(12);
        intArray.add(1998);
        assertTrue(intArray.contains(12));
        assertFalse(intArray.contains(11));
    }
    @Test
    public void checkIndexOd(){
        intArray.add(12);
        intArray.add(1998);
       assertEquals(1, intArray.indexOf(1998));
    }
    @Test
    public void checkEnsureCapacity(){
        intArray.ensureCapacity(9);
        assertEquals(10, intArray.getCapacity());
        intArray.ensureCapacity(100);
        assertEquals(100, intArray.getCapacity());
    }
    @Test
    public void checkGetCapacity(){
        for(int i=0;i<200;i++){
            intArray.add(i);
        }
       assertEquals(200, intArray.getCapacity());
    }
    @Test
    public void checkReverse() {
        intArray.add(12);
        intArray.add(1998);
        intArray.reverse();
        assertEquals(1998, intArray.get(0));
        assertEquals(12, intArray.get(1));
    }
    @Test
    public void checkToArray(){
        intArray.add(12);
        intArray.add(1998);
        Object[] resultArray = intArray.toArray();
        Object[] expectedArray = {12,1998};
        assertArrayEquals(expectedArray, resultArray);
    }

}