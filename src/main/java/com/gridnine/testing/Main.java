package com.gridnine.testing;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        for (Flight flight : FlightBuilder.createFlights()) {
            System.out.println(flight);
        }


    }
}