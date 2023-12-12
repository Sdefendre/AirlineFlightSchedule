package AirlineFlightSchedule;

public class Main{

    public static void main(String[] args) {
        Menu menu = new Menu();
        FlightSchedule schedule = new FlightSchedule();
        schedule.populateDebugData();
        
        int userChoice = 0;
        while(userChoice != Menu.Exit) {
            menu.display();
            switch ( userChoice = menu.getUserChoice() ) {

                case Menu.Set_Clock:
                    schedule.setDateTime();
                    break;
                case Menu.Clear_Schedule:
                    schedule.clearSchedule();
                    //schedule = new FlightSchedule();
                    break;
                case Menu.Add_Airline:
                    schedule.addAirline();
                    break;
                case Menu.Add_Flight:
                    schedule.addFlight();
                    break;
                case Menu.Cancel_Flight:
                    //System.out.println("*** UNIMPLEMENTED ***");
                    schedule.cancelFlight();
                    break;
                case Menu.Show_Flight_Info:
                    schedule.showFlightInfo();
                    break;
                case Menu.Show_Departures:
                    schedule.showDepartures();
                    break;
                case Menu.Show_Arrivals:
                    //System.out.println("*** UNIMPLEMENTED ***");
                    schedule.showArrivals();
                    break;
                case Menu.Find_Flights_Between_Two_Airports:
                    //System.out.println("*** UNIMPLEMENTED ***");
                    schedule.findFlightsBetweenTwoAirports();
                    break;
                case Menu.Exit:
                    String isExit = Validator.getString("Do you really want to exit? Y/N ");
                    if (isExit.charAt(0) == 'y' || isExit.charAt(0) == 'Y' ) {
                        return;
                    }
                    userChoice = 0; // continue to the menu
                    break;
                    
                default:
                    // program logic error 
                    System.out.println("Error: Invalid choice. Please try again.");                  
                    break;

            }
        }

    }//main
    
}//class MainApp
