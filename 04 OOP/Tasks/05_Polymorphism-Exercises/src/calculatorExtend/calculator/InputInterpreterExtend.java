package calculatorExtend.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class InputInterpreterExtend extends InputInterpreter {

    private Deque<Integer> memory;

    public InputInterpreterExtend(CalculationEngine engine) {
        super(engine);
        this.memory = new ArrayDeque<>();

    }

    @Override
    public Operation getOperation(String operationOutside) {
        Operation operation = super.getOperation(operationOutside);

        if (operation == null) {

            if (operationOutside.equals("/")) {
                return new DivisionOperation();
            } else if (operationOutside.equals("ms")) {
                return new MemorySaveOperation(this.memory);
            } else if (operationOutside.equals("mr")) {
                return new MemoryRecallOperation(this.memory);
            }


        }


        return operation;
    }
}
