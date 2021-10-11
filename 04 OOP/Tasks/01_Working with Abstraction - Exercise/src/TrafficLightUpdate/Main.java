package TrafficLightUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] initialTrafficLightStates = scan.nextLine().split("\\s+");
        int times = Integer.parseInt(scan.nextLine());

        List<TrafficLight> listTrafficLight = new ArrayList<>();

        for (String initialTrafficLightState : initialTrafficLightStates) {
            TrafficLight trafficLight = new TrafficLight(TrafficLightState.valueOf(initialTrafficLightState));
            listTrafficLight.add(trafficLight);
        }

        while(times-->0){
            for (TrafficLight trafficLight : listTrafficLight) {
                trafficLight.update();

                System.out.print(trafficLight+" ");
            }
            System.out.println();
        }
    }
}
