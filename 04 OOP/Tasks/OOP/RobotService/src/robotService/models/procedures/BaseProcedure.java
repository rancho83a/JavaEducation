package robotService.models.procedures;

import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.List;

import static robotService.common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public abstract class BaseProcedure implements Procedure {
    //TODO:if this fail -> try with protected getter
    protected List<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    @Override
    public String history() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append(System.lineSeparator());

        this.robots.forEach(r ->
                sb.append(r.toString())
                        .append(System.lineSeparator())
        );
        return sb.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime){
        if(robot.getProcedureTime()<procedureTime){
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);
        }
        int newProcedureTime = robot.getProcedureTime()-procedureTime;
        robot.setProcedureTime(newProcedureTime);
        this.robots.add(robot);
    }

}
