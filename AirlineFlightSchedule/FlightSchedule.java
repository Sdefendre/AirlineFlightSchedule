package AirlineFlightSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Collections;

public class FlightSchedule {

    // data attributes
    private int currentTime; // in range 0 to 2359
    private char currentDay; // U, M, T, W, R, F, S
    private ArrayList<Airline> airlines;
    private HashMap< String/*DL100*/, Flight> flightMap;

    public void populateDebugData() {
        airlines.add(new Airline("DL"));
        airlines.add(new Airline("AA"));
        airlines.add(new Airline("LH"));
        Random random = new Random();
        for (Airline airline : airlines) {
            int count = 3; // create this many random fights for each airline
            while (count-- > 0) {
                // randomly construct a value in range 100 - 999
                int number
                        = 100 * (1 + random.nextInt(9)/*1-9*/)
                        + random.nextInt(99);
                Flight flight = new Flight(airline, number);

                int time = (random.nextInt(2359 - 300) / 10) * 10;
                if (time % 100 > 60) {
                    time = time - 40;
                }
                flight.setDepartureInfo(
                        new DepartureArrivalInfo(
                                'M', time, "BOS", "E" + (10 + random.nextInt(15))
                        )
                );
                flight.setArrivalInfo(
                        new DepartureArrivalInfo(
                                'M', time + 200, "ATL", "A" + (10 + random.nextInt(15))
                        )
                );
                flightMap.put(flight.getFlightNumber(), flight);
            }
        }
    }

    private ArrayList<Flight> selectSortedDepartures(String airportCode, String dayOfWeek) {
        // construct temporary array of flight objects
        ArrayList<Flight> flights = new ArrayList<>();
        // gather matching flights
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (
                    (    airportCode.equals(flight.getDepartureInfo().getAirportCode())     )
                    && // logical AND
                    (    dayOfWeek.charAt(0) ==    flight.getDepartureInfo().getDayOfWeek() )
               )
            {
                flights.add(flight);
            }
        }
        // sort by departure time
        Collections.sort(flights);
        return flights;
        
    }//selectSortedDepartures

    public void showDepartures() {
        System.out.println("--- Departures --- ");
        String airportCode = Validator.getString("Enter Airport Code: ");
        String dayOfWeek = Validator.getString("Day M/T/.../S/U: ");

        System.out.println("____________________________");

        displayFlightHeader();

        ArrayList<Flight> sortedFlights = selectSortedDepartures(airportCode, dayOfWeek);
        for (Flight flight : sortedFlights) {
            displayOneFlight(flight);
            System.out.println();
        }

        System.out.println("____________________________");
        Validator.pause();
    }

    public void showUnsortedDepartures() {
        System.out.println("--- Departures --- ");
        String airportCode = Validator.getString("Enter Airport Code: ");

        System.out.println("____________________________");

        displayFlightHeader();
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (airportCode.equals(flight.getDepartureInfo().getAirportCode())) {
                displayOneFlight(flight);
                System.out.println();
            }
        }

        System.out.println("____________________________");
        Validator.pause();
    }

    public void showFlightInfo() {
        System.out.println("--- Flight Info --- ");
        String airlineCode = Validator.getString("Enter Airline Code: ");
        if (!isValidAirlineCode(airlineCode)) {
            System.out.println("[" + airlineCode + "] is not found");
            Validator.pause();
            return;
        }
        System.out.println("____________________________");
        displayFlightHeader();
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            //String flightNumber = entry.getKey();
            Flight flight = entry.getValue();
            if (airlineCode.equals(flight.getAirline().getCode())) {
                displayOneFlight(flight);
                System.out.println();
            }
        }
        System.out.println("____________________________");
        Validator.pause();
    }

    public void displayFlightHeader() {
        //DL618	M	BOS	E16	150	M	ATL	A18	350
        System.out.println("\tDeparture\t\t\tArrival");
        System.out.println("Flight\tDay\tFrom\tGate\tTime\tDay\tTo\tGate\tTime");
        System.out.println("-------------------------------------------------------");
    }

    public void displayOneFlight(Flight flight) {
        //DL618	M	BOS	E16	150	M	ATL	A18	350
        System.out.print(flight.getFlightNumber());
        System.out.print('\t');
        System.out.print(flight.getDepartureInfo().getDayOfWeek());
        System.out.print('\t');
        System.out.print(flight.getDepartureInfo().getAirportCode());
        System.out.print('\t');
        System.out.print(flight.getDepartureInfo().getGate());
        System.out.print('\t');
        System.out.print(flight.getDepartureInfo().getTime());

        System.out.print('\t');
        System.out.print(flight.getArrivalInfo().getDayOfWeek());
        System.out.print('\t');
        System.out.print(flight.getArrivalInfo().getAirportCode());
        System.out.print('\t');
        System.out.print(flight.getArrivalInfo().getGate());
        System.out.print('\t');
        System.out.print(flight.getArrivalInfo().getTime());
    }

    // constructors
    public FlightSchedule() {
        resetSchedule();
    }

    // operations
    public void clearSchedule() {
        System.out.println("--- Clear Schedule --- ");
        // ask user for confirmation
        String answer = Validator.getString("Are you sure you want to clear the schedule? (y/n): ");
        if (answer.charAt(0) != 'y') {
            return;
        }
        resetSchedule();
    }

    private void resetSchedule() {
        currentTime = 0;
        currentDay = 'M';
        airlines = new ArrayList<>();
        flightMap = new HashMap<>();
    }

    public void setDateTime() {
        System.out.println("1. Set Clock");

        System.out.println("Current Day is [" + currentDay + "] and time [" + currentTime + "]");

        System.out.println();
        String day = Validator.getString("Enter U, M, T, W, R, F, or S for corresponding day of the week: ");
        currentDay = day.charAt(0);

        System.out.println();
        currentTime = Validator.getInt("Please enter time in military notation: ", 0, 2359);
        System.out.println("Day set to [" + currentDay + "] and time [" + currentTime + "]");
        Validator.pause();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public char getCurrentDay() {
        return currentDay;
    }

    public void addAirline() {
        System.out.println("---- Add New Airline ----");
        String code = Validator.getString("Enter Airline code: ");
        code = code.toUpperCase();

        if (isValidAirlineCode(code)) {
            System.out.println("*** Airline with this code is already on file");
            Validator.pause();
            return;
        }
        Airline airline = new Airline();
        airline.setCode(code);
        airlines.add(airline);
        System.out.println("Airline [" + code + "] sucessfully added.");
        Validator.pause();
    }//addAirline

    public boolean isValidAirlineCode(String code) {
        for (Airline oneAirline : airlines) {
            if (code.equals(oneAirline.getCode())) {
                return true;
            }
        }
        return false;
    }//isValidAirlineCode

    public Airline getAirline(String code) {
        for (Airline oneAirline : airlines) {
            if (code.equals(oneAirline.getCode())) {
                return oneAirline;
            }
        }
        return null;
    }//getAirline

    public boolean isFlightExist(String flightNumber/*DL100*/) {
        return flightMap.containsKey(flightNumber);
    }

    public void addFlight() {
        System.out.println("---- Add New Flight ----");
        String airlineCode = Validator.getString("Airline code: ");
        if (!isValidAirlineCode(airlineCode)) {
            System.out.println("[" + airlineCode + "] code is not on file: ");
            Validator.pause();
            return;
        }
        Airline airline = getAirline(airlineCode);
        int flightNumber = Validator.getInt("Flight number: ");

        Flight flight = new Flight(airline, flightNumber);
        // check if this flight already on file,
        // if so, tell the user and exit...
        if (isFlightExist(flight.getFlightNumber())) {
            System.out.println("[" + flight.getFlightNumber() + "] already on file");
            Validator.pause();
            return;
        }
        flightMap.put(flight.getFlightNumber()/*DL100*/, flight);

        char dayOfWeek = Validator.getString("Departure Day: U, M, T, W, R, F, S ").charAt(0);
        int time = Validator.getInt("Departure time in military notation: ", 0, 2359);
        String airportCode = Validator.getString("Departure Airport: ");
        String gate = Validator.getString("Departure Gate: ");

        flight.setDepartureInfo(
                new DepartureArrivalInfo(dayOfWeek, time, airportCode, gate)
        );

        dayOfWeek = Validator.getString("Arrival Day: U, M, T, W, R, F, S ").charAt(0);
        time = Validator.getInt("Arrival time in military notation: ", 0, 2359);
        airportCode = Validator.getString("Arrival Airport: ");
        gate = Validator.getString("Arrival Gate: ");

        flight.setArrivalInfo(
                new DepartureArrivalInfo(dayOfWeek, time, airportCode, gate)
        );

    }//addFlight

    public void cancelFlight() {
        System.out.println("---- Cancel Flight ----");
        String flightNumber = Validator.getString("Enter flight number: ");
        if (!isFlightExist(flightNumber)) {
            System.out.println("Flight [" + flightNumber + "] is not on file");
            Validator.pause();
            return;
        }
        flightMap.remove(flightNumber);
        System.out.println("Flight [" + flightNumber + "] is cancelled");
        Validator.pause();
    }//cancelFlight

    public void showArrivals() {
        System.out.println("--- Arrivals --- ");
        String airportCode = Validator.getString("Enter Airport Code: ");
        String dayOfWeek = Validator.getString("Day M/T/.../S/U: ");

        System.out.println("____________________________");

        displayFlightHeader();

        ArrayList<Flight> sortedFlights = selectSortedArrivals(airportCode, dayOfWeek);
        for (Flight flight : sortedFlights) {
            displayOneFlight(flight);
            System.out.println();
        }

        System.out.println("____________________________");
        Validator.pause();
    }

    // show arrivals for all airlines
    public void showUnsortedArrivals() {
        System.out.println("--- Arrivals --- ");
        String airportCode = Validator.getString("Enter Airport Code: ");

        System.out.println("____________________________");

        displayFlightHeader();
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (airportCode.equals(flight.getArrivalInfo().getAirportCode())) {
                displayOneFlight(flight);
                System.out.println();
            }
        }

        System.out.println("____________________________");
        Validator.pause();
    }
    // show arrivals for selected airline based on airport code and day of week
    private ArrayList<Flight> selectSortedArrivals(String airportCode, String dayOfWeek) {
        // construct temporary array of flight objects
        ArrayList<Flight> flights = new ArrayList<>();
        // gather matching flights
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (
                    (    airportCode.equals(flight.getArrivalInfo().getAirportCode())     )
                    && // logical AND
                    (    dayOfWeek.charAt(0) ==    flight.getArrivalInfo().getDayOfWeek() )
               )
            {
                flights.add(flight);
            }
        }
        // sort by departure time
        Collections.sort(flights);
        return flights;
        
    }//selectSortedArrivals

    public void findFlightsBetweenTwoAirports() {
        System.out.println("--- Find Flights Between Two Airports --- ");
        String airportCode1 = Validator.getString("Enter Airport Code 1: ");
        String airportCode2 = Validator.getString("Enter Airport Code 2: ");
        String dayOfWeek = Validator.getString("Day M/T/.../S/U: ");

        System.out.println("____________________________");

        displayFlightHeader();

        ArrayList<Flight> sortedFlights = selectSortedFlightsBetweenTwoAirports(airportCode1, airportCode2, dayOfWeek);
        for (Flight flight : sortedFlights) {
            displayOneFlight(flight);
            System.out.println();
        }

        System.out.println("____________________________");
        Validator.pause();
    }
    // show arrivals for selected airline based on airport code and day of week
    private ArrayList<Flight> selectSortedFlightsBetweenTwoAirports(String airportCode1, String airportCode2, String dayOfWeek) {
        // construct temporary array of flight objects
        ArrayList<Flight> flights = new ArrayList<>();
        // gather matching flights
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (
                    (    airportCode1.equals(flight.getDepartureInfo().getAirportCode())     )
                    && // logical AND
                    (    airportCode2.equals(flight.getArrivalInfo().getAirportCode())     )
                    && // logical AND
                    (    dayOfWeek.charAt(0) ==    flight.getDepartureInfo().getDayOfWeek() )
               )
            {
                flights.add(flight);
            }
        }
        // sort by departure time
        Collections.sort(flights);
        return flights;
        
    }//selectSortedFlightsBetweenTwoAirports



}//class FlightSchedule
