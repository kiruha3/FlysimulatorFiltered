package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.servise.Impl.FilteredFlightsServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.builder.FlightBuilder.createFlight;
import static org.junit.Assert.assertEquals;

class FilteredFlightsServiceImplTest {

    @Test
    void eraseDepartureToCurrentPointInTime_Test() {
        final FilteredFlightsServiceImpl filteredFlightsServicelmpl = new FilteredFlightsServiceImpl();
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        List<Flight> listFlighWithDepartToCurrentPointInTime = Arrays.asList(
                createFlight(threeDaysFromNow.minusDays(10),threeDaysFromNow),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(1)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(42)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(32)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(22)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(12)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                createFlight(threeDaysFromNow.minusDays(36),threeDaysFromNow),
                createFlight(threeDaysFromNow.minusDays(0),threeDaysFromNow),
                createFlight(threeDaysFromNow,threeDaysFromNow.plusHours(5)));

        List <Flight> listFlighWitouthDepartToCurrentPointInTime = Arrays.asList(
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(1)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(42)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(32)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(22)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(12)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                createFlight(threeDaysFromNow.minusDays(0),threeDaysFromNow),
                createFlight(threeDaysFromNow,threeDaysFromNow.plusHours(5)));

        List<Flight> actualList =  filteredFlightsServicelmpl.eraseDepartureToCurrentPointInTime(listFlighWithDepartToCurrentPointInTime);
        List<Flight> exceptedList =  listFlighWitouthDepartToCurrentPointInTime;

        assertEquals(exceptedList.size(), actualList.size());
        for (int i = 0; i < exceptedList.size(); i++) {
            assertEquals(exceptedList.get(i).toString(), actualList.get(i).toString());
        }
    }

    @Test
    void eraseSegmentsWithArrivalDateEarlieThanDepartureDate_Test() {
        final FilteredFlightsServiceImpl filteredFlightsServicelmpl = new FilteredFlightsServiceImpl();
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        List<Flight> listFlighWithSegmentsWithArrivalDateEarlieThanDepartureDate = Arrays.asList(
                createFlight(threeDaysFromNow.minusDays(10),threeDaysFromNow),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(1)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(42)),
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(2)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(22)),
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(8)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                createFlight(threeDaysFromNow,threeDaysFromNow.minusDays(5)));

        List <Flight> listFlighWitouthSegmentsWithArrivalDateEarlieThanDepartureDate =  Arrays.asList(
                createFlight(threeDaysFromNow.minusDays(10),threeDaysFromNow),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(1)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(42)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(22)),
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)));

        List<Flight> actualList =  filteredFlightsServicelmpl.eraseSegmentsWithArrivalDateEarlieThanDepartureDate(listFlighWithSegmentsWithArrivalDateEarlieThanDepartureDate);
        List<Flight> exceptedList =  listFlighWitouthSegmentsWithArrivalDateEarlieThanDepartureDate;
        assertEquals(exceptedList.size(), actualList.size());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertEquals(exceptedList.get(i).toString(), actualList.get(i).toString());
        }
    }

    @Test
    void eraselightsWhereLandingMoreTwwoHoudrs_Test() {
        final FilteredFlightsServiceImpl filteredFlightsServicelmpl = new FilteredFlightsServiceImpl();
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        List<Flight> listFlighWithEraseFlightsWhereLandingMoreTwwoHoudrs = Arrays.asList(
                createFlight(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3),threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(9),threeDaysFromNow.plusHours(23), threeDaysFromNow.plusHours(25)),
                createFlight(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4)),
                createFlight(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)),
                createFlight(threeDaysFromNow.minusHours(4), threeDaysFromNow.plusHours(6),threeDaysFromNow.plusHours(7), threeDaysFromNow.plusHours(9)),
                createFlight(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3)),
                createFlight(threeDaysFromNow.minusHours(3), threeDaysFromNow.plusHours(4),threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(7)),
                createFlight(threeDaysFromNow.minusHours(2), threeDaysFromNow.minusHours(5)),
                createFlight(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3),threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(9),threeDaysFromNow.plusHours(23), threeDaysFromNow.plusHours(25))
        );

                List<Flight> listFlighWithoutEraselightsWhereLandingMoreTwoHoudrs = Arrays.asList(
                        createFlight(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3),threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(9),threeDaysFromNow.plusHours(23), threeDaysFromNow.plusHours(25)),
                        createFlight(threeDaysFromNow.minusHours(4), threeDaysFromNow.plusHours(6),threeDaysFromNow.plusHours(7), threeDaysFromNow.plusHours(9)),
                        createFlight(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3)),
                        createFlight(threeDaysFromNow.minusHours(3), threeDaysFromNow.plusHours(4),threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(7)),
                        createFlight(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3),threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(9),threeDaysFromNow.plusHours(23), threeDaysFromNow.plusHours(25))
                );

        List<Flight> actualList =  filteredFlightsServicelmpl.eraseSegmentsWithArrivalDateEarlieThanDepartureDate(listFlighWithEraseFlightsWhereLandingMoreTwwoHoudrs);
        List<Flight> exceptedList =  listFlighWithoutEraselightsWhereLandingMoreTwoHoudrs;
        assertEquals(exceptedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertEquals(actualList.get(i).toString(), actualList.get(i).toString());
        }
    }
}