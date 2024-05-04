package com.gridnine.testing;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    private static List<Flight> listFlight;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LocalDateTime now = LocalDateTime.now();

        listFlightSortedOne(listFlight);
//        inputfilterFlights();
//        System.out.println("listFlightSortedOne(listFlight) = " + );
    }

    private static List<Flight> listFlightSortedOne(List<Flight> listFlight) {
        List<Flight> list = listFlight.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
        inputfilterFlights(list);
        return list;
    }


    private static void inputfilterFlights(List<Flight> filterFlights){
        int j = 1;
        for (Flight filterFlight : filterFlights) {
            int i = 0;
            System.out.println("Номер перелета: \n" + j++);
            for (Segment segment : filterFlight.getSegments()) {
                i++;
                int k = i + 1;
                System.out.println("" + k + ") отправление " + segment.getArrivalDate() + "\t прибытие  "
                        + segment.getDepartureDate());
            }
        }
    }
}