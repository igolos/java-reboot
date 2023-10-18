package ru.sberbank.edu;

/***
 * Написали реализацию вычисления наибольшего делителя с помощью метода, который возвращает значение типа int
 */
public class GCD implements CommonDivisor{
    public int getDivisor(int firstNumber, int secondNumber) {
        while (secondNumber !=0) {
            int tmp = firstNumber%secondNumber;
            firstNumber = secondNumber;
            secondNumber = tmp;
        }
        return firstNumber;
    }
    }
