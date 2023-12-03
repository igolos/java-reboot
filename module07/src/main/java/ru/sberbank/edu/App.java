package ru.sberbank.edu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try{
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache=new WeatherCache(weatherProvider);
            executorService.submit(() -> {
                printThreadInfo();
                weatherCache.getWeatherInfo("qwerty");
            });
        executorService.submit(() -> {
            printThreadInfo();
            weatherCache.getWeatherInfo("Moscow");
        });
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.submit(() -> {
            printThreadInfo();
            weatherCache.getWeatherInfo("Moscow");
        });

        executorService.submit(() -> {
            printThreadInfo();
            weatherCache.getWeatherInfo("London");
        });
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.submit(() -> {
            printThreadInfo();
            weatherCache.getWeatherInfo("London");
        });
    }finally {
            executorService.shutdown();
        }
        }
    private static void printThreadInfo() {
        Thread currentThread = Thread.currentThread();
        System.out.println("Thread ID: " + currentThread.getId() + ", Thread Name: " + currentThread.getName());
   }
}
