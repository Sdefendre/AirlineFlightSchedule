package AirlineFlightSchedule;

public class DepartureArrivalInfo {

    // data attributes
    private char dayOfWeek; // (use single character encoding: U, M, T, W, R, F, S)
    private int time; // use military time
    private String airportCode;
    private String gate;
    
    // Constructors
    public DepartureArrivalInfo(char dayOfWeek, int time, String airportCode, String gate) {
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.airportCode = airportCode;
        this.gate = gate;
    }

    // Operations
    public char getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(char dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    
}//class DepartureArrivalInfo

