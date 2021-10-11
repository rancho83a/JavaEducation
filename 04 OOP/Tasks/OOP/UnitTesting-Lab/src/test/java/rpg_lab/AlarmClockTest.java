package rpg_lab;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AlarmClockTest {

    @Test
    public void isAlarmOn() {
        while (true) {

            try {
                simulateTest();
                System.out.println("Passed");
            } catch (AssertionError err) {
                System.out.println("Failed");
            }
        }

    }

    public void simulateTest() {
       // Time time = new Time();
        Time time = Mockito.mock(Time.class);
        when(time.isMorning()).thenReturn(true);
        AlarmClock alarmClock = new AlarmClock(time);

        assertTrue(alarmClock.isAlarmOn());
    }

}