package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        GeoPosition omskPosition = new GeoPosition(54, 59, 47, 73, 22, 20);
        GeoPosition moscowPosition = new GeoPosition(55, 45, 21, 37, 37, 1);
        GeoPosition rostovPosition = new GeoPosition(47, 14, 53, 39, 42, 39);
        CityInfo moscow = new CityInfo("Moscow", moscowPosition);
        CityInfo omsk = new CityInfo("Omsk", omskPosition);
        CityInfo rostov=new CityInfo("Rostov-na-Donu",rostovPosition);
        CityInfo doubleMoscow = new CityInfo("Moscow", moscowPosition);
        TravelService service = new TravelService();
        service.add(moscow);
        service.add(omsk);
        service.add(rostov);



        System.out.println(service.getDistance("Moscow","Rostov-na-Donu"));
        System.out.println(service.getCitiesNear("Moscow",2000));

    }
}
