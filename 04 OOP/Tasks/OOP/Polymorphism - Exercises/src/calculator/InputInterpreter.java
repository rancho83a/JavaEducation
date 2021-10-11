package calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class InputInterpreter {
    private CalculationEngine engine;
    private Deque<Integer> memory;


    public InputInterpreter(CalculationEngine engine) {
        this.engine = engine;
        this.memory = new ArrayDeque<>();
    }

    public boolean interpret(String input) {
        try {
            engine.pushNumber(Integer.parseInt(input));
        } catch (Exception ex) {
            engine.pushOperation(this.getOperation(input));
        }
        return true;
    }

    public Operation getOperation(String operation) {
        if (operation.equals("*")) {
            return new MultiplicationOperation();
        }

        if (operation.equals("/")) {
            return new DivideOperation();
        }
        if (operation.equals("ms")) {
            return new MemorySaveOperation(this.memory);
        }
        if(operation.equals("mr")){
            return  new MemoryReadOperation(this.memory);
        }

        return null;
    }
}
