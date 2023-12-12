package AirlineFlightSchedule;

public class Airline {
    // data attributes
    private String code;
    
    // constructors
    public Airline() {
    }
    
    public Airline( String code ) {
        this.code = code;
    }
    
    // operations
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}//class Airline

