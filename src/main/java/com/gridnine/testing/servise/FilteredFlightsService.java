package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface FilteredFlightsService {
    List<Flight> eraseDepartureToCurrentPointInTime(List<Flight> listFlight, String textMessage);
    List<Flight> eraseSegmentsWithArrivalDateEarlieThanDepartureDate(List<Flight> listFlight, String textMessage);
    List<Flight> eraselightsWhereLandingMoreTwwoHoudrs(List<Flight> listFlight, String textMessage);

    List<Flight> eraseDepartureToCurrentPointInTime(List<Flight> listFlight);
    List<Flight> eraseSegmentsWithArrivalDateEarlieThanDepartureDate(List<Flight> listFlight);
    List<Flight> eraselightsWhereLandingMoreTwwoHoudrs(List<Flight> listFlight);
    boolean searchFlightWhereLandingMorreThenTwoHours(List<Segment> listSegments);
    Duration getDuration(LocalDateTime time1, LocalDateTime time2);
    void inputfilterFlights(List<Flight> filterFlights);
}
