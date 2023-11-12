package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TravelServiceTest {
    GeoPosition omskPosition = new GeoPosition(54, 59, 47, 73, 22, 20);
    GeoPosition moscowPosition = new GeoPosition(55, 45, 21, 37, 37, 1);
    GeoPosition rostovPosition = new GeoPosition(47, 14, 53, 39, 42, 39);
    CityInfo moscow = new CityInfo("Moscow", moscowPosition);
    CityInfo omsk = new CityInfo("Omsk", omskPosition);
    CityInfo rostov=new CityInfo("Rostov-na-Donu",rostovPosition);
    CityInfo doubleMoscow = new CityInfo("Moscow", moscowPosition);
    TravelService service = new TravelService();


    @Test
    void getDistance() {
        service.add(moscow);
        service.add(omsk);
        service.add(rostov);

        assertEquals(957,service.getDistance(rostov.getName(),moscow.getName()));
    }

    @Test
    void getCitiesNear() {
        service.add(moscow);
        service.add(omsk);
        service.add(rostov);

        assertEquals("[Rostov-na-Donu]",service.getCitiesNear("Moscow",1000).toString());

    }
}