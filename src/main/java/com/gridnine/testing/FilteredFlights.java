package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class FilteredFlights {
    public List<Flight> listFlightSortedOne(List<Flight> listFlight) {
        List<Flight> listArrivalBeforeDepartur = listFlight.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
        inputfilterFlights(listArrivalBeforeDepartur);
        return listArrivalBeforeDepartur;
    }
    public void inputfilterFlights(List<Flight> filterFlights){
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
