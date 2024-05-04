package com.gridnine.testing;


import java.util.List;

import static com.gridnine.testing.FilteredFlights.*;

public class Main {
    private static List<Flight> listFlight;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        listFlight = FlightBuilder.createFlights();

        FlightBuilder.createFlights().stream().forEach(System.out::println);
        eraseDepartureToCurrentPointInTime(FlightBuilder.createFlights(),"eraseDepartureToCurrentPointInTime");
        eraseSegmentsWithArrivalDateEarlieThanDepartureDate(FlightBuilder.createFlights(),"eraseSegmentsWithArrivalDateEarlieThanDepartureDate");
        eraselightsWhereLandingMoreTwwoHoudrs(FlightBuilder.createFlights(),"eraselightsWhereLandingMoreTwwoHoudrs");
    }


}