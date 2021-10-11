package rpg_lab;

public class AlarmClock {

    private Time time;

    public AlarmClock (Time time){

        this.time = time;
    }

    public boolean isAlarmOn(){
        return time.isMorning();
    }
}
