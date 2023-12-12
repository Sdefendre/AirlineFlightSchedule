package AirlineFlightSchedule;

public class Flight implements Comparable<Flight> {
    public static final int ORDER_BY_DEPARTURE_TIME = 1;
    public static final int ORDER_BY_ARRIVAL_TIME = 2;
    public static int comparisonType = ORDER_BY_DEPARTURE_TIME;
    
    private Airline airline;
    private int number;          // 100   
    private String flightNumber; // "DL100"
    
    private DepartureArrivalInfo departureInfo;
    private DepartureArrivalInfo arrivalInfo;
    
    // constructor
    public Flight( Airline airline, int number ) {
        this.airline = airline;
        this.number = number;
        this.flightNumber = airline.getCode() + number;
    }
    
    // operations
    @Override
    public int compareTo( Flight another ) {
        // if this < another -- return -1
        // if this == another -- return 0
        // if this > another -- return +1
        if ( comparisonType == ORDER_BY_DEPARTURE_TIME) {
            if ( this.getDepartureInfo().getTime() < another.getDepartureInfo().getTime() ) {
                return -1;
            } else if ( this.getDepartureInfo().getTime() > another.getDepartureInfo().getTime() ) {
                return +1;
            }
            return 0;
            
        } else if ( comparisonType == ORDER_BY_ARRIVAL_TIME ) {
            if ( this.getArrivalInfo().getTime() < another.getArrivalInfo().getTime() ) {
                return -1;
            } else if ( this.getArrivalInfo().getTime() > another.getArrivalInfo().getTime() ) {
                return +1;
            }
            return 0;
            
        } else {
            return 0;
        }
    }
    
    public DepartureArrivalInfo getDepartureInfo() {
        return departureInfo;
    }

    public void setDepartureInfo(DepartureArrivalInfo departureInfo) {
        this.departureInfo = departureInfo;
    }

    public DepartureArrivalInfo getArrivalInfo() {
        return arrivalInfo;
    }

    // operations
    public void setArrivalInfo(DepartureArrivalInfo arrivalInfo) {
        this.arrivalInfo = arrivalInfo;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
        this.flightNumber = airline.getCode() + number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.flightNumber = airline.getCode() + number;
    }

    public String getFlightNumber() {
        return flightNumber;
    }    
    
}
