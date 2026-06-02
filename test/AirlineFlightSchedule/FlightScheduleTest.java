package AirlineFlightSchedule;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FlightScheduleTest {
    private FlightSchedule schedule;

    @Before
    public void setUp() {
        schedule = new FlightSchedule();
        schedule.populateDebugData();
    }

    @Test
    public void testGetAirlineExisting() {
        Airline airline = schedule.getAirline("DL");
        assertNotNull("Airline DL should be found", airline);
        assertEquals("DL", airline.getCode());
    }

    @Test
    public void testGetAirlineNonExisting() {
        Airline airline = schedule.getAirline("XX");
        assertNull("Airline XX should not be found", airline);
    }

    @Test
    public void testGetAirlineNull() {
        // This is now handled safely and should return null
        Airline airline = schedule.getAirline(null);
        assertNull("Retrieving null code should return null", airline);
    }

    @Test
    public void testGetAirlineEmpty() {
        Airline airline = schedule.getAirline("");
        assertNull("Retrieving empty code should return null", airline);
    }

    @Test
    public void testIsValidAirlineCodeExisting() {
        assertTrue("DL should be valid", schedule.isValidAirlineCode("DL"));
    }

    @Test
    public void testIsValidAirlineCodeNonExisting() {
        assertFalse("XX should be invalid", schedule.isValidAirlineCode("XX"));
    }

    @Test
    public void testIsValidAirlineCodeNull() {
        // This is now handled safely and should return false
        assertFalse("Null code should be invalid", schedule.isValidAirlineCode(null));
    }

    @Test
    public void testIsValidAirlineCodeEmpty() {
        assertFalse("Empty code should be invalid", schedule.isValidAirlineCode(""));
    }
}
