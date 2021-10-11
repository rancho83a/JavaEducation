package robotService.core.interfaces;

import robotService.models.garages.GarageImpl;
import robotService.models.garages.interfaces.Garage;
import robotService.models.procedures.Charge;
import robotService.models.procedures.Repair;
import robotService.models.procedures.Work;
import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.Cleaner;
import robotService.models.robots.Housekeeper;
import robotService.models.robots.interfaces.Robot;

import static robotService.common.ExceptionMessages.*;
import static robotService.common.OutputMessages.*;


public class ControllerImpl implements Controller {
    private Garage garage;
    private Procedure charge;
    private Procedure work;
    private Procedure repair;

    public ControllerImpl() {
        this.garage = new GarageImpl();
        this.charge = new Charge();
        this.work = new Work();
        this.repair = new Repair();
    }

    @Override
    public String manufacture(String robotType, String name, int energy, int happiness, int procedureTime) {
        Robot robot;
        if (robotType.equals("Cleaner")) {
            robot = new Cleaner(name, energy, happiness, procedureTime);

        } else if (robotType.equals("Housekeeper")) {
            robot = new Housekeeper(name, energy, happiness, procedureTime);

        } else {
            throw new IllegalArgumentException(String.format(INVALID_ROBOT_TYPE, robotType));
        }
        this.garage.manufacture(robot);
        return String.format(ROBOT_MANUFACTURED, robot.getName());
    }

    @Override
    public String repair(String robotName, int procedureTime) {

        this.repair.doService(this.getRobot(robotName), procedureTime);
        return String.format(REPAIR_PROCEDURE, robotName);
    }

    @Override
    public String work(String robotName, int procedureTime) {
        this.work.doService(this.getRobot(robotName), procedureTime);
        return String.format(WORK_PROCEDURE, robotName, procedureTime);
    }

    @Override
    public String charge(String robotName, int procedureTime) {

        this.charge.doService(this.getRobot(robotName), procedureTime);
        return String.format(CHARGE_PROCEDURE, robotName);
    }

    @Override
    public String sell(String robotName, String ownerName) {
        //TODO:may be delete this validate
        this.getRobot(robotName);
        this.garage.sell(robotName, ownerName);
        return String.format(SELL_ROBOT, ownerName, robotName);
    }

    @Override
    public String history(String procedureType) {
        if (procedureType.equals("Repair")) {
            return this.repair.history();
        } else if (procedureType.equals("Charge")) {
            return this.charge.history();
        }
        return this.work.history();

    }


    private Robot getRobot(String robotName) {
        Robot robot = this.garage.getRobots().get(robotName);
        if (robot == null) {
            throw new IllegalArgumentException(String.format(NON_EXISTING_ROBOT, robotName));
        }
        return robot;
    }
}
