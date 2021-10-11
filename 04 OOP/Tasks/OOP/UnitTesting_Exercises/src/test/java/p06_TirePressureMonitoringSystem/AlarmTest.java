package p06_TirePressureMonitoringSystem;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AlarmTest {

    @Test
    public void runAll() throws InterruptedException{
        int counter=1;
        while(true) {
            testAlarmWithLowPressure();
            testAlarmWithHighPressure();
            testAlarmWithNormalPressure();
            System.out.println(counter++);
        }
    }


    public void testAlarmWithLowPressure() {

         Sensor mockSensor = new Sensor();
//        Sensor mockSensor = Mockito.mock(Sensor.class);
//        when(mockSensor.popNextPressurePsiValue()).thenReturn(1.0);

        Alarm alarm = new Alarm(mockSensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());

    }


    public void testAlarmWithHighPressure() {

        // Sensor sensor = new Sensor();
        Sensor mockSensor = Mockito.mock(Sensor.class);
        when(mockSensor.popNextPressurePsiValue()).thenReturn(22.0);

        Alarm alarm = new Alarm(mockSensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }


    public void testAlarmWithNormalPressure() {

        // Sensor sensor = new Sensor();
        Sensor mockSensor = Mockito.mock(Sensor.class);
        when(mockSensor.popNextPressurePsiValue()).thenReturn(18.0);

        Alarm alarm = new Alarm(mockSensor);
        alarm.check();
        assertFalse(alarm.getAlarmOn());
    }
}