package com.gridnine.testing.testModule;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FilteredFlights {

    public static List<Flight> eraseDepartureToCurrentPointInTime(List<Flight> listFlight, String textMessage) {
        List<Flight> listeWithoutEraseDepartureToCurrentPointInTime;
        listeWithoutEraseDepartureToCurrentPointInTime = listFlight.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate()
                                .isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());

        System.out.println("\ntextMessage = " + textMessage);
        inputfilterFlights(listeWithoutEraseDepartureToCurrentPointInTime);
        return listeWithoutEraseDepartureToCurrentPointInTime;
    }

    public static List<Flight> eraseSegmentsWithArrivalDateEarlieThanDepartureDate(List<Flight> listFlight, String textMessage) {
        List<Flight> listWithoutSegmentsWithArrivalDateEarlieThanDepartureDate;
        listWithoutSegmentsWithArrivalDateEarlieThanDepartureDate = listFlight.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate()
                                .isBefore(segment.getArrivalDate())))
                .collect(Collectors.toList());

        System.out.println("\ntextMessage = " + textMessage+"\n");

        inputfilterFlights(listWithoutSegmentsWithArrivalDateEarlieThanDepartureDate);
        return listWithoutSegmentsWithArrivalDateEarlieThanDepartureDate;
    }

    public static List<Flight> eraselightsWhereLandingMoreTwwoHoudrs(List<Flight> listFlight, String textMessage) {
        List<Flight> listWithoutEraseFlightsArrivalAfterNow =
                listFlight.stream()
                .filter(flight ->
                        searchFlightWhereLandingMorreThenTwoHours(flight.getSegments()))
                .collect(Collectors.toList());

        System.out.println("\ntextMessage = " + textMessage+"\n");

        inputfilterFlights(listWithoutEraseFlightsArrivalAfterNow);
        return listWithoutEraseFlightsArrivalAfterNow;
    }

    public static boolean searchFlightWhereLandingMorreThenTwoHours(List<Segment> listSegments) {
        int listSize = listSegments.size();
        int i = 0;
        if (listSize <= 1) {
            return true;
        } else {
            for (Segment listSegment : listSegments) {
                i++;
                if (i >= listSize) {
                    return true;
                } else {
                    if (getDuration(listSegment.getArrivalDate(), listSegments.get(i).getDepartureDate()).getSeconds() > 7200) {
                        System.out.println("Исключен "+" разница составила:"+getDuration(listSegment.getArrivalDate(), listSegments.get(i).getDepartureDate()).getSeconds()/60 + " мин");
                        for (Segment segment : listSegments) {
                            System.out.println(segment);
                        }
                        return false;
                    }
                }
            }
        }
        return true;

    }

    private static Duration getDuration(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2);
    }

    static void inputfilterFlights(List<Flight> filterFlights) {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd 'T'HH:mm");

        int j = 1;
        for (Flight filterFlight : filterFlights) {
            int i = 0;
            System.out.print("Номер перелета: " + j++);
            for (Segment segment : filterFlight.getSegments()) {
                i++;
                System.out.print("| (" + i + ") Отправление [" + segment.getDepartureDate().format(fmt) +
                        " | <-> | прибытие " + segment.getArrivalDate().format(fmt) + "] ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------");
    }
}
