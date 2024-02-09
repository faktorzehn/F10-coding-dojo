package com.schacherbauer.dojo.brainluck.internal;

import java.util.Arrays;

public class ProgramState {
    int instructionPointer;
    int inputStreamPointer;

    Memory memory;
    String code;
    String input;
    StringBuilder output;
    boolean finished;

    public ProgramState(String code, String input) {
        this.code = code;
        this.input = input;
        this.instructionPointer = 0;
        this.memory = new Memory(2024);
        this.finished = false;
        this.inputStreamPointer = 0;
        this.output = new StringBuilder();
    }

    public boolean isFinished() {
        return finished;
    }

    public String getResult() {
        return output.toString();
    }

    private Operator getNextOperator() {
        return Arrays.stream(Operator.values()).filter(op -> op.matches(this)).findFirst().orElseThrow();
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (i == instructionPointer) {
                output.append("(").append(code.charAt(i)).append(")");
            } else {
                output.append(code.charAt(i));
            }
        }
        return output.toString();
    }

    public void executeStep() {
        var nextOperator = getNextOperator();
        nextOperator.execute(this);
        if (instructionPointer >= code.length()) {
            finished = true;
        }
    }
}
