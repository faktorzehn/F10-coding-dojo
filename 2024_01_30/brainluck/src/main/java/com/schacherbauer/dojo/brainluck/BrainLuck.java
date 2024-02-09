package com.schacherbauer.dojo.brainluck;

import com.schacherbauer.dojo.brainluck.internal.ProgramState;

public class BrainLuck {
    private final String code;

    public BrainLuck(String code) {
        this.code = code;
    }

    public String process(String input) {
        var programState = new ProgramState(code, input);
        while (!programState.isFinished()) {
            programState.executeStep();
        }
        return programState.getResult();
    }
}
