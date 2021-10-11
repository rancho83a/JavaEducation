package calculator;

import java.util.Deque;

public class MemoryReadOperation implements Operation{
   public Deque<Integer> memory;

    public MemoryReadOperation(Deque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {

    }

    @Override
    public int getResult() {
        return this.memory.pop();
    }

    @Override
    public boolean isCompleted() {
        return !this.memory.isEmpty();
    }
}
