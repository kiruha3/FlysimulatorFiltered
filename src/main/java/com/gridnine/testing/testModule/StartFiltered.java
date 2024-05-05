package com.gridnine.testing.testModule;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.servise.Impl.FilteredFlightsServiceImpl;

public class StartFiltered {
    public static void startFiltered() {
        final FilteredFlightsServiceImpl filteredFlightsService = new FilteredFlightsServiceImpl();
        System.out.println("Рейсы без отбора:");
        filteredFlightsService.inputfilterFlights(FlightBuilder.createFlights());

        System.out.println("Рейсы с Вылетом до текущего момента времени были исключены:" );
        filteredFlightsService.eraseDepartureToCurrentPointInTime(FlightBuilder.createFlights(),"eraseDepartureToCurrentPointInTime");

        System.out.println("Рейсы, время прибытия которых в Сегменты с датой прилёта раньше даты вылета были исключены.:" );
        filteredFlightsService.eraseSegmentsWithArrivalDateEarlieThanDepartureDate(FlightBuilder.createFlights(),"eraseSegmentsWithArrivalDateEarlieThanDepartureDate");

        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа были исключены :" );
        filteredFlightsService.eraselightsWhereLandingMoreTwwoHoudrs(FlightBuilder.createFlights(),"eraselightsWhereLandingMoreTwwoHoudrs");
    }
}
