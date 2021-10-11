package militaryElite;

import militaryElite.entities.*;
import militaryElite.enums.Corp;
import militaryElite.enums.State;

import java.util.*;

public class Main {
    public static Map<Integer, PrivateImpl> privateById = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine;
        List<SoldierImpl> soldiers = new ArrayList<>();

        while (!"End".equals(inputLine = scan.nextLine())) {

            SoldierImpl soldier = createSoldier(inputLine);
            if (soldier != null) {
                soldiers.add(soldier);
            }
        }

        for (SoldierImpl soldier : soldiers) {
            System.out.println(soldier.toString());
        }
    }

    private static SoldierImpl createSoldier(String inputLine) {
        String[] tokens = inputLine.split("\\s+");
        int id = Integer.parseInt(tokens[1]);
        String firstName = tokens[2];
        String lastName = tokens[3];

        SoldierImpl soldier = null;

        switch (tokens[0]) {
            case "Private":
                double salary = Double.parseDouble(tokens[4]);
                PrivateImpl priv = new PrivateImpl(id, firstName, lastName, salary);
                privateById.put(id, priv);
                soldier = priv;
                break;
            case "LeutenantGeneral":
                LieutenantGeneralImpl lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, Double.parseDouble(tokens[4]));
                for (int i = 5; i < tokens.length; i++) {
                    lieutenantGeneral.addPrivate(privateById.get(Integer.parseInt(tokens[i])));
                }
                soldier = lieutenantGeneral;
                break;
            case "Engineer":
                String corp = tokens[5];
                if (isCorpValid(corp)) {
                    EngineerImpl engineer =
                            new EngineerImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), Corp.valueOf(corp.toUpperCase()));

                    for (int i = 6; i < tokens.length; i = i + 2) {
                        Repair repair = new Repair(tokens[i], Integer.parseInt(tokens[i + 1]));
                        engineer.addRepair(repair);
                    }
                    soldier = engineer;
                }
                break;

            case "Commando":
                corp = tokens[5];
                if (isCorpValid(corp)) {
                    CommandoImpl commando =
                            new CommandoImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), Corp.valueOf(corp.toUpperCase()));
                    for (int i = 6; i < tokens.length; i = i + 2) {
                        if (tokens[i + 1].equals("inProgress") || tokens[i + 1].equals("Finished")) {
                            commando.addMission(new Mission(tokens[i], State.valueOf(tokens[i + 1].toUpperCase())));
                        }
                    }
                    soldier=commando;
                }
                break;
            case "Spy":
                soldier= new SpyImpl(id, firstName, lastName,tokens[4]);
                break;
        }
        return soldier;
    }

    private static boolean isCorpValid(String corp) {
        return corp.equals("Airforces") || corp.equals("Marines");
    }
}

