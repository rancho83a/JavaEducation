package trafficLights;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TraficLights[] lightsInput = Arrays.stream(scan.nextLine().split("\\s+"))
                .map(TraficLights::valueOf).toArray(TraficLights[]::new);
        int n = scan.nextInt();

        TraficLights[] enumTtraficLights = TraficLights.values();

        while (n-- > 0) {
            for (int i = 0; i < lightsInput.length; i++) {
                int nextInd = (lightsInput[i].ordinal() + 1)%enumTtraficLights.length;
//                if(nextInd==enumTtraficLights.length){
//                    nextInd=0;
//                }
                System.out.print(enumTtraficLights[nextInd]+" ");
                lightsInput[i]=enumTtraficLights[nextInd];
            }
            System.out.println();
        }
    }
}