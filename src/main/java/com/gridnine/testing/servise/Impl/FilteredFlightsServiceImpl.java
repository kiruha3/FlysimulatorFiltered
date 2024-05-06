package com.gridnine.testing.servise.Impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.servise.FilteredFlightsService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FilteredFlightsServiceImpl implements FilteredFlightsService {
    //Метод eraseDepartureToCurrentPointInTime с выводом названия метода нужен для наглядности
    @Override
    public List<Flight> eraseDepartureToCurrentPointInTime(List<Flight> listFlight, String textMessage) {
        List<Flight> listeWithoutEraseDepartureToCurrentPointInTime = eraseDepartureToCurrentPointInTime(listFlight);

        System.out.println("\n textMessage = " + textMessage);

        inputfilterFlights(listeWithoutEraseDepartureToCurrentPointInTime);

        return listeWithoutEraseDepartureToCurrentPointInTime;
    }

    //Метод eraseDepartureToCurrentPointInTime без вывода названия метода
    /**
     * eraseDepartureToCurrentPointInTime() должен исключить Вылеты до текущего момента времени\
     *
     * реализован с помощью stream, используется фильтрация
     * получаем полет, делим на сегменты получаем сегменты и с ними работаем
     * собираем в список
     * и возвращаем обработанный список
     *  **/
    @Override
    public List<Flight> eraseDepartureToCurrentPointInTime(List<Flight> listFlight) {
        return listFlight
                .stream()
                .filter(flight -> flight.getSegments().stream().allMatch(segment -> segment.getDepartureDate()
                        .isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    //Метод eraseSegmentsWithArrivalDateEarlieThanDepartureDate с выводом названия метода нужен для наглядности
    @Override
    public List<Flight> eraseSegmentsWithArrivalDateEarlieThanDepartureDate(List<Flight> listFlight, String textMessage) {
        List<Flight> listWithoutSegmentsWithArrivalDateEarlieThanDepartureDate =eraseSegmentsWithArrivalDateEarlieThanDepartureDate(listFlight);

        System.out.println("textMessage = " + textMessage);

        inputfilterFlights(listWithoutSegmentsWithArrivalDateEarlieThanDepartureDate);

        return listWithoutSegmentsWithArrivalDateEarlieThanDepartureDate;
    }

    //Метод eraseSegmentsWithArrivalDateEarlieThanDepartureDate без вывода названия метода
    /**
     * eraseSegmentsWithArrivalDateEarlieThanDepartureDate() должен исключить ЕСЛИ ЕСТЬ сегменты с датой прилёта раньше даты вылета
     *
     * реализован с помощью stream, используется фильтрация
     * получаем полет, делим на сегменты получаем сегменты и с ними работаем
     * собираем в список
     * и возвращаем обработанный список
     *  **/
    @Override
    public List<Flight> eraseSegmentsWithArrivalDateEarlieThanDepartureDate(List<Flight> listFlight) {
        return listFlight
                .stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .collect(Collectors.toList());
    }

    //Метод eraselightsWhereLandingMoreTwwoHoudrs с выводом названия метода нужен для наглядности
    @Override
    public List<Flight> eraselightsWhereLandingMoreTwwoHoudrs(List<Flight> listFlight, String textMessage) {
        List<Flight> listWithoutEraseFlightsArrivalAfterNow = eraselightsWhereLandingMoreTwwoHoudrs(listFlight);

        System.out.println("textMessage = " + textMessage);

        inputfilterFlights(listWithoutEraseFlightsArrivalAfterNow);

        return listWithoutEraseFlightsArrivalAfterNow;
    }


    //Метод eraselightsWhereLandingMoreTwwoHoudrs без вывода названия метода
    /**
     * eraselightsWhereLandingMoreTwwoHoudrs() должен исключить ЕСЛИ ЕСТЬ сегменты Общее время, КОТОРЫХ проведённое на земле превышает два часа
     * <p>
     * реализован с помощью stream, используется фильтрация
     * получаем полет, делим на сегменты получаем сегменты и с ними работаем
     * собираем в список
     * и возвращаем обработанный список
     *  **/
    @Override
    public List<Flight> eraselightsWhereLandingMoreTwwoHoudrs(List<Flight> listFlight) {
        return listFlight.stream()
                .filter(flight ->
                        searchFlightWhereLandingMorreThenTwoHours(flight.getSegments()))
                .collect(Collectors.toList());
    }

    //реализация модуля для поиска для отсеивания рейсов с нахождением 2 часов между прилетом и вылетом следующего рейса на земле
    /**
     * searchFlightWhereLandingMorreThenTwoHours() Реализация поиска диапазона между прилетом и вылетом более 2 часов,
     * в случае нахождения возвращает
     * false
     * для пропуска элемента
     *
     * реализован с помощью stream, используется фильтрация
     * получаем полет, делим на сегменты получаем сегменты и с ними работаем
     * собираем в список
     * и возвращаем обработанный список
     **/
    @Override
    public boolean searchFlightWhereLandingMorreThenTwoHours(List<Segment> listSegments) {
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
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //реализация нахождения разницы между двумя датами
    @Override
    public Duration getDuration(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2);
    }


    //кастомный вывод перелетов, для просмотра перелетов\рейсов
    @Override
    public void inputfilterFlights(List<Flight> filterFlights) {
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
