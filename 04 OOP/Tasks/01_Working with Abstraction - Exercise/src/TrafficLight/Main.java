package TrafficLight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] lights = scan.nextLine().split("\\s+");
        int times = Integer.parseInt(scan.nextLine());

        LightColor[] values = LightColor.values();
        while(times-->0){
            for (int i = 0; i < lights.length; i++) {
                int nextOrdinar = LightColor.valueOf(lights[i]).ordinal()+1;
                if (nextOrdinar == values.length){
                    nextOrdinar=0;
                }
                lights[i] = values[nextOrdinar].name();
                System.out.print(lights[i]+" ");
            }
            System.out.println();
        }
    }
}
