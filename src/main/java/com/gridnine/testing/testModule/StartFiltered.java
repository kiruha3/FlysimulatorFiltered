package com.gridnine.testing.testModule;

import com.gridnine.testing.builder.FlightBuilder;
import static com.gridnine.testing.testModule.FilteredFlights.*;

public class StartFiltered {
    public static void startFiltered() {
        System.out.println("Рейсы без отбора:");
        inputfilterFlights(FlightBuilder.createFlights());

        System.out.println("Рейсы с Вылетом до текущего момента времени были исключены:" );
        eraseDepartureToCurrentPointInTime(FlightBuilder.createFlights(),"eraseDepartureToCurrentPointInTime");

        System.out.println("Рейсы, время прибытия которых в Сегменты с датой прилёта раньше даты вылета были исключены.:" );
        eraseSegmentsWithArrivalDateEarlieThanDepartureDate(FlightBuilder.createFlights(),"eraseSegmentsWithArrivalDateEarlieThanDepartureDate");

        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа были исключены :" );
        eraselightsWhereLandingMoreTwwoHoudrs(FlightBuilder.createFlights(),"eraselightsWhereLandingMoreTwwoHoudrs");
    }
}
