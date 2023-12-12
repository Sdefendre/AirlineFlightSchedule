package AirlineFlightSchedule;

public class Menu {

    public static final int Set_Clock = 1;
    public static final int Clear_Schedule = 2;
    public static final int Add_Airline = 3;
    public static final int Add_Flight = 4;
    public static final int Cancel_Flight = 5;
    public static final int Show_Flight_Info = 6;
    public static final int Show_Departures = 7;
    public static final int Show_Arrivals = 8;
    public static final int Find_Flights_Between_Two_Airports = 9;
    public static final int Exit = 10;

    public void display() {
        System.out.println(
                "1. Set Clock\n"
                + "2. Clear Schedule\n"
                + "3. Add Airline\n"
                + "4. Add Flight\n"
                + "5. Cancel Flight\n"
                + "6. Show Flight Info\n"
                + "7. Show Departures\n"
                + "8. Show Arrivals\n"
                + "9. Find Flights Between Two Airports\n"
                + "10.Exit\n"
        );
    }//display

    public int getUserChoice() {
        return Validator.getInt(">>> ", Set_Clock, Exit);
    }//getUserChoice

}//class Menu

