package fr.ylombardi.adventofcode.y2020.d8;

public class Instruction {
    private Operation operation;
    private Integer value;
    private boolean executed;

    public Instruction(Operation operation, Integer value) {
        this.operation = operation;
        this.value = value;
        this.executed = false;
    }

    public Operation getOperation() {
        return operation;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public boolean isAcc() {
        return Operation.ACC.equals(operation);
    }

    public boolean isJmp() {
        return Operation.JMP.equals(operation);
    }
}
